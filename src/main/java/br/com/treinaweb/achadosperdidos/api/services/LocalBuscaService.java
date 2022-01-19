package br.com.treinaweb.achadosperdidos.api.services;

import java.util.List;

import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalSummaryResponse;

public interface LocalBuscaService {

    List<LocalSummaryResponse> findLocalByNome(String nome);

}
