package com.stephentuso.welcome;

import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;

/**
 * A page with a title and description that applies a parallax effect to the supplied layout.
 * The speed at which the children are moved is determined by their location in the layout,
 * the first will move the slowest and the last will move the fastest, or the opposite,
 * if the parallax factors are reversed. See documentation for the parallax factor methods
 * for more details.
 *
 * Created by stephentuso on 10/12/16.
 */

public class ParallaxPage extends WelcomePage<ParallaxPage> {

    private int layoutResId;
    private String title;
    private String description;
    private float firstParallaxFactor = 0.2f;
    private float lastParallaxFactor = 1.0f;
    private boolean parallaxRecursive = false;
    private String headerTypefacePath = null;
    private String descriptionTyefacePath = null;

    /**
     * A page with a title and description that applies a parallax effect to the supplied layout.
     * The speed at which the children are moved is determined by their location in the layout,
     * the first will move the slowest and the last will move the fastest, or the opposite,
     * if the parallax factors are reversed. See documentation for the parallax factor methods
     * for more details.
     *
     * @param layoutResId The layout to show/apply parallax effect to
     * @param title A title, shown in large font
     * @param description String shown below title
     */
    public ParallaxPage(@LayoutRes int layoutResId, String title, String description) {
        this.layoutResId = layoutResId;
        this.title = title;
        this.description = description;
    }

    /**
     * The factor that the first item in the layout should move, relative to
     * the movement of the page. The factors for items between the first and last
     * will be evenly distributed between the first and last parallax factors.
     *
     * If a = page offset; x = parallax factor
     * item offset = a + ax
     *
     * Meaning the following values will have these results:
     * -1: Completely stationary
     *  0: Same speed as page
     *  1: Twice as fast as page
     *
     * Default: 0.2
     *
     * @param factor The parallax factor for the first item in the layout
     *
     * @return This ParallaxPage object to allow method calls to be chained
     */
    public ParallaxPage firstParallaxFactor(float factor) {
        this.firstParallaxFactor = factor;
        return this;
    }

    /**
     * The factor that the last item in the layout should move, relative to
     * the movement of the page. The factors for items between the first and last
     * will be evenly distributed between the first and last parallax factors.
     *
     * If a = page offset; x = parallax factor
     * item offset = a + ax
     *
     * Meaning the following values will have these results:
     * -1: Completely stationary
     *  0: Same speed as page
     *  1: Twice as fast as page
     *
     * Default: 1
     *
     * @param factor The parallax factor for the last item in the layout
     *
     * @return This ParallaxPage object to allow method calls to be chained
     */
    public ParallaxPage lastParallaxFactor(float factor) {
        this.lastParallaxFactor = factor;
        return this;
    }

    /**
     * Whether or not the parallax effect should be recursive. If false, ViewGroups
     * will be treated as a single item, and their children will move at the same speed. If true,
     * the parallax effect will be applied to ViewGroup children individually.
     *
     * @param recursive Whether or not the effect should be recursive (travel through ViewGroups)
     *
     * @return This ParallaxPage object to allow method calls to be chained
     */
    public ParallaxPage recursive(boolean recursive) {
        this.parallaxRecursive = recursive;
        return this;
    }

    /**
     * Set the typeface of the header
     *
     * @param path The path to a typeface in the assets folder
     *
     * @return This ParallaxPage object to allow method calls to be chained
     */
    public ParallaxPage headerTypeface(String path) {
        this.headerTypefacePath = path;
        return this;
    }

    /**
     * Set the typeface of the description
     *
     * @param path The path to a typeface in the assets folder
     *
     * @return This ParallaxPage object to allow method calls to be chained
     */
    public ParallaxPage descriptionTypefacePath(String path) {
        this.descriptionTyefacePath = path;
        return this;
    }

    /* Package local getters for testing */

    /* package */ int getLayoutResId() {
        return layoutResId;
    }

    /* package */ String getTitle() {
        return title;
    }

    /* package */ String getDescription() {
        return description;
    }

    /* package */ float getFirstParallaxFactor() {
        return firstParallaxFactor;
    }

    /* package */ float getLastParallaxFactor() {
        return lastParallaxFactor;
    }

    /* package */ boolean getParallaxRecursive() {
        return parallaxRecursive;
    }

    /* package */ String getHeaderTypefacePath() {
        return headerTypefacePath;
    }

    /* package */ String getDescriptionTyefacePath() {
        return descriptionTyefacePath;
    }

    @Override
    public void setup(WelcomeConfiguration config) {
        super.setup(config);

        if (headerTypefacePath == null) {
            this.headerTypeface(config.getDefaultHeaderTypefacePath());
        }

        if (descriptionTyefacePath == null) {
            this.descriptionTypefacePath(config.getDefaultDescriptionTypefacePath());
        }
    }

    @Override
    public Fragment fragment() {
        return WelcomeParallaxFragment.newInstance(layoutResId, title, description, firstParallaxFactor, lastParallaxFactor, parallaxRecursive, headerTypefacePath, descriptionTyefacePath);
    }
}
