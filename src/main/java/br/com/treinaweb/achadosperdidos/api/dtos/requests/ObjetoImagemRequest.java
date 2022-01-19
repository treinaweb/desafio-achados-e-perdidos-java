package br.com.treinaweb.achadosperdidos.api.dtos.requests;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjetoImagemRequest {

    private MultipartFile imagemObjeto;

    public void setImagem_objeto(MultipartFile imagemObjeto) {
        this.setImagemObjeto(imagemObjeto);
    }

}
