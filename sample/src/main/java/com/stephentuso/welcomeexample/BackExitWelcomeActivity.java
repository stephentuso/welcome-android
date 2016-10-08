package com.stephentuso.welcomeexample;

import com.stephentuso.welcome.WelcomeConfiguration;
import com.stephentuso.welcome.ui.WelcomeActivity;

/**
 * Created by stephentuso on 10/5/16.
 */

public class BackExitWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultTitleTypefacePath("Roboto-Bold.ttf")
                .defaultHeaderTypefacePath("Roboto-Bold.ttf")
                .basicPage(R.drawable.ic_image_white, "No back navigation", "By default, the back button can be used to go back a page")
                .basicPage(R.drawable.ic_back_white, "Back will exit", "Pressing back on this page will close the activity, rather than going to the previous page")
                .backButtonNavigatesPages(false)
                .swipeToDismiss(true)
                .build();
    }

}
