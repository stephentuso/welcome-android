package com.stephentuso.welcome;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;

/**
 * A page with a large image, header, and description
 *
 * Created by stephentuso on 10/11/16.
 */
public class BasicPage extends WelcomePage<BasicPage> {

    private int drawableResId;
    private String title;
    private String description;
    private boolean showParallax = true;
    private String headerTypefacePath = null;
    private String descriptionTypefacePath = null;

    /**
     * A page with a large image, header, and description
     *
     * @param drawableResId Resource id of drawable to show
     * @param title Title, shown in large font
     * @param description Description, shown beneath title
     */
    public BasicPage(@DrawableRes int drawableResId, String title, String description) {
        this.drawableResId = drawableResId;
        this.title = title;
        this.description = description;
    }

    /**
     * Whether or not a parallax effect should be shown.
     * If true, the image will move at a faster rate than the text
     *
     * Default: true
     *
     * @param showParallax If parallax effect should be shown
     *
     * @return This BasicPage object to allow method calls to be chained
     */
    public BasicPage parallax(boolean showParallax) {
        this.showParallax = showParallax;
        return this;
    }

    /**
     * Set the typeface of the header
     *
     * @param typefacePath The path to a typeface in the assets folder
     *
     * @return This BasicPage object to allow method calls to be chained
     */
    public BasicPage headerTypeface(String typefacePath) {
        this.headerTypefacePath = typefacePath;
        return this;
    }

    /**
     * Set the typeface of the description
     *
     * @param typefacePath The path to a typeface in the assets folder
     *
     * @return This BasicPage object to allow method calls to be chained
     */
    public BasicPage descriptionTypeface(String typefacePath) {
        this.descriptionTypefacePath = typefacePath;
        return this;
    }

    /* Package local getters for testing */

    /* package */ int getDrawableResId() {
        return this.drawableResId;
    }

    /* package */ String getTitle() {
        return title;
    }

    /* package */ String getDescription() {
        return description;
    }

    /* package */ boolean getShowParallax() {
        return this.showParallax;
    }

    /* package */ String getHeaderTypefacePath() {
        return this.headerTypefacePath;
    }

    /* package */ String getDescriptionTypefacePath() {
        return this.descriptionTypefacePath;
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
    public Fragment fragment() {
        return WelcomeBasicFragment.newInstance(drawableResId, title, description, showParallax, headerTypefacePath, descriptionTypefacePath);
    }

}
