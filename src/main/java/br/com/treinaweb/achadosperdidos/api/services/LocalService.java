package br.com.treinaweb.achadosperdidos.api.services;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalPostRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalPutRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalResponse;

public interface LocalService {

    LocalResponse create(LocalPostRequest localPostRequest);

    LocalResponse getByAuthenticatedUser();

    LocalResponse updateByAuthenticatedUser(LocalPutRequest localPutRequest);

    void deleteByAuthenticatedUser();

}
