package br.com.treinaweb.achadosperdidos.api.dtos.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(SnakeCaseStrategy.class)
public class LocalBaseRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String nome;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String endereco;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String contato;

    @Size(max = 255)
    private String descricao;

}
