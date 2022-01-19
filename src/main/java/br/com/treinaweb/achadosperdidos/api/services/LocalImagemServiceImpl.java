package br.com.treinaweb.achadosperdidos.api.services;

import org.springframework.stereotype.Service;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.LocalImagemRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.achadosperdidos.core.repositories.LocalRepository;
import br.com.treinaweb.achadosperdidos.core.services.storage.adapters.StorageService;
import br.com.treinaweb.achadosperdidos.core.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalImagemServiceImpl implements LocalImagemService {

    private final SecurityUtils securityUtils;
    private final StorageService storageService;
    private final LocalRepository localRepository;

    @Override
    public MensagemResponse defineLocalImagem(LocalImagemRequest localImagemRequest) {
        var imagem = storageService.save(localImagemRequest.getImagemLocal());
        var local = securityUtils.getLocalFromAuthenticatedUser();
        local.setImagem(imagem);
        localRepository.save(local);
        return new MensagemResponse("Imagem definida com sucesso!");
    }

}
