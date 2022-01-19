package br.com.treinaweb.achadosperdidos.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.achadosperdidos.api.assemblers.Assembler;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.ObjetoResponse;
import br.com.treinaweb.achadosperdidos.api.services.LocalObjetoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locais/{localId}/objetos")
public class LocalObjetoRestControllerImpl implements LocalObjetoRestController {

    private final LocalObjetoService localObjetoService;
    private final Assembler<ObjetoResponse> objetoAssembler;

    @Override
    @GetMapping
    public List<ObjetoResponse> findObjetosByLocal(@PathVariable Long localId) {
        return objetoAssembler.addLinks(localObjetoService.findObjetosByLocal(localId));
    }

}
