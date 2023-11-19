package com.davidperezmillan.cdovideostoreservice.infrastructure.slack;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Log4j2
public class SlackComponent {
    @Value("${slack.bot.url}")
    private String slackWebhookUrl;
    @Value("${slack.bot.username}")
    private String username;
    @Value("${slack.bot.icon}")
    private String icon_emoji;
    @Value("${slack.bot.channel}")
    private String channel;

    public Object sendMessageNative(SlackMessage message) {
        try {
            log.info("Enviando mensaje a slack: " + message);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<SlackMessage> request = new HttpEntity<>(message, headers);
            return restTemplate.postForObject(slackWebhookUrl, request, String.class);
        } catch (HttpClientErrorException e) {
            log.error("Error enviando mensaje a slack: " + e.getMessage());
            return e.getMessage();
        }

    }

    public SlackMessage createMessage(String message) {
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setText(message);
        slackMessage.setUsername(username);
        slackMessage.setIcon_emoji(icon_emoji);
        slackMessage.setChannel(channel);
        return slackMessage;
    }

}