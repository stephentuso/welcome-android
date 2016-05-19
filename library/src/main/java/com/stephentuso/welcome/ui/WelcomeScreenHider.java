package com.stephentuso.welcome.ui;

import android.os.Build;
import android.view.View;

import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

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

        if (!enabled) {
            return;
        }

        if (position == mLastPage && mListener != null) {
            mListener.onViewHidden();
        }

        if (Build.VERSION.SDK_INT < 11) {
            return;
        }

        if (position == mLastPage - 1) {
            mView.setAlpha(1-positionOffset);
        } else if (position < mLastPage - 1 && mView.getAlpha() != 1f) {
            mView.setAlpha(1f);
        }

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
