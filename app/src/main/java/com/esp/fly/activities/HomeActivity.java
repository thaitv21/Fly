package com.esp.fly.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.esp.fly.adapters.HomePagerAdapter;
import com.esp.fly.R;
import com.esp.fly.events.LogoutEvent;
import com.esp.fly.models.User;
import com.esp.fly.services.ApplicationService;
import com.esp.fly.utils.ApplicationConstant;
import com.esp.fly.utils.MathUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import at.markushi.ui.CircleButton;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "HomeActivity";
    private Toolbar toolbar;

    private ImageView inboxIconView;
    private TextView inboxTextView;
    private ImageView contactIconView;
    private TextView contactTextView;
    private ImageView groupIconView;
    private TextView groupTextView;

    private ViewPager viewPager;
    private HomePagerAdapter pagerAdapter;
    private int previousTab;


    private CircleButton circleButton;
    private View guide;
    private TextView textGuide;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUser();
        initView();
        activeService();
        checkLogin();
    }

    private void initUser() {
//        String str =  "{\n" +
//                "  \"userid\": \"12345678\",\n" +
//                "  \"userinfo\": {\n" +
//                "    \"username\": \"ThanThai\",\n" +
//                "    \"avatar\": \"https://camo.mybb.com/e01de90be6012adc1b1701dba899491a9348ae79/687474703a2f2f7777772e6a71756572797363726970742e6e65742f696d616765732f53696d706c6573742d526573706f6e736976652d6a51756572792d496d6167652d4c69676874626f782d506c7567696e2d73696d706c652d6c69676874626f782e6a7067\",\n" +
//                "    \"sex\": true,\n" +
//                "    \"cover\": \"https://www.w3schools.com/css/img_lights.jpg\",\n" +
//                "    \"description\": \"No description\"\n" +
//                "  }\n" +
//                "}";
//        try {
//            JSONObject userJson = new JSONObject(str);
//            user = new User(userJson);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        user = User.getInstance();
    }

    private void checkLogin() {
        user = User.getInstance();
        if (user == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, ApplicationConstant.LOGIN_CODE);
        } else {

        }
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        inboxIconView = findViewById(R.id.home_icon_message);
        inboxTextView = findViewById(R.id.home_text_message);
        contactIconView = findViewById(R.id.home_icon_contact);
        contactTextView = findViewById(R.id.home_text_contact);
        groupIconView = findViewById(R.id.home_icon_group);
        groupTextView = findViewById(R.id.home_text_group);
        circleButton = findViewById(R.id.home_circle_button);
        guide = findViewById(R.id.tutorial);
        textGuide = findViewById(R.id.text_guide);
        viewPager = findViewById(R.id.home_view_pager);
        pagerAdapter = new HomePagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
        previousTab = 0;
    }

    private void activeService() {
        Intent intent = new Intent(this, ApplicationService.class);
        startService(intent);

    }

    public void onInboxClick(View view) {
        viewPager.setCurrentItem(0);
    }

    public void onContactClick(View view) {
        viewPager.setCurrentItem(1);
    }

    public void onGroupClick(View view) {
        viewPager.setCurrentItem(2);
    }

    public void onMenuClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_from_left);
    }

    public void onSearchClick(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);
    }

    public void onFabClick(View view) {
        int currentPage = viewPager.getCurrentItem();
        if (currentPage == 0) {
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        circleButton.setRotation(positionOffset * 180);
        Log.i(TAG, positionOffset + "-" + positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            inboxIconView.setImageResource(R.drawable.ic_message);
            contactIconView.setImageResource(R.drawable.ic_contact_unselected);
            groupIconView.setImageResource(R.drawable.ic_group_unselected);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                inboxTextView.setTextColor(getColor(R.color.colorMain));
                contactTextView.setTextColor(getColor(R.color.darkerGray));
                groupTextView.setTextColor(getColor(R.color.darkerGray));
            }

            if (position != previousTab) {
                textGuide.setText("New Message");
                guideAnimation();
            }

            previousTab = 0;
        } else if (position == 1) {
            contactIconView.setImageResource(R.drawable.ic_contact);
            inboxIconView.setImageResource(R.drawable.ic_message_unselected);
            groupIconView.setImageResource(R.drawable.ic_group_unselected);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                inboxTextView.setTextColor(getColor(R.color.darkerGray));
                contactTextView.setTextColor(getColor(R.color.colorMain));
                groupTextView.setTextColor(getColor(R.color.darkerGray));
            }
            if (position != previousTab) {
                textGuide.setText("Add Friend");
                guideAnimation();
            }

            previousTab = 1;
        } else if (position == 2) {
            groupIconView.setImageResource(R.drawable.ic_group);
            inboxIconView.setImageResource(R.drawable.ic_message_unselected);
            contactIconView.setImageResource(R.drawable.ic_contact_unselected);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                inboxTextView.setTextColor(getColor(R.color.darkerGray));
                contactTextView.setTextColor(getColor(R.color.darkerGray));
                groupTextView.setTextColor(getColor(R.color.colorMain));
            }

            if (position != previousTab) {
                textGuide.setText("New Group");
                guideAnimation();
            }

            previousTab = 2;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void guideAnimation() {
        if (guide.getVisibility() != View.VISIBLE) {
            guide.setVisibility(View.VISIBLE);
            final ScaleAnimation show = new ScaleAnimation(0f, 1.0f, 1.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0.5f);
            final ScaleAnimation hide = new ScaleAnimation(1.0f, 0f, 1.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0.5f);

            show.setDuration(100);
            hide.setDuration(100);
            show.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    View halfCircle = findViewById(R.id.half_circle);
                    halfCircle.setTranslationX(MathUtils.convertDpToPixel(HomeActivity.this, 259));
                    halfCircle.animate().translationX(0).setDuration(100).start();
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    new CountDownTimer(1000, 1) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            guide.startAnimation(hide);
                        }
                    }.start();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            hide.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    View halfCircle = findViewById(R.id.half_circle);
                    halfCircle.setTranslationX(0);
                    halfCircle.animate().translationX(MathUtils.convertDpToPixel(HomeActivity.this, 259)).setDuration(100).start();
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    guide.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            guide.startAnimation(show);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ApplicationConstant.LOGIN_CODE) {

        }
    }
}
