package br.com.treinaweb.achadosperdidos.core.utils;

import org.springframework.security.core.Authentication;

import br.com.treinaweb.achadosperdidos.core.models.Local;
import br.com.treinaweb.achadosperdidos.core.models.User;

public interface SecurityUtils {

    Authentication getAuthentication();

    void setAuthentication(Authentication authentication);

    String getAuthenticatedUserEmail();

    User getAuthenticatedUser();

    Local getLocalFromAuthenticatedUser();

    boolean isObjetoOwnedByAuthenticatedUser(Long objetoId);

    boolean isAuthenticated();

}
