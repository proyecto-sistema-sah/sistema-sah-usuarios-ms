package com.sistema.sah.usuarios.repository;

import com.sistema.sah.commons.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("usuario")
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, String> {

    Boolean existsByCorreoUsuario(String email);

}
