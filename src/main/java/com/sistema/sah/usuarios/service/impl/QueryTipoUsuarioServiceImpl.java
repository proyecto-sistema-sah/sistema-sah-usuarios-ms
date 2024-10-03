package com.sistema.sah.usuarios.service.impl;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.commons.helper.mapper.TipoUsuarioMapper;
import com.sistema.sah.usuarios.repository.ITipoUsuarioRepository;
import com.sistema.sah.usuarios.service.IQueryTipoUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryTipoUsuarioServiceImpl implements IQueryTipoUsuarioService {

    private final ITipoUsuarioRepository iTipoUsuarioRepository;

    private final TipoUsuarioMapper tipoUsuarioMapper;

    @Override
    public RespuestaGeneralDto getAllTipoUsuarios() {
        RespuestaGeneralDto respuesta = new RespuestaGeneralDto();
        respuesta.setStatus(HttpStatus.OK);
        respuesta.setData(tipoUsuarioMapper.listEntityTolistDto(iTipoUsuarioRepository.findAll()));
        respuesta.setMessage("Se consultaron todos los tipos de usuario");
        return respuesta;
    }
}
