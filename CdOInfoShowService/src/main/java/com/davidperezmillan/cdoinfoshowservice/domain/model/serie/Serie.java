package com.davidperezmillan.cdoinfoshowservice.domain.model.serie;

import lombok.*;

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
