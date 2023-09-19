package com.davidperezmillan.cdoinfoshowservice.application.converters.rest;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Info;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.InfoSeriesResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InfoSerieMapperTest {

    @Test
    void mapToInfoSeriesResponse() {
        Serie serie = new Serie();
        serie.setId(1);
        serie.setTitle("Serie 1");
        serie.setPoster("Poster 1");
        Info info = new Info();
        info.setSynopsis("Sinopsis 1");
        info.setYear(2020);
        info.setRating(5.0);
        serie.setInfo(info);

        InfoSeriesResponse response = InfoSerieMapper.mapToInfoSeriesResponse(serie);

        assertEquals(serie.getId(), response.getId());
        assertEquals(serie.getTitle(), response.getTitle());
        assertEquals(serie.getPoster(), response.getPoster());
        assertEquals(serie.getInfo().getSynopsis(), response.getSinopsis());
        assertEquals(serie.getInfo().getYear(), response.getYear());
        assertEquals(serie.getInfo().getRating(), response.getRating());

    }
}