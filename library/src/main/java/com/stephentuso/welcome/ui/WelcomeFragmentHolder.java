package com.stephentuso.welcome.ui;

import android.support.v4.app.Fragment;

/**
 * Created by stephentuso on 1/22/16.
 */
public abstract class WelcomeFragmentHolder {

    private Fragment mFragment;

    public abstract Fragment createFragment();

}
