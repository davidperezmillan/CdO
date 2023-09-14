package com.davidperezmillan.cdoinfoshowservice.application.converters;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Info;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.converter.InfoSerieMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.InfoSeriesResponse;

public class InfoSerieMapperImpl implements InfoSerieMapper {
    @Override
    public Serie map(InfoSeriesResponse source, Class<Serie> destinationType) {
        Serie serie = new Serie();
        serie.setId(source.getId());
        serie.setTitle(source.getTitle());
        serie.setPoster(source.getPoster());

        Info info = new Info();
        serie.setInfo(info);
        info.setSinopsis(source.getSinopsis());
        info.setYear(source.getYear());
        info.setRating(source.getRating());
        return serie;
    }

    @Override
    public InfoSeriesResponse map(Serie source, Class<InfoSeriesResponse> destinationType) {
        InfoSeriesResponse infoSeriesResponse = new InfoSeriesResponse();
        infoSeriesResponse.setId(source.getId());
        infoSeriesResponse.setTitle(source.getTitle());
        infoSeriesResponse.setPoster(source.getPoster());
        infoSeriesResponse.setSinopsis(source.getInfo().getSinopsis());
        infoSeriesResponse.setYear(source.getInfo().getYear());
        infoSeriesResponse.setRating(source.getInfo().getRating());

        return infoSeriesResponse;
    }
}
