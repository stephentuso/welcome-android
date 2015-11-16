package com.tusoapps.welcomeexample;

import android.graphics.Color;

import com.tusoapps.welcome.config.WelcomeScreenConfig;
import com.tusoapps.welcome.ui.WelcomeActivity;

/**
 * Created by stephentuso on 11/15/15.
 */
public class MyWelcomeActivity extends WelcomeActivity{
    @Override
    protected WelcomeScreenConfig configuration() {
        return new WelcomeScreenConfig()
                .basicPage(R.drawable.button_borderless_background, "Test", "Description", Color.GRAY)
                .basicPage(R.drawable.button_borderless_background, "Second page", "Test", Color.BLUE)
                .basicPage(0, "Third Page", "Lorem ipsum dolor sit amet", Color.GREEN)
                .basicPage(0, "Fourth Page", "Lorem ipsum dolor sit amet", Color.GREEN)
                .basicPage(0, "Fifth Page", "Lorem ipsum dolor sit amet", Color.GREEN)
                //.preferencePage(R.xml.prefs, null)
                .canSkip(false);
    }
}
