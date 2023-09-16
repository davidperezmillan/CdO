package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;


import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.models.SerieDAO;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories.SerieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class SerieDAOTest {

    @Autowired
    private SerieRepository serieRepository;

    @Test
    public void testSerieDAO() {
        // Crear una instancia de SerieDAO y guardarla en la base de datos
        SerieDAO serie = new SerieDAO();
        serie.setTitle("Mi Serie");
        serie.setReleaseYear(2022);
        serieRepository.save(serie);

        // Consultar la SerieDAO desde la base de datos
        SerieDAO serieConsultada = serieRepository.findById(serie.getId()).orElse(null);

        // Verificar que la SerieDAO se ha guardado correctamente
        assertThat(serieConsultada).isNotNull();
        assertThat(serieConsultada.getTitle()).isEqualTo("Mi Serie");
        assertThat(serieConsultada.getReleaseYear()).isEqualTo(2022);
    }
}
