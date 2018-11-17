package com.esp.fly.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.esp.fly.R;
import com.esp.fly.activities.LoginActivity;
import com.esp.fly.dialog.ColorDialog;
import com.esp.fly.events.ActiveNotificationEvent;
import com.esp.fly.events.ActiveSoundEvent;
import com.esp.fly.events.LogoutEvent;
import com.esp.fly.models.User;
import com.esp.fly.utils.ApplicationConstant;

import org.greenrobot.eventbus.EventBus;

public class GeneralFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, ColorDialog.ColorDialogInterface {

    private View rootView;
    private View colorView;
    private View soundView;
    private View notificationView;
    private View feedbackView;
    private View inviteFriendView;
    private View rateView;

    private Switch soundASwitch;
    private Switch notificationASwitch;

    private View logoutView;

    private SharedPreferences sharedPreferences;

    private boolean isReady = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_general, container, false);
        initViews();
        initData();
        return rootView;
    }

    private void initData() {
        sharedPreferences = getContext().getSharedPreferences(ApplicationConstant.NAME_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        boolean soundActive = sharedPreferences.getBoolean(ApplicationConstant.SOUND, true);
        boolean notificationActive = sharedPreferences.getBoolean(ApplicationConstant.NOTIFICATION, true);
        soundASwitch.setChecked(soundActive);
        notificationASwitch.setChecked(notificationActive);
        isReady = true;
    }

    private void initViews() {
        colorView = rootView.findViewById(R.id.setting_color);
        soundView = rootView.findViewById(R.id.setting_sound);
        notificationView = rootView.findViewById(R.id.setting_notification);
        feedbackView = rootView.findViewById(R.id.feedback);
        inviteFriendView = rootView.findViewById(R.id.invite_friend);
        rateView = rootView.findViewById(R.id.rate);
        soundASwitch = rootView.findViewById(R.id.sound_switch);
        notificationASwitch = rootView.findViewById(R.id.notification_switch);
        colorView.setOnClickListener(this);
        soundView.setOnClickListener(this);
        notificationView.setOnClickListener(this);
        feedbackView.setOnClickListener(this);
        inviteFriendView.setOnClickListener(this);
        rateView.setOnClickListener(this);
        soundASwitch.setOnCheckedChangeListener(this);
        notificationASwitch.setOnCheckedChangeListener(this);
        logoutView = rootView.findViewById(R.id.logout);
        logoutView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == colorView) {
            ColorDialog dialog = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                dialog = new ColorDialog(getContext());
                dialog.setDialogInterface(this);
                dialog.show();
            } else {
                Toast.makeText(getContext(), "Sorry! Your phone is too old for this feature.", Toast.LENGTH_SHORT).show();
            }
        } else if (view == soundView) {
            boolean check = soundASwitch.isChecked();
            if (check) {
                soundASwitch.setChecked(false);
            } else {
                soundASwitch.setChecked(true);
            }
        } else if (view == notificationView) {
            boolean check = notificationASwitch.isChecked();
            if (check) {
                notificationASwitch.setChecked(false);
            } else {
                notificationASwitch.setChecked(true);
            }
        } else if (view == feedbackView) {
            Toast.makeText(getContext(), "Coming soon!", Toast.LENGTH_SHORT).show();
        } else if (view == inviteFriendView) {
            Toast.makeText(getContext(), "Coming soon!", Toast.LENGTH_SHORT).show();
        } else if (view == rateView) {
            Toast.makeText(getContext(), "Coming soon!", Toast.LENGTH_SHORT).show();
        } else if (view == logoutView) {
            Toast.makeText(getContext(), "Logout!", Toast.LENGTH_SHORT).show();
            User.logout();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (isReady) {
            if (compoundButton == soundASwitch) {
                EventBus.getDefault().post(new ActiveSoundEvent(soundASwitch.isChecked()));
            } else if (compoundButton == notificationASwitch) {
                EventBus.getDefault().post(new ActiveNotificationEvent(notificationASwitch.isChecked()));
            }
        }
    }

    @Override
    public void onConfirm(ColorDialog dialog, int mainColor, int titleColor, int textColor) {
        Toast.makeText(getContext(), "Your change is going to update later", Toast.LENGTH_SHORT).show();
        dialog.cancel();
    }

    @Override
    public void onCancel(ColorDialog dialog) {
        dialog.cancel();
    }
}
