package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Info {
    private int id;
    private String title;
    private String originalTitle;
    private String poster;
    private String posterColor;
    private String cover;
    private int rating;
    private int votes;
    private Boolean isSerie;
    private String type;
    private String status;
    private int seasons;
    private int episodes;
    private int year;
    private int duration;
    private String durationText;
    private String country;
    private int countryId;
    @JsonIgnore
    private List<String> airDates;
    private List<ExternalLink> externalLinks;
    private List<CrewGroup> crew;
    private List<Genre> genres;
    private List<Topic> topics;
    private String sinopsis;
}
