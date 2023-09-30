package com.repill.was.global.slack;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class SlackService {

    public void sendSlackMessage(String payload, SlackApiClient slackApiClient) {
        slackApiClient.sendErrorMessage(payload);
    }
}
