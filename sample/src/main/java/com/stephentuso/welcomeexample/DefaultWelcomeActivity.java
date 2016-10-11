package com.stephentuso.welcomeexample;

import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;

/**
 * Created by stephentuso on 10/5/16.
 */

public class DefaultWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .page(new TitlePage(R.drawable.ic_image_white, "Default Welcome Screen"))
                .page(new BasicPage(R.drawable.ic_style_white, "Default style", "No custom styles are applied to this welcome screen"))
                .page(new BasicPage(R.drawable.ic_image_white, "Default properties", "No properties were set on the WelcomeScreenBuilder"))
                .build();
    }

}
