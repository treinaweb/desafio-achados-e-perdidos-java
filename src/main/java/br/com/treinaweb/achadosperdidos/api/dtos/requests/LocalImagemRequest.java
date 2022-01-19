package br.com.treinaweb.achadosperdidos.api.dtos.requests;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalImagemRequest {

    private MultipartFile imagemLocal;

    public void setImagem_local(MultipartFile imagemLocal) {
        this.setImagemLocal(imagemLocal);
    }

}
