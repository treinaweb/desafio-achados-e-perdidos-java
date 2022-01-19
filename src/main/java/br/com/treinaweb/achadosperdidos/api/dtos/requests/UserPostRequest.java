package br.com.treinaweb.achadosperdidos.api.dtos.requests;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonNaming(SnakeCaseStrategy.class)
public class UserPostRequest extends UserBaseRequest {

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String passwordConfirmation;

    @AssertTrue(message = "Campos `password` e `password_confirmation` devem ser iguais")
    private boolean isPasswordConfirmation() {
        return password != null
            && passwordConfirmation != null
            && password.equals(passwordConfirmation);
    }

}
