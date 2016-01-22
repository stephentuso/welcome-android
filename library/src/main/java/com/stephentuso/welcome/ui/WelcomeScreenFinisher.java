package com.stephentuso.welcome.ui;

import android.support.v4.app.Fragment;

/**
 * Created by stephentuso on 1/22/16.
 * Provides a way to correctly close a welcome screen
 */
public class WelcomeScreenFinisher {

    private Fragment mFragment;

    /**
     * @param fragment A fragment that is a custom page in a {@link WelcomeActivity}
     */
    public WelcomeScreenFinisher(Fragment fragment) {
        mFragment = fragment;
    }

    /**
     * Completes the welcome screen the provided fragment is a part of.
     * Checks that the activity is an instance of {@link WelcomeActivity} to avoid errors if
     * this is used in the wrong place.
     */
    public void finish() {
        if (mFragment.getActivity() instanceof WelcomeActivity) {
            WelcomeActivity welcomeActivity = (WelcomeActivity) mFragment.getActivity();
            welcomeActivity.completeWelcomeScreen();
        }
    }

}
