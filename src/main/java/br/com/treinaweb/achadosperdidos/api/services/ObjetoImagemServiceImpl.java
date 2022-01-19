package br.com.treinaweb.achadosperdidos.api.services;

import org.springframework.stereotype.Service;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoImagemRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.achadosperdidos.core.repositories.ObjetoRepository;
import br.com.treinaweb.achadosperdidos.core.services.storage.adapters.StorageService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObjetoImagemServiceImpl implements ObjetoImagemService {

    private final StorageService storageService;
    private final ObjetoRepository objetoRepository;

    @Override
    public MensagemResponse defineObjetoImagemByObjetoId(Long objetoId, ObjetoImagemRequest objetoImagemRequest) {
        var objeto = objetoRepository.findByIdOrElseThrow(objetoId);
        var imagem = storageService.save(objetoImagemRequest.getImagemObjeto());
        objeto.setImagem(imagem);
        objetoRepository.save(objeto);
        return new MensagemResponse("Imagem definida com sucesso");
    }

}
