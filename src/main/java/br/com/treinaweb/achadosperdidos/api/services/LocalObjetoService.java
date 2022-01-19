package br.com.treinaweb.achadosperdidos.api.services;

import java.util.List;

import br.com.treinaweb.achadosperdidos.api.dtos.responses.ObjetoResponse;

public interface LocalObjetoService {

    List<ObjetoResponse> findObjetosByLocal(Long localId);

}
