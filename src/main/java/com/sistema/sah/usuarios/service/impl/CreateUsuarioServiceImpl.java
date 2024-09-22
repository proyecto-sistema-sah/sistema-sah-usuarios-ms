package com.sistema.sah.usuarios.service.impl;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.commons.helper.mapper.UsuarioMapper;
import com.sistema.sah.commons.helper.util.Utilidades;
import com.sistema.sah.seguridad.dto.UserSecurityDto;
import com.sistema.sah.seguridad.repository.UsuarioRepository;
import com.sistema.sah.seguridad.service.IJwtService;
import com.sistema.sah.seguridad.service.impl.JwtService;
import com.sistema.sah.usuarios.service.ICreateUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUsuarioServiceImpl implements ICreateUsuarioService {

    private final UsuarioMapper usuarioMapper;

    private final UsuarioRepository usuarioRepository;


    private final JwtService jwtService;


    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public RespuestaGeneralDto saveUsuario(UserSecurityDto usuarioDto) {
        RespuestaGeneralDto respuestaGeneralDto = new RespuestaGeneralDto();
        usuarioDto.setCodigoUsuario(Utilidades.generarCodigo(usuarioDto.getTipoUsuarioDtoFk().getNombreTipoUsuario()));
        usuarioDto.setContrasena(passwordEncoder.encode(usuarioDto.getContrasena()));
        usuarioRepository.save(usuarioMapper.dtoToEntity(usuarioDto));
        respuestaGeneralDto.setMessage("Se creo correctamente el usuario");
        respuestaGeneralDto.setStatus(HttpStatus.CREATED);
        respuestaGeneralDto.setData(jwtService.generarToken(usuarioDto));
        return respuestaGeneralDto;
    }


}
