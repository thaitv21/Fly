package com.esp.fly.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DeviceUtils {

    public static float convertDpToPixel(Context context, float dp){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

}
