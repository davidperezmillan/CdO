package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.converter;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.InfoPlayMaxResponse;

public interface PlayMaxInfoMapper {

    Serie map(InfoPlayMaxResponse source, Class<Serie> destinationType);

    Serie mapList(InfoPlayMaxResponse source, Class<Serie> destinationType);
}
