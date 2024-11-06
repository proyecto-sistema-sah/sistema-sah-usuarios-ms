package com.sistema.sah.usuarios.service.impl;

import com.sistema.sah.usuarios.repository.IBlackListTokenRepository;
import com.sistema.sah.usuarios.service.IDeleteTokenBlackListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteTokenBlackListService implements IDeleteTokenBlackListService {

    private final IBlackListTokenRepository iBlackListTokenRepository;

    @Override
    @Transactional
    public void eliminarTokensExpirados() {
        try{
            log.info("Se comienza eliminacion de tokens expirados");
            iBlackListTokenRepository.deleteTokensExpire();
            log.info("Se termina eliminacion de tokens expirados");
        }catch (Exception ex){
            log.error("Error interno " , ex);
        }
    }
}
