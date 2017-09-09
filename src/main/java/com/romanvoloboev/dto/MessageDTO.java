package com.romanvoloboev.dto;

/**
 * @author romanvoloboev
 */
public class MessageDTO {
    private long messageId;
    private String userName;
    private String text;

    public MessageDTO(String text) {
        this.text = text;
    }

    public MessageDTO(String userName, String text) {
        this.userName = userName;
        this.text = text;
    }

    public MessageDTO(long messageId, String userName, String text) {
        this.messageId = messageId;
        this.userName = userName;
        this.text = text;
    }

    public long getMessageId() {
        return messageId;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }
}
