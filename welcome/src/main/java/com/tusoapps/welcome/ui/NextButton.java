package com.tusoapps.welcome.ui;

import android.view.View;

/**
 * Created by stephentuso on 11/15/15.
 */
public class NextButton extends WelcomeScreenViewWrapper {

    public NextButton(View button) {
        super(button);
    }

    @Override
    public void onPageSelected(int pageIndex, int lastPageIndex) {
        setVisibility(pageIndex != lastPageIndex);
    }
}
