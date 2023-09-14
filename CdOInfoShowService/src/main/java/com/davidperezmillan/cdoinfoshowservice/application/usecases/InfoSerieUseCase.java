package com.davidperezmillan.cdoinfoshowservice.application.usecases;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;

public interface InfoSerieUseCase {

    Serie get(int id);

}
