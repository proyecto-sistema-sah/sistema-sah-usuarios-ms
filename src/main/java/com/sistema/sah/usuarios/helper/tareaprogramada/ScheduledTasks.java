package com.sistema.sah.usuarios.helper.tareaprogramada;

import com.sistema.sah.usuarios.service.IDeleteTokenBlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final IDeleteTokenBlackListService iDeleteTokenBlackListService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void eliminarTokensExpirados(){
        iDeleteTokenBlackListService.eliminarTokensExpirados();
    }

}
