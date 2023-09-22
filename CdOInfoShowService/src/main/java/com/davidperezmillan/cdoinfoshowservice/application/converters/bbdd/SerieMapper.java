package com.davidperezmillan.cdoinfoshowservice.application.converters.bbdd;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.request.SerieEntityRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.response.SerieEntityResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class SerieMapper {

    public static SerieEntityRequest toSerieEntityRequest(Serie serie) {
        ModelMapper modelMapper = new ModelMapper();

        // Configura un mapeo personalizado para la clase PersonaDTO
        modelMapper.addMappings(new PropertyMap<Serie, SerieEntityRequest>() {
            @Override
            protected void configure() {
                map().setId((long) source.getId());
                map().setReleaseYear(source.getInfo().getYear());
                map().setSinopsis(source.getInfo().getSynopsis());
                map().setSerie(source.getIsSerie());
            }
        });

        return modelMapper.map(serie, SerieEntityRequest.class);
    }

    public static Serie toSerie(SerieEntityResponse serieEntityResponse) {
        ModelMapper modelMapper = new ModelMapper();

        // Configura un mapeo personalizado para la clase PersonaDTO
        modelMapper.addMappings(new PropertyMap<SerieEntityResponse, Serie>() {
            @Override
            protected void configure() {
                map().setId(source.getId().intValue());
                map().getInfo().setYear(source.getReleaseYear());
                map().getInfo().setSynopsis(source.getSinopsis());
                map().setIsSerie(source.isSerie());
            }
        });

        return modelMapper.map(serieEntityResponse, Serie.class);
    }
}
