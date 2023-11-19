package com.davidperezmillan.cdovideostoreservice.infrastructure.slack;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SlackComponentTest {


    @Test
    void sendMessage() {
        var message = "Test Unitario realizado :heart: :smile:";
        var response = SlackService.sendMessage(message);
        assertEquals("ok", response);
    }




}
