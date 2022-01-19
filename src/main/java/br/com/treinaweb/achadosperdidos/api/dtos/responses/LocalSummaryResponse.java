package br.com.treinaweb.achadosperdidos.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LocalSummaryResponse extends HateoasResponse {

    private Long id;
    private String nome;
    private String endereco;
    private String contato;
    private String descricao;
    private String imagem;

}
