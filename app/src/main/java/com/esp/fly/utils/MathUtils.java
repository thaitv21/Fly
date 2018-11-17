package com.esp.fly.utils;

import android.content.Context;
import android.content.res.Resources;
import android.icu.text.SimpleDateFormat;
import android.util.DisplayMetrics;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MathUtils {

    public static String calculateTimeAgo(String time) { //12/30/00/02/10/1997 <=> hh/mm/ss/dd/mm/yyyy
        Date currentTime = Calendar.getInstance().getTime();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date messageTime = dateFormat.parse(time);
                long diff = currentTime.getTime() - messageTime.getTime();
                return convertLongToTimeAgo(diff);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String convertLongToTimeAgo(long timeago) {
        int days = (int) (timeago / 86400000);
        int hours = (int) ((timeago - 86400000 * days) / 3600000);
        int minute = (int) ((timeago - days*86400000 - 3600000*hours) / 60000);
        String rs = "";
        if (days > 0) {
            rs += days + " ngày ";
        }
        if (hours > 0) {
            rs += hours + "giờ";
        }
        if (minute > 0) {
            rs += minute + "phút";
        }
        rs += " trước";
        return rs;
    }

    public static int getRandomInt(int range) {
        Random rd = new Random();
        return rd.nextInt(range);
    }

    public static float convertDpToPixel(Context context, float dp){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

}
