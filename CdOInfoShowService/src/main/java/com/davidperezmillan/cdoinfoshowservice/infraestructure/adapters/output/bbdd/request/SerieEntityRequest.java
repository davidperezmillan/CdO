package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.request;

import lombok.Data;

@Data
public class SerieEntityRequest {
    private Long id;
    private String title;
    private int releaseYear;
    private String sinopsis;
}
