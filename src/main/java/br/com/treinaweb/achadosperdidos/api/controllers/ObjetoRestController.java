package br.com.treinaweb.achadosperdidos.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.ObjetoResponse;

public interface ObjetoRestController {

    ObjetoResponse create(ObjetoRequest objetoRequest);

    List<ObjetoResponse> findAllByAuthenticatedUser();

    ResponseEntity<Void> deleteById(Long objetoId);

    ObjetoResponse updateById(Long objetoId, ObjetoRequest objetoRequest);

    ObjetoResponse findById(Long objetoId);

}
