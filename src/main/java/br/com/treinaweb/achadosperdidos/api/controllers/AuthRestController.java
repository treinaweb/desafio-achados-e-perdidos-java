package br.com.treinaweb.achadosperdidos.api.controllers;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.LoginRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.requests.RefreshRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LoginResponse;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;

public interface AuthRestController {

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refresh(RefreshRequest refreshRequest);

    MensagemResponse logout();

}
