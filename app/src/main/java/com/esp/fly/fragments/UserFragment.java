package com.esp.fly.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.esp.fly.R;
import com.esp.fly.events.UpdateProfileEvent;
import com.esp.fly.helpers.ImageHelper;
import com.esp.fly.models.User;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends Fragment implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {


    private static final int PICK_AVATAR = 1;
    private static final int PICK_COVER = 2;
    private View rootView;
    private View changePhotoView;
    private Button saveButton;

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText descriptionEditText;

    private CircleImageView avatarView;
    private ImageView coverView;

    private User user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user, container, false);
        user = User.getInstance();
        initView();
        initData();
        return rootView;
    }

    private void initData() {
        user = User.getInstance();
        if (user != null) {
            if (user.getAvatarUrl() != null) {
                Glide.with(getContext()).load(user.getAvatarUrl()).into(avatarView);
            }
            if (user.getCoverUrl() != null) {
                Glide.with(getContext()).load(user.getCoverUrl()).into(coverView);
            }
            if (user.getName() != null) {
                nameEditText.setText(user.getName());
                nameEditText.setSelection(nameEditText.getText().toString().length());
            }
            if (user.getDescription() != null && !user.getDescription().equals("")) {
                descriptionEditText.setText(user.getDescription());
                descriptionEditText.setSelection(descriptionEditText.getText().toString().length());
            } else {
                descriptionEditText.setText("");
                descriptionEditText.setHint("No Description");
            }
            if (user.getPhone() == null || user.getPhone().equals("")) {
                phoneEditText.setHint("No phone");
            } else {
                phoneEditText.setText(user.getPhone());
                phoneEditText.setSelection(phoneEditText.getText().toString().length());
            }
        }
    }

    private void initView() {
        avatarView = rootView.findViewById(R.id.user_avatar);
        coverView = rootView.findViewById(R.id.user_cover);
        changePhotoView = rootView.findViewById(R.id.user_change_photos);
        saveButton = rootView.findViewById(R.id.user_save_edit);
        nameEditText = rootView.findViewById(R.id.user_change_name);
        phoneEditText = rootView.findViewById(R.id.user_change_phone);
        descriptionEditText = rootView.findViewById(R.id.user_change_description);
        changePhotoView.setOnClickListener(this);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == changePhotoView) {
            PopupMenu popupMenu = new PopupMenu(getContext(), changePhotoView);
            popupMenu.getMenuInflater().inflate(R.menu.change_photos, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        } else if (view == saveButton) {
            nameEditText.setFocusable(false);
            phoneEditText.setFocusable(false);
            descriptionEditText.setFocusable(false);
            nameEditText.setFocusableInTouchMode(true);
            phoneEditText.setFocusableInTouchMode(true);
            descriptionEditText.setFocusableInTouchMode(true);
            nameEditText.clearFocus();
            phoneEditText.clearFocus();
            descriptionEditText.clearFocus();

            UpdateProfileEvent.Builder builder = new UpdateProfileEvent.Builder();
            if (user != null) {
                user.setName(nameEditText.getText().toString());
                user.setPhone(phoneEditText.getText().toString());
                user.setDescription(descriptionEditText.getText().toString());
                builder.setUserId(user.getId())
                        .setUserName(user.getName())
                        .setMale(user.isMale())
                        .setUserAvatar(user.getCover())
                        .setUserCover(user.getCover())
                        .setDescription(user.getDescription());

            }
            EventBus.getDefault().post(builder.build());
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.menu_avatar) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_AVATAR);
        } else if (item.getItemId() == R.id.menu_cover) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_COVER);
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_AVATAR) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    avatarView.setImageBitmap(bitmap);
                    if (user != null) {
                        user.setAvatar(ImageHelper.encodeBitmapToByteArray(bitmap));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == PICK_COVER) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    coverView.setImageBitmap(bitmap);
                    if (user != null) {
                        user.setCover(ImageHelper.encodeBitmapToByteArray(bitmap));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
