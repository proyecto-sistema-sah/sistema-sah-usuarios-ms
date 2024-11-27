package com.sistema.sah.usuarios.helper.tareaprogramada;

import com.sistema.sah.usuarios.service.IDeleteTokenBlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Clase que define tareas programadas para la gestión del sistema.
 * <p>
 * Este componente incluye tareas que se ejecutan de forma periódica, como la eliminación
 * de tokens JWT expirados de la lista negra.
 * </p>
 */
@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final IDeleteTokenBlackListService deleteTokenBlackListService;

    /**
     * Tarea programada para eliminar tokens JWT expirados de la lista negra.
     * <p>
     * Esta tarea se ejecuta diariamente a la medianoche, utilizando una expresión cron.
     * </p>
     *
     * <ul>
     * <li>Expresión Cron: {@code "0 0 0 * * ?"}</li>
     * <li>Horario: Medianoche, todos los días.</li>
     * </ul>
     *
     * @see IDeleteTokenBlackListService#eliminarTokensExpirados()
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void eliminarTokensExpirados() {
        deleteTokenBlackListService.eliminarTokensExpirados();
    }
}
