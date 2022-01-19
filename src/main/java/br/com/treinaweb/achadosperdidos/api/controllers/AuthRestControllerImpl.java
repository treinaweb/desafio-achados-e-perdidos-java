package br.com.treinaweb.achadosperdidos.api.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.LoginRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.requests.RefreshRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LoginResponse;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.achadosperdidos.api.services.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestControllerImpl implements AuthRestController {

    private final AuthService authService;

    @Override
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Override
    @PostMapping("/refresh")
    public LoginResponse refresh(@RequestBody @Valid RefreshRequest refreshRequest) {
        return authService.refresh(refreshRequest);
    }

    @Override
    public MensagemResponse logout() {
        return authService.logout();
    }

}
