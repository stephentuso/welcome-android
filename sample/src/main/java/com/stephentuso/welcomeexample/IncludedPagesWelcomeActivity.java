package com.stephentuso.welcomeexample;

import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 10/10/16.
 */

public class IncludedPagesWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .defaultBackgroundColor(R.color.colorPrimary)
                .theme(R.style.GeneralWelcomeScreenTheme)
                .swipeToDismiss(true)
                .defaultTitleTypefacePath("Roboto-Bold.ttf")
                .defaultHeaderTypefacePath("Roboto-Bold.ttf")
                .titlePage(R.drawable.ic_image_white, "Title Page")
                .basicPage(R.drawable.ic_image_white, "Basic Page", "A page with a title and description", R.color.purple_background)
                .parallaxPage(R.layout.parallax_example_two, "Parallax page", "Applies a parallax effect and has a title and description", R.color.red_background)
                .fullScreenParallaxPage(R.layout.parallax_example_fullscreen, R.color.orange_background)
                .build();

    }

}
