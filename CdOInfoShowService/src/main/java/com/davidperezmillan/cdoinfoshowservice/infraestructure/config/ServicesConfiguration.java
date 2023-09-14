package com.davidperezmillan.cdoinfoshowservice.infraestructure.config;

import com.davidperezmillan.cdoinfoshowservice.application.converters.PlayMaxInfoMapperImpl;
import com.davidperezmillan.cdoinfoshowservice.application.converters.PlayMaxSearchMapperImpl;
import com.davidperezmillan.cdoinfoshowservice.application.services.primary.GetInfoSerieService;
import com.davidperezmillan.cdoinfoshowservice.application.services.primary.KeepAliveService;
import com.davidperezmillan.cdoinfoshowservice.application.services.primary.SearchInfoSerieService;
import com.davidperezmillan.cdoinfoshowservice.application.services.secondary.InfoServiceService;
import com.davidperezmillan.cdoinfoshowservice.application.services.secondary.SearchSeriesService;
import com.davidperezmillan.cdoinfoshowservice.application.usecases.InfoSerieUseCase;
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
        return new SearchSeriesService(new PlayMaxAdapter(new PlayMaxSearchMapperImpl(), new PlayMaxInfoMapperImpl()));
    }

    @Bean
    public SearchInfoUseCase searchInfoUseCase(SearchSeriesService searchSeriesService) {
        return new SearchInfoSerieService(searchSeriesService());
    }

    @Bean
    public InfoServiceService infoServiceService() {
        return new InfoServiceService(new PlayMaxAdapter(new PlayMaxSearchMapperImpl(), new PlayMaxInfoMapperImpl()));
    }

    @Bean
    public InfoSerieUseCase infoSerieUseCase(GetInfoSerieService getInfoSerieService) {
        return new GetInfoSerieService(infoServiceService());
    }
}
