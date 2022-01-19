package br.com.treinaweb.achadosperdidos.api.services;

import java.util.List;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.ObjetoResponse;

public interface ObjetoService {

    ObjetoResponse create(ObjetoRequest objetoRequest);

    List<ObjetoResponse> findAllByAuthenticatedUser();

    void deleteById(Long objetoId);

    ObjetoResponse updateById(Long objetoId, ObjetoRequest objetoRequest);

    ObjetoResponse findById(Long objetoId);

}
