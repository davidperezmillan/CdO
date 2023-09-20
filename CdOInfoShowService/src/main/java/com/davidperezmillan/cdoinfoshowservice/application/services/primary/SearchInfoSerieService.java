package com.davidperezmillan.cdoinfoshowservice.application.services.primary;

import com.davidperezmillan.cdoinfoshowservice.application.services.secondary.SearchSeriesService;
import com.davidperezmillan.cdoinfoshowservice.application.usecases.SearchInfoUseCase;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SearchInfoSerieService implements SearchInfoUseCase {

    private final SearchSeriesService searchSeriesService;

    public SearchInfoSerieService(SearchSeriesService searchSeriesService) {
        this.searchSeriesService = searchSeriesService;
    }

    @Override
    public Serie[] search(String search, boolean isSerie) {
        Serie serie = new Serie();
        serie.setTitle(search);
        serie.setIsSerie(isSerie);
        return searchSeriesService.search(serie);
        /*
         * // filter only isSerie true return Arrays.stream(searchSeriesService.search(search))
         * .filter(Serie::getIsSerie) .toArray(Serie[]::new);
         */
    }
}
