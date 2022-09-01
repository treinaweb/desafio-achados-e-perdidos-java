package br.com.treinaweb.achadosperdidos.api.dtos.responses;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
@EqualsAndHashCode(callSuper = false)
public class ObjetoResponse extends HateoasResponse {

    private Long id;
    private String nome;
    private String descricao;
    private boolean entrege;
    private LocalDateTime dataCadastro;
    private String imagem;
    private String contato;

}
