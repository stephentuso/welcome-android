package com.stephentuso.welcome;

import android.support.v4.view.ViewPager;

/**
 * Implemented by library components to respond to page scroll events
 * and initial setup
 *
 * Created by stephentuso on 11/16/15.
 */
/* package */ interface OnWelcomeScreenPageChangeListener extends ViewPager.OnPageChangeListener {
    void setup(WelcomeConfiguration config);
}

