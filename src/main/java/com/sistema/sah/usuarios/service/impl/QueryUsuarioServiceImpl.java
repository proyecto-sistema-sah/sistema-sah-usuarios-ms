package com.sistema.sah.usuarios.service.impl;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.commons.entity.UsuarioEntity;
import com.sistema.sah.commons.helper.mapper.UsuarioMapper;
import com.sistema.sah.seguridad.dto.AuthResponseDto;
import com.sistema.sah.seguridad.dto.LoginDto;
import com.sistema.sah.seguridad.dto.UserSecurityDto;
import com.sistema.sah.seguridad.repository.UsuarioRepository;
import com.sistema.sah.seguridad.service.impl.JwtService;
import com.sistema.sah.usuarios.service.IQueryUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Implementación del servicio para la consulta y gestión de usuarios.
 * <p>
 * Proporciona métodos para listar usuarios y autenticar usuarios mediante login.
 * </p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class QueryUsuarioServiceImpl implements IQueryUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /**
     * Obtiene una lista de todos los usuarios registrados en el sistema.
     * <p>
     * Este método realiza una consulta a la base de datos y convierte las entidades de usuarios
     * en DTOs para ser devueltos al cliente.
     * </p>
     *
     * @return un objeto {@link RespuestaGeneralDto} que contiene la lista de usuarios.
     */
    @Override
    @Transactional(readOnly = true)
    public RespuestaGeneralDto findAllUsuario() {
        log.info("Iniciando consulta de todos los usuarios");
        RespuestaGeneralDto respuesta = RespuestaGeneralDto.builder()
                .data(usuarioMapper.listEntityTolistDto(usuarioRepository.findAll()))
                .message("Se consultaron correctamente el listado de usuarios")
                .status(HttpStatus.OK)
                .build();
        log.info("Consulta de usuarios completada con éxito");
        return respuesta;
    }

    /**
     * Autentica a un usuario en el sistema utilizando sus credenciales.
     * <p>
     * Este método valida las credenciales proporcionadas por el usuario y,
     * en caso de éxito, genera un token JWT para futuras solicitudes.
     * </p>
     *
     * @param loginDto objeto que contiene las credenciales del usuario.
     * @return un objeto {@link RespuestaGeneralDto} que contiene el token JWT o un mensaje de error.
     */
    @Override
    public RespuestaGeneralDto loginUser(LoginDto loginDto) {
        log.info("Intentando iniciar sesión para el usuario: {}", loginDto.getEmail());
        RespuestaGeneralDto respuestaGeneralDto = new RespuestaGeneralDto();
        try {
            // Autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getContrasena())
            );

            if (!authentication.isAuthenticated()) {
                log.warn("Autenticación fallida para el usuario: {}", loginDto.getEmail());
                return generarRespuesta(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
            }

            // Buscar la entidad del usuario autenticado
            Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findByCorreoUsuario(loginDto.getEmail());
            if (usuarioOpt.isEmpty()) {
                log.warn("Usuario no encontrado con el correo: {}", loginDto.getEmail());
                return generarRespuesta(HttpStatus.NOT_FOUND, "Usuario no encontrado");
            }

            UsuarioEntity usuarioEntity = usuarioOpt.get();
            UserSecurityDto user = new UserSecurityDto(usuarioMapper.entityToDto(usuarioEntity));

            // Generar token JWT
            AuthResponseDto token = jwtService.generarToken(user);

            // Configurar respuesta exitosa
            log.info("Inicio de sesión exitoso para el usuario: {}", loginDto.getEmail());
            return RespuestaGeneralDto.builder()
                    .data(token)
                    .message("Inicio de sesión exitoso")
                    .status(HttpStatus.OK)
                    .build();

        } catch (Exception e) {
            log.error("Error durante el inicio de sesión", e);
            return generarRespuesta(HttpStatus.UNAUTHORIZED, "Error en las credenciales o en el proceso de autenticación");
        }
    }

    /**
     * Genera una respuesta estándar para manejar errores y situaciones específicas.
     *
     * @param status  el estado HTTP de la respuesta.
     * @param message el mensaje descriptivo de la respuesta.
     * @return un objeto {@link RespuestaGeneralDto} con el estado y el mensaje.
     */
    private RespuestaGeneralDto generarRespuesta(HttpStatus status, String message) {
        return RespuestaGeneralDto.builder()
                .status(status)
                .message(message)
                .build();
    }
}
