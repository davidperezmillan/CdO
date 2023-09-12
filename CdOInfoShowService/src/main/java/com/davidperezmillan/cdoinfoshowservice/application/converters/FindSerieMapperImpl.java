package com.davidperezmillan.cdoinfoshowservice.application.converters;

import com.davidperezmillan.cdoinfoshowservice.domain.model.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.converter.FindSerieMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.request.FindSeriesRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.FindSeriesResponse;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.Item;

public class FindSerieMapperImpl implements FindSerieMapper {

    @Override
    public Serie map(FindSeriesRequest source, Class<Serie> destinationType) {
        Serie serie = new Serie();
        serie.setTitle(source.getTitle());
        return serie;
    }

    @Override
    public Item map(Serie source, Class<Item> destinationType) {
        Item response = new Item();
        response.setId(source.getId());
        response.setTitle(source.getTitle());
        response.setPoster(source.getPoster());
        response.setSerie(source.getIsSerie());
        response.setYear(source.getYear());
        response.setRating(source.getRating());
        return response;
    }

    @Override
    public FindSeriesResponse mapList(Serie[] source, Class<FindSeriesResponse> destinationType) {
        FindSeriesResponse response = new FindSeriesResponse();
        response.setItems(new Item[source.length]);
        Item[] series = response.getItems();
        for (int i = 0; i < source.length; i++) {
            response.getItems()[i] = map(source[i], Item.class);
        }
        return response;
    }
}
