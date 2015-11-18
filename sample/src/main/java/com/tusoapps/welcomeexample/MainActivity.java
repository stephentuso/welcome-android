package com.tusoapps.welcomeexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tusoapps.welcome.Welcomer;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver welcomeScreenBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeScreenBroadcastReceiver = Welcomer.registerReceiver(this, new Welcomer.WelcomeActionListener() {
            @Override
            public void welcomeCompleted() {
                Toast.makeText(getApplicationContext(), "Completed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void welcomeFailed() {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });

        final Context activity = this;

        findViewById(R.id.button_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Welcomer(activity, MyWelcomeActivity.class).forceShow();
            }
        });

        new Welcomer(this, MyWelcomeActivity.class).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Welcomer.unregisterReceiver(this, welcomeScreenBroadcastReceiver);
    }
}
