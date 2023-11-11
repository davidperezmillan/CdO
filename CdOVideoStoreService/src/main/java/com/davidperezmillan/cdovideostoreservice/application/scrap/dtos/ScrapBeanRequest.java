package com.davidperezmillan.cdovideostoreservice.application.scrap.dtos;

import lombok.Data;

@Data
public class ScrapBeanRequest {

    private String title;
    private int session;
    private int episode;
    private CalidadEnum quality;

}
