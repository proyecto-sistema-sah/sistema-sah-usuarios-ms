package com.sistema.sah.usuarios.service;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.seguridad.dto.UserSecurityDto;

/**
 * Interfaz para el servicio de creación de usuarios.
 * <p>
 * Proporciona métodos para gestionar la creación de nuevos usuarios en el sistema,
 * incluyendo la validación y el almacenamiento de la información del usuario.
 * </p>
 */
public interface ICreateUsuarioService {

    /**
     * Crea y guarda un nuevo usuario en el sistema.
     * <p>
     * Este método recibe los datos del usuario encapsulados en un objeto {@link UserSecurityDto},
     * realiza las validaciones necesarias y guarda la información en la base de datos.
     * </p>
     *
     * @param usuarioDto objeto que contiene la información del usuario a crear.
     * @return un objeto {@link RespuestaGeneralDto} con el estado y el mensaje del resultado de la operación.
     */
    RespuestaGeneralDto saveUsuario(UserSecurityDto usuarioDto);

}
