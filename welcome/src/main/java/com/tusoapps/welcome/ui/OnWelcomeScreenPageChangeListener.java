package com.tusoapps.welcome.ui;

import android.support.v4.view.ViewPager;

import com.tusoapps.welcome.config.WelcomeScreenConfig;

/**
 * Created by stephentuso on 11/16/15.
 */
public interface OnWelcomeScreenPageChangeListener extends ViewPager.OnPageChangeListener {
    void setup(WelcomeScreenConfig config);
}

