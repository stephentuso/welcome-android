package com.stephentuso.welcomeexample;

import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 10/5/16.
 */

public class DefaultWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(getApplicationContext())
                .titlePage(R.drawable.ic_image_white, "Default Welcome Screen")
                .basicPage(R.drawable.ic_style_white, "Default style", "No custom styles are applied to this welcome screen")
                .basicPage(R.drawable.ic_image_white, "Default properties", "No properties were set on the WelcomeScreenBuilder")
                .build();
    }

}
