package com.davidperezmillan.cdoinfoshowservice.application.services.secondary;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.PlayMaxAdapter;

public class SearchSeriesService {

    private final PlayMaxAdapter playMaxAdapter;

    public SearchSeriesService(PlayMaxAdapter playMaxAdapter) {
        this.playMaxAdapter = playMaxAdapter;
    }

    public Serie[] search(Serie search) {
        return playMaxAdapter.search(search.getTitle());
    }
}
