package br.com.treinaweb.achadosperdidos.api.dtos.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.hateoas.Link;

import lombok.Getter;

@Getter
public class HateoasResponse {

    private List<LinkResponse> links = new ArrayList<>();

    public void addLinks(List<Link> links) {
        links.forEach(link -> this.links.add(linkToLinkResponse(link)));
    }

    public void addLink(Link ...links) {
        Stream.of(links).forEach(link -> this.links.add(linkToLinkResponse(link)));
    }

    public void addLink(Link link) {
        this.links.add(linkToLinkResponse(link));
    }

    private LinkResponse linkToLinkResponse(Link link) {
        return LinkResponse.builder()
            .type(link.getType())
            .rel(link.getRel().toString())
            .uri(link.getHref())
            .build();
    }

}
