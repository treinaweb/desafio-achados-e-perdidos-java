package br.com.treinaweb.achadosperdidos.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.achadosperdidos.api.assemblers.Assembler;
import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.ObjetoResponse;
import br.com.treinaweb.achadosperdidos.api.services.ObjetoService;
import br.com.treinaweb.achadosperdidos.core.permissions.AchadosPerdidosPermissions;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/objetos")
@RequiredArgsConstructor
public class ObjetoRestControllerImpl implements ObjetoRestController {

    private final ObjetoService objetoService;
    private final Assembler<ObjetoResponse> objetoAssembler;

    @Override
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @AchadosPerdidosPermissions.isAuthenticated
    public ObjetoResponse create(@RequestBody @Valid ObjetoRequest objetoRequest) {
        return objetoAssembler.addLinks(objetoService.create(objetoRequest));
    }

    @Override
    @GetMapping
    @AchadosPerdidosPermissions.isAuthenticated
    public List<ObjetoResponse> findAllByAuthenticatedUser() {
        return objetoAssembler.addLinks(objetoService.findAllByAuthenticatedUser());
    }

    @Override
    @DeleteMapping("/{objetoId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @AchadosPerdidosPermissions.isObjetoOwnedByAuthenticatedUser
    public ResponseEntity<Void> deleteById(@PathVariable Long objetoId) {
        objetoService.deleteById(objetoId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{objetoId}")
    @AchadosPerdidosPermissions.isObjetoOwnedByAuthenticatedUser
    public ObjetoResponse updateById(
        @PathVariable Long objetoId, @RequestBody @Valid ObjetoRequest objetoRequest
    ) {
        return objetoAssembler.addLinks(objetoService.updateById(objetoId, objetoRequest));
    }

    @Override
    @GetMapping("/{objetoId}")
    @AchadosPerdidosPermissions.isObjetoOwnedByAuthenticatedUser
    public ObjetoResponse findById(@PathVariable Long objetoId) {
        return objetoAssembler.addLinks(objetoService.findById(objetoId));
    }

}
