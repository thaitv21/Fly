package com.esp.fly.events;

import com.esp.fly.utils.ApplicationConstant;

public class ChangeColorEvent{

    private int color;

    public ChangeColorEvent(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
