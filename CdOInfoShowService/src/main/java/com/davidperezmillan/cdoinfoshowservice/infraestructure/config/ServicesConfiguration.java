package com.davidperezmillan.cdoinfoshowservice.infraestructure.config;

import com.davidperezmillan.cdoinfoshowservice.application.services.primary.KeepAliveService;
import com.davidperezmillan.cdoinfoshowservice.application.services.primary.SearchInfoSerieService;
import com.davidperezmillan.cdoinfoshowservice.application.services.secondary.SearchSeriesService;
import com.davidperezmillan.cdoinfoshowservice.application.usecases.KeepAliveUseCase;
import com.davidperezmillan.cdoinfoshowservice.application.usecases.SearchInfoUseCase;
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
        return new SearchSeriesService();
    }

    @Bean
    public SearchInfoUseCase searchInfoUseCase(SearchSeriesService searchSeriesService) {
        return new SearchInfoSerieService(new SearchSeriesService());
    }
}
