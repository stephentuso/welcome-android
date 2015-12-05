package com.stephentuso.welcome.ui;

import android.support.v4.view.ViewPager;

import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 11/16/15.
 */
public interface OnWelcomeScreenPageChangeListener extends ViewPager.OnPageChangeListener {
    void setup(WelcomeScreenConfiguration config);
}

