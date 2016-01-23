package com.stephentuso.welcome.ui;

import android.support.v4.app.Fragment;

/**
 * Created by stephentuso on 1/22/16.
 */
public abstract class WelcomeFragmentHolder {

    private Fragment mFragment = null;

    public Fragment createFragment() {
        mFragment = fragment();
        return mFragment;
    }

    protected abstract Fragment fragment();

    public Fragment getFragment() {
        return mFragment;
    }

}
