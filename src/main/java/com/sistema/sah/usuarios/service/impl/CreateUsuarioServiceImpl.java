package com.sistema.sah.usuarios.service.impl;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.commons.helper.mapper.UsuarioMapper;
import com.sistema.sah.commons.helper.util.Utilidades;
import com.sistema.sah.seguridad.dto.UserSecurityDto;
import com.sistema.sah.seguridad.repository.UsuarioRepository;
import com.sistema.sah.seguridad.service.impl.JwtService;
import com.sistema.sah.usuarios.repository.IUsuarioRepository;
import com.sistema.sah.usuarios.service.ICreateUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio para la creación de usuarios.
 * <p>
 * Proporciona la lógica necesaria para registrar nuevos usuarios en el sistema,
 * incluyendo la generación de códigos únicos, el cifrado de contraseñas y la emisión
 * de tokens JWT para los usuarios creados.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class CreateUsuarioServiceImpl implements ICreateUsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final IUsuarioRepository iUsuarioRepository;

    /**
     * Registra un nuevo usuario en el sistema.
     * <p>
     * Este método genera un código único para el usuario, cifra la contraseña proporcionada,
     * guarda la información del usuario en la base de datos y emite un token JWT para el usuario creado.
     * Maneja posibles errores y devuelve una respuesta adecuada.
     * </p>
     *
     * @param usuarioDto objeto que contiene los datos del usuario a registrar.
     * @return un objeto {@link RespuestaGeneralDto} que incluye el estado, un mensaje y el token JWT del usuario creado.
     */
    @Override
    @Transactional
    public RespuestaGeneralDto saveUsuario(UserSecurityDto usuarioDto) {
        try {
            // Validar datos del usuario
            validarDatosUsuario(usuarioDto);

            // Generar un código único para el usuario basado en su tipo
            String codigoUsuario = Utilidades.generarCodigo(usuarioDto.getTipoUsuarioDtoFk().getNombreTipoUsuario());
            usuarioDto.setCodigoUsuario(codigoUsuario);

            // Cifrar la contraseña del usuario
            String contrasenaCifrada = passwordEncoder.encode(usuarioDto.getContrasena());
            usuarioDto.setContrasena(contrasenaCifrada);

            // Guardar el usuario en la base de datos
            usuarioRepository.save(usuarioMapper.dtoToEntity(usuarioDto));

            // Generar un token JWT para el usuario creado
            String tokenJwt = jwtService.generarToken(usuarioDto).getToken();

            // Configurar el mensaje de respuesta
            return RespuestaGeneralDto.builder()
                    .message("Usuario creado exitosamente")
                    .status(HttpStatus.CREATED)
                    .data(tokenJwt)
                    .build();

        } catch (IllegalArgumentException ex) {
            // Manejar errores de validación
            return RespuestaGeneralDto.builder()
                    .message("Error en los datos del usuario: " + ex.getMessage())
                    .status(HttpStatus.BAD_REQUEST)
                    .build();

        } catch (Exception ex) {
            // Manejar cualquier otro error inesperado
            return RespuestaGeneralDto.builder()
                    .message("Error interno al crear el usuario: " + ex.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    /**
     * Valida los datos del usuario antes de proceder con el registro.
     * <p>
     * Este método verifica que los datos obligatorios estén presentes y sean válidos.
     * </p>
     *
     * @param usuarioDto el objeto que contiene los datos del usuario.
     * @throws IllegalArgumentException si alguno de los datos es inválido.
     */
    private void validarDatosUsuario(UserSecurityDto usuarioDto) {
        if (usuarioDto.getTipoUsuarioDtoFk() == null || usuarioDto.getTipoUsuarioDtoFk().getNombreTipoUsuario() == null) {
            throw new IllegalArgumentException("El tipo de usuario es obligatorio");
        }
        if (usuarioDto.getContrasena() == null || usuarioDto.getContrasena().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if(iUsuarioRepository.existsByCorreoUsuario(usuarioDto.getCorreoUsuario())) {
            throw new IllegalArgumentException("El correo ya existe");
        }
    }
}
