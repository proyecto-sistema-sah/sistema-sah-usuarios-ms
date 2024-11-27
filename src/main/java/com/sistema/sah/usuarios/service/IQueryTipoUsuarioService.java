package com.sistema.sah.usuarios.service;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;

/**
 * Interfaz para el servicio de consulta de tipos de usuario.
 * <p>
 * Proporciona métodos para obtener información sobre los diferentes tipos de usuario
 * registrados en el sistema.
 * </p>
 */
public interface IQueryTipoUsuarioService {

    /**
     * Obtiene todos los tipos de usuario registrados en el sistema.
     * <p>
     * Este método recupera una lista de los tipos de usuario disponibles, encapsulada
     * en un objeto {@link RespuestaGeneralDto}, que incluye información sobre el estado
     * y el mensaje de la operación.
     * </p>
     *
     * @return un objeto {@link RespuestaGeneralDto} que contiene la lista de tipos de usuario
     * y detalles adicionales sobre la respuesta.
     */
    RespuestaGeneralDto getAllTipoUsuarios();
}
