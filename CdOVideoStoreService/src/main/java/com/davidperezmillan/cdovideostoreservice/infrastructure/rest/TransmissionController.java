package com.davidperezmillan.cdovideostoreservice.infrastructure.rest;

import com.davidperezmillan.cdovideostoreservice.application.services.SearchTvShowService;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.PageResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowRequest;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.transmission.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transmission")
public class TransmissionController {

    SearchTvShowService searchTvShowService;

    TransmissionService transmissionService;

    @Autowired
    public TransmissionController(SearchTvShowService searchTvShowService, TransmissionService transmissionService) {
        this.searchTvShowService = searchTvShowService;
        this.transmissionService = transmissionService;
    }

    @PostMapping("/{id}")
    public int scrapEpisodes(@PathVariable Long id) {
        PageResponse respuesta = searchTvShowService.getId(id);
        TvShowResponse serie = (TvShowResponse) respuesta.getContent().get(0);
        transmissionService.addTorrent(serie.getSessions().get(0).getEpisodes().get(0));
        return 1;
    }

}
