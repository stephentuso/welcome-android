package com.tusoapps.welcome.config;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stephentuso on 11/15/15.
 */
public class WelcomeScreenPageList extends ArrayList<WelcomeScreenPage> {

    public WelcomeScreenPageList(WelcomeScreenPage... pages) {
        super(Arrays.asList(pages));
    }

    public Fragment getFragment(int index) {
        return get(index).getFragment();
    }

    public int getBackgroundColorValue(int index) {
        return get(index).getBackgroundColorValue();
    }

}
