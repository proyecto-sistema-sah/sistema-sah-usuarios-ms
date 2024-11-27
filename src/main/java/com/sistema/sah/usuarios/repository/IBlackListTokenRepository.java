package com.sistema.sah.usuarios.repository;

import com.sistema.sah.commons.entity.BlackListTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestionar operaciones sobre la entidad {@link BlackListTokenEntity}.
 * <p>
 * Este repositorio proporciona métodos para interactuar con la tabla de tokens en la lista negra,
 * incluyendo la eliminación de tokens expirados.
 * </p>
 */
@Repository
public interface IBlackListTokenRepository extends JpaRepository<BlackListTokenEntity, Integer> {

    /**
     * Elimina los tokens de la lista negra que ya han expirado.
     * <p>
     * Esta operación utiliza una consulta SQL nativa para eliminar todos los registros
     * cuya fecha de expiración sea anterior a la fecha y hora actuales.
     * </p>
     *
     * <pre>
     * DELETE FROM sah.black_list_token WHERE fecha_expiracion < NOW();
     * </pre>
     *
     * <strong>Nota:</strong> Este método debe ejecutarse dentro de una transacción debido al uso de
     * la anotación {@code @Modifying}.
     */
    @Modifying
    @Query(value = "DELETE FROM sah.black_list_token WHERE fecha_expiracion < NOW()", nativeQuery = true)
    void deleteTokensExpired();
}
