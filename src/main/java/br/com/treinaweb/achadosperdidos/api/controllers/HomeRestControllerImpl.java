package br.com.treinaweb.achadosperdidos.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.achadosperdidos.api.assemblers.Assembler;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.HomeResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeRestControllerImpl implements HomeRestController {

    private final Assembler<HomeResponse> homeAssembler;

    @Override
    @GetMapping
    public HomeResponse home() {
        return homeAssembler.addLinks(new HomeResponse());
    }

}
