package br.com.treinaweb.achadosperdidos.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalPostRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalResponse;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalSummaryResponse;
import br.com.treinaweb.achadosperdidos.core.models.Local;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface LocalMapper {

    LocalMapper INSTANCE = Mappers.getMapper(LocalMapper.class);

    @Mapping(target = "user", source = "usuario")
    Local toModel(LocalPostRequest localPostRequest);

    @Mapping(target = "usuario", source = "user")
    @Mapping(target = "imagem", source = "imagem.url")
    LocalResponse toResponse(Local local);

    @Mapping(target = "imagem", source = "imagem.url")
    LocalSummaryResponse toSummaryResponse(Local local);

}
