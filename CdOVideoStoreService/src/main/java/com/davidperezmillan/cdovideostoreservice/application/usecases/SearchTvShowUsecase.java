package com.davidperezmillan.cdovideostoreservice.application.usecases;

import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowResponse;

import java.util.List;

public interface SearchTvShowUsecase {

    List<TvShowResponse> getAll();

    List<TvShowResponse> getTvShow(String title);
}
