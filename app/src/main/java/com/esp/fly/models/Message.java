package com.esp.fly.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    private String content;
    private String time;
    private boolean fromUser;

    public Message() {

    }

    public Message(JSONObject message) {
        try {
            String content = message.getString("content");
            String time = message.getString("time");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Message(String content, String time, boolean fromUser) {
        this.content = content;
        this.time = time;
        this.fromUser = fromUser;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public boolean isFromUser() {
        return fromUser;
    }
}
