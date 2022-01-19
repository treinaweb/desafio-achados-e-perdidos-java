package br.com.treinaweb.achadosperdidos.core.services.storage.exceptions;

public class StorageServiceException extends RuntimeException {

    public StorageServiceException() {}

    public StorageServiceException(String message) {
        super(message);
    }

}
