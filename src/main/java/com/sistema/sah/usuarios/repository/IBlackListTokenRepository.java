package com.sistema.sah.usuarios.repository;

import com.sistema.sah.commons.entity.BlackListTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlackListTokenRepository extends JpaRepository<BlackListTokenEntity, Integer > {

    @Modifying
    @Query(value = """
        DELETE FROM sah.black_list_token where fecha_expiracion < NOW();
    """ ,nativeQuery = true)
    void deleteTokensExpire();

}
