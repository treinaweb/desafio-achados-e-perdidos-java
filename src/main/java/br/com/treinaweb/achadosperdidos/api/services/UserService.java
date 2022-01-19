package br.com.treinaweb.achadosperdidos.api.services;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.UserPostRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.requests.UserPutRequest;
import br.com.treinaweb.achadosperdidos.core.models.User;

public interface UserService {

    User create(UserPostRequest userPostRequest);

    User updateById(Long userId, UserPutRequest userPutRequest);

}
