package br.com.treinaweb.achadosperdidos.api.services;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalImagemRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;

public interface LocalImagemService {

    MensagemResponse defineLocalImagem(LocalImagemRequest localImagemRequest);

}
