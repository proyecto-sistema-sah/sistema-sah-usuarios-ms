package com.sistema.sah.usuarios.repository;

import com.sistema.sah.commons.entity.VistaTiposUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para gestionar operaciones sobre la vista materializada `vista_tipos_usuario`.
 * <p>
 * Este repositorio proporciona métodos de solo lectura para interactuar con la vista.
 * </p>
 *
 * @see JpaRepository
 */
@Repository
public interface IVistaTiposUsuarioRepository extends JpaRepository<VistaTiposUsuarioEntity, Long> {

    /**
     * Encuentra todos los tipos de usuario en la vista materializada.
     *
     * @return una lista de entidades {@link VistaTiposUsuarioEntity}.
     */
    List<VistaTiposUsuarioEntity> findAll();
}
