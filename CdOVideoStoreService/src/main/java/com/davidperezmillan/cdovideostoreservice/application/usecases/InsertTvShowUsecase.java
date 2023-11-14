package com.davidperezmillan.cdovideostoreservice.application.usecases;

import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowResponse;

import java.util.List;

public interface InsertTvShowUsecase {
    List<TvShowResponse> addTitleByLetter(String letter);

    List<TvShowResponse> addPremieres();

    int addCapitulos(String title);

    int addCapitulos(Long id);
}
