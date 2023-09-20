package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities.SerieEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SerieRepositoryTest {

    @Autowired
    SerieRepository serieRepository;

    @BeforeEach
    public void setUp() {
        // Agregar datos de prueba antes de cada prueba si es necesario
        SerieEntity serie1 = new SerieEntity();
        serie1.setId(1L);
        serie1.setTitle("Serie 1");
        serieRepository.save(serie1);

        SerieEntity serie2 = new SerieEntity();
        serie2.setId(2L);
        serie2.setTitle("Serie 2");
        serieRepository.save(serie2);
    }

    @Test
    public void testFindAll() {
        List<SerieEntity> series = serieRepository.findAll();
        assertEquals(2, series.size());
    }

    @Test
    public void testFindById() {
        Optional<SerieEntity> optionalSerie = serieRepository.findById(1L);
        assertTrue(optionalSerie.isPresent());
        assertEquals("Serie 1", optionalSerie.get().getTitle());
    }

    @Test
    public void testSave() {
        SerieEntity serie3 = new SerieEntity();
        serie3.setId(3L);
        serie3.setTitle("Serie 3");
        SerieEntity savedSerie = serieRepository.save(serie3);

        Optional<SerieEntity> optionalSerie = serieRepository.findById(savedSerie.getId());
        assertTrue(optionalSerie.isPresent());
        assertEquals("Serie 3", optionalSerie.get().getTitle());
    }

    @Test
    public void testDeleteById() {
        serieRepository.deleteById(1L);
        Optional<SerieEntity> optionalSerie = serieRepository.findById(1L);
        assertTrue(optionalSerie.isEmpty());
    }

    // Puedes agregar más pruebas según sea necesario para tu repositorio
}
