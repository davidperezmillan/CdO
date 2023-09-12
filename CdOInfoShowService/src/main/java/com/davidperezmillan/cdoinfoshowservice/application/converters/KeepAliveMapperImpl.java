package com.davidperezmillan.cdoinfoshowservice.application.converters;

import com.davidperezmillan.cdoinfoshowservice.domain.model.Live;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.converter.KeepAliveMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.request.KeepAliveRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.response.KeepAliveResponse;

public class KeepAliveMapperImpl implements KeepAliveMapper {

    @Override
    public Live map(KeepAliveRequest source, Class<Live> destinationType) {
        Live live = new Live();
        live.setName(source.getName());
        return live;

    }

    @Override
    public KeepAliveResponse map(Live source, Class<KeepAliveResponse> destinationType) {
        KeepAliveResponse keepAliveResponse = new KeepAliveResponse("Estoy vivo, " + source.getName() + "!!!");
        return keepAliveResponse;
    }
}
