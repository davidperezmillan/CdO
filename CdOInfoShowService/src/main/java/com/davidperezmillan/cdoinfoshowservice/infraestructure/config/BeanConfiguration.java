package com.davidperezmillan.cdoinfoshowservice.infraestructure.config;

import com.davidperezmillan.cdoinfoshowservice.application.converters.PlayMaxInfoMapperImpl;
import com.davidperezmillan.cdoinfoshowservice.application.converters.PlayMaxSearchMapperImpl;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.converter.PlayMaxInfoMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.converter.PlayMaxSearchMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PlayMaxSearchMapper PlayMaxSearchMapper() {
        return new PlayMaxSearchMapperImpl();
    }

    @Bean
    public PlayMaxInfoMapper PlayMaxInfoMapper() {
        return new PlayMaxInfoMapperImpl();
    }

}
