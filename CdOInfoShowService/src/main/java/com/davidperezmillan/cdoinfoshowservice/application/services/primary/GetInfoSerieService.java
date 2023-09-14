package com.davidperezmillan.cdoinfoshowservice.application.services.primary;

import com.davidperezmillan.cdoinfoshowservice.application.services.secondary.InfoServiceService;
import com.davidperezmillan.cdoinfoshowservice.application.usecases.InfoSerieUseCase;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import org.springframework.stereotype.Service;

@Service
public class GetInfoSerieService implements InfoSerieUseCase {

    private final InfoServiceService infoServiceService;

    public GetInfoSerieService(InfoServiceService infoServiceService) {
        this.infoServiceService = infoServiceService;
    }

    @Override
    public Serie get(int id) {
        return infoServiceService.get(id);
    }
}
