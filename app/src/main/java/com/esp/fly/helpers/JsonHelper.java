package com.esp.fly.helpers;


import com.esp.fly.utils.ApplicationConstant;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {

    public static String createUserJson(String userId, String userName, Boolean isMale, String avatar, String cover, String description) {
        boolean isNull = true;
        JSONObject jsonObject = new JSONObject();
        try {
            if (userId != null) {
                jsonObject.put(ApplicationConstant.USER_ID, userId);
                isNull = false;
            }
            if (userName != null) {
                jsonObject.put(ApplicationConstant.USER_NAME, userName);
                isNull = false;
            }
            if (isMale != null) {
                jsonObject.put(ApplicationConstant.USER_MALE, isMale);
                isNull = false;
            }
            if (avatar != null) {
                jsonObject.put(ApplicationConstant.USER_AVATAR, avatar);
                isNull = false;
            }
            if (cover != null) {
                jsonObject.put(ApplicationConstant.USER_COVER, cover);
                isNull = false;
            }
            if (description != null) {
                jsonObject.put(ApplicationConstant.USER_DESCRIPTION, description);
                isNull = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isNull) {
            return null;
        } else {
            return jsonObject.toString();
        }
    }
}
