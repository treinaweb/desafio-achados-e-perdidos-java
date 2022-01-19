package br.com.treinaweb.achadosperdidos.api.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.LoginRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.requests.RefreshRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LoginResponse;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.achadosperdidos.core.services.token.adapters.TokenService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        var email = loginRequest.getEmail();
        var password = loginRequest.getPassword();
        var authentication = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authentication);
        return LoginResponse.builder()
            .access(tokenService.generateAccessToken(email))
            .refresh(tokenService.genarateRefreshToken(email))
            .build();
    }

    @Override
    public LoginResponse refresh(RefreshRequest refreshRequest) {
        var refreshToken = refreshRequest.getRefresh();
        var email = tokenService.getSubjectFromRefreshToken(refreshToken);
        userDetailsService.loadUserByUsername(email);
        return LoginResponse.builder()
            .access(tokenService.generateAccessToken(email))
            .refresh(tokenService.genarateRefreshToken(email))
            .build();
    }

    @Override
    public MensagemResponse logout() {
        return new MensagemResponse("Logout realizado com sucesso!");
    }

}
