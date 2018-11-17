package com.esp.fly.models;

import java.util.List;

public class Group {

    private String groupId;
    private String groupImageUrl;
    private String groupName;
    private List<Friend> friendList;
    private MessageMap<Friend, Message> messages;

    public Group(String groupId) {
        //receive response from server
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupImageUrl() {
        return groupImageUrl;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Friend> getFriendList() {
        return friendList;
    }

    public MessageMap<Friend, Message> getMessages() {
        return messages;
    }
}
