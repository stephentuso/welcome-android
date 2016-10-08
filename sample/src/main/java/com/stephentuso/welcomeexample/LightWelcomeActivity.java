package com.stephentuso.welcomeexample;

import com.stephentuso.welcome.WelcomeConfiguration;
import com.stephentuso.welcome.ui.WelcomeActivity;

/**
 * Created by stephentuso on 10/5/16.
 */

public class LightWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.white)
                .defaultTitleTypefacePath("Roboto-Bold.ttf")
                .defaultHeaderTypefacePath("Roboto-Bold.ttf")
                .titlePage(R.drawable.ic_style_blue, "Light Theme")
                .basicPage(R.drawable.ic_brush_blue, "Easy styling", "All colors can be customized with styles")
                .swipeToDismiss(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }

}
