package br.com.treinaweb.achadosperdidos.api.dtos.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class ObjetoDonoRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String donoNome;

    @CPF
    @NotNull
    @NotBlank
    @Size(min = 11, max = 11)
    private String donoCpf;

}
