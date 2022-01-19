package br.com.treinaweb.achadosperdidos.api.controllers;

import org.springframework.http.ResponseEntity;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalPostRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalPutRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalResponse;

public interface LocalRestController {

    LocalResponse create(LocalPostRequest localPostRequest);

    LocalResponse getByAuthenticatedUser();

    LocalResponse updateByAuthenticatedUser(LocalPutRequest localPutRequest);

    ResponseEntity<Void> deleteByAuthenticatedUser();

}
