package br.com.treinaweb.achadosperdidos.api.controllers;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoImagemRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;

public interface ObjetoImagemRestController {

    public MensagemResponse defineObjetoImagemByObjetoId(Long objetoId, ObjetoImagemRequest objetoImagemRequest);

}
