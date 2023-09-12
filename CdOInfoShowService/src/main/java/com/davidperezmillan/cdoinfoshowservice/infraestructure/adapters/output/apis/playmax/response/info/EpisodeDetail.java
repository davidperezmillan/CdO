package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EpisodeDetail {
    private int id;
    private int season;
    private int episode;
    private String episodeText;
    private String name;
    private DateDetails date; // Debes crear una clase Date que represente la fecha
    private int rating;
    private UserEpisodeInfo user;
    private Before before;
}
