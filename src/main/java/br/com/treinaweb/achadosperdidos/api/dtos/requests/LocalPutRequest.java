package br.com.treinaweb.achadosperdidos.api.dtos.requests;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
@EqualsAndHashCode(callSuper = false)
public class LocalPutRequest extends LocalBaseRequest {

    @Valid
    private UserPutRequest usuario;

}
