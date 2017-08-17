package com.example.test.calculator;

import android.app.Activity;
import android.os.Bundle;

public class AboutActivity extends Activity {
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
