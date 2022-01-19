package br.com.treinaweb.achadosperdidos.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import br.com.treinaweb.achadosperdidos.api.controllers.LocalBuscaRestControllerImpl;
import br.com.treinaweb.achadosperdidos.api.controllers.LocalRestControllerImpl;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.HomeResponse;

@Component
public class HomeAssembler implements Assembler<HomeResponse> {

    @Override
    public HomeResponse addLinks(HomeResponse resource) {
        resource.addLink(linkTo(methodOn(LocalRestControllerImpl.class).create(null))
            .withRel("criar_local")
            .withType(HttpMethod.POST.name()));
        resource.addLink(linkTo(methodOn(LocalBuscaRestControllerImpl.class).findLocalByNome(null))
            .withRel("buscar_locais")
            .expand()
            .withType(HttpMethod.GET.name()));

        return resource;
    }

}
