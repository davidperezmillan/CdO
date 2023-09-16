package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.converter;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.models.SerieDAO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class SerieDAOMapper {

    public static ModelMapper toSerieDAO() {
        ModelMapper modelMapper = new ModelMapper();

        // Configura un mapeo personalizado para la clase PersonaDTO
        modelMapper.addMappings(new PropertyMap<Serie, SerieDAO>() {
            @Override
            protected void configure() {
                map().setReleaseYear(source.getInfo().getYear());
            }
        });

        return modelMapper;
    }

    public static ModelMapper toSerie(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<SerieDAO, Serie>() {
            @Override
            protected void configure() {
                map().getInfo().setYear(source.getReleaseYear());
            }
        });
        return modelMapper;

    }
}
