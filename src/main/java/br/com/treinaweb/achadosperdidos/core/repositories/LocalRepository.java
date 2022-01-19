package br.com.treinaweb.achadosperdidos.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.achadosperdidos.core.exceptions.LocalNotFoundException;
import br.com.treinaweb.achadosperdidos.core.models.Local;

public interface LocalRepository extends JpaRepository<Local, Long> {

    Optional<Local> findByUserEmail(String userEmail);

    List<Local> findByNomeContainingIgnoreCase(String nome);

    default Local findByIdOrElseThrow(Long id) {
        return this.findById(id)
            .orElseThrow(LocalNotFoundException::new);
    }

    default Local findByUserEmailOrElseThrow(String userEmail) {
        return this.findByUserEmail(userEmail)
            .orElseThrow(LocalNotFoundException::new);
    }

}
