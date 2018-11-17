package com.esp.fly.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.widget.Toast;

import com.esp.fly.events.ActiveNotificationEvent;
import com.esp.fly.events.ChangeColorEvent;
import com.esp.fly.events.ActiveSoundEvent;
import com.esp.fly.events.UpdateProfileEvent;
import com.esp.fly.helpers.ImageHelper;
import com.esp.fly.helpers.JsonHelper;
import com.esp.fly.models.User;
import com.esp.fly.utils.ApplicationConstant;
import com.esp.fly.utils.FileUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ApplicationService extends Service {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public ApplicationService() {
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    @Subscribe
    public void getSharePreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    @Subscribe
    public void onUpdateProfileEvent(UpdateProfileEvent event) {
        Context context = getApplicationContext();
        if (context != null && event != null) {
            String json = JsonHelper.createUserJson(event.getUserId(), event.getUserName(), event.isMale(),
                    encodeByteArrayToString(event.getUserAvatar()),
                    encodeByteArrayToString(event.getUserCover()), event.getDescription());
            FileUtils.saveUserInfo(context, json);
        }
        //upload data => server response new avatarUrl and coverUrl => update instance of User
//        User.getInstance().setAvatarUrl();
//        User.getInstance().setCoverUrl();
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
    }

    private String encodeByteArrayToString(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        } else {
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
    }

    @Subscribe
    public void onChangeColorEvent(ChangeColorEvent event) {
        sharedPreferences = getApplicationContext().getSharedPreferences(ApplicationConstant.NAME_SHARE_PREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt(ApplicationConstant.COLOR, event.getColor());
        editor.commit();
    }

    @Subscribe
    public void onActiveSound(ActiveSoundEvent event) {
        sharedPreferences = getApplicationContext().getSharedPreferences(ApplicationConstant.NAME_SHARE_PREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean(ApplicationConstant.SOUND, event.isActive());
        editor.commit();
        Toast.makeText(this, "Setting sound changed", Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onActiveNotification(ActiveNotificationEvent event) {
        sharedPreferences = getApplicationContext().getSharedPreferences(ApplicationConstant.NAME_SHARE_PREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean(ApplicationConstant.NOTIFICATION, event.isActive());
        editor.commit();
        Toast.makeText(this, "Setting notification changed", Toast.LENGTH_SHORT).show();
    }
}
