package com.stephentuso.welcomeexample;

import com.stephentuso.welcome.WelcomeConfiguration;
import com.stephentuso.welcome.ui.WelcomeActivity;

/**
 * Created by stephentuso on 10/5/16.
 */

public class NoSkipWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultTitleTypefacePath("Roboto-Bold.ttf")
                .defaultHeaderTypefacePath("Roboto-Bold.ttf")
                .basicPage(R.drawable.ic_image_white, "No skip", "canSkip(false) was called on the builder")
                .basicPage(R.drawable.ic_back_white, "Back behaviour", "Pressing back on the first page will finish this with RESULT_CANCELED")
                .basicPage(R.drawable.ic_check_white, "Completion", "RESULT_OK will only be returned after navigating all the way to the end")
                .canSkip(false)
                .swipeToDismiss(true)
                .build();
    }

}
