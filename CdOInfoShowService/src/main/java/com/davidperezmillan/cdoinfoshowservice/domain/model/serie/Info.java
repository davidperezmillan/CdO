package com.davidperezmillan.cdoinfoshowservice.domain.model.serie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Info {

    private String sinopsis;
    private int year;
    private double rating;
}
