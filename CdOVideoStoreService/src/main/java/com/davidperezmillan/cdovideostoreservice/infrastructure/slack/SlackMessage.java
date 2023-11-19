package com.davidperezmillan.cdovideostoreservice.infrastructure.slack;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SlackMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String text;
    private String icon_emoji;
    private String channel;

}