package br.com.treinaweb.achadosperdidos.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class ImagemNotFoundException extends EntityNotFoundException {

    public ImagemNotFoundException() {
        super("Imagem não encontrada");
    }

    public ImagemNotFoundException(String message) {
        super(message);
    }

}
