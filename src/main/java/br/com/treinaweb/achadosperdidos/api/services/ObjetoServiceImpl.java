package br.com.treinaweb.achadosperdidos.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.ObjetoRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.responses.ObjetoResponse;
import br.com.treinaweb.achadosperdidos.api.mappers.ObjetoMapper;
import br.com.treinaweb.achadosperdidos.core.exceptions.ObjetoNotFoundException;
import br.com.treinaweb.achadosperdidos.core.models.Objeto;
import br.com.treinaweb.achadosperdidos.core.repositories.ObjetoRepository;
import br.com.treinaweb.achadosperdidos.core.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObjetoServiceImpl implements ObjetoService {

    private final ObjetoRepository objetoRepository;
    private final ObjetoMapper objetoMapper;
    private final SecurityUtils securityUtils;

    @Override
    public ObjetoResponse create(ObjetoRequest objetoRequest) {
        var objeto = objetoMapper.toModel(objetoRequest);
        objeto.setLocal(securityUtils.getLocalFromAuthenticatedUser());
        var createdObjeto = objetoRepository.save(objeto);
        return objetoMapper.toResponse(createdObjeto);
    }

    @Override
    public List<ObjetoResponse> findAllByAuthenticatedUser() {
        return objetoRepository.findByLocal(securityUtils.getLocalFromAuthenticatedUser())
            .stream()
            .map(objetoMapper::toResponse)
            .toList();
    }

    @Override
    public void deleteById(Long objetoId) {
        var objeto = findObjetoById(objetoId);
        objetoRepository.delete(objeto);
    }

    @Override
    public ObjetoResponse updateById(Long objetoId, ObjetoRequest objetoRequest) {
        var objeto = findObjetoById(objetoId);
        BeanUtils.copyProperties(objetoRequest, objeto);
        var updatedObjeto = objetoRepository.save(objeto);
        return objetoMapper.toResponse(updatedObjeto);
    }

    @Override
    public ObjetoResponse findById(Long objetoId) {
        var objeto = findObjetoById(objetoId);
        return objetoMapper.toResponse(objeto);
    }

    private Objeto findObjetoById(Long objetoId) {
        return objetoRepository.findById(objetoId)
            .orElseThrow(ObjetoNotFoundException::new);
    }

}
