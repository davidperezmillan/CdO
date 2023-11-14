package com.davidperezmillan.cdovideostoreservice.application.usecases;

import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.PageResponse;

public interface SearchTvShowUsecase {

    PageResponse getAll(int page, int size);

    PageResponse getTvShow(String title, int page, int size);
}
