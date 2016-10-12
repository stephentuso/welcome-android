package com.stephentuso.welcome;

import android.support.v4.app.Fragment;

/**
 * Created by stephentuso on 10/11/16.
 */

public class FragmentWelcomePage extends WelcomePage<FragmentWelcomePage> {

    private WelcomeFragmentHolder fragmentHolder;

    public FragmentWelcomePage(WelcomeFragmentHolder fragmentHolder) {
        this.fragmentHolder = fragmentHolder;
    }

    @Override
    public Fragment getFragment() {
        return fragmentHolder.getFragment();
    }

    @Override
    public Fragment createFragment() {
        return fragmentHolder.createFragment();
    }

}
