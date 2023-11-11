package com.davidperezmillan.cdovideostoreservice.application.scrap.dtos;

import lombok.Data;

@Data
public class ScrapBeanResponse {

    private String name;
    private String url;
    private int session;
    private int episode;
    private CalidadEnum quality;

}