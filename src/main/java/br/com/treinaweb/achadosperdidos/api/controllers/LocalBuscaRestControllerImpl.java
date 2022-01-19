package br.com.treinaweb.achadosperdidos.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.achadosperdidos.api.assemblers.Assembler;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalSummaryResponse;
import br.com.treinaweb.achadosperdidos.api.services.LocalBuscaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locais/busca")
public class LocalBuscaRestControllerImpl implements LocalBuscaRestController {

    private final LocalBuscaService localBuscaService;
    private final Assembler<LocalSummaryResponse> localSummaryAssembler;

    @Override
    @GetMapping
    public List<LocalSummaryResponse> findLocalByNome(
        @RequestParam(required = false, defaultValue = "") String nome
    ) {
        return localSummaryAssembler.addLinks(localBuscaService.findLocalByNome(nome));
    }

}
