package com.esp.fly.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.swipe.SwipeLayout;
import com.esp.fly.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ColorDialog extends Dialog implements View.OnClickListener {

    private SwipeLayout mainLayout;
    private SwipeLayout titleLayout;
    private SwipeLayout textLayout;

    private View mainColor;
    private View titleColor;
    private View textColor;

    private CircleImageView mainBlue, mainRed, mainPink, mainGreen, mainViolet;
    private CircleImageView titleRed, titleBlack, titleWhite, titleYellow, titleGreen;
    private CircleImageView textBlack, textWhite, textGreen, textPink, textViolet;

    private CircleImageView mainColorView;
    private CircleImageView titleColorView;
    private CircleImageView textColorView;

    private Button cancel, confirm;

    private ColorDialogInterface dialogInterface;

    private int[] main;
    private int[] title;
    private int[] text;
    private int mainIndex = 0;
    private int titleIndex = 0;
    private int textIndex = 0;

    public interface ColorDialogInterface {
        void onConfirm(ColorDialog dialog, int mainColor, int titleColor, int textColor);

        void onCancel(ColorDialog dialog);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ColorDialog(@NonNull Context context) {
        super(context);
        main = new int[]{context.getColor(R.color.colorBlue), context.getColor(R.color.colorRed), context.getColor(R.color.colorPink),
                context.getColor(R.color.colorGreen), context.getColor(R.color.colorViolet)};
        title = new int[] {context.getColor(R.color.colorRed), context.getColor(R.color.colorBlack),
                context.getColor(R.color.colorWhite), context.getColor(R.color.colorYellow), context.getColor(R.color.colorGreen)};
        text = new int[] {context.getColor(R.color.colorBlack), context.getColor(R.color.colorWhite), context.getColor(R.color.colorGreen),
                context.getColor(R.color.colorPink), context.getColor(R.color.colorViolet)};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pick_color);
        initView();
    }

    public void setDialogInterface(ColorDialogInterface dialogInterface) {
        this.dialogInterface = dialogInterface;
    }

    private void initView() {

        mainLayout = findViewById(R.id.main_layout);
        titleLayout = findViewById(R.id.title_layout);
        textLayout = findViewById(R.id.text_layout);

        mainColor = findViewById(R.id.main_color);
        titleColor = findViewById(R.id.title_color);
        textColor = findViewById(R.id.text_color);

        mainBlue = findViewById(R.id.main_color_blue);
        mainRed = findViewById(R.id.main_color_red);
        mainPink = findViewById(R.id.main_color_pink);
        mainGreen = findViewById(R.id.main_color_green);
        mainViolet = findViewById(R.id.main_color_violet);

        titleBlack = findViewById(R.id.title_color_black);
        titleGreen = findViewById(R.id.title_color_green);
        titleRed = findViewById(R.id.title_color_red);
        titleWhite = findViewById(R.id.title_color_white);
        titleYellow = findViewById(R.id.title_color_yellow);

        textBlack = findViewById(R.id.text_color_black);
        textGreen = findViewById(R.id.text_color_green);
        textPink = findViewById(R.id.text_color_pink);
        textViolet = findViewById(R.id.text_color_violet);
        textWhite = findViewById(R.id.text_color_white);

        mainColorView = findViewById(R.id.main_color_view);
        titleColorView = findViewById(R.id.title_color_view);
        textColorView = findViewById(R.id.text_color_view);

        cancel = findViewById(R.id.cancel_button);
        confirm = findViewById(R.id.confirm_button);

        mainLayout.setOnClickListener(this);
        titleLayout.setOnClickListener(this);
        textLayout.setOnClickListener(this);
        mainColor.setOnClickListener(this);
        titleColor.setOnClickListener(this);
        textColor.setOnClickListener(this);
        mainBlue.setOnClickListener(this);
        mainViolet.setOnClickListener(this);
        mainGreen.setOnClickListener(this);
        mainRed.setOnClickListener(this);
        mainPink.setOnClickListener(this);
        titleBlack.setOnClickListener(this);
        titleGreen.setOnClickListener(this);
        titleRed.setOnClickListener(this);
        titleYellow.setOnClickListener(this);
        titleWhite.setOnClickListener(this);
        textBlack.setOnClickListener(this);
        textGreen.setOnClickListener(this);
        textPink.setOnClickListener(this);
        textViolet.setOnClickListener(this);
        textWhite.setOnClickListener(this);

        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mainColor) {
            mainLayout.open(true);
            titleLayout.close(true);
            textLayout.close(true);
        } else if (view == titleColor) {
            titleLayout.open(true);
            mainLayout.close(true);
            textLayout.close(true);
        } else if (view == textColor) {
            textLayout.open(true);
            mainLayout.close(true);
            titleLayout.close(true);
        } else if (view == cancel) {
            if (dialogInterface != null) {
                dialogInterface.onCancel(this);
            }
        } else if (view == confirm) {
            if (dialogInterface != null) {
                dialogInterface.onConfirm(this, main[mainIndex], title[titleIndex], text[textIndex]);
            }
        } else {
            if (view == mainBlue) {
                mainIndex = 0;
            } else if (view == mainRed) {
                mainIndex = 1;
            } else if (view == mainPink) {
                mainIndex = 2;
            } else if (view == mainGreen) {
                mainIndex = 3;
            } else if (view == mainViolet) {
                mainIndex = 4;
            } else if (view == titleRed) {
                titleIndex = 0;
            } else if (view == titleBlack) {
                titleIndex = 1;
            } else if (view == titleWhite) {
                titleIndex = 2;
            } else if (view == titleYellow) {
                titleIndex = 3;
            } else if (view == titleGreen) {
                titleIndex = 4;
            } else if (view == textBlack) {
                textIndex = 0;
            } else if (view == textWhite) {
                textIndex = 1;
            } else if (view == textGreen) {
                textIndex = 2;
            } else if (view == textPink) {
                textIndex = 3;
            } else if (view == textViolet) {
                textIndex = 4;
            }
            mainColorView.setColorFilter(main[mainIndex]);
            titleColorView.setColorFilter(title[titleIndex]);
            textColorView.setColorFilter(text[textIndex]);
            mainLayout.close(true);
            titleLayout.close(true);
            textLayout.close(true);
        }
    }
}
