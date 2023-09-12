package com.davidperezmillan.cdoinfoshowservice.application.usecases;

import com.davidperezmillan.cdoinfoshowservice.domain.model.Live;

public interface KeepAliveUseCase {

    String getKeepAlive();

    String saludo(Live live);
}
