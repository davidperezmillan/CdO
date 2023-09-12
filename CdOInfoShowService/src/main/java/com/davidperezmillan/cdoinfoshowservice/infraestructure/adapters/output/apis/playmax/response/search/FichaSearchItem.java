package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FichaSearchItem {
    private int id;
    private String title;
    private String poster;
    private String posterColor;
    private String cover;
    private double rating;
    private int votes;
    private Boolean isSerie;
    private String type;
    private int year;
    @JsonProperty("user")
    private UserSearch user;
}