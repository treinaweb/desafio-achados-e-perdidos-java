package br.com.treinaweb.achadosperdidos.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class ObjetoNotFoundException extends EntityNotFoundException {

    public ObjetoNotFoundException() {
        super("Objeto não encontrado");
    }

    public ObjetoNotFoundException(String message) {
        super(message);
    }

}
