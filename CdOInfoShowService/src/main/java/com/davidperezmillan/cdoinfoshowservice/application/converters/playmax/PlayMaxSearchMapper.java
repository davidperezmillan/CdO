package com.davidperezmillan.cdoinfoshowservice.application.converters.playmax;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Info;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.FichaSearchItem;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.SearchPlayMaxResponse;
import org.modelmapper.ModelMapper;

public interface PlayMaxSearchMapper {

    /*
     * static Serie toSerie(FichaSearchItem source) { ModelMapper modelMapper = new ModelMapper();
     *
     * modelMapper.createTypeMap(FichaSearchItem.class, Item.class) .addMappings(mapper -> mapper.map(src ->
     * src.getYear(), Item::setYear)) .addMappings(mapper -> mapper.map(src -> src.getRating(), Item::setRating));
     *
     * return modelMapper.map(source, Serie.class); }
     */

    static Serie[] mapList(SearchPlayMaxResponse searchPlayMaxResponse) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(FichaSearchItem.class, Serie.class)
                .addMappings(mapper -> mapper.map(src -> src.getId(), Serie::setId))
                .addMappings(mapper -> mapper.map(src -> src.getTitle(), Serie::setTitle))
                .addMappings(mapper -> mapper.map(src -> src.getPoster(), Serie::setPoster))
                .addMappings(mapper -> mapper.map(src -> src.getIsSerie(), Serie::setIsSerie));

        modelMapper.createTypeMap(FichaSearchItem.class, Info.class)
                .addMappings(mapper -> mapper.map(src -> src.getYear(), Info::setYear))
                .addMappings(mapper -> mapper.map(src -> src.getRating(), Info::setRating));

        Serie[] series = new Serie[searchPlayMaxResponse.getResult().getFicha().getFichas().size()];
        for (FichaSearchItem ficha : searchPlayMaxResponse.getResult().getFicha().getFichas()) {
            Serie serie = modelMapper.map(ficha, Serie.class);
            Info info = modelMapper.map(ficha, Info.class);
            serie.setInfo(info);
            series[searchPlayMaxResponse.getResult().getFicha().getFichas().indexOf(ficha)] = serie;
        }

        return series;

    }

}
