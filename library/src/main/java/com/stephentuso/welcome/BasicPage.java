package com.stephentuso.welcome;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;

import com.stephentuso.welcome.ui.fragments.BasicWelcomeFragment;

/**
 * Created by stephentuso on 10/11/16.
 */

public class BasicPage extends WelcomePage {

    private int drawableResId;
    private String title;
    private String description;
    private boolean showParallax = true;
    private String headerTypefacePath = null;
    private String descriptionTypefacePath = null;

    private BasicWelcomeFragment fragment = null;

    public BasicPage(@DrawableRes int drawableResId, String title, String description) {
        this.drawableResId = drawableResId;
        this.title = title;
        this.description = description;
    }

    public BasicPage parallax(boolean showParallax) {
        this.showParallax = showParallax;
        return this;
    }

    public BasicPage headerTypeface(String typefacePath) {
        this.headerTypefacePath = typefacePath;
        return this;
    }

    public BasicPage descriptionTypeface(String typefacePath) {
        this.descriptionTypefacePath = typefacePath;
        return this;
    }

    @Override
    public void setup(WelcomeConfiguration config) {
        super.setup(config);

        if (this.headerTypefacePath == null) {
            headerTypeface(config.getDefaultHeaderTypefacePath());
        }

        if (this.descriptionTypefacePath == null) {
            descriptionTypeface(config.getDefaultDescriptionTypefacePath());
        }

    }

    @Override
    public Fragment getFragment() {
        return fragment;
    }

    @Override
    public Fragment createFragment() {
        this.fragment = BasicWelcomeFragment.newInstance(drawableResId, title, description, showParallax, headerTypefacePath, descriptionTypefacePath);
        return fragment;
    }

}
