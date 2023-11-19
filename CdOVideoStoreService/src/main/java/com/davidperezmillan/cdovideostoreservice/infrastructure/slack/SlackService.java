package com.davidperezmillan.cdovideostoreservice.infrastructure.slack;

import org.springframework.stereotype.Service;

@Service
public class SlackService {

    private static SlackComponent slackComponent;

    public SlackService(SlackComponent slackComponent) {
        SlackService.slackComponent = slackComponent;
    }

    public static Object sendMessage(String message) {
        SlackMessage slackMessage = slackComponent.createMessage(message);
        return slackComponent.sendMessageNative(slackMessage);
    }
}
