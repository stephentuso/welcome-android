package com.stephentuso.welcome;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by stephentuso on 11/15/15.
 */
public class WelcomePageList extends ArrayList<WelcomePage> implements OnWelcomeScreenPageChangeListener {

    public WelcomePageList(WelcomePage... pages) {
        super(Arrays.asList(pages));
    }

    public Fragment getFragment(int index) {
        return get(index).getFragment();
    }

    public BackgroundColor getBackgroundColor(Context context, int index) {
        return get(index).getBackground(context);
    }

    public BackgroundColor[] getBackgroundColors(Context context) {
        ArrayList<BackgroundColor> colors = new ArrayList<BackgroundColor>();
        for (WelcomePage page : this) {
            colors.add(page.getBackground(context));
        }
        return colors.toArray(new BackgroundColor[1]);
    }

    public void reversePageOrder() {
        Collections.reverse(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (WelcomePage page : this) {
            page.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        for (WelcomePage page : this) {
            page.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        for (WelcomePage page : this) {
            page.onPageScrollStateChanged(state);
        }
    }

    @Override
    public void setup(WelcomeConfiguration config) {
        for (WelcomePage page : this) {
            page.setup(config);
        }
    }
}
