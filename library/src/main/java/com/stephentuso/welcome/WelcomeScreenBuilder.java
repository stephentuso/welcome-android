package com.stephentuso.welcome;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;

import com.stephentuso.welcome.config.BackgroundColor;
import com.stephentuso.welcome.config.WelcomeScreenConfiguration;
import com.stephentuso.welcome.ui.fragments.BasicWelcomeFragment;
import com.stephentuso.welcome.ui.fragments.TitleFragment;

/**
 * Created by stephentuso on 11/16/15.
 *
 * Class that wraps a {@link com.stephentuso.welcome.config.WelcomeScreenConfiguration.Parameters WelcomeScreenConfiguration.Parameters}
 * object and provides convenience methods that make it easy to create a WelcomeScreenConfiguration.
 */
public class WelcomeScreenBuilder {

    private WelcomeScreenConfiguration.Parameters mConfigParams;

    public WelcomeScreenBuilder(Context context) {
        mConfigParams = new WelcomeScreenConfiguration.Parameters(context);
    }

    /**
     * Enables or disables swipe to dismiss (disabled by default)
     * @param swipeToDismiss True to enable swipe to dismiss, false to disable it
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder swipeToDismiss(boolean swipeToDismiss) {
        mConfigParams.setSwipeToDismiss(swipeToDismiss);
        return this;
    }

    /**
     * Sets whether or not the welcome screen can be skipped.
     * Skipping is allowed by default
     * @param canSkip True to allow skipping, false to disable it
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder canSkip(boolean canSkip) {
        mConfigParams.setCanSkip(canSkip);
        return this;
    }

    /**
     * Only applies if skipping is allowed. Sets whether or not the back button can skip the welcome screen.
     * This is enabled by default.
     * @param backButtonSkips True to allow the back button to skip the welcome screen, false to disable it
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder backButtonSkips(boolean backButtonSkips) {
        mConfigParams.setBackButtonSkips(backButtonSkips);
        return this;
    }

    /**
     * Sets the theme of the welcome screen (Default is dark)
     * @param theme The theme to be used
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder theme(WelcomeScreenConfiguration.Theme theme) {
        mConfigParams.setTheme(theme);
        return this;
    }

    /**
     * Sets the resource id of the theme used by the welcome screen
     * @param resId The style resource id of the theme to be used
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder theme(@StyleRes int resId) {
        mConfigParams.setThemeResId(resId);
        return this;
    }

    /**
     * Set the color to be used when no background color is specified for a page
     * Default is colorPrimary if available (support library or lollipop+), otherwise material blue 600
     *
     * @param resId The color resource id to use as the default background color
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder defaultBackgroundColor(@ColorRes int resId) {
        mConfigParams.setDefaultBackgroundColor(resId);
        return this;
    }

    /**
     * Set the color to be used when no background color is specified for a page.
     * Default is colorPrimary if available (support library or lollipop+), otherwise material blue 600
     *
     * @param backgroundColor BackgroundColor to use as the default background color
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder defaultBackgroundColor(BackgroundColor backgroundColor) {
        mConfigParams.setDefaultBackgroundColor(backgroundColor);
        return this;
    }

    /**
     * Adds a page with a large image, heading, and description, uses the default background color
     * @param drawableId The drawable resource id to use for the image
     * @param title Text for the header TextView
     * @param description Text for the description TextView
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder basicPage(@DrawableRes int drawableId, String title, String description) {
        return basicPage(drawableId, title, description, 0);
    }

    /**
     * Adds a page with a large image, heading, and description
     * @param drawableId Drawable resource id to use for the image
     * @param title Text for the header TextView
     * @param description Text for the description TextView
     * @param colorResId Ccolor resource id to be used as the background color
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder basicPage(@DrawableRes int drawableId, String title, String description, @ColorRes int colorResId) {
        mConfigParams.add(BasicWelcomeFragment.newInstance(drawableId, title, description), colorResId);
        return this;
    }

    //This needs more work
    /*public WelcomeScreenBuilder preferencePage(int preferencesResId, @ColorRes int colorResId) {
        mConfigParams.add(PreferenceWelcomeFragment.newInstance(preferencesResId), colorResId);
        return this;
    }*/

    /**
     * Adds a page with a large image and a title, uses the default background color
     * @param resId The drawable resource id of an image
     * @param title Text for the title TextView
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder titlePage(@DrawableRes int resId, String title) {
        return titlePage(resId, title, 0);
    }

    /**
     * Adds a page with a large image and a title
     * @param resId The drawable resource id of an image
     * @param title Text for the title TextView
     * @param colorResId Color resource id to be used as the background color
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder titlePage(@DrawableRes int resId, String title, @ColorRes int colorResId) {
        mConfigParams.add(TitleFragment.newInstance(resId, title), colorResId);
        return this;
    }

    /**
     * Adds a fragment, uses the default background color
     * @param fragment Fragment to add
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder page(Fragment fragment) {
        page(fragment, 0);
        return this;
    }

    /**
     * Adds a fragment
     * @param fragment Fragment to add
     * @param colorResId Color resource id to be used as the background color
     * @return this WelcomeScreenBuilder
     */
    public WelcomeScreenBuilder page(Fragment fragment, @ColorRes int colorResId) {
        mConfigParams.add(fragment, colorResId);
        return this;
    }

    /**
     * @return A WelcomeScreenConfiguration using the parameters that have been set
     */
    public WelcomeScreenConfiguration build() {
        return new WelcomeScreenConfiguration(mConfigParams);
    }

}