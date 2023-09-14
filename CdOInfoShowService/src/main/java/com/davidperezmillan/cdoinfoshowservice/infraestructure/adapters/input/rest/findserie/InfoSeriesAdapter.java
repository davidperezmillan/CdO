package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie;

import com.davidperezmillan.cdoinfoshowservice.application.usecases.InfoSerieUseCase;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.converter.InfoSerieMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.InfoSeriesResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/info")
public class InfoSeriesAdapter {

    private final InfoSerieUseCase infoSerieUseCase;
    private final InfoSerieMapper mapper;

    @Autowired
    public InfoSeriesAdapter(InfoSerieUseCase infoSerieUseCase, InfoSerieMapper mapper) {
        this.infoSerieUseCase = infoSerieUseCase;
        this.mapper = mapper;
    }

    @CrossOrigin(origins = "http://localhost")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfoSeriesResponse> infoSeries(@PathVariable int id) {
        log.info("InfoSeries.infoSeries: " + id);
        return new ResponseEntity<>(mapper.map(infoSerieUseCase.get(id), InfoSeriesResponse.class), HttpStatus.OK);
    }
}
