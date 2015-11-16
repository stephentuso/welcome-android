package com.tusoapps.welcome.ui;

import android.view.View;
import android.widget.Button;

/**
 * Created by stephentuso on 11/15/15.
 */
public class NextButton extends WelcomeScreenButton {

    public NextButton(View button) {
        super(button);
    }

    @Override
    public void onPageSelected(int pageIndex, int maxPageIndex) {
        setVisibility(pageIndex != maxPageIndex);
    }
}
