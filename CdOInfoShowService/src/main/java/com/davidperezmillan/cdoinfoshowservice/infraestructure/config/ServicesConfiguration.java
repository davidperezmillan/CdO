package com.davidperezmillan.cdoinfoshowservice.infraestructure.config;

import com.davidperezmillan.cdoinfoshowservice.application.converters.PlayMaxSearchMapperImpl;
import com.davidperezmillan.cdoinfoshowservice.application.services.primary.KeepAliveService;
import com.davidperezmillan.cdoinfoshowservice.application.services.primary.SearchInfoSerieService;
import com.davidperezmillan.cdoinfoshowservice.application.services.secondary.SearchSeriesService;
import com.davidperezmillan.cdoinfoshowservice.application.usecases.KeepAliveUseCase;
import com.davidperezmillan.cdoinfoshowservice.application.usecases.SearchInfoUseCase;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.PlayMaxAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Bean
    public KeepAliveUseCase keepAliveUseCase() {
        return new KeepAliveService();
    }

    @Bean
    public SearchSeriesService searchSeriesService() {
        return new SearchSeriesService(new PlayMaxAdapter(new PlayMaxSearchMapperImpl()));
    }

    @Bean
    public SearchInfoUseCase searchInfoUseCase(SearchSeriesService searchSeriesService) {
        return new SearchInfoSerieService(new SearchSeriesService(new PlayMaxAdapter(new PlayMaxSearchMapperImpl())));
    }
}
