package br.com.treinaweb.achadosperdidos.api.assemblers;

import java.util.Collection;
import java.util.List;

import br.com.treinaweb.achadosperdidos.api.dtos.responses.HateoasResponse;

public interface Assembler<R extends HateoasResponse> {

    R addLinks(R resource);

    default List<R> addLinks(Collection<R> collectionResource) {
        collectionResource.forEach(this::addLinks);
        return collectionResource.stream().toList();
    }

}
