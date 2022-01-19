package br.com.treinaweb.achadosperdidos.core.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.treinaweb.achadosperdidos.api.dtos.responses.ErrorResponse;
import br.com.treinaweb.achadosperdidos.core.services.token.adapters.TokenService;
import br.com.treinaweb.achadosperdidos.core.services.token.exceptions.TokenServiceException;
import br.com.treinaweb.achadosperdidos.core.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccessTokenRequestFilter extends OncePerRequestFilter {

    private static final String TOKEN_TYPE = "Bearer ";

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    private final SecurityUtils securityUtils;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            tryDoFilterInternal(request, response, filterChain);
        } catch (TokenServiceException exception) {
            var status = HttpStatus.UNAUTHORIZED;
            var errorResponse = ErrorResponse.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .mensagem(exception.getLocalizedMessage())
                .path(request.getRequestURI())
                .build();

            response.setStatus(status.value());
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
    }

    private void tryDoFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws IOException, ServletException {
        var email = "";
        var authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isTokenPresent(authorizationHeader)) {
            var accessToken = authorizationHeader.substring(TOKEN_TYPE.length());
            email = tokenService.getSubjectFromAccessToken(accessToken);
        }
        if (isEmailNotInContext(email)) {
            addEmailInContext(email, request);
        }
        filterChain.doFilter(request, response);
    }

    private void addEmailInContext(String email, HttpServletRequest request) {
        var user = userDetailsService.loadUserByUsername(email);
        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        securityUtils.setAuthentication(authentication);
    }

    private boolean isEmailNotInContext(String email) {
        return !email.isEmpty() && securityUtils.getAuthentication() == null;
    }

    private boolean isTokenPresent(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith(TOKEN_TYPE);
    }

}
