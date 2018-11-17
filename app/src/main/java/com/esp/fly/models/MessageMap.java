package com.esp.fly.models;

public class MessageMap<Friend, Message> {

    private Friend sender;
    private Message message;

    public MessageMap(Friend sender, Message message) {
        this.sender = sender;
        this.message = message;
    }

    public Friend getSender() {
        return sender;
    }

    public void setSender(Friend sender) {
        this.sender = sender;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
