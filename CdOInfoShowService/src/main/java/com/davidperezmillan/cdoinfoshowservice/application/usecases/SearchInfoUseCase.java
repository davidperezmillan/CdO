package com.davidperezmillan.cdoinfoshowservice.application.usecases;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;

public interface SearchInfoUseCase {

    Serie[] search(Serie search);

    Serie[] search(String search);

}
