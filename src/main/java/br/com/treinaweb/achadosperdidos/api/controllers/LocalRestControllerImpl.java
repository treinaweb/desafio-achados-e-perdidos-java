package br.com.treinaweb.achadosperdidos.api.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.achadosperdidos.api.assemblers.Assembler;
import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalPostRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalPutRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalResponse;
import br.com.treinaweb.achadosperdidos.api.services.LocalService;
import br.com.treinaweb.achadosperdidos.core.permissions.AchadosPerdidosPermissions;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/locais")
@RequiredArgsConstructor
public class LocalRestControllerImpl implements LocalRestController{

    private final LocalService localService;
    private final Assembler<LocalResponse> localAssembler;

    @Override
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public LocalResponse create(@RequestBody @Valid LocalPostRequest localPostRequest) {
        return localAssembler.addLinks(localService.create(localPostRequest));
    }

    @Override
    @GetMapping
    @AchadosPerdidosPermissions.isAuthenticated
    public LocalResponse getByAuthenticatedUser() {
        return localAssembler.addLinks(localService.getByAuthenticatedUser());
    }

    @Override
    @PutMapping
    @AchadosPerdidosPermissions.isAuthenticated
    public LocalResponse updateByAuthenticatedUser(@RequestBody @Valid LocalPutRequest localPutRequest) {
        return localAssembler.addLinks(localService.updateByAuthenticatedUser(localPutRequest));
    }

    @Override
    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @AchadosPerdidosPermissions.isAuthenticated
    public ResponseEntity<Void> deleteByAuthenticatedUser() {
        localService.deleteByAuthenticatedUser();
        return ResponseEntity.noContent().build();
    }

}
