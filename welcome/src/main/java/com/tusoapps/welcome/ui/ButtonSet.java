package com.tusoapps.welcome.ui;

/**
 * Created by stephentuso on 11/15/15.
 */
public class ButtonSet {

    private WelcomeScreenButton[] mButtons;

    public ButtonSet(WelcomeScreenButton... buttons) {
        mButtons = buttons;
    }

    public void onPageSelected(int pageIndex, int maxPageIndex) {
        for (WelcomeScreenButton button : mButtons) {
            button.onPageSelected(pageIndex, maxPageIndex);
        }
    }

}
