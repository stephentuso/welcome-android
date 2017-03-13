package com.stephentuso.welcome;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/**
 * A page with a large title and an image
 *
 * Created by stephentuso on 10/11/16.
 */
public class TitlePage extends WelcomePage<TitlePage> {

    private int drawableResId;
    private String title;
    private boolean showParallax = true;
    private String titleTypefacePath = null;
    private int titleColor = WelcomeUtils.NO_COLOR_SET;

    /**
     * A page with a large title and an image
     *
     * @param drawableResId The resource id of the drawable to show
     * @param title Title
     */
    public TitlePage(@DrawableRes int drawableResId, String title) {
        this.drawableResId = drawableResId;
        this.title = title;
    }

    /**
     * Whether or not a parallax effect should be shown.
     * If true, the image will move at a faster rate than the text
     *
     * Default: true
     *
     * @param showParallax If parallax effect should be shown
     *
     * @return This TitlePage object to allow method calls to be chained
     */
    public TitlePage parallax(boolean showParallax) {
        this.showParallax = showParallax;
        return this;
    }

    /**
     * Set the typeface of the title
     *
     * @param typefacePath The path to a typeface in the assets folder
     *
     * @return This TitlePage object to allow method calls to be chained
     */
    public TitlePage titleTypeface(String typefacePath) {
        this.titleTypefacePath = typefacePath;
        return this;
    }

    /**
     * Set the color of the title
     *
     * @param color Color int
     *
     * @return This TitlePage object to allow method calls to be chained
     */
    public TitlePage titleColor(@ColorInt int color) {
        this.titleColor = color;
        return this;
    }

    /**
     * Set the color of the title from a color resource id
     *
     * @param context Context used to resolve color
     *
     * @param colorRes Resource id of color to set
     *
     * @return This TitlePage object to allow method calls to be chained
     */
    public TitlePage titleColorResource(Context context, @ColorRes int colorRes) {
        this.titleColor = ContextCompat.getColor(context, colorRes);
        return this;
    }

    /* Package local getters for testing */

    /* package */ int getDrawableResId() {
        return drawableResId;
    }

    /* package */ String getTitle() {
        return title;
    }

    /* package */ boolean getShowParallax() {
        return showParallax;
    }

    /* package */ String getTitleTypefacePath() {
        return titleTypefacePath;
    }

    /* package */ int getTitleColor() {
        return titleColor;
    }

    @Override
    public void setup(WelcomeConfiguration config) {
        super.setup(config);

        if (this.titleTypefacePath == null) {
            titleTypeface(config.getDefaultTitleTypefacePath());
        }

    }

    @Override
    public Fragment fragment() {
        return WelcomeTitleFragment.newInstance(drawableResId, title, showParallax, titleTypefacePath, titleColor);
    }

}
