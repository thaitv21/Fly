package com.esp.fly.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Friend {

    private String id;
    private String name;
    private String avatarUrl;
    private String coverUrl;
    private boolean isMale;
    private String description;

    public Friend(String id) {
        //get data from server
    }

    public Friend(String id, String name, String avatarUrl, String coverUrl, boolean isMale, String description) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.coverUrl = coverUrl;
        this.isMale = isMale;
        this.description = description;
    }

    public Friend(JSONObject friend) {
        try {
            this.id = friend.getString("id");
            this.name = friend.getString("name");
            this.avatarUrl = friend.getString("avatar");
            this.coverUrl = friend.getString("cover");
            this.isMale = friend.getBoolean("sex");
            this.description = friend.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public boolean isMale() {
        return isMale;
    }

    public String getDescription() {
        return description;
    }
}
