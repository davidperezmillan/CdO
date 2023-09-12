package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestsPlayMax {
    private int rateLimit;
    private int blockExpiresIn;
}
