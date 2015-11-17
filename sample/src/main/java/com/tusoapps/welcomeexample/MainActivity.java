package com.tusoapps.welcomeexample;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tusoapps.welcome.Welcomer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Welcomer(this, MyWelcomeActivity.class).forceShow();
    }
}
