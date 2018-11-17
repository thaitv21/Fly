package com.esp.fly.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esp.fly.R;

public class SearchActivity extends AppCompatActivity implements TextView.OnEditorActionListener, TextWatcher{

    private EditText searchView;
    private ImageView clearTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        clearTextButton = findViewById(R.id.clear_text_search_view);
        searchView = findViewById(R.id.search_edit_text);
        searchView.setOnEditorActionListener(this);
        searchView.addTextChangedListener(this);
    }

    public void onBackClick(View view) {
        finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_from_left);
    }

    public void onClearText(View view) {
        searchView.setText("");
        searchView.setSelection(0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_from_left);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            performSearch();
            return true;
        }
        return false;
    }

    private void performSearch() {
        hideKeyboard();
        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() > 0) {
            clearTextButton.setVisibility(View.VISIBLE);
        } else {
            clearTextButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
