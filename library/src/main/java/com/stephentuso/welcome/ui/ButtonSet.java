package com.stephentuso.welcome.ui;

/**
 * Created by stephentuso on 11/15/15.
 */
public class ButtonSet {

    private WelcomeScreenViewWrapper[] mButtons;

    public ButtonSet(WelcomeScreenViewWrapper... buttons) {
        mButtons = buttons;
    }

    public void onPageSelected(int pageIndex, int maxPageIndex) {
        for (WelcomeScreenViewWrapper button : mButtons) {
            button.onPageSelected(pageIndex, maxPageIndex);
        }
    }

}
