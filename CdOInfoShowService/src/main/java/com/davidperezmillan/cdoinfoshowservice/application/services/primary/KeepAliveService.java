package com.davidperezmillan.cdoinfoshowservice.application.services.primary;

import com.davidperezmillan.cdoinfoshowservice.application.usecases.KeepAliveUseCase;
import com.davidperezmillan.cdoinfoshowservice.domain.model.Live;
import org.springframework.stereotype.Service;

@Service
public class KeepAliveService implements KeepAliveUseCase {

    @Override
    public String getKeepAlive() {
        return "Keep alive !!!";
    }

    @Override
    public String saludo(Live live) {
        return "Hola " + live.getName() + "!!!";
    }

}
