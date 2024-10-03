package com.sistema.sah.usuarios.controller;

import com.sistema.sah.commons.dto.RespuestaGeneralDto;
import com.sistema.sah.commons.dto.TipoUsuarioDto;
import com.sistema.sah.usuarios.service.IQueryTipoUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tipo-usuario")
@RequiredArgsConstructor
@Log4j2
public class TipoUsuarioController {

    private final IQueryTipoUsuarioService iQueryTipoUsuarioService;

    @GetMapping("all")
    public RespuestaGeneralDto getAllTipoUsuarios(){
        return iQueryTipoUsuarioService.getAllTipoUsuarios();
    }

}
