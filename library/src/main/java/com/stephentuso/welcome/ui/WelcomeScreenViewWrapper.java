package com.stephentuso.welcome.ui;

import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;

import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 11/15/15.
 */
public abstract class WelcomeScreenViewWrapper implements OnWelcomeScreenPageChangeListener {

    private View mView;
    private int mFirstPageIndex = 0;
    private int mLastPageIndex = 0;
    private boolean mAnimate = true;

    public WelcomeScreenViewWrapper(View view) {
        mView = view;
    }

    public abstract void onPageSelected(int pageIndex, int firstPageIndex, int lastPageIndex);

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        onPageSelected(position, mFirstPageIndex, mLastPageIndex);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void setup(WelcomeScreenConfiguration config) {
        mFirstPageIndex = config.firstPageIndex();
        mLastPageIndex = config.lastViewablePageIndex();
        mAnimate = config.getAnimateButtons();
    }

    protected View getView() {
        return this.mView;
    }

    protected void setVisibility(boolean visible) {
        setVisibility(visible, mAnimate);
    }

    protected void setVisibility(boolean visible, boolean animate) {
        mView.setEnabled(visible);
        if (visible) {
            show(animate);
        } else {
            hide(animate);
        }
    }

    protected void hide(boolean animate) {
        if (animate) {
            hideWithAnimation();
        } else {
            hideImmediately();
        }
    }

    private void show(boolean animate) {
        if (animate) {
            showWithAnimation();
        } else {
            showImmediately();
        }
    }

    protected void hideImmediately() {
        mView.setVisibility(View.INVISIBLE);
    }

    protected void showImmediately() {
        setAlpha(1f);
        mView.setVisibility(View.VISIBLE);
    }

    protected void hideWithAnimation() {
        ViewPropertyAnimatorListener listener = new ViewPropertyAnimatorListener() {

            @Override
            public void onAnimationStart(View view) {}

            @Override
            public void onAnimationEnd(View view) {
                setAlpha(0f);
                mView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(View view) {
                setAlpha(0f);
                mView.setVisibility(View.INVISIBLE);
            }
        };

        ViewCompat.animate(mView).alpha(0f).setListener(listener).start();
    }

    protected void showWithAnimation() {

        ViewPropertyAnimatorListener listener = new ViewPropertyAnimatorListener() {

            @Override
            public void onAnimationStart(View view) {
                mView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(View view) {
                setAlpha(1f);
            }

            @Override
            public void onAnimationCancel(View view) {
                setAlpha(1f);
            }
        };

        ViewCompat.animate(mView).alpha(1f).setListener(listener).start();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        mView.setOnClickListener(listener);
    }

    private void setAlpha(float alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            mView.setAlpha(alpha);
    }

}
