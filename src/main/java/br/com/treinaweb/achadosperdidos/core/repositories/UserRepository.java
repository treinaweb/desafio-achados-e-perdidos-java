package br.com.treinaweb.achadosperdidos.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.achadosperdidos.core.exceptions.UserNotFoundException;
import br.com.treinaweb.achadosperdidos.core.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User findByIdOrElseThrow(Long userId) {
        return this.findById(userId)
            .orElseThrow(UserNotFoundException::new);
    }

    default User findByEmailOrElseThrow(String email) {
        return this.findByEmail(email)
            .orElseThrow(UserNotFoundException::new);
    }

}
