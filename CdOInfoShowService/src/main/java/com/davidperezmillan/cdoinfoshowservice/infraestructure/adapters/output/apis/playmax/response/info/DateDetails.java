package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DateDetails {

    private int unix;
    private String text;
    private Boolean reached;
    private String hour;
    private String nowDiff;

}
