package com.tusoapps.welcomeexample;

import android.content.res.Configuration;
import android.graphics.Color;

import com.tusoapps.welcome.WelcomeScreenBuilder;
import com.tusoapps.welcome.config.BackgroundColor;
import com.tusoapps.welcome.config.WelcomeScreenConfig;
import com.tusoapps.welcome.ui.WelcomeActivity;

/**
 * Created by stephentuso on 11/15/15.
 */
public class MyWelcomeActivity extends WelcomeActivity{
    @Override
    protected WelcomeScreenConfig configuration() {
        return new WelcomeScreenBuilder(this)
                .theme(R.style.CustomWelcomeScreenTheme)
                .defaultBackgroundColor(new BackgroundColor(Color.WHITE))
                .titlePage(R.drawable.periodic_table, "Welcome")
                .basicPage(R.drawable.button_borderless_background, "Test", "Description")
                .basicPage(R.drawable.button_borderless_background, "Second page", "Test")
                .basicPage(0, "Third Page", "Lorem ipsum dolor sit amet")
                .basicPage(0, "Fourth Page", "Lorem ipsum dolor sit amet")
                .basicPage(0, "Fifth Page", "Lorem ipsum dolor sit amet")
                .canSkip(false)
                .swipeToDismiss(true)
                .build();
    }
}
