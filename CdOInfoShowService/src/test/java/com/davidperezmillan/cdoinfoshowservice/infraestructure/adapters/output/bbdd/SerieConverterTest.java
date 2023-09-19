package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Info;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.application.converters.SerieEntityMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities.SerieEntity;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerieConverterTest {

    @Test
    void converter_serie_to_SerieEntity_ok() {
        Serie serie = new Serie();
        serie.setId(1);
        serie.setTitle("Ahsoka");
        Info info = new Info();
        info.setYear(2021);
        info.setSynopsis("Ejemplo de sinopsis");
        serie.setInfo(info);

        ModelMapper modelMapper = SerieEntityMapper.toSerieEntity();
        SerieEntity serieEntity = modelMapper.map(serie, SerieEntity.class);

        assertEquals(serie.getId(), serieEntity.getId());
        assertEquals(serie.getTitle(), serieEntity.getTitle());
        assertEquals(serie.getInfo().getYear(), serieEntity.getReleaseYear());
        assertEquals(serie.getInfo().getSynopsis(), serieEntity.getSinopsis());

    }

    @Test
    void converter_SerieEntity_to_serie_ok() {
        SerieEntity serieEntity = new SerieEntity();
        serieEntity.setId(1L);
        serieEntity.setTitle("Ahsoka");
        serieEntity.setReleaseYear(2021);
        serieEntity.setSinopsis("Ejemplo de sinopsis");

        ModelMapper modelMapper = SerieEntityMapper.toSerie();
        Serie serie = modelMapper.map(serieEntity, Serie.class);

        assertEquals(serieEntity.getId(), serie.getId());
        assertEquals(serieEntity.getTitle(), serie.getTitle());
        assertEquals(serieEntity.getReleaseYear(), serie.getInfo().getYear());
        assertEquals(serieEntity.getSinopsis(), serie.getInfo().getSynopsis());
    }
}
