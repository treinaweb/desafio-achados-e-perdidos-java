package br.com.treinaweb.achadosperdidos.api.dtos.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String password;

}
