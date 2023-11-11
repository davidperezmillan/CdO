package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.converters.SerieEntityMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories.SerieRepository;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.request.SerieEntityRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.response.SerieEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerieService {
    @Autowired
    SerieRepository serieRepository;

    // Puedes agregar más métodos según sea necesario
    public SerieEntityResponse createSerie(SerieEntityRequest serie) {
        return SerieEntityMapper.toSeriesEntityResponse(serieRepository.save(SerieEntityMapper.toSerieEntity(serie)));

    }

    public SerieEntityResponse getSerieById(int id) {
        return SerieEntityMapper.toSeriesEntityResponse(serieRepository.findById((long) id).orElse(null));
    }

}
