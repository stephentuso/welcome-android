package com.stephentuso.welcomeexample;

import com.stephentuso.welcome.WelcomeConfiguration;
import com.stephentuso.welcome.ui.WelcomeActivity;

/**
 * Created by stephentuso on 10/5/16.
 */

public class DefaultWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .titlePage(R.drawable.ic_image_white, "Default Welcome Screen")
                .basicPage(R.drawable.ic_style_white, "Default style", "No custom styles are applied to this welcome screen")
                .basicPage(R.drawable.ic_image_white, "Default properties", "No properties were set on the WelcomeScreenBuilder")
                .build();
    }

}
