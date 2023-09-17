package com.davidperezmillan.cdoinfoshowservice.infraestructure.config;

import com.davidperezmillan.cdoinfoshowservice.application.converters.*;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.converter.FindSerieMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.converter.InfoSerieMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.converter.KeepAliveMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.converter.PlayMaxInfoMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.converter.PlayMaxSearchMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public KeepAliveMapper mapper() {
        return new KeepAliveMapperImpl();
    }

    @Bean
    public FindSerieMapper findSerieMapper() {
        return new FindSerieMapperImpl();
    }

    @Bean
    public InfoSerieMapper infoSerieMapper() {
        return new InfoSerieMapperImpl();
    }

    @Bean
    public PlayMaxSearchMapper PlayMaxSearchMapper() {
        return new PlayMaxSearchMapperImpl();
    }

    @Bean
    public PlayMaxInfoMapper PlayMaxInfoMapper() {
        return new PlayMaxInfoMapperImpl();
    }

}
