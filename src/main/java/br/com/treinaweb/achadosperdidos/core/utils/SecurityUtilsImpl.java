package br.com.treinaweb.achadosperdidos.core.utils;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.treinaweb.achadosperdidos.core.models.Local;
import br.com.treinaweb.achadosperdidos.core.models.User;
import br.com.treinaweb.achadosperdidos.core.repositories.LocalRepository;
import br.com.treinaweb.achadosperdidos.core.repositories.ObjetoRepository;
import br.com.treinaweb.achadosperdidos.core.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityUtilsImpl implements SecurityUtils {

    private final UserRepository userRepository;
    private final LocalRepository localRepository;
    private final ObjetoRepository objetoRepository;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getAuthenticatedUser() {
        return userRepository.findByEmailOrElseThrow(getAuthenticatedUserEmail());
    }

    @Override
    public String getAuthenticatedUserEmail() {
        return getAuthentication().getName();
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public Local getLocalFromAuthenticatedUser() {
        return localRepository.findByUserEmailOrElseThrow(getAuthenticatedUserEmail());
    }

    @Override
    public boolean isObjetoOwnedByAuthenticatedUser(Long objetoId) {
        var objeto = objetoRepository.findByIdOrElseThrow(objetoId);
        if (objeto.getLocal().equals(getLocalFromAuthenticatedUser())) {
            return true;
        }
        var message = String.format("Usuário logado não é dono do objeto de id %d", objetoId);
        throw new AccessDeniedException(message);
    }

    @Override
    public boolean isAuthenticated() {
        var authentication = getAuthentication();
        return authentication != null
            && !(authentication instanceof AnonymousAuthenticationToken)
            && authentication.isAuthenticated();
    }

}
