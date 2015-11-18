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
                .titlePage(R.drawable.periodic_table, "Welcome to\nPeriodic Table")
                .basicPage(R.drawable.periodic_table, "Information at your fingertips", "Information on elements, formulas and constants, and more")
                .basicPage(R.drawable.periodic_table, "Dynamic table", "Tap an element or pinch to zoom in and view basic properties. Tap again to view the full info page.")
                .basicPage(R.drawable.periodic_table, "Customizable", "Choose which properties the table shows, sort the element list, and more.")
                .canSkip(true)
                .backButtonSkips(false)
                .swipeToDismiss(true)
                .build();
    }
}
