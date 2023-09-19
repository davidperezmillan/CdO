package com.davidperezmillan.cdoinfoshowservice.application.converters.rest;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.request.FindSeriesRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.FindSeriesResponse;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.Item;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;

public interface FindSeriesMapper {

    static Serie toSerieFind(FindSeriesRequest source) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<FindSeriesRequest, Serie>() {
            @Override
            protected void configure() {
                map().setTitle(source.getTitle());
            }
        });

        return modelMapper.map(source, Serie.class);
    }

    static FindSeriesResponse mapToFindSeriesResponse(Serie[] series) {
        ModelMapper modelMapper = new ModelMapper();

        // Configura el mapeo de Serie a Item
        modelMapper.createTypeMap(Serie.class, Item.class).addMapping(src -> src.getInfo().getYear(), Item::setYear)
                .addMapping(src -> src.getInfo().getRating(), Item::setRating);

        // Mapea los objetos Serie a objetos Item
        Item[] items = modelMapper.map(series, new TypeToken<Item[]>() {
        }.getType());

        // Crea un objeto FindSeriesResponse y asigna los items
        FindSeriesResponse response = new FindSeriesResponse();
        response.setItems(items);

        return response;
    }

}
