package com.davidperezmillan.cdovideostoreservice.infrastructure.rest;

import com.davidperezmillan.cdovideostoreservice.application.usecases.DeleteTvShowUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tvshows")
public class TvShowDeleteController {

    DeleteTvShowUsecase deleteTvShowUsecase;

    @Autowired
    public TvShowDeleteController(DeleteTvShowUsecase deleteTvShowUsecase) {
        this.deleteTvShowUsecase = deleteTvShowUsecase;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteTvShowUsecase.delete(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        deleteTvShowUsecase.deleteAll();
    }

}
