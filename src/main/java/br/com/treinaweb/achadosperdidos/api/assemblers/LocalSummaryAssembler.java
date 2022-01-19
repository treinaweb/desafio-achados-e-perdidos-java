package br.com.treinaweb.achadosperdidos.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import br.com.treinaweb.achadosperdidos.api.controllers.LocalObjetoRestControllerImpl;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalSummaryResponse;

@Component
public class LocalSummaryAssembler implements Assembler<LocalSummaryResponse> {

    @Override
    public LocalSummaryResponse addLinks(LocalSummaryResponse resource) {
        var localId = resource.getId();
        resource.addLink(linkTo(methodOn(LocalObjetoRestControllerImpl.class).findObjetosByLocal(localId))
            .withRel("objetos_local")
            .withType(HttpMethod.GET.name()));

        return resource;
    }

}
