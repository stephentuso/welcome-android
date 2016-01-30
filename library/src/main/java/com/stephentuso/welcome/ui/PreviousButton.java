package com.stephentuso.welcome.ui;

import android.view.View;

import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 1/30/16.
 */
public class PreviousButton extends WelcomeScreenViewWrapper {

    private boolean shouldShow = false;

    public PreviousButton(View button) {
        super(button);
    }

    @Override
    public void setup(WelcomeScreenConfiguration config) {
        super.setup(config);
        this.shouldShow = config.getShowPrevButton();
    }

    @Override
    public void onPageSelected(int pageIndex, int firstPageIndex, int lastPageIndex) {
        setVisibility(shouldShow && pageIndex != firstPageIndex);
    }


}
