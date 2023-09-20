package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.converters;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities.SerieEntity;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.request.SerieEntityRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.response.SerieEntityResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerieEntityMapperTest {

    @Test
    void toSerieEntity() {
        SerieEntityRequest serieEntityRequest = new SerieEntityRequest();
        serieEntityRequest.setId(1L);
        serieEntityRequest.setTitle("Ahsoka");
        serieEntityRequest.setReleaseYear(2021);

        SerieEntity serieEntity = SerieEntityMapper.toSerieEntity(serieEntityRequest);

        assertEquals(serieEntityRequest.getId(), serieEntity.getId());
        assertEquals(serieEntityRequest.getTitle(), serieEntity.getTitle());
        assertEquals(serieEntityRequest.getReleaseYear(), serieEntity.getReleaseYear());

    }

    @Test
    void toSeriesEntityResponse() {
        SerieEntity serieEntity = new SerieEntity();
        serieEntity.setId(1L);
        serieEntity.setTitle("Ahsoka");
        serieEntity.setReleaseYear(2021);

        SerieEntityResponse serieEntityResponse = SerieEntityMapper.toSeriesEntityResponse(serieEntity);

        assertEquals(serieEntity.getId(), serieEntityResponse.getId());
        assertEquals(serieEntity.getTitle(), serieEntityResponse.getTitle());
        assertEquals(serieEntity.getReleaseYear(), serieEntityResponse.getReleaseYear());

    }
}