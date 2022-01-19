package br.com.treinaweb.achadosperdidos.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.treinaweb.achadosperdidos.api.dtos.responses.ObjetoResponse;
import br.com.treinaweb.achadosperdidos.api.mappers.ObjetoMapper;
import br.com.treinaweb.achadosperdidos.core.repositories.LocalRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalObjetoServiceImpl implements LocalObjetoService {

    private final LocalRepository localRepository;
    private final ObjetoMapper objetoMapper;

    @Override
    public List<ObjetoResponse> findObjetosByLocal(Long localId) {
        return localRepository.findByIdOrElseThrow(localId)
            .getObjetos()
            .stream()
            .map(objetoMapper::toResponse)
            .toList();
    }

}
