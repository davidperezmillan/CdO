package com.davidperezmillan.cdovideostoreservice.application.services;

import com.davidperezmillan.cdovideostoreservice.application.usecases.SpecialUseCase;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.TvShow;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.repositories.TvShowRepository;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.PageResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class SpecialService implements SpecialUseCase {

    private final TvShowRepository tvShowRepository;

    private final InsertTvShowService insertTvShowService;
    private final SearchTvShowService searchTvShowService;

    @Autowired
    public SpecialService(TvShowRepository tvShowRepository, InsertTvShowService insertTvShowService,
            SearchTvShowService searchTvShowService) {
        this.tvShowRepository = tvShowRepository;
        this.insertTvShowService = insertTvShowService;
        this.searchTvShowService = searchTvShowService;
    }

    @Override
    public PageResponse find(TvShowRequest tvShowRequest, int page, int size) {

        if (tvShowRequest.getId() != null) {
            Page<TvShow> itemsFind = null;
            TvShow itenFind = tvShowRepository.findById(tvShowRequest.getId()).orElse(null);
            if (itenFind == null) {
                insertTvShowService.addTitleByLetter(tvShowRequest.getTitle());
                Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
                itemsFind = tvShowRepository.findByTitleContainingIgnoreCase(tvShowRequest.getTitle(), pageable);
            } else {
                itemsFind = new PageImpl<>(new ArrayList<>(Collections.singleton(itenFind)));

            }
            itemsFind.getContent().forEach(tvShow -> {
                insertTvShowService.addCapitulos(tvShow.getId());
            });

        }
        if (tvShowRequest.getTitle() != null) {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            Page<TvShow> itemsFind = tvShowRepository.findByTitleContainingIgnoreCase(tvShowRequest.getTitle(),
                    pageable);
            if (itemsFind.isEmpty()) {
                insertTvShowService.addTitleByLetter(tvShowRequest.getTitle());
                itemsFind = tvShowRepository.findByTitleContainingIgnoreCase(tvShowRequest.getTitle(), pageable);
            }
            itemsFind.getContent().forEach(tvShow -> {
                insertTvShowService.addCapitulos(tvShow.getId());
            });

        }
        return searchTvShowService.getTvShow(tvShowRequest.getTitle(), page, size);
    }

}
