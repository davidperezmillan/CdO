package com.davidperezmillan.cdoinfoshowservice.application.converters.playmax;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Info;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.InfoPlayMaxResponse;
import org.modelmapper.ModelMapper;

public interface PlayMaxInfoMapper {

    static Serie toSerie(InfoPlayMaxResponse source) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(
                com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.Info.class,
                Serie.class).addMapping(src -> src.getTitle(), Serie::setTitle)
                .addMapping(src -> src.getId(), Serie::setId).addMapping(src -> src.getPoster(), Serie::setPoster)
                .addMapping(src -> src.getIsSerie(), Serie::setIsSerie);

        modelMapper.createTypeMap(
                com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.Info.class,
                Info.class).addMappings(mapper -> mapper.map(src -> src.getYear(), Info::setYear))
                .addMappings(mapper -> mapper.map(src -> src.getRating(), Info::setRating))
                .addMappings(mapper -> mapper.map(src -> src.getSinopsis(), Info::setSynopsis));

        com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.Info infoSource = source
                .getResult().getInfo();
        Serie serie = modelMapper.map(infoSource, Serie.class);
        Info info = modelMapper.map(infoSource, Info.class);
        serie.setInfo(info);
        return serie;
    }

}
