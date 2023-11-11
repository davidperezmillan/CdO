package com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SessionResponse {

    private Map<Integer, String> episodes = new HashMap<Integer, String>();
}
