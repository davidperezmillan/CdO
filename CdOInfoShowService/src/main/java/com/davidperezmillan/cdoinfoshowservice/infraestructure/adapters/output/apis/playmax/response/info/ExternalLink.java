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
public class ExternalLink {
    private String name;
    private String externalId;
    private String externalIdExtra;
    private String link;
}
