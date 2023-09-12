package com.davidperezmillan.cdoinfoshowservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Serie {

    public Serie(String title) {
        this.title = title;
    }
    private int id;
    private String title;
    private String poster;
    private Boolean isSerie;
    private int year;
    private double rating;
}
