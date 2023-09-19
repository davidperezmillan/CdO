package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.converter.SerieEntityMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities.SerieEntity;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SerieService {
    @Autowired
    SerieRepository serieRepository;

    // Puedes agregar más métodos según sea necesario
    public Serie createSerie(Serie serie) {
        return SerieEntityMapper.toSerie().map(
                serieRepository.save(SerieEntityMapper.toSerieEntity().map(serie, SerieEntity.class)), Serie.class);
    }

    public Serie getSerieById(int id) {
        return SerieEntityMapper.toSerieEntity().map(serieRepository.findById((long) id).orElse(null), Serie.class);
    }

}
