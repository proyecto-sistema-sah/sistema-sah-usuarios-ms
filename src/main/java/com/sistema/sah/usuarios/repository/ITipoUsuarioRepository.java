package com.sistema.sah.usuarios.repository;

import com.sistema.sah.commons.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestionar operaciones sobre la entidad {@link TipoUsuarioEntity}.
 * <p>
 * Este repositorio proporciona métodos CRUD predeterminados para interactuar con la base de datos
 * utilizando la entidad {@link TipoUsuarioEntity}.
 * </p>
 *
 * @see JpaRepository
 */
@Repository
public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long> {

}
