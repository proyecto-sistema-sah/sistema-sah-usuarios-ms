package com.sistema.sah.usuarios.service;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.seguridad.dto.LoginDto;

/**
 * Interfaz para el servicio de consulta y gestión de usuarios.
 * <p>
 * Proporciona métodos para obtener la lista de usuarios registrados en el sistema y para realizar
 * operaciones de autenticación como el inicio de sesión.
 * </p>
 */
public interface IQueryUsuarioService {

   /**
    * Obtiene la lista de todos los usuarios registrados en el sistema.
    * <p>
    * Este método devuelve la información de todos los usuarios en un objeto
    * {@link RespuestaGeneralDto}, que incluye detalles como el estado y el mensaje
    * de la operación.
    * </p>
    *
    * @return un objeto {@link RespuestaGeneralDto} que contiene la lista de usuarios
    * y detalles adicionales sobre la respuesta.
    */
   RespuestaGeneralDto findAllUsuario();

   /**
    * Realiza la autenticación de un usuario mediante sus credenciales.
    * <p>
    * Este método valida las credenciales proporcionadas en el objeto {@link LoginDto}
    * y genera una respuesta que indica el éxito o el fracaso de la operación, junto
    * con los detalles correspondientes.
    * </p>
    *
    * @param loginDto objeto que contiene las credenciales de inicio de sesión del usuario.
    * @return un objeto {@link RespuestaGeneralDto} con el resultado del proceso de autenticación.
    */
   RespuestaGeneralDto loginUser(LoginDto loginDto);
}
