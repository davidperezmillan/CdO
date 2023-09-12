package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.converter;

import com.davidperezmillan.cdoinfoshowservice.domain.model.Live;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.request.KeepAliveRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.response.KeepAliveResponse;

public interface KeepAliveMapper {

    Live map(KeepAliveRequest source, Class<Live> destinationType);

    KeepAliveResponse map(Live source, Class<KeepAliveResponse> destinationType);
}
