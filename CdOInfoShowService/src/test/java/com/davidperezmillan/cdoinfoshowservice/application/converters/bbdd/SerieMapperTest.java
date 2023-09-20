package com.davidperezmillan.cdoinfoshowservice.application.converters.bbdd;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Info;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.request.SerieEntityRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.response.SerieEntityResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerieMapperTest {

    @Test
    void toSerieEntity() {
        Serie serie = new Serie();
        serie.setId(1);
        serie.setTitle("Ahsoka");
        Info info = new Info();
        info.setYear(2021);
        serie.setInfo(info);

        SerieEntityRequest serieEntity = SerieMapper.toSerieEntityRequest(serie);

        assertEquals(serie.getId(), serieEntity.getId());
        assertEquals(serie.getTitle(), serieEntity.getTitle());
        assertEquals(serie.getInfo().getYear(), serieEntity.getReleaseYear());
    }

    @Test
    void toSerie() {
        SerieEntityResponse serieEntityResponse = new SerieEntityResponse();
        serieEntityResponse.setId(1L);
        serieEntityResponse.setTitle("Ahsoka");
        serieEntityResponse.setReleaseYear(2021);

        Serie serie = SerieMapper.toSerie(serieEntityResponse);

        assertEquals(serieEntityResponse.getId().intValue(), serie.getId());
        assertEquals(serieEntityResponse.getTitle(), serie.getTitle());
        assertEquals(serieEntityResponse.getReleaseYear(), serie.getInfo().getYear());

    }
}