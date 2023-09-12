package com.davidperezmillan.cdoinfoshowservice.application.services.primary;

import com.davidperezmillan.cdoinfoshowservice.application.services.secondary.SearchSeriesService;
import com.davidperezmillan.cdoinfoshowservice.application.usecases.SearchInfoUseCase;
import com.davidperezmillan.cdoinfoshowservice.domain.model.Serie;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;

@Service
public class SearchInfoSerieService implements SearchInfoUseCase {

    private final SearchSeriesService searchSeriesService;

    public SearchInfoSerieService(SearchSeriesService searchSeriesService) {
        this.searchSeriesService = searchSeriesService;
    }

    @Override
    public Serie[] search(Serie search) {
        return searchSeriesService.search(search);
/*        return Arrays.stream(searchSeriesService.search(search))
                .sorted(Comparator.comparingInt(Serie::getYear).reversed()).toArray(Serie[]::new);*/

    }
}
