package br.com.treinaweb.achadosperdidos.api.services;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoImagemRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;

public interface ObjetoImagemService {

    MensagemResponse defineObjetoImagemByObjetoId(Long objetoId, ObjetoImagemRequest objetoImagemRequest);

}
