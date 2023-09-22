package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.response;

import lombok.Data;

@Data
public class SerieEntityResponse {

    private Long id;
    private String title;
    private int releaseYear;
    private String sinopsis;
    private boolean serie;
}
