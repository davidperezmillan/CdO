package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;


import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.models.SerieDAO;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories.SerieRepository;
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
    private SerieRepository serieRepository;

    @BeforeEach
    public void setUp() {
        // Agregar datos de prueba antes de cada prueba si es necesario
        SerieDAO serie1 = new SerieDAO();
        serie1.setTitle("Serie 1");
        serieRepository.save(serie1);

        SerieDAO serie2 = new SerieDAO();
        serie2.setTitle("Serie 2");
        serieRepository.save(serie2);
    }

    @Test
    public void testFindAll() {
        List<SerieDAO> series = serieRepository.findAll();
        assertEquals(2, series.size());
    }

    @Test
    public void testFindById() {
        Optional<SerieDAO> optionalSerie = serieRepository.findById(1L);
        assertTrue(optionalSerie.isPresent());
        assertEquals("Serie 1", optionalSerie.get().getTitle());
    }

    @Test
    public void testSave() {
        SerieDAO serie3 = new SerieDAO();
        serie3.setTitle("Serie 3");
        SerieDAO savedSerie = serieRepository.save(serie3);

        Optional<SerieDAO> optionalSerie = serieRepository.findById(savedSerie.getId());
        assertTrue(optionalSerie.isPresent());
        assertEquals("Serie 3", optionalSerie.get().getTitle());
    }

    @Test
    public void testDeleteById() {
        serieRepository.deleteById(1L);
        Optional<SerieDAO> optionalSerie = serieRepository.findById(1L);
        assertTrue(optionalSerie.isEmpty());
    }

    // Puedes agregar más pruebas según sea necesario para tu repositorio
}

