package com.stephentuso.welcomeexample;

import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 10/5/16.
 */

public class BackExitWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .theme(R.style.SampleWelcomeScreenTheme)
                .basicPage(R.drawable.photo, "No back navigation", "By default, the back button can be used to go back a page")
                .basicPage(R.drawable.photo, "Back will exit", "Pressing back on this page will close the activity, rather than going to the previous page")
                .backButtonNavigatesPages(false)
                .swipeToDismiss(true)
                .build();
    }

}
