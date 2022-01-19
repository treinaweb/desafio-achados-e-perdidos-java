package br.com.treinaweb.achadosperdidos.api.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoDonoRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.achadosperdidos.api.services.ObjetoDonoService;
import br.com.treinaweb.achadosperdidos.core.permissions.AchadosPerdidosPermissions;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/objetos/{objetoId}/donos")
public class ObjetoDonoRestControllerImpl implements ObjetoDonoRestController {

    private final ObjetoDonoService objetoDonoService;

    @Override
    @PatchMapping
    @AchadosPerdidosPermissions.isObjetoOwnedByAuthenticatedUser
    public MensagemResponse defineDonoObjeto(
        @PathVariable Long objetoId,
        @RequestBody @Valid ObjetoDonoRequest objetoDonoRequest
    ) {
        return objetoDonoService.defineDonoObjeto(objetoId, objetoDonoRequest);
    }

}
