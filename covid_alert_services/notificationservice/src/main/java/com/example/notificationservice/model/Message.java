package com.example.notificationservice.model;

public class Message {

    private String messageContent;

    public Message() {}

    public Message(String message) {
        this.messageContent = message;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
