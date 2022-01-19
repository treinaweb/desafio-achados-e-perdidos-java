package br.com.treinaweb.achadosperdidos.api.controllers;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalImagemRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;

public interface LocalImagemRestController {

    MensagemResponse defineLocalImagem(LocalImagemRequest localImagemRequest);

}
