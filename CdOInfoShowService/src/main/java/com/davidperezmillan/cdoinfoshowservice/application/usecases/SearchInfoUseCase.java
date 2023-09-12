package com.davidperezmillan.cdoinfoshowservice.application.usecases;

import com.davidperezmillan.cdoinfoshowservice.domain.model.Serie;

public interface SearchInfoUseCase {

    Serie[] search(Serie search);

}
