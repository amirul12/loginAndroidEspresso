package com.amirul.loginespressotesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        String welcomeMessage = String.format("Hi %s!", getIntent().getStringExtra("email"));
        TextView tvEmail = (TextView) findViewById(R.id.tv_welcome);
        tvEmail.setText(welcomeMessage);

    }
}
