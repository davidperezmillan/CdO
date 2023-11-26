package com.davidperezmillan.cdovideostoreservice.infrastructure.rest;

import com.davidperezmillan.cdovideostoreservice.application.services.SpecialService;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.PageResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/find")
public class FindSpecialController {

    SpecialService specialService;

    @Autowired
    public FindSpecialController(SpecialService specialService) {
        this.specialService = specialService;
    }

    @PostMapping("/")
    public PageResponse scrap(@RequestBody TvShowRequest tvShowRequest) {
        return specialService.find(tvShowRequest, 0, 10);
    }

}
