package com.stephentuso.welcomeexample;

import android.graphics.Color;

import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.BackgroundColor;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;
import com.stephentuso.welcome.ui.WelcomeActivity;

/**
 * Created by stephentuso on 11/15/15.
 */
public class MyWelcomeActivity extends WelcomeActivity{
    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .theme(R.style.CustomWelcomeScreenTheme)
                .titlePage(R.drawable.photo, "Welcome", R.color.blue_background)
                .basicPage(R.drawable.photo, "Easy to use", "Add a welcome screen to your app with only a few lines of code.", R.color.red_background)
                .basicPage(R.drawable.photo, "Customizable", "All elements of the welcome screen can be customized easily.", R.color.purple_background)
                .canSkip(true)
                .backButtonSkips(false)
                .swipeToDismiss(true)
                .build();
    }
}
