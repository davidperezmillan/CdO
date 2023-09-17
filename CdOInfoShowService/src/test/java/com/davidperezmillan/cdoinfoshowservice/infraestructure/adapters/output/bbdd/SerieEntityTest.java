package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities.SerieEntity;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories.SerieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class SerieEntityTest {

    @Autowired
    private SerieRepository serieRepository;

    @Test
    public void testSerieEntity() {
        // Crear una instancia de SerieEntity y guardarla en la base de datos
        SerieEntity serie = new SerieEntity();
        serie.setTitle("Mi Serie");
        serie.setReleaseYear(2022);
        serieRepository.save(serie);

        // Consultar la SerieEntity desde la base de datos
        SerieEntity serieConsultada = serieRepository.findById(serie.getId()).orElse(null);

        // Verificar que la SerieEntity se ha guardado correctamente
        assertThat(serieConsultada).isNotNull();
        assertThat(serieConsultada.getTitle()).isEqualTo("Mi Serie");
        assertThat(serieConsultada.getReleaseYear()).isEqualTo(2022);
    }
}
