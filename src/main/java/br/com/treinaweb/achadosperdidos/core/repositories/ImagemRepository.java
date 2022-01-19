package br.com.treinaweb.achadosperdidos.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.achadosperdidos.core.exceptions.ImagemNotFoundException;
import br.com.treinaweb.achadosperdidos.core.models.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

    Optional<Imagem> findByFilename(String filename);

    default Imagem findByIdOrElseThrow(Long id) {
        return this.findById(id)
            .orElseThrow(ImagemNotFoundException::new);
    }

    default Imagem findByFilenameOrElseThrow(String filename) {
        return this.findByFilename(filename)
            .orElseThrow(ImagemNotFoundException::new);
    }

}
