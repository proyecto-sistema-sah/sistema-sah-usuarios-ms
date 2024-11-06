package com.sistema.sah.usuarios.repository;

import com.sistema.sah.commons.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long> {

}
