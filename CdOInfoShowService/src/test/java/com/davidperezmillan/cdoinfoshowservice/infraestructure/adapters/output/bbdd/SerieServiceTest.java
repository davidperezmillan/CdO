package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities.SerieEntity;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories.SerieRepository;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.request.SerieEntityRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.response.SerieEntityResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SerieServiceTest {

    @Mock
    private SerieRepository serieRepository;

    @Autowired
    SerieService serieService;

    public SerieServiceTest() {
        serieService = new SerieService();
    }

    @Test
    void createSerie() {
        SerieEntity serieEntity = new SerieEntity();
        serieEntity.setId(1L);
        serieEntity.setTitle("Ahsoka");
        serieEntity.setReleaseYear(2021);
        when(serieRepository.save(any(SerieEntity.class))).thenReturn(serieEntity);

        SerieEntityRequest serieEntityRequest = new SerieEntityRequest();
        serieEntityRequest.setId(1L);
        serieEntityRequest.setTitle("Ahsoka");
        serieEntityRequest.setReleaseYear(2021);

        SerieEntityResponse serieEntityResponse = serieService.createSerie(serieEntityRequest);

        assertEquals(serieEntityRequest.getId(), serieEntityResponse.getId());
        assertEquals(serieEntityRequest.getTitle(), serieEntityResponse.getTitle());
        assertEquals(serieEntityRequest.getReleaseYear(), serieEntityResponse.getReleaseYear());

    }

}