package com.stephentuso.welcome;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

/**
 * A page that applies a parallax effect to the supplied layout.
 * The speed at which the children are moved is determined by their location in the layout,
 * the first will move the slowest and the last will move the fastest, or the opposite,
 * if the parallax factors are reversed. See documentation for the parallax factor methods
 * for more details.
 *
 * Created by stephentuso on 10/12/16.
 */

public class FullscreenParallaxPage extends WelcomePage<FullscreenParallaxPage> {

    private int layoutResId;
    private float firstParallaxFactor = 0.2f;
    private float lastParallaxFactor = 1f;
    private boolean parallaxRecursive = false;

    /**
     * A page that applies a parallax effect to the supplied layout.
     * The speed at which the children are moved is determined by their location in the layout.
     *
     * @param layoutResId The layout to show
     */
    public FullscreenParallaxPage(@LayoutRes int layoutResId) {
        this.layoutResId = layoutResId;
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
     * @return This FullscreenParallaxPage object to allow method calls to be chained
     */
    public FullscreenParallaxPage firstParallaxFactor(float factor) {
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
     * @param factor The parallax factor for the first item in the layout
     *
     * @return This FullscreenParallaxPage object to allow method calls to be chained
     */
    public FullscreenParallaxPage lastParallaxFactor(float factor) {
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
     * @return This FullscreenParallaxPage object to allow method calls to be chained
     */
    public FullscreenParallaxPage recursive(boolean recursive) {
        this.parallaxRecursive = recursive;
        return this;
    }

    /* Package local getters for testing */

    /* package */ int getLayoutResId() {
        return layoutResId;
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

    @Override
    public Fragment fragment() {
        return WelcomeFullScreenParallaxFragment.newInstance(layoutResId, firstParallaxFactor, lastParallaxFactor, parallaxRecursive);
    }

}
