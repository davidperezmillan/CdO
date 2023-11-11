package com.davidperezmillan.cdovideostoreservice.infrastructure.rest;

import com.davidperezmillan.cdovideostoreservice.application.services.InsertTvShowService;
import com.davidperezmillan.cdovideostoreservice.application.services.SearchTvShowService;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowRequest;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tvshows")
public class RestScrapController {

    InsertTvShowService insertTvShowService;

    SearchTvShowService searchTvShowService;

    @Autowired
    public RestScrapController(InsertTvShowService insertTvShowService, SearchTvShowService searchTvShowService) {
        this.insertTvShowService = insertTvShowService;
        this.searchTvShowService = searchTvShowService;
    }

    @GetMapping("/{title}")
    public List<TvShowResponse> getTvShow(@PathVariable String title) {
        return searchTvShowService.getTvShow(title);
    }

    @GetMapping("/all/{title}")
    public List<TvShowResponse> all(@PathVariable("title") String title) {
        insertTvShowService.addTitleByLetter(title);
        insertTvShowService.addCapitulos(title);
        return searchTvShowService.getTvShow(title);
    }

    @PostMapping("/")
    public int scrap(@RequestBody TvShowRequest tvShowRequest) {
        return insertTvShowService.addTitleByLetter(tvShowRequest.getTitle());
    }

    @PostMapping("/estrenos")
    public int scrapPremieres() {
        return insertTvShowService.addPremieres();
    }

    @PostMapping("/episodes")
    public int scrapEpisodes(@RequestBody TvShowRequest tvShowRequest) {
        return insertTvShowService.addCapitulos(tvShowRequest.getTitle());
    }

    @PostMapping("/episodes/{id}")
    public int scrapEpisodes(@PathVariable Long id) {
        return insertTvShowService.addCapitulos(id);
    }

}
