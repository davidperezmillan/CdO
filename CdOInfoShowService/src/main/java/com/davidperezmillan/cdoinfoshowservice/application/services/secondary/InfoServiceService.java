package com.davidperezmillan.cdoinfoshowservice.application.services.secondary;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.PlayMaxAdapter;

public class InfoServiceService {

    private final PlayMaxAdapter playMaxAdapter;

    public InfoServiceService(PlayMaxAdapter playMaxAdapter) {
        this.playMaxAdapter = playMaxAdapter;
    }

    public Serie get(int id) {
        return playMaxAdapter.info(id);
    }
}
