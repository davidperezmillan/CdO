package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie;

import com.davidperezmillan.cdoinfoshowservice.application.converters.rest.InfoSerieMapper;
import com.davidperezmillan.cdoinfoshowservice.application.usecases.InfoSerieUseCase;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.InfoSeriesResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/info")
public class InfoSeriesAdapter {

    private final InfoSerieUseCase infoSerieUseCase;

    @Autowired
    public InfoSeriesAdapter(InfoSerieUseCase infoSerieUseCase) {
        this.infoSerieUseCase = infoSerieUseCase;
    }

    @CrossOrigin(origins = "http://localhost")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfoSeriesResponse> infoSeries(@PathVariable int id) {
        log.info("InfoSeries.infoSeries: " + id);
        return Optional.ofNullable(infoSerieUseCase.get(id))
                .map(searchResponse -> new ResponseEntity<>(InfoSerieMapper.mapToInfoSeriesResponse(searchResponse),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
