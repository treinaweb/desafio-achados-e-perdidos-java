package br.com.treinaweb.achadosperdidos.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import br.com.treinaweb.achadosperdidos.api.controllers.LocalImagemRestControllerImpl;
import br.com.treinaweb.achadosperdidos.api.controllers.LocalRestControllerImpl;
import br.com.treinaweb.achadosperdidos.api.controllers.ObjetoRestControllerImpl;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalResponse;

@Component
public class LocalAssembler implements Assembler<LocalResponse> {

    @Override
    public LocalResponse addLinks(LocalResponse resource) {
        resource.addLink(linkTo(methodOn(LocalRestControllerImpl.class).getByAuthenticatedUser())
            .withSelfRel()
            .withType(HttpMethod.GET.name()));
        resource.addLink(linkTo(methodOn(LocalRestControllerImpl.class).updateByAuthenticatedUser(null))
            .withRel("atualizar_local")
            .withType(HttpMethod.PUT.name()));
        resource.addLink(linkTo(methodOn(LocalRestControllerImpl.class).deleteByAuthenticatedUser())
            .withRel("apagar_local")
            .withType(HttpMethod.DELETE.name()));
        resource.addLink(linkTo(methodOn(LocalImagemRestControllerImpl.class).defineLocalImagem(null))
            .withRel("definir_imagem_local")
            .withType(HttpMethod.POST.name()));
        resource.addLink(linkTo(methodOn(ObjetoRestControllerImpl.class).findAllByAuthenticatedUser())
            .withRel("listar_objetos_local")
            .withType(HttpMethod.GET.name()));
        resource.addLink(linkTo(methodOn(ObjetoRestControllerImpl.class).create(null))
            .withRel("adicionar_objeto_local")
            .withType(HttpMethod.POST.name()));

        return resource;
    }

}
