package com.stephentuso.welcomeexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.stephentuso.welcome.WelcomeCompletedEvent;
import com.stephentuso.welcome.WelcomeFailedEvent;
import com.stephentuso.welcome.WelcomeScreenShower;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WelcomeScreenShower.register(this);

        new WelcomeScreenShower(this, MyWelcomeActivity.class).show();

        final Context activity = this;

        findViewById(R.id.button_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new WelcomeScreenShower(activity, MyWelcomeActivity.class).forceShow();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WelcomeScreenShower.unregister(this);
    }

    public void onEvent(WelcomeCompletedEvent event) {
        Toast.makeText(getApplicationContext(), event.welcomeScreenKey + " completed", Toast.LENGTH_SHORT).show();
    }

    public void onEvent(WelcomeFailedEvent event) {
        Toast.makeText(getApplicationContext(), event.welcomeScreenKey + " failed", Toast.LENGTH_SHORT).show();
    }


}
