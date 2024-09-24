package com.sistema.sah.usuarios.controller;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.commons.dto.UsuarioDto;
import com.sistema.sah.seguridad.dto.LoginDto;
import com.sistema.sah.seguridad.dto.UserSecurityDto;
import com.sistema.sah.usuarios.service.ICreateUsuarioService;
import com.sistema.sah.usuarios.service.IQueryUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Log4j2
public class UsuarioController {

    private final IQueryUsuarioService usuarioService;

    private final ICreateUsuarioService createUsuarioService;


    @GetMapping("/buscar-todos")
    public ResponseEntity<RespuestaGeneralDto> listadoUsuarios(){
        RespuestaGeneralDto respuestaGeneralDto = usuarioService.findAllUsuario();
        return ResponseEntity.status(respuestaGeneralDto.getStatus()).body(respuestaGeneralDto);
    }

    @PostMapping("/login")
    public ResponseEntity<RespuestaGeneralDto> login(@RequestBody LoginDto loginDto){
        log.error(loginDto);
        RespuestaGeneralDto respuestaGeneralDto = usuarioService.loginUser(loginDto);
        log.error(respuestaGeneralDto);
        return ResponseEntity.status(respuestaGeneralDto.getStatus()).body(respuestaGeneralDto);
    }

    @PostMapping("/crear")
    public ResponseEntity<RespuestaGeneralDto> createUsuario(@RequestBody UsuarioDto usuarioDto){
        RespuestaGeneralDto respuestaGeneralDto = createUsuarioService.saveUsuario(new UserSecurityDto(usuarioDto));
        return ResponseEntity.status(respuestaGeneralDto.getStatus()).body(respuestaGeneralDto);
    }

}
