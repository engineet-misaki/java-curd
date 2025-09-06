package com.store_sample.store.domain.messages.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {

    private String id;

    private int channelId;

    private String text;

    private String username;

    private LocalDateTime timestamp;
}
