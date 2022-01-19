package br.com.treinaweb.achadosperdidos.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class LocalNotFoundException extends EntityNotFoundException {

    public LocalNotFoundException() {
        super("Local não encontrado");
    }

    public LocalNotFoundException(String message) {
        super(message);
    }

}
