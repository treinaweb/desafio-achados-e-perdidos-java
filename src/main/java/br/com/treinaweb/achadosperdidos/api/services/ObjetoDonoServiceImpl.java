package br.com.treinaweb.achadosperdidos.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoDonoRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.achadosperdidos.core.repositories.ObjetoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObjetoDonoServiceImpl implements ObjetoDonoService {

    private final ObjetoRepository objetoRepository;

    @Override
    public MensagemResponse defineDonoObjeto(Long objetoId, ObjetoDonoRequest objetoDonoRequest) {
        var objeto = objetoRepository.findByIdOrElseThrow(objetoId);
        BeanUtils.copyProperties(objetoDonoRequest, objeto);
        objeto.setEntrege(true);
        objetoRepository.save(objeto);
        return new MensagemResponse("Dono objeto definido com sucesso!");
    }

}
