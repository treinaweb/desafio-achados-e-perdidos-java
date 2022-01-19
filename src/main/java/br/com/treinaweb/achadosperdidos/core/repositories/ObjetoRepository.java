package br.com.treinaweb.achadosperdidos.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.achadosperdidos.core.exceptions.ObjetoNotFoundException;
import br.com.treinaweb.achadosperdidos.core.models.Local;
import br.com.treinaweb.achadosperdidos.core.models.Objeto;

public interface ObjetoRepository extends JpaRepository<Objeto, Long> {

    List<Objeto> findByLocal(Local local);

    default Objeto findByIdOrElseThrow(Long objetoId) {
        return this.findById(objetoId)
            .orElseThrow(ObjetoNotFoundException::new);
    }

}
