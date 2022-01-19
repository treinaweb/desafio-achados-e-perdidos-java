package br.com.treinaweb.achadosperdidos.core.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.achadosperdidos.core.repositories.ImagemRepository;
import br.com.treinaweb.achadosperdidos.core.services.storage.providers.LocalStorageService;
import lombok.RequiredArgsConstructor;

@RestController
@Profile("dev")
@RequiredArgsConstructor
@RequestMapping("/uploads")
public class LocalStorageRestController {

    private final ImagemRepository imagemRepository;
    private final LocalStorageService localStorageService;

    @GetMapping
    public ResponseEntity<Object> findByFilename(@RequestParam String filename) {
        var imagem = imagemRepository.findByFilenameOrElseThrow(filename);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, imagem.getContentType())
            .header(HttpHeaders.CONTENT_LENGTH, imagem.getContentLength().toString())
            .body(localStorageService.getAllBytesFromFileByFilename(filename));
    }

}
