package br.com.treinaweb.achadosperdidos.api.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoImagemRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.achadosperdidos.api.services.ObjetoImagemService;
import br.com.treinaweb.achadosperdidos.core.permissions.AchadosPerdidosPermissions;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/objetos/{objetoId}/imagem")
public class ObjetoImagemRestControllerImpl implements ObjetoImagemRestController {

    private final ObjetoImagemService objetoImagemService;

    @Override
    @PostMapping
    @AchadosPerdidosPermissions.isObjetoOwnedByAuthenticatedUser
    public MensagemResponse defineObjetoImagemByObjetoId(
        @PathVariable Long objetoId,
        @ModelAttribute @Valid ObjetoImagemRequest objetoImagemRequest
    ) {
        return objetoImagemService.defineObjetoImagemByObjetoId(objetoId, objetoImagemRequest);
    }

}
