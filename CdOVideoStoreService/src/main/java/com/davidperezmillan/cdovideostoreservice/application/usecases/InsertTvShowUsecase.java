package com.davidperezmillan.cdovideostoreservice.application.usecases;

public interface InsertTvShowUsecase {
    int addTitleByLetter(String letter);

    int addPremieres();

    int addCapitulos(String title);

    int addCapitulos(Long id);
}
