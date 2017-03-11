package com.stephentuso.welcomeexample;

import android.graphics.Color;

import com.stephentuso.welcome.BackgroundColor;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;

/**
 * Created by stephentuso on 10/5/16.
 */

public class LightWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(new BackgroundColor(Color.WHITE))
                .defaultTitleTypefacePath("Roboto-Bold.ttf")
                .defaultHeaderTypefacePath("Roboto-Bold.ttf")
                .page(new TitlePage(R.drawable.ic_style_blue, "Light Theme")
                        .titleColorResource(this, R.color.colorAccent))
                .page(new BasicPage(R.drawable.ic_brush_blue, "Easy styling",
                        "All colors can be customized with styles"))
                .swipeToDismiss(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }

}
