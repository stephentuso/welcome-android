package com.stephentuso.welcome.config;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

    public BackgroundColor getBackgroundColor(int index) {
        return get(index).getBackgroundColor();
    }

    public BackgroundColor[] getBackgroundColors() {
        ArrayList<BackgroundColor> colors = new ArrayList<BackgroundColor>();
        for (WelcomeScreenPage page : this) {
            colors.add(page.getBackgroundColor());
        }
        return colors.toArray(new BackgroundColor[1]);
    }

    public void reversePageOrder() {
        Collections.reverse(this);
    }

}
