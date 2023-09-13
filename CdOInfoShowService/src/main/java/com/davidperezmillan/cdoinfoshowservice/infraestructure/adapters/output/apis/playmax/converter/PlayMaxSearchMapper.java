package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.converter;

import com.davidperezmillan.cdoinfoshowservice.domain.model.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.FichaSearchItem;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.SearchPlayMaxResponse;

public interface PlayMaxSearchMapper {

    Serie map(FichaSearchItem source, Class<Serie> destinationType);

    SearchPlayMaxResponse map(Serie source, Class<SearchPlayMaxResponse> destinationType);

    Serie[] mapList(SearchPlayMaxResponse source, Class<Serie[]> destinationType);
}
