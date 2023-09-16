package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Info;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.converter.SerieDAOMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.models.SerieDAO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerieConverterTest {


    @Test
    void converter_serie_to_serieDAO_ok() {
        Serie serie = new Serie();
        serie.setId(1);
        serie.setTitle("Ahsoka");
        Info info = new Info();
        info.setYear(2021);
        serie.setInfo(info);

        ModelMapper modelMapper = SerieDAOMapper.toSerieDAO();
        SerieDAO serieDAO = modelMapper.map(serie, SerieDAO.class);

        assertEquals(serie.getId(), serieDAO.getId());
        assertEquals(serie.getTitle(), serieDAO.getTitle());
        assertEquals(serie.getInfo().getYear(), serieDAO.getReleaseYear());


    }

    @Test
    void converter_serieDAO_to_serie_ok() {
        SerieDAO serieDAO = new SerieDAO();
        serieDAO.setId(1L);
        serieDAO.setTitle("Ahsoka");
        serieDAO.setReleaseYear(2021);

        ModelMapper modelMapper = SerieDAOMapper.toSerie();
        Serie serie = modelMapper.map(serieDAO, Serie.class);

        assertEquals(serieDAO.getId(), serie.getId());
        assertEquals(serieDAO.getTitle(), serie.getTitle());
        assertEquals(serieDAO.getReleaseYear(), serie.getInfo().getYear());
    }
}


