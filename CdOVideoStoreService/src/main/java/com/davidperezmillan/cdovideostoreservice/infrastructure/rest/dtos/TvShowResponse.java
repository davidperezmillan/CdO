package com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class TvShowResponse {

    private Long id;
    private String title;
    private String sinopsis;
    private String createdAt;
    private String updatedAt;
    private Map<Integer, SessionResponse> sessions = new HashMap<>();

}
