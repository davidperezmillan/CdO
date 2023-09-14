package com.davidperezmillan.cdoinfoshowservice.application.converters;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Info;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.converter.PlayMaxInfoMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.InfoPlayMaxResponse;

public class PlayMaxInfoMapperImpl implements PlayMaxInfoMapper {
    @Override
    public Serie map(InfoPlayMaxResponse source, Class<Serie> destinationType) {
        Serie s = new Serie();
        s.setTitle(source.getResult().getInfo().getTitle());
        s.setId(source.getResult().getInfo().getId());
        s.setPoster(source.getResult().getInfo().getPoster());
        s.setIsSerie(source.getResult().getInfo().getIsSerie());

        Info info = new Info();
        info.setYear(source.getResult().getInfo().getYear());
        info.setRating(source.getResult().getInfo().getRating());
        info.setSinopsis(source.getResult().getInfo().getSinopsis());
        s.setInfo(info);
        return s;
    }

    @Override
    public Serie mapList(InfoPlayMaxResponse source, Class<Serie> destinationType) {
        return null;
    }
}
