package br.com.treinaweb.achadosperdidos.api.controllers;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoDonoRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;

public interface ObjetoDonoRestController {

    MensagemResponse defineDonoObjeto(Long objetoId, ObjetoDonoRequest objetoDonoRequest);

}
