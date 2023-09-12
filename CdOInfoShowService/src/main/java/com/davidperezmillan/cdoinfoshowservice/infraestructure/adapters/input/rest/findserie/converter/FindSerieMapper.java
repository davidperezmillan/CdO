package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.converter;

import com.davidperezmillan.cdoinfoshowservice.domain.model.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.request.FindSeriesRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.FindSeriesResponse;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.Item;

public interface FindSerieMapper {

    Serie map(FindSeriesRequest source, Class<Serie> destinationType);

    Item map(Serie source, Class<Item> destinationType);

    FindSeriesResponse mapList(Serie[] source, Class<FindSeriesResponse> destinationType);
}
