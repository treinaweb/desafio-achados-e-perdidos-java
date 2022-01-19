package br.com.treinaweb.achadosperdidos.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.UserPostRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.UserResponse;
import br.com.treinaweb.achadosperdidos.core.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "senha", source = "password")
    @Mapping(target = "local", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toModel(UserPostRequest userPostRequest);

    UserResponse toResponse(User user);

}
