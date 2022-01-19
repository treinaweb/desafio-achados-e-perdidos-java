package br.com.treinaweb.achadosperdidos.core.services.storage.providers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.treinaweb.achadosperdidos.core.controllers.LocalStorageRestController;
import br.com.treinaweb.achadosperdidos.core.models.Imagem;
import br.com.treinaweb.achadosperdidos.core.repositories.ImagemRepository;
import br.com.treinaweb.achadosperdidos.core.services.storage.adapters.StorageService;
import br.com.treinaweb.achadosperdidos.core.services.storage.exceptions.StorageServiceException;
import lombok.RequiredArgsConstructor;

@Service
@Profile("dev")
@RequiredArgsConstructor
public class LocalStorageService implements StorageService {

    private static final Path UPLOAD_DIR = Paths.get("uploads");

    private final ImagemRepository imagemRepository;

    @Override
    public Imagem save(MultipartFile file) {
        try {
            return trySave(file);
        } catch (IOException exception) {
            throw new StorageServiceException(exception.getLocalizedMessage());
        }
    }

    public byte[] getAllBytesFromFileByFilename(String filename) {
        try {
            return findByFilename(filename).getInputStream().readAllBytes();
        } catch (IOException exception) {
            throw new StorageServiceException(exception.getLocalizedMessage());
        }
    }

    private Resource findByFilename(String filename) {
        var file = UPLOAD_DIR.resolve(filename);

        try {
            return new UrlResource(file.toUri());
        } catch (MalformedURLException exception) {
            throw new StorageServiceException(exception.getLocalizedMessage());
        }
    }

    private Imagem trySave(MultipartFile file) throws IOException {
        if (!Files.exists(UPLOAD_DIR)) {
            Files.createDirectories(UPLOAD_DIR);
        }

        var imagem = generateImagem(file);
        Files.copy(file.getInputStream(), UPLOAD_DIR.resolve(imagem.getFilename()));
        return imagemRepository.save(imagem);
    }

    private Imagem generateImagem(MultipartFile file) throws IOException {
        var filename = genareteFilename(file);
        return Imagem.builder()
            .filename(filename)
            .contentLength(file.getSize())
            .contentType(file.getContentType())
            .url(getFileUrlByFilename(filename))
            .build();
    }

    private String getFileUrlByFilename(String filename) throws IOException {
        return linkTo(methodOn(LocalStorageRestController.class).findByFilename(filename)).toString();
    }

    private String genareteFilename(MultipartFile file) {
        var originalFilename = file.getOriginalFilename();
        var ext = originalFilename.split("\\.")[1];
        return UUID.randomUUID().toString() + "." + ext;
    }

}
