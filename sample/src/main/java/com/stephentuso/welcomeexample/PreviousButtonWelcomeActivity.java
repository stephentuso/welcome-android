package com.stephentuso.welcomeexample;

import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.FullscreenParallaxPage;
import com.stephentuso.welcome.ParallaxPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;

/**
 * Created by stephentuso on 11/16/16.
 */

public class PreviousButtonWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.colorPrimary)
                .showPrevButton(true)
                .backButtonNavigatesPages(false)
                .defaultTitleTypefacePath("Roboto-Bold.ttf")
                .defaultHeaderTypefacePath("Roboto-Bold.ttf")
                .showActionBarBackButton(true)
                .page(new TitlePage(R.drawable.ic_image_white, "Title Page"))
                .page(new BasicPage(R.drawable.ic_image_white, "Basic Page", "A page with a title and description").background(R.color.purple_background))
                .page(new ParallaxPage(R.layout.parallax_example_two, "Parallax page", "Applies a parallax effect and has a title and description").background(R.color.red_background))
                .page(new FullscreenParallaxPage(R.layout.parallax_example_fullscreen).background(R.color.orange_background))
                .build();
    }

}
