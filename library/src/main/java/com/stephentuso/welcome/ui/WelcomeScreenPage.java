package com.stephentuso.welcome.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

/**
 * Created by stephentuso on 11/15/15.
 */
public class WelcomeScreenPage implements ViewPager.OnPageChangeListener {

    private final Fragment fragment;
    private final BackgroundColor backgroundColor;
    private int index = -2;

    public interface OnChangeListener {
        void onScrolled(int pageIndex, float offset, int offsetPixels);
        void onSelected(int pageIndex);
        void onScrollStateChanged(int state);
    }

    public WelcomeScreenPage(Fragment fragment, BackgroundColor backgroundColor) {
        this.fragment = fragment;
        this.backgroundColor = backgroundColor;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public BackgroundColor getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (fragment instanceof OnChangeListener && index - position <= 1) {

            int fragmentWidth = 0;
            if (this.fragment.getView() != null) {
                fragmentWidth = this.fragment.getView().getWidth();
            }

            boolean lowerPosition = position < index;
            float offset = lowerPosition ? -(1 - positionOffset) : positionOffset;
            int offsetPixels = lowerPosition ? -(fragmentWidth - positionOffsetPixels) : positionOffsetPixels;

            ((OnChangeListener) fragment).onScrolled(index, offset, offsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (fragment instanceof OnChangeListener && index == position) {
            ((OnChangeListener) fragment).onSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (fragment instanceof OnChangeListener) {
            ((OnChangeListener) fragment).onScrollStateChanged(state);
        }
    }
}
