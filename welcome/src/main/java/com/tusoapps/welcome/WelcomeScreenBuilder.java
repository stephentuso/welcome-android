package com.tusoapps.welcome;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;

import com.tusoapps.welcome.config.BackgroundColor;
import com.tusoapps.welcome.config.WelcomeScreenConfig;
import com.tusoapps.welcome.ui.fragments.BasicWelcomeFragment;
import com.tusoapps.welcome.ui.PreferenceWelcomeFragment;
import com.tusoapps.welcome.ui.fragments.TitleFragment;

/**
 * Created by stephentuso on 11/16/15.
 */
public class WelcomeScreenBuilder {

    private WelcomeScreenConfig mConfig;

    public WelcomeScreenBuilder(Context context) {
        mConfig = new WelcomeScreenConfig(context);
    }

    public WelcomeScreenBuilder swipeToDismiss(boolean swipeToDismiss) {
        mConfig.setSwipeToDismiss(swipeToDismiss);
        return this;
    }

    public WelcomeScreenBuilder theme(WelcomeScreenConfig.Theme theme) {
        mConfig.setTheme(theme);
        return this;
    }

    public WelcomeScreenBuilder theme(int resId) {
        mConfig.setThemeResId(resId);
        return this;
    }

    public WelcomeScreenBuilder defaultBackgroundColor(@ColorRes int resId) {
        mConfig.setDefaultBackgroundColor(resId);
        return this;
    }

    public WelcomeScreenBuilder defaultBackgroundColor(BackgroundColor backgroundColor) {
        mConfig.setDefaultBackgroundColor(backgroundColor);
        return this;
    }

    public WelcomeScreenBuilder basicPage(@DrawableRes int drawableId, String title, String description) {
        return basicPage(drawableId, title, description, 0);
    }

    public WelcomeScreenBuilder basicPage(@DrawableRes int drawableId, String title, String description, @ColorRes int colorResId) {
        mConfig.add(BasicWelcomeFragment.newInstance(drawableId, title, description), colorResId);
        return this;
    }

    public WelcomeScreenBuilder preferencePage(int preferencesResId, @ColorRes int colorResId) {
        mConfig.add(PreferenceWelcomeFragment.newInstance(preferencesResId), colorResId);
        return this;
    }

    public WelcomeScreenBuilder titlePage(@DrawableRes int resId, String title) {
        return titlePage(resId, title, 0);
    }

    public WelcomeScreenBuilder titlePage(@DrawableRes int resId, String title, @ColorRes int colorResId) {
        mConfig.add(TitleFragment.newInstance(resId, title), colorResId);
        return this;
    }

    public WelcomeScreenBuilder page(Fragment fragment) {
        page(fragment, 0);
        return this;
    }

    public WelcomeScreenBuilder page(Fragment fragment, @ColorRes int colorResId) {
        mConfig.add(fragment, colorResId);
        return this;
    }

    public WelcomeScreenBuilder canSkip(boolean canSkip) {
        mConfig.setCanSkip(canSkip);
        return this;
    }

    public WelcomeScreenBuilder backButtonSkips(boolean backButtonSkips) {
        mConfig.setBackButtonSkips(backButtonSkips);
        return this;
    }

    public WelcomeScreenConfig build() {
        mConfig.finish();
        return mConfig;
    }

}