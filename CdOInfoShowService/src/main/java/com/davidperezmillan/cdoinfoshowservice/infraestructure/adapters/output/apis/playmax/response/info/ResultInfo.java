package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResultInfo {

    @JsonProperty("user")
    private UserInfo user;
    @JsonProperty("info")
    private Info info;
    @JsonProperty("tvBroadcasts")
    @JsonIgnore
    private List<String> tvBroadcasts;
    @JsonProperty("episodes")
    private List<Episode> episodes;
}
