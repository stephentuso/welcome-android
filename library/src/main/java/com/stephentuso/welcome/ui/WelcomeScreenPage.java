package com.stephentuso.welcome.ui;

import android.support.v4.app.Fragment;

import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 11/15/15.
 */
public class WelcomeScreenPage implements OnWelcomeScreenPageChangeListener {

    private final WelcomeFragmentHolder fragmentHolder;
    private final BackgroundColor backgroundColor;
    private int index = -2;
    private boolean isRtl = false;
    private int totalPages = 0;

    /**
     * Interface to be implemented by fragments that are part of a WelcomeActivity
     */
    public interface OnChangeListener {

        /**
         * Called when this page is coming into view
         * @param pageIndex The position index of this page
         * @param offset The % offset of this page, negative if page is off the screen on the right, positive if off on the left
         * @param offsetPixels The offset of this page in pixels, negative if page is off the screen on the right, positive if off on the left
         */
        void onWelcomeScreenPageScrolled(int pageIndex, float offset, int offsetPixels);

        /**
         * Called when the selected page changes
         * @param pageIndex The position index of this page
         * @param selectedPageIndex The index of the page that was selected
         */
        void onWelcomeScreenPageSelected(int pageIndex, int selectedPageIndex);

        /**
         * Called when the scroll state of the ViewPager changes
         *
         * @param pageIndex The position index of this page
         * @param state The new scroll state
         */
        void onWelcomeScreenPageScrollStateChanged(int pageIndex, int state);
    }

    public WelcomeScreenPage(WelcomeFragmentHolder fragmentHolder, BackgroundColor backgroundColor) {
        this.fragmentHolder = fragmentHolder;
        this.backgroundColor = backgroundColor;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Fragment getFragment() {
        return fragmentHolder.getFragment();
    }

    public Fragment createFragment() {
        return fragmentHolder.createFragment();
    }

    public BackgroundColor getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setup(WelcomeScreenConfiguration config) {
        isRtl = config.isRtl();
        totalPages = config.pageCount();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        //Correct position for RTL. One is subtracted to make it zero indexed
        if (isRtl)
            position = totalPages - 1 - position;

        if (getFragment() != null && getFragment() instanceof OnChangeListener && position - index <= 1) {
            Fragment fragment = getFragment();

            int fragmentWidth = 0;
            if (fragment.getView() != null) {
                fragmentWidth = fragment.getView().getWidth();
            }

            boolean lowerPosition = isRtl ? position > index : position < index;
            float offset = lowerPosition ? -(1 - positionOffset) : positionOffset;
            int offsetPixels = lowerPosition ? -(fragmentWidth - positionOffsetPixels) : positionOffsetPixels;

            ((OnChangeListener) fragment).onWelcomeScreenPageScrolled(index, offset, offsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (getFragment() != null && getFragment() instanceof OnChangeListener) {
            ((OnChangeListener) getFragment()).onWelcomeScreenPageSelected(index, position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (getFragment() != null && getFragment() instanceof OnChangeListener) {
            ((OnChangeListener) getFragment()).onWelcomeScreenPageScrollStateChanged(index, state);
        }
    }
}
