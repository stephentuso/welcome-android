package com.stephentuso.welcome;

import android.support.v4.view.ViewPager;

/**
 * Created by stephentuso on 11/16/15.
 */
public interface OnWelcomeScreenPageChangeListener extends ViewPager.OnPageChangeListener {
    void setup(WelcomeConfiguration config);
}

