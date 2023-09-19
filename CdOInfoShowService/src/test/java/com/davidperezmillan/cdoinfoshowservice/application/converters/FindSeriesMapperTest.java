package com.davidperezmillan.cdoinfoshowservice.application.converters;

import com.davidperezmillan.cdoinfoshowservice.application.converters.rest.FindSeriesMapper;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.request.FindSeriesRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.FindSeriesResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindSeriesMapperTest {

    @Test
    public void testToSerie() {
        // Arrange
        FindSeriesRequest request = new FindSeriesRequest();
        request.setTitle("Nombre de prueba");

        // Act
        Serie serie = FindSeriesMapper.toSerieFind(request);

        // Assert
        assertEquals(request.getTitle(), serie.getTitle());
    }

    @Test
    public void testToFindSeriesResponse() {
        // Arrange
        Serie[] series = new Serie[2];
        series[0] = new Serie();
        series[0].setId(1);
        series[0].setTitle("Serie 1");
        series[0].setPoster("Poster 1");
        // Agrega más series aquí según sea necesario

        // Act
        FindSeriesResponse response = FindSeriesMapper.mapToFindSeriesResponse(series);

        // Assert
        assertEquals(series.length, response.getItems().length);
        // Agrega más aserciones aquí según sea necesario
    }

}
