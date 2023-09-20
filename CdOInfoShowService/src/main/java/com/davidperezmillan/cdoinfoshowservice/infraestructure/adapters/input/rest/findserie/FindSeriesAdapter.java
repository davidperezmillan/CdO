package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie;

import com.davidperezmillan.cdoinfoshowservice.application.usecases.SearchInfoUseCase;
import com.davidperezmillan.cdoinfoshowservice.application.converters.rest.FindSeriesMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.FindSeriesResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/series")
public class FindSeriesAdapter {

    private final SearchInfoUseCase searchInfoUseCase;

    @Autowired
    public FindSeriesAdapter(SearchInfoUseCase searchInfoUseCase) {
        this.searchInfoUseCase = searchInfoUseCase;
    }

    @CrossOrigin(origins = "http://localhost")
    @GetMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FindSeriesResponse> findSeries(@RequestParam String search,
            @RequestParam(required = false) boolean isSerie) {
        log.info("FindSeries.findSeries: " + search);
        return Optional.ofNullable(searchInfoUseCase.search(search, isSerie))
                .map(series -> new ResponseEntity<>(FindSeriesMapper.mapToFindSeriesResponse(series), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
