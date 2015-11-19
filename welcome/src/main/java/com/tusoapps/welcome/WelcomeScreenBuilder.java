package com.tusoapps.welcome;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;

import com.tusoapps.welcome.config.BackgroundColor;
import com.tusoapps.welcome.config.WelcomeScreenConfiguration;
import com.tusoapps.welcome.ui.fragments.BasicWelcomeFragment;
import com.tusoapps.welcome.ui.fragments.PreferenceWelcomeFragment;
import com.tusoapps.welcome.ui.fragments.TitleFragment;

/**
 * Created by stephentuso on 11/16/15.
 */
public class WelcomeScreenBuilder {

    private WelcomeScreenConfiguration.Parameters mConfigParams;

    public WelcomeScreenBuilder(Context context) {
        mConfigParams = new WelcomeScreenConfiguration.Parameters(context);
    }

    public WelcomeScreenBuilder swipeToDismiss(boolean swipeToDismiss) {
        mConfigParams.setSwipeToDismiss(swipeToDismiss);
        return this;
    }

    public WelcomeScreenBuilder theme(WelcomeScreenConfiguration.Theme theme) {
        mConfigParams.setTheme(theme);
        return this;
    }

    public WelcomeScreenBuilder theme(int resId) {
        mConfigParams.setThemeResId(resId);
        return this;
    }

    public WelcomeScreenBuilder defaultBackgroundColor(@ColorRes int resId) {
        mConfigParams.setDefaultBackgroundColor(resId);
        return this;
    }

    public WelcomeScreenBuilder defaultBackgroundColor(BackgroundColor backgroundColor) {
        mConfigParams.setDefaultBackgroundColor(backgroundColor);
        return this;
    }

    public WelcomeScreenBuilder basicPage(@DrawableRes int drawableId, String title, String description) {
        return basicPage(drawableId, title, description, 0);
    }

    public WelcomeScreenBuilder basicPage(@DrawableRes int drawableId, String title, String description, @ColorRes int colorResId) {
        mConfigParams.add(BasicWelcomeFragment.newInstance(drawableId, title, description), colorResId);
        return this;
    }

    public WelcomeScreenBuilder preferencePage(int preferencesResId, @ColorRes int colorResId) {
        mConfigParams.add(PreferenceWelcomeFragment.newInstance(preferencesResId), colorResId);
        return this;
    }

    public WelcomeScreenBuilder titlePage(@DrawableRes int resId, String title) {
        return titlePage(resId, title, 0);
    }

    public WelcomeScreenBuilder titlePage(@DrawableRes int resId, String title, @ColorRes int colorResId) {
        mConfigParams.add(TitleFragment.newInstance(resId, title), colorResId);
        return this;
    }

    public WelcomeScreenBuilder page(Fragment fragment) {
        page(fragment, 0);
        return this;
    }

    public WelcomeScreenBuilder page(Fragment fragment, @ColorRes int colorResId) {
        mConfigParams.add(fragment, colorResId);
        return this;
    }

    public WelcomeScreenBuilder canSkip(boolean canSkip) {
        mConfigParams.setCanSkip(canSkip);
        return this;
    }

    public WelcomeScreenBuilder backButtonSkips(boolean backButtonSkips) {
        mConfigParams.setBackButtonSkips(backButtonSkips);
        return this;
    }

    public WelcomeScreenConfiguration build() {
        return new WelcomeScreenConfiguration(mConfigParams);
    }

}