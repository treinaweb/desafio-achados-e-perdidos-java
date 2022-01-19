package br.com.treinaweb.achadosperdidos.core.entitylisteners;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;

import br.com.treinaweb.achadosperdidos.core.models.Objeto;

public class ObjetoEntityListener {

    @PrePersist
    public void prePersist(Objeto objeto) {
        objeto.setDataCadastro(LocalDateTime.now());
        objeto.setEntrege(false);
    }

}
