package br.com.treinaweb.achadosperdidos.core.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.treinaweb.achadosperdidos.core.dtos.AuthenticatedUser;
import br.com.treinaweb.achadosperdidos.core.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
            .map(user -> new AuthenticatedUser(user))
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

}
