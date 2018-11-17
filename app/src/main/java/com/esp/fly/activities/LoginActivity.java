package com.esp.fly.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.esp.fly.R;
import com.esp.fly.models.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        FacebookCallback<LoginResult>, GraphRequest.Callback {

    private static final int GOOGLE_SIGN_IN = 1;
    private CallbackManager facebookCallbackManager;
    private String userId;
    private String avatarUrl;
    private String userName;
    private boolean userGender;
    private String userCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        facebookCallbackManager = CallbackManager.Factory.create();
    }

    public void onLoginFacebook(View view) {
        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
        LoginManager.getInstance().registerCallback(facebookCallbackManager, this);
        LoginManager.getInstance().logInWithReadPermissions(this, permissionNeeds);

    }

    public void onLoginGoogle(View view) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            userId = acct.getId();
            userName = acct.getDisplayName();
            if (acct.getPhotoUrl() != null) {
                avatarUrl = acct.getPhotoUrl().toString();
            }
            User.init(userId, userName, avatarUrl, null, true,"", "");
            finish();
        } else {
//            updateUI(false);
            Toast.makeText(this, "Sign-in failed! Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        Bundle params = new Bundle();
        params.putString("fields", "id,email,gender,cover,picture.type(large)");
        new GraphRequest(loginResult.getAccessToken(), "me", params, HttpMethod.GET, this).executeAsync();
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCompleted(GraphResponse response) {
        if (response != null) {
            try {
                JSONObject data = response.getJSONObject();
                if (data.has("id")) {
                    userId = data.getString("id");
                }
                if (data.has("picture")) {
                    avatarUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");
                }
                if (data.has("gender")) {
                    String gender = data.getString("gender");
                    if (gender.equals("male")) {
                        userGender = true;
                    } else {
                        userGender = false;
                    }
                }
                if (data.has("cover")) {
                    userCover = data.getJSONObject("cover").getString("source");
                }
                userName = Profile.getCurrentProfile().getName();
                User.init(userId, userName, avatarUrl, userCover, userGender, "", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        finish();
    }
}
