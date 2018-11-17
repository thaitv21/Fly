package com.esp.fly.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Conservation {

    private String friendId;
    private List<Message> messages;

    public Conservation(String friendId) {
        this.friendId = friendId;
        messages = new ArrayList<>();
        //send friendId to request message list
        //parse data
    }

    public Conservation(String friendId, List<Message> messages) {
        this.friendId = friendId;
        this.messages = messages;
    }

    public Conservation(JSONObject conservation) {
        messages = new ArrayList<>();
        try {
            this.friendId = conservation.getString("friend_id");
            JSONArray msgs = conservation.getJSONArray("messages");
            for (int i = 0; i < msgs.length(); i++) {
                messages.add(new Message(msgs.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getFriendId() {
        return friendId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Message getLastMessage() {
        //send request to server
        //receive response
        //get last message
        return null;
    }
}
