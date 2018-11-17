package com.esp.fly.events;

import android.support.v7.app.AlertDialog;

public class UpdateProfileEvent {

    private String userId;
    private String userName;
    private byte[] userAvatar;
    private byte[] userCover;
    private boolean isMale;
    private String description;

    public UpdateProfileEvent(Builder builder) {
        userId = builder.userId;
        userName = builder.userName;
        userAvatar = builder.userAvatar;
        userCover = builder.userCover;
        isMale = builder.isMale;
        description = builder.description;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public byte[] getUserCover() {
        return userCover;
    }

    public boolean isMale() {
        return isMale;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {
        private String userId;
        private String userName;
        private byte[] userAvatar;
        private byte[] userCover;
        private boolean isMale;
        private String description;

        public Builder() {
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setUserAvatar(byte[] userAvatar) {
            this.userAvatar = userAvatar;
            return this;
        }

        public Builder setUserCover(byte[] userCover) {
            this.userCover = userCover;
            return this;
        }

        public Builder setMale(boolean male) {
            isMale = male;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public UpdateProfileEvent build() {
            return new UpdateProfileEvent(this);
        }
    }
}
