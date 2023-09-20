package com.davidperezmillan.cdoinfoshowservice.application.services.secondary;

import com.davidperezmillan.cdoinfoshowservice.application.converters.playmax.PlayMaxSearchMapper;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.PlayMaxAdapter;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

@Log4j2
public class SearchSeriesService {

    private final PlayMaxAdapter playMaxAdapter;

    public SearchSeriesService(PlayMaxAdapter playMaxAdapter) {
        this.playMaxAdapter = playMaxAdapter;
    }

    public Serie[] search(Serie search) {
        if (search.getIsSerie() != null) {
            log.info("searching for series by title: {} by filter {}", search.getTitle(), search.getIsSerie());
            return Arrays.stream(PlayMaxSearchMapper.mapList(playMaxAdapter.search(search.getTitle())))
                    .filter(source -> source.getIsSerie().equals(search.getIsSerie())).toArray(Serie[]::new);
        }
        return PlayMaxSearchMapper.mapList(playMaxAdapter.search(search.getTitle()));
    }
}
