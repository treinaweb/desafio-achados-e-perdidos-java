package br.com.treinaweb.achadosperdidos.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalPostRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalPutRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalResponse;
import br.com.treinaweb.achadosperdidos.api.mappers.LocalMapper;
import br.com.treinaweb.achadosperdidos.core.exceptions.LocalNotFoundException;
import br.com.treinaweb.achadosperdidos.core.repositories.LocalRepository;
import br.com.treinaweb.achadosperdidos.core.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalServiceImpl implements LocalService {

    private final LocalRepository localRepository;
    private final LocalMapper localMapper;
    private final UserService userService;
    private final SecurityUtils securityUtils;

    @Override
    public LocalResponse create(LocalPostRequest localPostRequest) {
        var user = userService.create(localPostRequest.getUsuario());
        var local = localMapper.toModel(localPostRequest);
        local.setUser(user);
        var createdLocal = localRepository.save(local);
        return localMapper.toResponse(createdLocal);
    }

    @Override
    public LocalResponse getByAuthenticatedUser() {
        var authenticatedUserEmail = securityUtils.getAuthenticatedUserEmail();
        var local = localRepository.findByUserEmail(authenticatedUserEmail)
            .orElseThrow(LocalNotFoundException::new);
        return localMapper.toResponse(local);
    }

    @Override
    public LocalResponse updateByAuthenticatedUser(LocalPutRequest localPutRequest) {
        var local = securityUtils.getLocalFromAuthenticatedUser();
        var updatedUser = userService.updateById(local.getUser().getId(), localPutRequest.getUsuario());
        local.setUser(updatedUser);
        BeanUtils.copyProperties(localPutRequest, local, "user", "usuario");
        var updatedLocal = localRepository.save(local);
        return localMapper.toResponse(updatedLocal);
    }

    @Override
    public void deleteByAuthenticatedUser() {
        var local = securityUtils.getLocalFromAuthenticatedUser();
        localRepository.delete(local);
    }

}
