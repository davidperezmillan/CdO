package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.converters;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities.SerieEntity;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.request.SerieEntityRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.response.SerieEntityResponse;
import org.modelmapper.ModelMapper;

public class SerieEntityMapper {

    // request to entity
    public static SerieEntity toSerieEntity(SerieEntityRequest serieEntityRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(serieEntityRequest, SerieEntity.class);

    }

    // entity to response
    public static SerieEntityResponse toSeriesEntityResponse(SerieEntity serieEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(serieEntity, SerieEntityResponse.class);
    }
}
