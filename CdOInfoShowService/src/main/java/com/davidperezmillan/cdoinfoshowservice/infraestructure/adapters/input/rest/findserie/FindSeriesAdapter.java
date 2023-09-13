package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie;

import com.davidperezmillan.cdoinfoshowservice.application.usecases.SearchInfoUseCase;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.converter.FindSerieMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.FindSeriesResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/series")
public class FindSeriesAdapter {

    private final SearchInfoUseCase searchInfoUseCase;
    private final FindSerieMapper mapper;

    @Autowired
    public FindSeriesAdapter(SearchInfoUseCase searchInfoUseCase, FindSerieMapper mapper) {
        this.searchInfoUseCase = searchInfoUseCase;
        this.mapper = mapper;
    }
    

    @CrossOrigin(origins = "http://localhost")
    @GetMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FindSeriesResponse> findSeries(@RequestParam String search) {
        log.info("FindSeries.findSeries: " + search);
        return new ResponseEntity<FindSeriesResponse>(mapper.mapList(searchInfoUseCase.search(search), FindSeriesResponse.class), HttpStatus.OK);


    }
}
