package com.stephentuso.welcome.ui;

import android.support.v4.app.Fragment;

import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by stephentuso on 11/15/15.
 */
public class WelcomeScreenPageList extends ArrayList<WelcomeScreenPage> implements OnWelcomeScreenPageChangeListener {

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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (WelcomeScreenPage page : this) {
            page.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        for (WelcomeScreenPage page : this) {
            page.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        for (WelcomeScreenPage page : this) {
            page.onPageScrollStateChanged(state);
        }
    }

    @Override
    public void setup(WelcomeScreenConfiguration config) {
        for (WelcomeScreenPage page : this) {
            page.setup(config);
        }
    }
}
