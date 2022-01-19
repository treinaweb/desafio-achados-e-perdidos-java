package br.com.treinaweb.achadosperdidos.core.services.storage.adapters;

import org.springframework.web.multipart.MultipartFile;

import br.com.treinaweb.achadosperdidos.core.models.Imagem;

public interface StorageService {

    Imagem save(MultipartFile file);

}
