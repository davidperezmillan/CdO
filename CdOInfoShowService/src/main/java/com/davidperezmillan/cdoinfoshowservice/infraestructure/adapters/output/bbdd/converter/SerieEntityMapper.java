package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.converter;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities.SerieEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class SerieEntityMapper {

    public static ModelMapper toSerieEntity() {
        ModelMapper modelMapper = new ModelMapper();

        // Configura un mapeo personalizado para la clase PersonaDTO
        modelMapper.addMappings(new PropertyMap<Serie, SerieEntity>() {
            @Override
            protected void configure() {
                map().setId((long) source.getId());
                map().setReleaseYear(source.getInfo().getYear());
                map().setSinopsis(source.getInfo().getSinopsis());
            }
        });

        return modelMapper;
    }

    public static ModelMapper toSerie() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<SerieEntity, Serie>() {
            @Override
            protected void configure() {
                map().setId(source.getId().intValue());
                map().getInfo().setYear(source.getReleaseYear());
                map().getInfo().setSinopsis(source.getSinopsis());
            }
        });
        return modelMapper;

    }
}
