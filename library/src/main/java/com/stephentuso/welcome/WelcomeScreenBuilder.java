package com.stephentuso.welcome;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;

import com.stephentuso.welcome.ui.BackgroundColor;
import com.stephentuso.welcome.ui.WelcomeFragmentHolder;
import com.stephentuso.welcome.ui.fragments.BasicWelcomeFragment;
import com.stephentuso.welcome.ui.fragments.FullScreenParallaxWelcomeFragment;
import com.stephentuso.welcome.ui.fragments.ParallaxWelcomeFragment;
import com.stephentuso.welcome.ui.fragments.TitleFragment;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 11/16/15.
 *
 * Class that wraps a {@link com.stephentuso.welcome.util.WelcomeScreenConfiguration.Parameters WelcomeScreenConfiguration.Parameters}
 * object and provides a convenient way to create a {@link com.stephentuso.welcome.util.WelcomeScreenConfiguration WelcomeScreenConfiguration}.
 */
public class WelcomeScreenBuilder {

    private WelcomeScreenConfiguration.Parameters mConfigParams;
    private String defaultTitleTypefacePath = "";
    private String defaultHeaderTypefacePath = "";
    private String defaultDescriptionTypefacePath = "";

    public WelcomeScreenBuilder(Context context) {
        mConfigParams = new WelcomeScreenConfiguration.Parameters(context);
    }

    /**
     * Enables or disables swipe to dismiss (disabled by default)
     *
     * @param swipeToDismiss True to enable swipe to dismiss, false to disable it
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder swipeToDismiss(boolean swipeToDismiss) {
        mConfigParams.setSwipeToDismiss(swipeToDismiss);
        return this;
    }

    /**
     * Sets whether or not the welcome screen can be skipped.
     * Skipping is allowed by default
     *
     * @param canSkip True to allow skipping, false to disable it
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder canSkip(boolean canSkip) {
        mConfigParams.setCanSkip(canSkip);
        return this;
    }

    /**
     * Only applies if skipping is allowed. Sets whether or not the back button can skip the welcome screen.
     * This is enabled by default.
     *
     * @param backButtonSkips True to allow the back button to skip the welcome screen, false to disable it
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder backButtonSkips(boolean backButtonSkips) {
        mConfigParams.setBackButtonSkips(backButtonSkips);
        return this;
    }

    /**
     * Set whether or not pressing the back button will move to the previous page in the welcome screen.
     * This is true by default
     *
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder backButtonNavigatesPages(boolean navigatesPages) {
        mConfigParams.setBackButtonNavigatesPages(navigatesPages);
        return this;
    }

    /**
     * Set whether or not the buttons fade out/in when changing visibilty
     *
     * @param animateButtons True to animate buttons, false to not
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder animateButtons(boolean animateButtons) {
        mConfigParams.setAnimateButtons(animateButtons);
        return this;
    }

    /**
     * Indicate that a done button is going to be provided in a custom fragment.
     * Use {@link com.stephentuso.welcome.ui.WelcomeScreenFinisher#finish() WelcomeScreenFinisher.finish()} in your button's onClickListener
     * to close the welcome screen correctly.
     *
     * @param useCustomDoneButton Whether or not a done button will be present in the last fragment
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder useCustomDoneButton(boolean useCustomDoneButton) {
        mConfigParams.setUseCustomDoneButton(useCustomDoneButton);
        return this;
    }

    /**
     * Set the visibility of the next button
     *
     * @param showNextButton Whether or not to show the next button
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder showNextButton(boolean showNextButton) {
        mConfigParams.setShowNextButton(showNextButton);
        return this;
    }

    /**
     * Sets the visibility of the previous button (navigates back through the pages).
     * This shows in the same spot as the skip button. If this welcome screen can be skipped,
     * setting this to true will hide the skip button on all but the first page.
     *
     * @param showPrevButton Whether or not to show the previous button
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder showPrevButton(boolean showPrevButton) {
        mConfigParams.setShowPrevButton(showPrevButton);
        return this;
    }

    /**
     * If the action bar is enabled, setting this to true will cause the back
     * button to be shown. Tapping it will cancel the welcome screen.
     * (Show the action bar with a custom theme, set 'windowActionBar' to true
     * and 'windowNoTitle' to false)
     *
     * @param showBackButton Whether or not to show the back button on the action bar
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder showActionBarBackButton(boolean showBackButton) {
        mConfigParams.setShowActionBarBAckButton(showBackButton);
        return this;
    }

    /**
     * Set the path to a typeface (in assets) to be used for the skip button
     *
     * @param typefacePath The path to a typeface file in assets folder
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder skipButtonTypefacePath(String typefacePath) {
        mConfigParams.setSkipButtonTypefacePath(typefacePath);
        return this;
    }

    /**
     * Set the path to a typeface (in assets) to be used for the done button
     *
     * @param typefacePath The path to a typeface file in assets folder
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder doneButtonTypefacePath(String typefacePath) {
        mConfigParams.setDoneButtonTypefacePath(typefacePath);
        return this;
    }

    /**
     * Set the path to a typeface (in assets) to be used by default for titles
     *
     * @param typefacePath The path to a typeface file in assets folder
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder defaultTitleTypefacePath(String typefacePath) {
        this.defaultTitleTypefacePath = typefacePath;
        return this;
    }

    /**
     * Set the path to a typeface (in assets) to be used by default for headers
     *
     * @param typefacePath The path to a typeface file in assets folder
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder defaultHeaderTypefacePath(String typefacePath) {
        this.defaultHeaderTypefacePath = typefacePath;
        return this;
    }

    /**
     * Set the path to a typeface (in assets) to be used by default for descriptions
     *
     * @param typefacePath The path to a typeface file in assets folder
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder defaultDescriptionTypefacePath(String typefacePath) {
        this.defaultDescriptionTypefacePath = typefacePath;
        return this;
    }

    /**
     * Set the animation that is used when the welcome screen closes
     *
     * @param exitAnimation The animation to use
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder exitAnimation(@AnimRes int exitAnimation) {
        mConfigParams.setExitAnimation(exitAnimation);
        return this;
    }

    /**
     * Sets the theme of the welcome screen (Default is dark)
     *
     * @param theme The theme to be used
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder theme(WelcomeScreenConfiguration.Theme theme) {
        mConfigParams.setTheme(theme);
        return this;
    }

    /**
     * Sets the resource id of the theme used by the welcome screen
     *
     * @param resId The style resource id of the theme to be used
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
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
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
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
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder defaultBackgroundColor(BackgroundColor backgroundColor) {
        mConfigParams.setDefaultBackgroundColor(backgroundColor);
        return this;
    }

    /**
     * Adds a page with a large image, heading, and description, uses the default background color
     *
     * @param drawableId The drawable resource id to use for the image
     * @param title Text for the header TextView
     * @param description Text for the description TextView
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder basicPage(@DrawableRes int drawableId, String title, String description) {
        return basicPage(drawableId, title, description, 0);
    }

    /**
     * Adds a page with a large image, heading, and description
     *
     * @param drawableId Drawable resource id to use for the image
     * @param title Text for the header TextView
     * @param description Text for the description TextView
     * @param colorResId Color resource id to be used as the background color
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder basicPage(@DrawableRes final int drawableId, final String title, final String description, @ColorRes int colorResId) {
        return basicPage(drawableId, title, description, colorResId, true);
    }

    /**
     * Adds a page with a large image, heading, and description
     *
     * @param drawableId Drawable resource id to use for the image
     * @param title Text for the header TextView
     * @param description Text for the description TextView
     * @param colorResId Color resource id to be used as the background color
     * @param showParallaxAnim Whether or not to show a parallax animation as the page is scrolled
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder basicPage(@DrawableRes final int drawableId, final String title, final String description, @ColorRes int colorResId, final boolean showParallaxAnim) {
        return basicPage(drawableId, title, description, colorResId, showParallaxAnim, defaultHeaderTypefacePath, defaultDescriptionTypefacePath);
    }

    /**
     * Adds a page with a large image, heading, and description
     *
     * @param drawableId Drawable resource id to use for the image
     * @param title Text for the header TextView
     * @param description Text for the description TextView
     * @param colorResId Color resource id to be used as the background color
     * @param showParallaxAnim Whether or not to show a parallax animation as the page is scrolled
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder basicPage(@DrawableRes final int drawableId, final String title, final String description, @ColorRes int colorResId,
                                          final boolean showParallaxAnim, final String headerTypefacePath, final String descriptionTypefacePath) {
        mConfigParams.add(new WelcomeFragmentHolder() {
            @Override
            public Fragment fragment() {
                return BasicWelcomeFragment.newInstance(drawableId, title, description, showParallaxAnim, headerTypefacePath, descriptionTypefacePath);
            }
        }, colorResId);
        return this;
    }

    //This needs more work
    /*public WelcomeScreenBuilder preferencePage(int preferencesResId, @ColorRes int colorResId) {
        mConfigParams.add(PreferenceWelcomeFragment.newInstance(preferencesResId), colorResId);
        return this;
    }*/

    /**
     * Adds a page with a large image and a title, uses the default background color
     *
     * @param resId The drawable resource id of an image
     * @param title Text for the title TextView
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder titlePage(@DrawableRes int resId, String title) {
        return titlePage(resId, title, 0);
    }

    /**
     * Adds a page with a large image and a title
     *
     * @param resId The drawable resource id of an image
     * @param title Text for the title TextView
     * @param colorResId Color resource id to be used as the background color
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder titlePage(@DrawableRes final int resId, final String title, @ColorRes int colorResId) {
        return titlePage(resId, title, colorResId, true);
    }

    /**
     * Adds a page with a large image and a title
     *
     * @param resId The drawable resource id of an image
     * @param title Text for the title TextView
     * @param colorResId Color resource id to be used as the background color
     * @param showParallaxAnim Whether or not to show a parallax animation as the page is scrolled
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder titlePage(@DrawableRes final int resId, final String title, @ColorRes int colorResId, final boolean showParallaxAnim) {
        return titlePage(resId, title, colorResId, showParallaxAnim, defaultTitleTypefacePath);
    }

    /**
     * Adds a page with a large image and a title
     *
     * @param resId The drawable resource id of an image
     * @param title Text for the title TextView
     * @param colorResId Color resource id to be used as the background color
     * @param showParallaxAnim Whether or not to show a parallax animation as the page is scrolled
     * @param titleTypefacePath The path to a typeface in assets to be used for the title
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder titlePage(@DrawableRes final int resId, final String title, @ColorRes int colorResId, final boolean showParallaxAnim,
                                          final String titleTypefacePath) {
        mConfigParams.add(new WelcomeFragmentHolder() {
            @Override
            public Fragment fragment() {
                return TitleFragment.newInstance(resId, title, showParallaxAnim, titleTypefacePath);
            }
        }, colorResId);
        return this;
    }

    /**
     * Adds a page with a header and description that applies a parallax effect to the supplied layout.
     * The speed at which the children are moved is determined by their location in the layout,
     * the first will move the slowest and the last will move the fastest.
     *
     * @param resId The layout resource id to apply the parallax effect to
     * @param title Text for the header TextView
     * @param description Text for the description TextView
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder parallaxPage(@LayoutRes final int resId, final String title, final String description) {
        return parallaxPage(resId, title, description, 0);
    }

    /**
     * Adds a page with a header and description that applies a parallax effect to the supplied layout.
     * The speed at which the children are moved is determined by their location in the layout,
     * the first will move the slowest and the last will move the fastest.
     *
     * @param resId The layout resource id to apply the parallax effect to
     * @param title Text for the header TextView
     * @param description Text for the description TextView
     * @param colorResId Color resource id to be used as the background color
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder parallaxPage(@LayoutRes final int resId, final String title, final String description, @ColorRes int colorResId) {
        return parallaxPage(resId, title, description, colorResId, 0.2f, 1.0f);
    }

    /**
     * Adds a page with a header and description that applies a parallax effect to the supplied layout.
     * The speed at which the children are moved is determined by their location in the layout,
     * the first will move the slowest and the last will move the fastest.
     *
     * @param resId The layout resource id to apply the parallax effect to
     * @param title Text for the header TextView
     * @param description Text for the description TextView
     * @param colorResId Color resource id to be used as the background color
     * @param startParallaxFactor The speed at which the first child should move. Negative values for slower than normal, positive for faster. The default value is 0.2.
     *                            A child with a factor of -1.0 will stay completely still, a child with a factor of 1.0 will move twice as fast.
     * @param endParallaxFactor The speed at which the last child should move. Negative values for slower than normal, positive for faster. The default value is 1.0.
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder parallaxPage(@LayoutRes final int resId, final String title, final String description, @ColorRes int colorResId,
                                             final float startParallaxFactor, final float endParallaxFactor) {
        return parallaxPage(resId, title, description, colorResId, startParallaxFactor, endParallaxFactor, defaultHeaderTypefacePath, defaultDescriptionTypefacePath);
    }

    /**
     * Adds a page with a header and description that applies a parallax effect to the supplied layout.
     * The speed at which the children are moved is determined by their location in the layout,
     * the first will move the slowest and the last will move the fastest.
     *
     * @param resId The layout resource id to apply the parallax effect to
     * @param title Text for the header TextView
     * @param description Text for the description TextView
     * @param colorResId Color resource id to be used as the background color
     * @param startParallaxFactor The speed at which the first child should move. Negative values for slower than normal, positive for faster. The default value is 0.2.
     *                            A child with a factor of -1.0 will stay completely still, a child with a factor of 1.0 will move twice as fast.
     * @param endParallaxFactor The speed at which the last child should move. Negative values for slower than normal, positive for faster. The default value is 1.0.
     * @param headerTypefacePath The path to a typeface in assets to be used for the header
     * @param descriptionTypefacePath The path to a typeface in assets to be used for the description
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder parallaxPage(@LayoutRes final int resId, final String title, final String description, @ColorRes int colorResId,
                                             final float startParallaxFactor, final float endParallaxFactor, final String headerTypefacePath, final String descriptionTypefacePath) {
        mConfigParams.add(new WelcomeFragmentHolder() {
            @Override
            protected Fragment fragment() {
                return ParallaxWelcomeFragment.newInstance(resId, title, description, startParallaxFactor, endParallaxFactor, false, headerTypefacePath, descriptionTypefacePath);
            }
        }, colorResId);
        return this;
    }

    /**
     * Adds a page that applies a parallax effect to the supplied layout.
     * The speed at which the children are moved is determined by their location in the layout,
     * the first will move the slowest and the last will move the fastest.
     * The default background color is used.
     *
     * @param resId The layout resource id to apply the parallax effect to
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder fullScreenParallaxPage(@LayoutRes final int resId) {
        return fullScreenParallaxPage(resId, 0, 0.2f, 1.0f);
    }

    /**
     * Adds a page that applies a parallax effect to the supplied layout.
     * The speed at which the children are moved is determined by their location in the layout,
     * the first will move the slowest and the last will move the fastest.
     *
     * @param resId The layout resource id to apply the parallax effect to
     * @param colorResId Color resource id to be used as the background color
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder fullScreenParallaxPage(@LayoutRes final int resId, @ColorRes final int colorResId) {
        return fullScreenParallaxPage(resId, colorResId, 0.2f, 1.0f);
    }

    /**
     * Adds a page that applies a parallax effect to the supplied layout.
     * The speed at which the children are moved is determined by their location in the layout,
     * the first will move the slowest and the last will move the fastest.
     *
     * @param resId The layout resource id to apply the parallax effect to
     * @param colorResId Color resource id to be used as the background color
     * @param startParallaxFactor The speed at which the first child should move. Negative values for slower than normal, positive for faster. The default value is 0.2.
     *                            A child with a factor of -1.0 will stay completely still, a child with a factor of 1.0 will move twice as fast.
     * @param endParallaxFactor The speed at which the last child should move. Negative values for slower than normal, positive for faster. The default value is 1.0.
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder fullScreenParallaxPage(@LayoutRes final int resId, @ColorRes final int colorResId, final float startParallaxFactor, final float endParallaxFactor) {
        mConfigParams.add(new WelcomeFragmentHolder() {
            @Override
            protected Fragment fragment() {
                return FullScreenParallaxWelcomeFragment.newInstance(resId, startParallaxFactor, endParallaxFactor, false);
            }
        }, colorResId);
        return this;
    }

    /**
     * Adds a fragment, uses the default background color
     *
     * @param fragmentHolder FragmentHolder that creates the fragment to add
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder page(WelcomeFragmentHolder fragmentHolder) {
        page(fragmentHolder, 0);
        return this;
    }

    /**
     * Adds a fragment
     *
     * @param fragmentHolder FragmentHolder that creates the fragment to add
     * @param colorResId Color resource id to be used as the background color
     * @return this WelcomeScreenBuilder object to allow method calls to be chained
     */
    public WelcomeScreenBuilder page(WelcomeFragmentHolder fragmentHolder, @ColorRes int colorResId) {
        mConfigParams.add(fragmentHolder, colorResId);
        return this;
    }

    /**
     * @return A {@link com.stephentuso.welcome.util.WelcomeScreenConfiguration WelcomeScreenConfiguration} with the parameters set on this object
     */
    public WelcomeScreenConfiguration build() {
        return new WelcomeScreenConfiguration(mConfigParams);
    }

}