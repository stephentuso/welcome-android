package com.stephentuso.welcome.ui;

import android.view.View;

import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 11/15/15.
 */
public abstract class WelcomeScreenViewWrapper implements OnWelcomeScreenPageChangeListener {

    private View mView;
    private int mLastPageIndex;

    public WelcomeScreenViewWrapper(View view) {
        mView = view;
    }

    public abstract void onPageSelected(int pageIndex, int lastPageIndex);

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        onPageSelected(position, mLastPageIndex);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void setup(WelcomeScreenConfiguration config) {
        mLastPageIndex = config.lastViewablePageIndex();
    }

    protected void setVisibility(boolean visible) {
        mView.setEnabled(visible);
        if (visible) {
            show();
        } else {
            hide();
        }
    }

    protected void hide() {
        mView.setVisibility(View.INVISIBLE);
    }

    protected void show() {
        mView.setVisibility(View.VISIBLE);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        mView.setOnClickListener(listener);
    }

}
