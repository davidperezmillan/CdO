package com.davidperezmillan.cdoinfoshowservice.application.converters;

import com.davidperezmillan.cdoinfoshowservice.domain.model.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.converter.PlayMaxSearchMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.FichaSearchItem;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.SearchPlayMaxResponse;

public class PlayMaxSearchMapperImpl implements PlayMaxSearchMapper {
    @Override
    public Serie map(FichaSearchItem source, Class<Serie> destinationType) {
        Serie s = new Serie();
        s.setTitle(source.getTitle());
        s.setId(source.getId());
        s.setPoster(source.getPoster());
        s.setIsSerie(source.getIsSerie());
        s.setYear(source.getYear());
        s.setRating(source.getRating());
        return s;
    }

    @Override
    public Serie[] mapList(SearchPlayMaxResponse source, Class<Serie[]> destinationType) {
        Serie[] listSearch = new Serie[source.getResult().getFicha().getFichas().size()];
        for (int i = 0; i < source.getResult().getFicha().getFichas().size(); i++) {
            Serie item = map(source.getResult().getFicha().getFichas().get(i), Serie.class);
            listSearch[i] = item;
        }
        return listSearch;
    }

    @Override
    public SearchPlayMaxResponse map(Serie source, Class<SearchPlayMaxResponse> destinationType) {
        return null;
    }
}
