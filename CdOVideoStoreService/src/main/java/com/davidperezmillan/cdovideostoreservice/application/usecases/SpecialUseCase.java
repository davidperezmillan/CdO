package com.davidperezmillan.cdovideostoreservice.application.usecases;

import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.PageResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowRequest;

public interface SpecialUseCase {

    PageResponse find(TvShowRequest tvShowRequest, int page, int size);
}
