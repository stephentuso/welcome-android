package com.stephentuso.welcomeexample;

import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.BackgroundColor;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 10/5/16.
 */

public class DefaultWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .defaultBackgroundColor(new BackgroundColor(getResources().getColor(R.color.blue_background)))
                .titlePage(R.drawable.photo, "Default Welcome Screen")
                .basicPage(R.drawable.photo, "Default style", "No custom styles are applied to this welcome screen")
                .basicPage(R.drawable.photo, "Default properties", "No properties were set on the WelcomeScreenBuilder, other than the background color")
                .build();
    }

    public static String welcomeKey() {
        return "DefaultWelcomeActivity";
    }

}
