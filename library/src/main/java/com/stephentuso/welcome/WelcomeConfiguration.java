package com.stephentuso.welcome;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;

import com.stephentuso.welcome.ui.fragments.BasicWelcomeFragment;
import com.stephentuso.welcome.ui.fragments.FullScreenParallaxWelcomeFragment;
import com.stephentuso.welcome.ui.fragments.ParallaxWelcomeFragment;
import com.stephentuso.welcome.ui.fragments.TitleFragment;

/**
 * Created by stephentuso on 11/15/15.
 * All getters are in WelcomeConfiguration, all setters are in Builder
 */
public class WelcomeConfiguration {

    public static final int NO_ANIMATION_SET = -1;
    public static final int NO_THEME_SET = -1;

    private Builder builder;

    private WelcomeScreenPageList pages;

    public WelcomeConfiguration(Builder builder) {
        this.builder = builder;

        this.pages = new WelcomeScreenPageList();
        pages.addAll(builder.pages);

        if (pageCount() == 0) {
            throw new IllegalStateException("0 pages; at least one page must be added");
        }

        if (getSwipeToDismiss()) {
            FragmentWelcomePage blankPage = new FragmentWelcomePage(new WelcomeFragmentHolder() {
                @Override
                protected Fragment fragment() {
                    return new Fragment();
                }
            });
            blankPage.setBackgroundColor(pages.getBackgroundColor(pageCount() - 1));
            pages.add(blankPage);
        }

        if (isRtl()) {
            pages.reversePageOrder();
        }

    }

    /**
     * Get context
     *
     * @return The context the builder was initialized with
     */
    public Context getContext() {
        return builder.context;
    }

    /**
     * Get the fragment at the specified index
     *
     * @param index the index of the fragment
     * @return fragment
     */
    public Fragment getFragment(int index) {
        return pages.getFragment(index);
    }

    /**
     * Creates the fragment at the specified index
     *
     * @param index the index of the fragment
     * @return fragment
     */
    public Fragment createFragment(int index) {
        return pages.get(index).createFragment();
    }

    /**
     * Get the default background color
     *
     * @return default color
     */
    public BackgroundColor getDefaultBackgroundColor() {
        return builder.defaultBackgroundColor;
    }

    /**
     * Get the background colors for the pages
     * @return array of background colors
     */
    public BackgroundColor[] getBackgroundColors() {
        return pages.getBackgroundColors();
    }

    /**
     * Get the total number of pages, will be +1 if swipeToDismiss is enabled
     *
     * @return total number of pages
     */
    public int pageCount() {
        return pages.size();
    }

    /**
     * Get the number of viewable pages, not affected by swipeToDismiss
     *
     * @return number of viewable pages
     */
    public int viewablePageCount() {
        return getSwipeToDismiss() ? pageCount() - 1 : pageCount();
    }

    /**
     * @return list of pages
     */
    public WelcomeScreenPageList getPages() {
        return pages;
    }

    /**
     * Whether or not back button should skip
     *
     * @return backButtonSkips
     */
    public boolean getBackButtonSkips() {
        return builder.backButtonSkips;
    }

    /**
     * Whether or not back button should navigate through pages
     *
     * @return backButtonNavigatesPages
     */
    public boolean getBackButtonNavigatesPages() {
        return builder.backButtonNavigatesPages;
    }

    /**
     * Typeface path for skip button
     *
     * @return path to typeface in assets folder
     */
    public String getSkipButtonTypefacePath() {
        return builder.skipButtonTypefacePath;
    }

    /**
     * Typeface path for done button
     *
     * @return path to typeface in assets folder
     */
    public String getDoneButtonTypefacePath() {
        return builder.doneButtonTypefacePath;
    }

    /**
     * Default typeface path for titles
     *
     * @return path to typeface in assets folder
     */
    public String getDefaultTitleTypefacePath() {
        return builder.defaultTitleTypefacePath;
    }

    /**
     * Default typeface path for headers
     *
     * @return path to typeface in assets folder
     */
    public String getDefaultHeaderTypefacePath() {
        return builder.defaultHeaderTypefacePath;
    }

    /**
     * Default typeface path for descriptions
     *
     * @return path to typeface in assets folder
     */
    public String getDefaultDescriptionTypefacePath() {
        return builder.defaultDescriptionTypefacePath;
    }

    /**
     * True if can skip, false if not
     *
     * @return canSkip
     */
    public boolean getCanSkip() {
        return builder.canSkip;
    }

    /**
     * Check if swipeToDismiss is enabled
     * Returns false if SDK_INT is less than 11
     *
     * @return swipeToDismiss
     */
    public boolean getSwipeToDismiss() {
        return builder.swipeToDismiss && Build.VERSION.SDK_INT >= 11;
    }

    /**
     * Check if layout is RTL
     *
     * @return true if RTL, false if not
     */
    public boolean isRtl() {
        return builder.context.getResources().getBoolean(R.bool.isRtl);
    }

    /**
     * Get the index of the first page, dependent
     * on if the layout is RTL
     *
     * @return first page index
     */
    public int firstPageIndex() {
        return isRtl() ? pages.size() - 1 : 0;
    }

    /**
     * Get the index of the last page, dependent
     * on if the layout is RTL
     *
     * @return last page index
     */
    public int lastPageIndex() {
        return isRtl() ? 0 : pages.size() - 1;
    }

    /**
     * Get the index of the last viewable page,
     * dependent on if the layout is RTL
     *
     * @return last viewable page index
     */
    public int lastViewablePageIndex() {
        return getSwipeToDismiss() ? Math.abs(lastPageIndex() - 1) : lastPageIndex();
    }

    /**
     * Get the resource id of the theme
     *
     * @return theme resource id
     */
    public int getThemeResId() {
        return builder.themeResId;
    }

    /**
     * Get the resource id of the exit animation
     *
     * @return animation resource id
     */
    public @AnimRes int getExitAnimation() {
        return builder.exitAnimationResId;
    }

    /**
     * Whether or not buttons should animate
     *
     * @return animate buttons
     */
    public boolean getAnimateButtons() {
        return builder.animateButtons;
    }

    /**
     * Whether or not a custom done button should be used.
     * If true, the default done button should be hidden
     *
     * @return use custom done button
     */
    public boolean getUseCustomDoneButton() {
        return builder.useCustomDoneButton;
    }

    /**
     * Whether or not the next button should be shown
     *
     * @return show next button
     */
    public boolean getShowNextButton() {
        return builder.showNextButton;
    }

    /**
     * Whether or not the previous button should be shown
     *
     * @return show previous button
     */
    public boolean getShowPrevButton() {
        return builder.showPrevButton;
    }

    /**
     * Whether or not the action bar back button should be shown
     *
     * @return show action bar back button
     */
    public boolean getShowActionBarBackButton() {
        return builder.showActionBarBackButton;
    }

    public static class Builder {

        protected WelcomeScreenPageList pages = new WelcomeScreenPageList();
        protected boolean canSkip = true;
        protected boolean backButtonSkips = true;
        protected boolean backButtonNavigatesPages = true;
        protected BackgroundColor defaultBackgroundColor;
        protected Context context;
        protected int themeResId = NO_THEME_SET;
        protected boolean swipeToDismiss = false;
        protected int exitAnimationResId = NO_ANIMATION_SET;
        protected String skipButtonTypefacePath = null;
        protected String doneButtonTypefacePath = null;
        protected String defaultTitleTypefacePath = null;
        protected String defaultHeaderTypefacePath = null;
        protected String defaultDescriptionTypefacePath = null;
        protected boolean animateButtons = true;
        protected boolean useCustomDoneButton = false;
        protected boolean showNextButton = true;
        protected boolean showPrevButton = false;
        protected boolean showActionBarBackButton = false;

        /**
         * Creates a new Builder
         *
         * @param context Context object
         */
        public Builder(Context context) {
            this.context = context;
            initDefaultBackgroundColor(this.context);
        }

        private void initDefaultBackgroundColor(Context context) {
            // Default background color
            final int standardBackgroundColor = ColorHelper.getColor(context, R.color.default_background_color);

            // AppCompat colorPrimary
            int defaultBackgroundColor = ColorHelper.resolveColorAttribute(context, R.attr.colorPrimary, standardBackgroundColor);

            // Android system colorPrimary
            if (defaultBackgroundColor == standardBackgroundColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                defaultBackgroundColor = ColorHelper.resolveColorAttribute(context, android.R.attr.colorPrimary, defaultBackgroundColor);

            this.defaultBackgroundColor = new BackgroundColor(defaultBackgroundColor, standardBackgroundColor);
        }

        /**
         * Builds the configuration
         *
         * @return A {@link WelcomeConfiguration} with the parameters set on this object
         */
        public WelcomeConfiguration build() {
            return new WelcomeConfiguration(this);
        }

        /**
         * Enables or disables swipe to dismiss (disabled by default)
         *
         * @param swipeToDismiss True to enable swipe to dismiss, false to disable it
         * @return this Builder object to allow method calls to be chained
         */
        public Builder swipeToDismiss(boolean swipeToDismiss) {
            this.swipeToDismiss = swipeToDismiss;
            return this;
        }

        /**
         * Sets whether or not the welcome screen can be skipped.
         * Skipping is allowed by default
         *
         * @param canSkip True to allow skipping, false to disable it
         * @return this Builder object to allow method calls to be chained
         */
        public Builder canSkip(boolean canSkip) {
            this.canSkip = canSkip;
            return this;
        }

        /**
         * Only applies if skipping is allowed. Sets whether or not the back button can skip the welcome screen.
         * This is enabled by default.
         *
         * @param backButtonSkips True to allow the back button to skip the welcome screen, false to disable it
         * @return this Builder object to allow method calls to be chained
         */
        public Builder backButtonSkips(boolean backButtonSkips) {
            this.backButtonSkips = backButtonSkips;
            return this;
        }

        /**
         * Set whether or not pressing the back button will move to the previous page in the welcome screen.
         * This is true by default
         *
         * @return this Builder object to allow method calls to be chained
         */
        public Builder backButtonNavigatesPages(boolean navigatesPages) {
            this.backButtonNavigatesPages = navigatesPages;
            return this;
        }

        /**
         * Set whether or not the buttons fade out/in when changing visibilty
         *
         * @param animateButtons True to animate buttons, false to not
         * @return this Builder object to allow method calls to be chained
         */
        public Builder animateButtons(boolean animateButtons) {
            this.animateButtons = animateButtons;
            return this;
        }

        /**
         * Indicate that a done button is going to be provided in a custom fragment.
         * Use {@link WelcomeScreenFinisher#finish() WelcomeScreenFinisher.finish()} in your button's onClickListener
         * to close the welcome screen correctly.
         *
         * @param useCustomDoneButton Whether or not a done button will be present in the last fragment
         * @return this Builder object to allow method calls to be chained
         */
        public Builder useCustomDoneButton(boolean useCustomDoneButton) {
            this.useCustomDoneButton = useCustomDoneButton;
            return this;
        }

        /**
         * Set the visibility of the next button
         *
         * @param showNextButton Whether or not to show the next button
         * @return this Builder object to allow method calls to be chained
         */
        public Builder showNextButton(boolean showNextButton) {
            this.showNextButton = showNextButton;
            return this;
        }

        /**
         * Sets the visibility of the previous button (navigates back through the pages).
         * This shows in the same spot as the skip button. If this welcome screen can be skipped,
         * setting this to true will hide the skip button on all but the first page.
         *
         * @param showPrevButton Whether or not to show the previous button
         * @return this Builder object to allow method calls to be chained
         */
        public Builder showPrevButton(boolean showPrevButton) {
            this.showPrevButton = showPrevButton;
            return this;
        }

        /**
         * If the action bar is enabled, setting this to true will cause the back
         * button to be shown. Tapping it will cancel the welcome screen.
         * (Show the action bar with a custom theme, set 'windowActionBar' to true
         * and 'windowNoTitle' to false)
         *
         * @param showBackButton Whether or not to show the back button on the action bar
         * @return this Builder object to allow method calls to be chained
         */
        public Builder showActionBarBackButton(boolean showBackButton) {
            this.showActionBarBackButton = showBackButton;
            return this;
        }

        /**
         * Set the path to a typeface (in assets) to be used for the skip button
         *
         * @param typefacePath The path to a typeface file in assets folder
         * @return this Builder object to allow method calls to be chained
         */
        public Builder skipButtonTypefacePath(String typefacePath) {
            this.skipButtonTypefacePath = typefacePath;
            return this;
        }

        /**
         * Set the path to a typeface (in assets) to be used for the done button
         *
         * @param typefacePath The path to a typeface file in assets folder
         * @return this Builder object to allow method calls to be chained
         */
        public Builder doneButtonTypefacePath(String typefacePath) {
            this.doneButtonTypefacePath = typefacePath;
            return this;
        }

        /**
         * Set the path to a typeface (in assets) to be used by default for titles
         *
         * @param typefacePath The path to a typeface file in assets folder
         * @return this Builder object to allow method calls to be chained
         */
        public Builder defaultTitleTypefacePath(String typefacePath) {
            this.defaultTitleTypefacePath = typefacePath;
            return this;
        }

        /**
         * Set the path to a typeface (in assets) to be used by default for headers
         *
         * @param typefacePath The path to a typeface file in assets folder
         * @return this Builder object to allow method calls to be chained
         */
        public Builder defaultHeaderTypefacePath(String typefacePath) {
            this.defaultHeaderTypefacePath = typefacePath;
            return this;
        }

        /**
         * Set the path to a typeface (in assets) to be used by default for descriptions
         *
         * @param typefacePath The path to a typeface file in assets folder
         * @return this Builder object to allow method calls to be chained
         */
        public Builder defaultDescriptionTypefacePath(String typefacePath) {
            this.defaultDescriptionTypefacePath = typefacePath;
            return this;
        }

        /**
         * Set the animation that is used when the welcome screen closes
         *
         * @param exitAnimationResId The resource id of the animation to use
         * @return this Builder object to allow method calls to be chained
         */
        public Builder exitAnimation(@AnimRes int exitAnimationResId) {
            this.exitAnimationResId = exitAnimationResId;
            return this;
        }

        /**
         * @deprecated Set the theme in the android manifest
         *
         * Sets the resource id of the theme used by the welcome screen.
         * This will override the theme set in the manifest.
         *
         * @param resId The style resource id of the theme to be used
         * @return this Builder object to allow method calls to be chained
         */
        @Deprecated
        public Builder theme(@StyleRes int resId) {
            this.themeResId = resId;
            return this;
        }

        /**
         * Set the color to be used when no background color is specified for a page
         * Default is colorPrimary if available (support library or lollipop+), otherwise material blue 600
         *
         * @param resId The color resource id to use as the default background color
         * @return this Builder object to allow method calls to be chained
         */
        public Builder defaultBackgroundColor(@ColorRes int resId) {
            this.defaultBackgroundColor = new BackgroundColor(ColorHelper.getColor(context, resId));
            return this;
        }

        /**
         * Set the color to be used when no background color is specified for a page.
         * Default is colorPrimary if available (support library or lollipop+), otherwise material blue 600
         *
         * @param backgroundColor BackgroundColor to use as the default background color
         * @return this Builder object to allow method calls to be chained
         */
        public Builder defaultBackgroundColor(BackgroundColor backgroundColor) {
            this.defaultBackgroundColor = backgroundColor;
            return this;
        }

        /**
         * Adds a fragment, uses the default background color
         *
         * @param fragmentHolder FragmentHolder that creates the fragment to add
         * @return this WelcomeScreenBuilder object to allow method calls to be chained
         */
        public Builder page(WelcomeFragmentHolder fragmentHolder) {
            return page(fragmentHolder, 0);
        }

        /**
         * Adds a fragment
         *
         * @param fragmentHolder FragmentHolder that creates the fragment to add
         * @param resId Color resource id to be used as the background color
         *
         * @return this Builder object to allow method calls to be chained
         */
        public Builder page(WelcomeFragmentHolder fragmentHolder, @ColorRes int resId) {
            return page(new FragmentWelcomePage(fragmentHolder), resId);
        }

        /**
         * Adds a fragment
         *
         * @param fragmentHolder FragmentHolder that creates the fragment to add
         * @param backgroundColor Background color for this page
         *
         * @return this Builder object to allow method calls to be chained
         */
        public Builder page(WelcomeFragmentHolder fragmentHolder, BackgroundColor backgroundColor) {
            return page(new FragmentWelcomePage(fragmentHolder), backgroundColor);
        }

        /**
         * Adds a page, uses the default background color
         *
         * @param page The page to add
         *
         * @return this Builder object to allow method calls to be chained
         */
        public Builder page(WelcomePage page) {
            return page(page, defaultBackgroundColor);
        }

        /**
         * Adds a page
         *
         * @param page The page to add
         * @param colorResId The background color of the page
         *
         * @return this Builder object to allow method calls to be chained
         */
        public Builder page(WelcomePage page, @ColorRes int colorResId) {
            return page(page, new BackgroundColor(getColor(colorResId), defaultBackgroundColor.value()));
        }

        /**
         * Adds a page
         *
         * @param page The page to add
         * @param backgroundColor The background color of the page
         *
         * @return this Builder object to allow method calls to be chained
         */
        public Builder page(WelcomePage page, BackgroundColor backgroundColor) {
            page.setIndex(pages.size());
            page.setBackgroundColor(backgroundColor);
            pages.add(page);
            return this;
        }

        /**
         * Adds a page with a large image, heading, and description, uses the default background color
         *
         * @param drawableId The drawable resource id to use for the image
         * @param title Text for the header TextView
         * @param description Text for the description TextView
         * @return this Builder object to allow method calls to be chained
         */
        public Builder basicPage(@DrawableRes int drawableId, String title, String description) {
            return basicPage(drawableId, title, description, 0);
        }

        /**
         * Adds a page with a large image, heading, and description
         *
         * @param drawableId Drawable resource id to use for the image
         * @param title Text for the header TextView
         * @param description Text for the description TextView
         * @param colorResId Color resource id to be used as the background color
         * @return this Builder object to allow method calls to be chained
         */
        public Builder basicPage(@DrawableRes final int drawableId, final String title, final String description, @ColorRes int colorResId) {
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
         * @return this Builder object to allow method calls to be chained
         */
        public Builder basicPage(@DrawableRes final int drawableId, final String title, final String description, @ColorRes int colorResId, final boolean showParallaxAnim) {
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
         * @return this Builder object to allow method calls to be chained
         */
        public Builder basicPage(@DrawableRes final int drawableId, final String title, final String description, @ColorRes int colorResId,
                                              final boolean showParallaxAnim, final String headerTypefacePath, final String descriptionTypefacePath) {
            page(new WelcomeFragmentHolder() {
                @Override
                public Fragment fragment() {
                    return BasicWelcomeFragment.newInstance(drawableId, title, description, showParallaxAnim, headerTypefacePath, descriptionTypefacePath);
                }
            }, colorResId);
            return this;
        }

        /**
         * Adds a page with a large image and a title, uses the default background color
         *
         * @param resId The drawable resource id of an image
         * @param title Text for the title TextView
         * @return this Builder object to allow method calls to be chained
         */
        public Builder titlePage(@DrawableRes int resId, String title) {
            return titlePage(resId, title, 0);
        }

        /**
         * Adds a page with a large image and a title
         *
         * @param resId The drawable resource id of an image
         * @param title Text for the title TextView
         * @param colorResId Color resource id to be used as the background color
         * @return this Builder object to allow method calls to be chained
         */
        public Builder titlePage(@DrawableRes final int resId, final String title, @ColorRes int colorResId) {
            return titlePage(resId, title, colorResId, true);
        }

        /**
         * Adds a page with a large image and a title
         *
         * @param resId The drawable resource id of an image
         * @param title Text for the title TextView
         * @param colorResId Color resource id to be used as the background color
         * @param showParallaxAnim Whether or not to show a parallax animation as the page is scrolled
         * @return this Builder object to allow method calls to be chained
         */
        public Builder titlePage(@DrawableRes final int resId, final String title, @ColorRes int colorResId, final boolean showParallaxAnim) {
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
         * @return this Builder object to allow method calls to be chained
         */
        public Builder titlePage(@DrawableRes final int resId, final String title, @ColorRes int colorResId, final boolean showParallaxAnim,
                                              final String titleTypefacePath) {
            page(new WelcomeFragmentHolder() {
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
         * @return this Builder object to allow method calls to be chained
         */
        public Builder parallaxPage(@LayoutRes final int resId, final String title, final String description) {
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
         * @return this Builder object to allow method calls to be chained
         */
        public Builder parallaxPage(@LayoutRes final int resId, final String title, final String description, @ColorRes int colorResId) {
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
         * @return this Builder object to allow method calls to be chained
         */
        public Builder parallaxPage(@LayoutRes final int resId, final String title, final String description, @ColorRes int colorResId,
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
         * @return this Builder object to allow method calls to be chained
         */
        public Builder parallaxPage(@LayoutRes final int resId, final String title, final String description, @ColorRes int colorResId,
                                                 final float startParallaxFactor, final float endParallaxFactor, final String headerTypefacePath, final String descriptionTypefacePath) {
            page(new WelcomeFragmentHolder() {
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
         * @return this Builder object to allow method calls to be chained
         */
        public Builder fullScreenParallaxPage(@LayoutRes final int resId) {
            return fullScreenParallaxPage(resId, 0, 0.2f, 1.0f);
        }

        /**
         * Adds a page that applies a parallax effect to the supplied layout.
         * The speed at which the children are moved is determined by their location in the layout,
         * the first will move the slowest and the last will move the fastest.
         *
         * @param resId The layout resource id to apply the parallax effect to
         * @param colorResId Color resource id to be used as the background color
         * @return this Builder object to allow method calls to be chained
         */
        public Builder fullScreenParallaxPage(@LayoutRes final int resId, @ColorRes final int colorResId) {
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
         * @return this Builder object to allow method calls to be chained
         */
        public Builder fullScreenParallaxPage(@LayoutRes final int resId, @ColorRes final int colorResId, final float startParallaxFactor, final float endParallaxFactor) {
            page(new WelcomeFragmentHolder() {
                @Override
                protected Fragment fragment() {
                    return FullScreenParallaxWelcomeFragment.newInstance(resId, startParallaxFactor, endParallaxFactor, false);
                }
            }, colorResId);
            return this;
        }

        private Integer getColor(@ColorRes int resId) {
            if (resId == 0)
                return null;
            return ColorHelper.getColor(context, resId);
        }

    }

}