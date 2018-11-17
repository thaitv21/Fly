package com.esp.fly.events;

import com.esp.fly.utils.ApplicationConstant;

public class ActiveSoundEvent {

    private boolean isActive;

    public ActiveSoundEvent(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }
}
