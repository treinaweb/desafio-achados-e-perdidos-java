package br.com.treinaweb.achadosperdidos.api.controllers;

import java.util.List;

import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalSummaryResponse;

public interface LocalBuscaRestController {

    List<LocalSummaryResponse> findLocalByNome(String nome);

}
