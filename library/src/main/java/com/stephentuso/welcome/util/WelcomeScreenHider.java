package com.stephentuso.welcome.util;

import android.view.View;

import com.stephentuso.welcome.ui.OnWelcomeScreenPageChangeListener;

/**
 * Created by stephentuso on 11/16/15.
 */
public class WelcomeScreenHider implements OnWelcomeScreenPageChangeListener {

    private View mView;
    private Integer mLastPage = null;
    private OnViewHiddenListener mListener = null;
    private boolean enabled = false;

    public WelcomeScreenHider(View viewToHide) {
        mView = viewToHide;
    }

    public interface OnViewHiddenListener {
        void onViewHidden();
    }

    public void setOnViewHiddenListener(OnViewHiddenListener listener) {
        mListener = listener;
    }

    @Override
    public void setup(WelcomeScreenConfiguration config) {
        enabled = config.getSwipeToDismiss();
        mLastPage = config.lastPageIndex();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (!enabled)
            return;

        if (position == mLastPage - 1) {
            mView.setAlpha(1-positionOffset);
        }

        if (position == mLastPage && mListener != null) {
            mListener.onViewHidden();
        }

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
