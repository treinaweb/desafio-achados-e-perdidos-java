package br.com.treinaweb.achadosperdidos.api.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalImagemRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.achadosperdidos.api.services.LocalImagemService;
import br.com.treinaweb.achadosperdidos.core.permissions.AchadosPerdidosPermissions;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locais/imagem")
public class LocalImagemRestControllerImpl implements LocalImagemRestController{

    private final LocalImagemService localImagemService;

    @Override
    @PostMapping
    @AchadosPerdidosPermissions.isAuthenticated
    public MensagemResponse defineLocalImagem(@ModelAttribute @Valid LocalImagemRequest localImagemRequest) {
        return localImagemService.defineLocalImagem(localImagemRequest);
    }

}
