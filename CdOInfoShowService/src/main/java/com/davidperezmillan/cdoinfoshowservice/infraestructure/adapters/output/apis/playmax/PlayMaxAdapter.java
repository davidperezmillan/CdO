package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax;

import com.davidperezmillan.cdoinfoshowservice.domain.model.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.converter.PlayMaxSearchMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.InfoPlayMaxResponse;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.SearchPlayMaxResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Log4j2
public class PlayMaxAdapter {

    private static final String RETURN_FORMAT = "json";
    private static final String API_KEY = "5d0249e059e1b250cc915c94";
    private static final String AUTH_KEY = "f948850f8d986d19b6bfdb8f9c32127696c22f5d";

    private final ObjectMapper objectMapper;

    private final PlayMaxSearchMapper searchInfoUseCase;

    @Autowired
    public PlayMaxAdapter(PlayMaxSearchMapper searchInfoUseCase) {
        this.searchInfoUseCase = searchInfoUseCase;
        this.objectMapper = new ObjectMapper();
    }

    public Serie[] search(String search) {
        try {
            String url = String.format("https://playmax.mx/api/%s/get/search/v1/%s/%s/?query=%s&sessions=1",
                    RETURN_FORMAT, API_KEY, AUTH_KEY, URLEncoder.encode(search, StandardCharsets.UTF_8.toString()));
            String jsonResponse = get(url);
            return searchInfoUseCase.mapList(getSearchPlayMaxResponse(jsonResponse), Serie[].class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public InfoPlayMaxResponse info(int fichaId) {
        String url = String.format("https://playmax.mx/api/%s/get/ficha/v1.2/%s/%s/?fichaId=%s", RETURN_FORMAT, API_KEY,
                AUTH_KEY, fichaId);
        try {
            String jsonResponse = get(url);
            return getInfoPlayMaxResponse(jsonResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private InfoPlayMaxResponse getInfoPlayMaxResponse(String jsonResponse) throws JsonProcessingException {
        if (jsonResponse != null) {
            return objectMapper.readValue(jsonResponse, InfoPlayMaxResponse.class);
        }
        return null;
    }

    private SearchPlayMaxResponse getSearchPlayMaxResponse(String jsonResponse) throws JsonProcessingException {
        if (jsonResponse != null) {
            return objectMapper.readValue(jsonResponse, SearchPlayMaxResponse.class);
        }
        return null;
    }

    private String get(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }

        return null;
    }

}
