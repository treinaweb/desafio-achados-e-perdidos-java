package br.com.treinaweb.achadosperdidos.api.dtos.responses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private Integer status;
    private LocalDateTime timestamp;
    private String mensagem;
    private String path;
    private Map<String, List<String>> errors;


}
