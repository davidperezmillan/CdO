package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive;

import com.davidperezmillan.cdoinfoshowservice.application.usecases.KeepAliveUseCase;
import com.davidperezmillan.cdoinfoshowservice.domain.model.Live;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.converter.KeepAliveMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.request.KeepAliveRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.response.KeepAliveResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/keepalive")
public class KeepAliveAdapter {

    private final KeepAliveUseCase keepAliveUseCase;
    private final KeepAliveMapper mapper;

    @Autowired
    public KeepAliveAdapter(KeepAliveUseCase keepAliveUseCase, KeepAliveMapper mapper) {
        this.keepAliveUseCase = keepAliveUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<String> keepAlive() {
        String response = keepAliveUseCase.getKeepAlive();
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost")
    @PostMapping(value = "saludo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KeepAliveResponse> keepAlivePost(@RequestBody KeepAliveRequest keepAliveRequest) {
        log.info("KeepAliveAdapter.keepAlivePost: " + keepAliveRequest.toString());
        Live live = mapper.map(keepAliveRequest, Live.class);
        keepAliveUseCase.saludo(live);
        KeepAliveResponse keepAliveResponse = mapper.map(live, KeepAliveResponse.class);
        return new ResponseEntity<KeepAliveResponse>(keepAliveResponse, HttpStatus.OK);

    }
}
