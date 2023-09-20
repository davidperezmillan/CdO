package com.davidperezmillan.cdoinfoshowservice.application.usecases;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;

public interface SearchInfoUseCase {

    Serie[] search(String search, boolean isSerie);

}
