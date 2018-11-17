package com.esp.fly.events;

import com.esp.fly.utils.ApplicationConstant;

public class ActiveNotificationEvent {

    private boolean isActive;

    public ActiveNotificationEvent(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

}
