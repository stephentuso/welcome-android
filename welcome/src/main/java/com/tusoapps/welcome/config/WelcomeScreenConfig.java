package com.tusoapps.welcome.config;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.tusoapps.welcome.ui.WelcomeFragmentBasic;

import java.util.Arrays;

/**
 * Created by stephentuso on 11/15/15.
 */
public class WelcomeScreenConfig {

    private WelcomeScreenPageList mPages = new WelcomeScreenPageList();
    private boolean mCanSkip;

    public WelcomeScreenConfig() {

    }

    public WelcomeScreenConfig basicPage(@DrawableRes int drawableId, String title, String description, @Nullable @ColorInt Integer backgroundColor) {
        mPages.add(new WelcomeScreenPage(WelcomeFragmentBasic.newInstance(drawableId, title, description), new BackgroundColor(backgroundColor)));
        return this;
    }

    public WelcomeScreenConfig page(Fragment fragment) {
        mPages.add(new WelcomeScreenPage(fragment, new BackgroundColor(null)));
        return this;
    }

    public WelcomeScreenConfig canSkip(boolean canSkip) {
        mCanSkip = canSkip;
        return this;
    }

    public Fragment getFragment(int index) {
        return mPages.getFragment(index);
    }

    public int pageCount() {
        return mPages.size();
    }

    public boolean getCanSkip() {
        return mCanSkip;
    }

}