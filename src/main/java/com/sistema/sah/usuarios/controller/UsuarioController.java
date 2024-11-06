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

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Log4j2
public class UsuarioController {

    private final IQueryUsuarioService usuarioService;

    private final ICreateUsuarioService createUsuarioService;

    private final ITokenBlackListService tokenBlacklistService;

    @GetMapping("/buscar-todos")
    public ResponseEntity<RespuestaGeneralDto> listadoUsuarios(){
        RespuestaGeneralDto respuestaGeneralDto = usuarioService.findAllUsuario();
        return ResponseEntity.status(respuestaGeneralDto.getStatus()).body(respuestaGeneralDto);
    }

    @PostMapping("/login")
    public ResponseEntity<RespuestaGeneralDto> login(@RequestBody LoginDto loginDto){
        RespuestaGeneralDto respuestaGeneralDto = usuarioService.loginUser(loginDto);
        return ResponseEntity.status(respuestaGeneralDto.getStatus()).body(respuestaGeneralDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<RespuestaGeneralDto> logout(@RequestHeader("Authorization") String token){
        RespuestaGeneralDto respuestaGeneralDto = new RespuestaGeneralDto();
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7); // Remueve "Bearer " del token
            tokenBlacklistService.blackListToken(jwtToken); // Añadir el token a la lista negra
            respuestaGeneralDto.setMessage("Logout exitoso, token invalidado.");
            respuestaGeneralDto.setStatus(HttpStatus.OK);
        }else{
            respuestaGeneralDto.setMessage("Error logout");
            respuestaGeneralDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(respuestaGeneralDto.getStatus()).body(respuestaGeneralDto);
    }

    @PostMapping("/crear")
    public ResponseEntity<RespuestaGeneralDto> createUsuario(@RequestBody UsuarioDto usuarioDto){
        RespuestaGeneralDto respuestaGeneralDto = createUsuarioService.saveUsuario(new UserSecurityDto(usuarioDto));
        return ResponseEntity.status(respuestaGeneralDto.getStatus()).body(respuestaGeneralDto);
    }


}
