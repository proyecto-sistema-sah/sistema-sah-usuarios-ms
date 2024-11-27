package com.sistema.sah.usuarios.controller;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.commons.dto.UsuarioDto;
import com.sistema.sah.seguridad.dto.LoginDto;
import com.sistema.sah.seguridad.dto.UserSecurityDto;
import com.sistema.sah.seguridad.service.ITokenBlackListService;
import com.sistema.sah.usuarios.service.ICreateUsuarioService;
import com.sistema.sah.usuarios.service.IQueryUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gestionar las operaciones relacionadas con los usuarios.
 * <p>
 * Este controlador incluye endpoints para listar usuarios, realizar login, logout y
 * crear nuevos usuarios.
 * </p>
 */
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Log4j2
public class UsuarioController {

    private final IQueryUsuarioService usuarioService;
    private final ICreateUsuarioService createUsuarioService;
    private final ITokenBlackListService tokenBlacklistService;

    /**
     * Endpoint para obtener la lista de todos los usuarios.
     *
     * @return una respuesta con la lista de usuarios y el estado HTTP correspondiente.
     */
    @GetMapping("/buscar-todos")
    public ResponseEntity<RespuestaGeneralDto> listadoUsuarios() {
        RespuestaGeneralDto respuesta = usuarioService.findAllUsuario();
        return ResponseEntity.status(respuesta.getStatus()).body(respuesta);
    }

    /**
     * Endpoint para realizar el login de un usuario.
     *
     * @param loginDto objeto que contiene las credenciales de acceso del usuario.
     * @return una respuesta con el resultado del login y el estado HTTP correspondiente.
     */
    @PostMapping("/login")
    public ResponseEntity<RespuestaGeneralDto> login(@RequestBody LoginDto loginDto) {
        RespuestaGeneralDto respuesta = usuarioService.loginUser(loginDto);
        return ResponseEntity.status(respuesta.getStatus()).body(respuesta);
    }

    /**
     * Endpoint para realizar el logout de un usuario.
     * <p>
     * Este método invalida el token JWT del usuario añadiéndolo a una lista negra.
     * </p>
     *
     * @param token el token JWT del usuario que se va a invalidar.
     * @return una respuesta indicando el éxito o error del logout.
     */
    @PostMapping("/logout")
    public ResponseEntity<RespuestaGeneralDto> logout(@RequestHeader("Authorization") String token) {
        RespuestaGeneralDto respuesta = new RespuestaGeneralDto();
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7); // Remueve "Bearer " del token
            tokenBlacklistService.blackListToken(jwtToken); // Añade el token a la lista negra
            respuesta.setMessage("Logout exitoso, token invalidado.");
            respuesta.setStatus(HttpStatus.OK);
        } else {
            respuesta.setMessage("Error en el logout: token inválido o ausente");
            respuesta.setStatus(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(respuesta.getStatus()).body(respuesta);
    }

    /**
     * Endpoint para crear un nuevo usuario.
     *
     * @param usuarioDto objeto que contiene la información del usuario a crear.
     * @return una respuesta con el resultado de la creación y el estado HTTP correspondiente.
     */
    @PostMapping("/crear")
    public ResponseEntity<RespuestaGeneralDto> createUsuario(@RequestBody UsuarioDto usuarioDto) {
        UserSecurityDto userSecurityDto = new UserSecurityDto(usuarioDto);
        RespuestaGeneralDto respuesta = createUsuarioService.saveUsuario(userSecurityDto);
        return ResponseEntity.status(respuesta.getStatus()).body(respuesta);
    }
}
