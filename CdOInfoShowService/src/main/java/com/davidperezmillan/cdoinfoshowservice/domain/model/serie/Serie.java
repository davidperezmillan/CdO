package com.davidperezmillan.cdoinfoshowservice.domain.model.serie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Serie {

    private int id;
    private String title;
    private String poster;
    private Boolean isSerie;

    private Info info;
}
