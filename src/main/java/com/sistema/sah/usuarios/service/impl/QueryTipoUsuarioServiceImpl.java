package com.sistema.sah.usuarios.service.impl;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.commons.dto.TipoAlimentoDto;
import com.sistema.sah.commons.dto.TipoUsuarioDto;
import com.sistema.sah.commons.helper.mapper.TipoUsuarioMapper;
import com.sistema.sah.usuarios.repository.ITipoUsuarioRepository;
import com.sistema.sah.usuarios.service.IQueryTipoUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la consulta de tipos de usuario.
 * <p>
 * Este servicio permite obtener una lista de todos los tipos de usuario disponibles
 * en el sistema, devolviendo los datos en un formato estructurado.
 * </p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class QueryTipoUsuarioServiceImpl implements IQueryTipoUsuarioService {

    private final ITipoUsuarioRepository tipoUsuarioRepository;
    private final TipoUsuarioMapper tipoUsuarioMapper;

    /**
     * Obtiene todos los tipos de usuario registrados en el sistema.
     * <p>
     * Este método consulta la base de datos, convierte las entidades a DTOs
     * y devuelve una respuesta estructurada con el resultado.
     * </p>
     *
     * @return un objeto {@link RespuestaGeneralDto} que contiene la lista de tipos de usuario,
     * el estado HTTP y un mensaje descriptivo.
     */
    @Override
    public RespuestaGeneralDto getAllTipoUsuarios() {
        RespuestaGeneralDto respuesta = new RespuestaGeneralDto();
        try {
            log.info("Iniciando la consulta de todos los tipos de usuario");

            // Obtener la lista de tipos de usuario desde la base de datos
            List<TipoUsuarioDto> listaTiposUsuario = tipoUsuarioMapper.listEntityTolistDto(tipoUsuarioRepository.findAll());

            // Configurar la respuesta
            respuesta.setStatus(HttpStatus.OK);
            respuesta.setData(listaTiposUsuario);
            respuesta.setMessage("Consulta exitosa de todos los tipos de usuario");

            log.info("Consulta completada exitosamente con {} tipos de usuario encontrados", listaTiposUsuario.size());
        } catch (Exception ex) {
            log.error("Error al consultar los tipos de usuario", ex);
            respuesta.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            respuesta.setMessage("Error interno al consultar los tipos de usuario: " + ex.getMessage());
        }
        return respuesta;
    }
}
