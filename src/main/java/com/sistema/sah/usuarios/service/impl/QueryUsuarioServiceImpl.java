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

@Service
@RequiredArgsConstructor
@Slf4j
public class QueryUsuarioServiceImpl implements IQueryUsuarioService {


  private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    @Transactional(readOnly = true)
    public RespuestaGeneralDto findAllUsuario() {
        RespuestaGeneralDto respuesta = new RespuestaGeneralDto();
        respuesta.setData(usuarioMapper.listEntityTolistDto(usuarioRepository.findAll()));
        respuesta.setMessage("Se consultaron correctamente el listado de usuarios");
        respuesta.setStatus(HttpStatus.OK);
       return respuesta;
    }

    @Override
    public RespuestaGeneralDto loginUser(LoginDto loginDto) {
        RespuestaGeneralDto respuestaGeneralDto = new RespuestaGeneralDto();
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getContrasena()));
            if(authentication.isAuthenticated()){
                respuestaGeneralDto.setMessage("Se inicio correctamente");
                respuestaGeneralDto.setStatus(HttpStatus.OK);
                UsuarioEntity usuarioEntity = usuarioRepository.findByCorreoUsuario(loginDto.getEmail()).orElseThrow();
                UserSecurityDto user = new UserSecurityDto(usuarioMapper.entityToDto(usuarioEntity));
                AuthResponseDto token = jwtService.generarToken(user);
                respuestaGeneralDto.setData(token);
            }else{
                respuestaGeneralDto.setMessage("Hubo un problema en las credenciales");
                respuestaGeneralDto.setStatus(HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            log.error("Error ", e);
            respuestaGeneralDto.setMessage("Hubo un problema en las credenciales");
            respuestaGeneralDto.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return respuestaGeneralDto;
    }


}
