package com.stephentuso.welcomeexample;

import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 11/15/15.
 */
public class SampleWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .theme(R.style.SampleWelcomeScreenTheme)
                .defaultBackgroundColor(R.color.colorPrimary)
                .defaultTitleTypefacePath("Montserrat-Bold.ttf")
                .defaultHeaderTypefacePath("Montserrat-Bold.ttf")
                .basicPage(R.drawable.ic_front_desk_white, "Welcome", "An Android library for onboarding, instructional screens, and more", R.color.orange_background)
                .basicPage(R.drawable.ic_thumb_up_white, "Simple to use", "Add a welcome screen to your app with only a few lines of code.", R.color.red_background)
                .parallaxPage(R.layout.parallax_example, "Easy parallax", "Supply a layout and parallax effects will automatically be applied", R.color.purple_background, 0.2f, 2f)
                .basicPage(R.drawable.ic_edit_white, "Customizable", "All elements of the welcome screen can be customized easily.", R.color.blue_background)
                .swipeToDismiss(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }

    public static String welcomeKey() {
        return "WelcomeScreen";
    }

}
