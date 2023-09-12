package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErrorPlayMax {
    private int code;
    private int httpCode;
    private String message;

}
