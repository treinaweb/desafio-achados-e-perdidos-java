package br.com.treinaweb.achadosperdidos.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import br.com.treinaweb.achadosperdidos.api.controllers.ObjetoDonoRestControllerImpl;
import br.com.treinaweb.achadosperdidos.api.controllers.ObjetoImagemRestControllerImpl;
import br.com.treinaweb.achadosperdidos.api.controllers.ObjetoRestControllerImpl;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.ObjetoResponse;
import br.com.treinaweb.achadosperdidos.core.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ObjetoAssembler implements Assembler<ObjetoResponse> {

    private final SecurityUtils securityUtils;

    @Override
    public ObjetoResponse addLinks(ObjetoResponse resource) {
        var objetoId = resource.getId();
        if (securityUtils.isAuthenticated()) {
            resource.addLink(linkTo(methodOn(ObjetoRestControllerImpl.class).findById(objetoId))
                .withSelfRel()
                .withType(HttpMethod.GET.name()));
            resource.addLink(linkTo(methodOn(ObjetoRestControllerImpl.class).updateById(objetoId, null))
                .withRel("atualizar_objeto")
                .withType(HttpMethod.PUT.name()));
            resource.addLink(linkTo(methodOn(ObjetoRestControllerImpl.class).deleteById(objetoId))
                .withRel("apagar_objeto")
                .withType(HttpMethod.DELETE.name()));
            resource.addLink(linkTo(methodOn(ObjetoImagemRestControllerImpl.class).defineObjetoImagemByObjetoId(objetoId, null))
                .withRel("definir_imagem_objeto")
                .withType(HttpMethod.POST.name()));
            resource.addLink(linkTo(methodOn(ObjetoDonoRestControllerImpl.class).defineDonoObjeto(objetoId, null))
                .withRel("definir_dono_objeto")
                .withType(HttpMethod.PATCH.name()));
        }

        return resource;
    }

}
