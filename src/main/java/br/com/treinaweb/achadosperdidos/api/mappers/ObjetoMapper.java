package br.com.treinaweb.achadosperdidos.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.ObjetoResponse;
import br.com.treinaweb.achadosperdidos.core.models.Objeto;

@Mapper(componentModel = "spring")
public interface ObjetoMapper {

    ObjetoMapper INSTANCE = Mappers.getMapper(ObjetoMapper.class);

    Objeto toModel(ObjetoRequest objetoRequest);

    @Mapping(target = "imagem", source = "imagem.url")
    ObjetoResponse toResponse(Objeto objeto);

}
