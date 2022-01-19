package br.com.treinaweb.achadosperdidos.api.dtos.requests;

import javax.validation.constraints.AssertTrue;

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
public class UserPutRequest extends UserBaseRequest {

    private String password;

    private String passwordConfirmation;

    @AssertTrue(message = "Campos `password` e `password_confirmation` devem ser iguais")
    private boolean isPasswordConfirmation() {
        if (password != null && passwordConfirmation != null) {
            return password.equals(passwordConfirmation);
        }
        return true;
    }

}
