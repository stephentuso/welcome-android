package com.stephentuso.welcome.ui;

import android.view.View;

import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 11/15/15.
 */
public class DoneButton extends WelcomeScreenViewWrapper {

    private boolean shouldShow = true;

    public DoneButton(View button) {
        super(button);
        hideImmediately();
    }

    @Override
    public void setup(WelcomeScreenConfiguration config) {
        super.setup(config);
        shouldShow = !config.getUseCustomDoneButton();
    }

    @Override
    public void onPageSelected(int pageIndex, int lastPageIndex) {
        setVisibility(shouldShow && pageIndex == lastPageIndex);
    }


}
