package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Info;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities.SerieEntity;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories.SerieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SerieServiceTest {

    @Mock
    private SerieRepository serieRepository;

    @Autowired
    SerieService serieService;

    @Test
    public void testCreateSerie() {
        // Configurar el comportamiento del mock
        SerieEntity SerieEntity = new SerieEntity();
        SerieEntity.setTitle("Example Title");
        SerieEntity.setId(1L);
        SerieEntity.setReleaseYear(2021);
        when(serieRepository.save(SerieEntity)).thenReturn(SerieEntity);

        Serie serie = new Serie();
        serie.setTitle(SerieEntity.getTitle());
        serie.setId(1);
        Info info = new Info();
        info.setYear(2021);
        serie.setInfo(info);

        // Llamar al método que queremos probar
        Serie serieSalved = serieService.createSerie(serie);

        assertEquals(SerieEntity.getTitle(), serieSalved.getTitle());
        assertEquals(SerieEntity.getId(), serieSalved.getId());
        assertEquals(SerieEntity.getReleaseYear(), serieSalved.getInfo().getYear());
    }

    @Test
    public void testGetSerieById() {
        // Configurar el comportamiento del mock
        SerieEntity SerieEntity = new SerieEntity();
        SerieEntity.setId(1L);
        SerieEntity.setTitle("Example Title");

        when(serieRepository.findById(1L)).thenReturn(java.util.Optional.of(SerieEntity));

        // Llamar al método que queremos probar
        Serie resultSerie = serieService.getSerieById(1);

        // Verificar el resultado
        assertEquals(SerieEntity.getTitle(), resultSerie.getTitle());
    }

}
