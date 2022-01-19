package br.com.treinaweb.achadosperdidos.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkResponse {

    private String rel;
    private String uri;
    private String type;

}
