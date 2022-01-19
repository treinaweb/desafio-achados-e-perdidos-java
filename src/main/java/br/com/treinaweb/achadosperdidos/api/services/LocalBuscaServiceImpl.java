package br.com.treinaweb.achadosperdidos.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.treinaweb.achadosperdidos.api.dtos.responses.LocalSummaryResponse;
import br.com.treinaweb.achadosperdidos.api.mappers.LocalMapper;
import br.com.treinaweb.achadosperdidos.core.repositories.LocalRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalBuscaServiceImpl implements LocalBuscaService {

    private final LocalRepository localRepository;
    private final LocalMapper localMapper;

    @Override
    public List<LocalSummaryResponse> findLocalByNome(String nome) {
        return localRepository.findByNomeContainingIgnoreCase(nome)
            .stream()
            .map(localMapper::toSummaryResponse)
            .toList();
    }

}
