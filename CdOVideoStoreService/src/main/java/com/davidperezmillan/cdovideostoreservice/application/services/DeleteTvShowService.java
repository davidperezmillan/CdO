package com.davidperezmillan.cdovideostoreservice.application.services;

import com.davidperezmillan.cdovideostoreservice.application.usecases.DeleteTvShowUsecase;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.repositories.TvShowRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class DeleteTvShowService implements DeleteTvShowUsecase {

    private final TvShowRepository tvShowRepository;

    public DeleteTvShowService(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public void delete(Long id) {
        log.info("Se va a eliminar la serie de TV con id: " + id);
        tvShowRepository.deleteById(id);

    }

    @Override
    public void deleteAll() {
        log.info("Se van a eliminar todas las series de TV");
        tvShowRepository.deleteAll();

    }

}
