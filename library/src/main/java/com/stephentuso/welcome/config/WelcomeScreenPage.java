package com.stephentuso.welcome.config;

import android.support.v4.app.Fragment;

/**
 * Created by stephentuso on 11/15/15.
 */
public class WelcomeScreenPage {

    private final Fragment fragment;
    private final BackgroundColor backgroundColor;

    public WelcomeScreenPage(Fragment fragment, BackgroundColor backgroundColor) {
        this.fragment = fragment;
        this.backgroundColor = backgroundColor;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public BackgroundColor getBackgroundColor() {
        return backgroundColor;
    }

}
