package com.sistema.sah.usuarios.controller;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.usuarios.service.IQueryTipoUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador que gestiona las operaciones relacionadas con el tipo de usuario.
 * <p>
 * Este controlador expone endpoints para obtener información sobre los tipos de usuario
 * disponibles en el sistema.
 * </p>
 */
@RestController
@RequestMapping("/tipo-usuario")
@RequiredArgsConstructor
@Log4j2
public class TipoUsuarioController {

    private final IQueryTipoUsuarioService queryTipoUsuarioService;

    /**
     * Endpoint para obtener todos los tipos de usuario.
     * <p>
     * Este método llama al servicio {@link IQueryTipoUsuarioService} para obtener
     * una lista de todos los tipos de usuario registrados en el sistema.
     * </p>
     *
     * @return un objeto {@link RespuestaGeneralDto} que contiene la lista de tipos de usuario
     * junto con información adicional sobre la respuesta.
     */
    @GetMapping("/all")
    public RespuestaGeneralDto getAllTipoUsuarios() {
        RespuestaGeneralDto respuesta = queryTipoUsuarioService.getAllTipoUsuarios();
        return respuesta;
    }
}
