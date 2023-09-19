package com.davidperezmillan.cdoinfoshowservice.application.converters.rest;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.InfoSeriesResponse;
import org.modelmapper.ModelMapper;

public interface InfoSerieMapper {

    static InfoSeriesResponse mapToInfoSeriesResponse(Serie serie) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Serie.class, InfoSeriesResponse.class)
                .addMappings(mapper -> mapper.map(src -> src.getInfo().getSynopsis(), InfoSeriesResponse::setSinopsis))
                .addMappings(mapper -> mapper.map(src -> src.getInfo().getYear(), InfoSeriesResponse::setYear))
                .addMappings(mapper -> mapper.map(src -> src.getInfo().getRating(), InfoSeriesResponse::setRating));

        return modelMapper.map(serie, InfoSeriesResponse.class);
    }

}
