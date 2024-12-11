package com.sistema.sah.commons.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad que representa la vista materializada `vista_tipos_usuario`.
 * <p>
 * Esta clase mapea la vista materializada que muestra los datos de tipos de usuario.
 * </p>
 */
@Data
@Entity
@Table(name = "vista_tipos_usuario", schema = "sah")
public class VistaTiposUsuarioEntity {

    @Id
    @Column(name = "id_tipo_usuario", nullable = false)
    private Long id;

    @Column(name = "nombre_tipo_usuario", nullable = false)
    private String nombreTipoUsuario;
}
