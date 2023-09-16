package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.models.SerieDAO;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories.SerieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SerieServiceTest {

    @Mock
    private SerieRepository serieRepository;

    @Test
    public void testCreateSerie() {
        // Configurar el comportamiento del mock
        SerieDAO serieDAO = new SerieDAO();
        serieDAO.setTitle("Example Title");

        when(serieRepository.save(serieDAO)).thenReturn(serieDAO);

        // Crear una instancia de SerieService con el mock
        SerieService serieService = new SerieService(serieRepository);

        Serie serie = new Serie();
        serie.setTitle(serieDAO.getTitle());
        // Llamar al método que queremos probar
        serieService.createSerie(serie);

    }

    @Test
    public void testGetSerieById() {
        // Configurar el comportamiento del mock
        SerieDAO serieDAO = new SerieDAO();
        serieDAO.setId(1L);
        serieDAO.setTitle("Example Title");

        when(serieRepository.findById(1L)).thenReturn(java.util.Optional.of(serieDAO));

        // Crear una instancia de SerieService con el mock
        SerieService serieService = new SerieService(serieRepository);

        // Llamar al método que queremos probar
        Serie resultSerie = serieService.getSerieById(1);

        // Verificar el resultado
        assertEquals(serieDAO.getTitle(), resultSerie.getTitle());
    }

}
