package com.davidperezmillan.cdoinfoshowservice.application.services.secondary;

import com.davidperezmillan.cdoinfoshowservice.application.converters.playmax.PlayMaxInfoMapper;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.PlayMaxAdapter;
import org.springframework.beans.factory.annotation.Autowired;

public class InfoServiceService {

    // @Autowired
    // SerieService serieService;
    private final PlayMaxAdapter playMaxAdapter;

    @Autowired
    public InfoServiceService(PlayMaxAdapter playMaxAdapter) {
        this.playMaxAdapter = playMaxAdapter;
    }

    public Serie get(int id) {
        return PlayMaxInfoMapper.toSerie(playMaxAdapter.info(id));
        // serieService.createSerie(SerieMapper.toSerieEntityRequest(serie));
        // return serie;
    }
}
