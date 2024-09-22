package com.sistema.sah.usuarios.service;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.seguridad.dto.UserSecurityDto;

public interface ICreateUsuarioService {

    RespuestaGeneralDto saveUsuario(UserSecurityDto usuarioDto);


}
