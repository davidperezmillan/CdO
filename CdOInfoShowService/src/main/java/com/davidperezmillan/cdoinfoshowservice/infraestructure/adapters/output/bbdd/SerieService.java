package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.converter.SerieEntityMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities.SerieEntity;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories.SerieRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SerieService {
    @Autowired
    SerieRepository serieRepository;

    private final ModelMapper modelMapperToEntity = SerieEntityMapper.toSerieEntity();
    private final ModelMapper modelMapper = SerieEntityMapper.toSerie();

    // Puedes agregar más métodos según sea necesario
    public Serie createSerie(Serie serie) {
        return modelMapper.map(serieRepository.save(modelMapperToEntity.map(serie, SerieEntity.class)), Serie.class);
    }

    public Serie getSerieById(int id) {
        return modelMapperToEntity.map(serieRepository.findById((long) id).orElse(null), Serie.class);
    }

}
