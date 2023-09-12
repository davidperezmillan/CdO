package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info;

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
public class Episode {
    private int season;
    private List<EpisodeDetail> episodesList;
}
