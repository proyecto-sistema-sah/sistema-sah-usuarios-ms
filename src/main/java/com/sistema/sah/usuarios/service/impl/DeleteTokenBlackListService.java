package com.sistema.sah.usuarios.service.impl;

import com.sistema.sah.usuarios.repository.IBlackListTokenRepository;
import com.sistema.sah.usuarios.service.IDeleteTokenBlackListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio para la eliminación de tokens expirados de la lista negra.
 * <p>
 * Este servicio se encarga de eliminar los registros de tokens cuya fecha de expiración
 * ya ha pasado, optimizando el uso de recursos y manteniendo la base de datos limpia.
 * </p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteTokenBlackListService implements IDeleteTokenBlackListService {

    private final IBlackListTokenRepository blackListTokenRepository;

    /**
     * Elimina los tokens expirados de la lista negra.
     * <p>
     * Este método utiliza el repositorio {@link IBlackListTokenRepository} para ejecutar
     * la operación de eliminación de manera transaccional. Registra logs antes y después
     * del proceso, así como en caso de errores.
     * </p>
     */
    @Override
    @Transactional
    public void eliminarTokensExpirados() {
        log.info("Inicio del proceso de eliminación de tokens expirados de la lista negra");
        try {
            blackListTokenRepository.deleteTokensExpired();
            log.info("Eliminación completada con éxito.");
        } catch (Exception ex) {
            log.error("Error durante la eliminación de tokens expirados", ex);
            throw new RuntimeException("Error al eliminar tokens expirados: " + ex.getMessage(), ex);
        }
    }
}
