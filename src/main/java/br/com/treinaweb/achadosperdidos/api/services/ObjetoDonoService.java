package br.com.treinaweb.achadosperdidos.api.services;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoDonoRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;

public interface ObjetoDonoService {

    MensagemResponse defineDonoObjeto(Long objetoId, ObjetoDonoRequest objetoDonoRequest);

}
