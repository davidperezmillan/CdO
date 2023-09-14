package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.converter;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.InfoSeriesResponse;

public interface InfoSerieMapper {

    Serie map(InfoSeriesResponse source, Class<Serie> destinationType);

    InfoSeriesResponse map(Serie source, Class<InfoSeriesResponse> destinationType);
}
