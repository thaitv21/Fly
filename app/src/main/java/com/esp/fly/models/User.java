package com.esp.fly.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String name;
    private String avatarUrl;
    private String coverUrl;
    private boolean isMale;
    private String phone;
    private String description;
    private List<Conservation> conservations;
    private List<Friend> friends;
    private List<String> groupsId;

    private byte[] avatar;
    private byte[] cover;

    private static User instance;

    public static User getInstance() {
        return instance;
    }

    public static void init(String id, String name, String avatarUrl, String coverUrl, boolean isMale, String phone, String description) {
        instance = new User(id, name, avatarUrl, coverUrl, isMale, phone, description);
    }

    public static void logout() {
        instance = null;
    }

    public User(String id, String name, String avatarUrl, String coverUrl, boolean isMale, String phone, String description) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.coverUrl = coverUrl;
        this.isMale = isMale;
        this.phone = phone;
        this.description = description;
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

    public List<Conservation> getConservations() {
        return conservations;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public List<String> getGroupsId() {
        return groupsId;
    }

    public void setAvatar(String avatarUrl) {

    }

    public void setCover(String coverUrl) {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setConservations(List<Conservation> conservations) {
        this.conservations = conservations;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public void setGroupsId(List<String> groupsId) {
        this.groupsId = groupsId;
    }

    public void addFriend(Friend friend) {
        if (friends == null) {
            friends = new ArrayList<>();
        }
        friends.add(friend);
    }

    public void addConservation(Conservation conservation) {
        if (conservations == null) {
            conservations = new ArrayList<>();
        }
        conservations.add(conservation);
    }

    public void addGroup(String groupId) {
        if (groupsId == null) {
            groupsId = new ArrayList<>();
        }
        groupsId.add(groupId);
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }
}
