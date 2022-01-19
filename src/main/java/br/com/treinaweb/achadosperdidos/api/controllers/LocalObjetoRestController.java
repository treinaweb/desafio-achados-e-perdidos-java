package br.com.treinaweb.achadosperdidos.api.controllers;

import java.util.List;

import br.com.treinaweb.achadosperdidos.api.dtos.responses.ObjetoResponse;

public interface LocalObjetoRestController {

    List<ObjetoResponse> findObjetosByLocal(Long localId);

}
