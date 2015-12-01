package com.stephentuso.welcome.ui;

import android.view.View;

/**
 * Created by stephentuso on 11/15/15.
 */
public class SkipButton extends WelcomeScreenViewWrapper {

    private boolean enabled = true;

    public SkipButton(View button, boolean enabled) {
        super(button);
        this.enabled = enabled;
        if (!enabled)
            button.setVisibility(View.GONE);
    }

    @Override
    public void onPageSelected(int pageIndex, int lastPageIndex) {
        setVisibility(enabled && pageIndex != lastPageIndex);
    }

}
