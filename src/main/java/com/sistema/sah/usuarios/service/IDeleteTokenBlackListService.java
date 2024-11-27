package com.sistema.sah.usuarios.service;

/**
 * Interfaz para el servicio de eliminación de tokens expirados de la lista negra.
 * <p>
 * Proporciona métodos para gestionar la limpieza de tokens JWT que ya no son válidos
 * y se encuentran en la lista negra, asegurando que el sistema mantenga un estado consistente
 * y eficiente.
 * </p>
 */
public interface IDeleteTokenBlackListService {

    /**
     * Elimina los tokens JWT expirados de la lista negra.
     * <p>
     * Este método identifica y elimina todos los registros de tokens cuya fecha de expiración
     * ya haya pasado, optimizando el uso de recursos del sistema.
     * </p>
     */
    void eliminarTokensExpirados();

}
