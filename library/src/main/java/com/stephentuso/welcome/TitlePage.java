package com.stephentuso.welcome;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;

import com.stephentuso.welcome.ui.fragments.WelcomeTitleFragment;

/**
 * Created by stephentuso on 10/11/16.
 */

public class TitlePage extends WelcomePage {

    private int drawableResId;
    private String title;
    private boolean showParallax = true;
    private String titleTypefacePath = null;

    private WelcomeTitleFragment fragment;

    public TitlePage(@DrawableRes int drawableResId, String title) {
        this.drawableResId = drawableResId;
        this.title = title;
    }

    public TitlePage parallax(boolean showParallax) {
        this.showParallax = showParallax;
        return this;
    }

    public TitlePage titleTypeface(String typefacePath) {
        this.titleTypefacePath = typefacePath;
        return this;
    }

    @Override
    public void setup(WelcomeConfiguration config) {
        super.setup(config);

        if (this.titleTypefacePath == null) {
            titleTypeface(config.getDefaultHeaderTypefacePath());
        }

    }

    @Override
    public Fragment getFragment() {
        return fragment;
    }

    @Override
    public Fragment createFragment() {
        this.fragment = WelcomeTitleFragment.newInstance(drawableResId, title, showParallax, titleTypefacePath);
        return fragment;
    }


}
