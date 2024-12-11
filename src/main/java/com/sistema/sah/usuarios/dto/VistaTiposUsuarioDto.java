package com.sistema.sah.usuarios.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VistaTiposUsuarioDto {

    private Long id;

    private String nombreTipoUsuario;

}
