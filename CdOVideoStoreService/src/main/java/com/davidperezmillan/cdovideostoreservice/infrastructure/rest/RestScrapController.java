package com.davidperezmillan.cdovideostoreservice.infrastructure.rest;

import com.davidperezmillan.cdovideostoreservice.application.services.InsertTvShowService;
import com.davidperezmillan.cdovideostoreservice.application.services.SearchTvShowService;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.PageResponse;
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

    @CrossOrigin(origins = { "http://localhost", "http://192.168.1.195" })
    @GetMapping("/")
    public PageResponse getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return searchTvShowService.getAll(page, size);
    }

    @CrossOrigin(origins = { "http://localhost", "http://192.168.1.195" })
    @GetMapping("/{title}")
    public PageResponse getTvShow(@PathVariable String title, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return searchTvShowService.getTvShow(title, page, size);
    }

    @GetMapping("/all/{title}")
    public PageResponse all(@PathVariable("title") String title, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        insertTvShowService.addTitleByLetter(title);
        insertTvShowService.addCapitulos(title);
        return searchTvShowService.getTvShow(title, page, size);
    }

    @PostMapping("/")
    public List<TvShowResponse> scrap(@RequestBody TvShowRequest tvShowRequest) {
        return insertTvShowService.addTitleByLetter(tvShowRequest.getTitle());
    }

    @PostMapping("/estrenos")
    public List<TvShowResponse> scrapPremieres() {
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
