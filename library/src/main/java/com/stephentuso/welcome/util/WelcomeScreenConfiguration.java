package com.stephentuso.welcome.util;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;

import com.stephentuso.welcome.R;
import com.stephentuso.welcome.ui.WelcomeScreenPage;
import com.stephentuso.welcome.ui.WelcomeScreenPageList;
import com.stephentuso.welcome.ui.BackgroundColor;

/**
 * Created by stephentuso on 11/15/15.
 */
public class WelcomeScreenConfiguration {

    public static final int NO_ANIMATION_SET = -1;
    public static final String NO_TYPEFACE = "";

    public enum Theme {
        /**
         * Dark background, light text
         */
        DARK(R.style.WelcomeScreenTheme),
        /**
         * Light background, dark text
         */
        LIGHT(R.style.WelcomeScreenTheme_Light);

        private int resId;

        Theme(int resId) {
            this.resId = resId;
        }
    }

    private Parameters mParameters;

    public WelcomeScreenConfiguration(Parameters parameters) {
        mParameters = parameters;
        if (isRtl() || Build.VERSION.SDK_INT < 11)
            mParameters.mSwipeToDismiss = false;

        if (mParameters.mSwipeToDismiss) {
            mParameters.addPage(new WelcomeScreenPage(new Fragment(), mParameters.mPages.getBackgroundColor(lastPageIndex())));
        }
    }

    public Fragment getFragment(int index) {
        return mParameters.mPages.getFragment(index);
    }

    public BackgroundColor[] getBackgroundColors() {
        return mParameters.mPages.getBackgroundColors();
    }

    public int pageCount() {
        return mParameters.mPages.size();
    }

    public int viewablePageCount() {
        return mParameters.mSwipeToDismiss ? mParameters.mPages.size() - 1 : mParameters.mPages.size();
    }

    public boolean getBackButtonSkips() {
        return mParameters.mBackButtonSkips;
    }

    public String getButtonTypefacePath() {
        return mParameters.mButtonTypefacePath;
    }

    public boolean getCanSkip() {
        return mParameters.mCanSkip;
    }

    public boolean getSwipeToDismiss() {
        return mParameters.mSwipeToDismiss;
    }

    public boolean isRtl() {
        return mParameters.isRtl();
    }

    public int firstPageIndex() {
        return isRtl() ? mParameters.mPages.size() - 1 : 0;
    }

    public int lastPageIndex() {
        return isRtl() ? 0 : mParameters.mPages.size() - 1;
    }

    public int lastViewablePageIndex() {
        return mParameters.mSwipeToDismiss ? Math.abs(lastPageIndex() - 1) : lastPageIndex();
    }

    public int getThemeResId() {
        return mParameters.mThemeResId;
    }

    public int getExitAnimation() {
        return mParameters.mExitAnimationResId;
    }

    public boolean getAnimateButtons() {
        return mParameters.animateButtons;
    }

    public static class Parameters {

        private WelcomeScreenPageList mPages = new WelcomeScreenPageList();
        private boolean mCanSkip = true;
        private boolean mBackButtonSkips = true;
        private BackgroundColor mDefaultBackgroundColor;
        private Context mContext;
        private int mThemeResId = Theme.DARK.resId;
        private boolean mSwipeToDismiss = false;
        private int mExitAnimationResId = NO_ANIMATION_SET;
        private String mButtonTypefacePath = NO_TYPEFACE;
        private boolean animateButtons = true;

        public Parameters(Context context) {
            mContext = context;
            setDefaultBackgroundColor(mContext);
        }

        private void setDefaultBackgroundColor(Context context) {
            final int standardBackgroundColor = ColorHelper.getColor(context, R.color.default_background_color);
            int defaultBackgroundColor = ColorHelper.resolveColorAttribute(context, R.attr.colorPrimary, standardBackgroundColor);
            if (defaultBackgroundColor == standardBackgroundColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                defaultBackgroundColor = ColorHelper.resolveColorAttribute(context, android.R.attr.colorPrimary, defaultBackgroundColor);
            mDefaultBackgroundColor = new BackgroundColor(defaultBackgroundColor, standardBackgroundColor);
        }

        public void setTheme(Theme theme) {
            mThemeResId = theme.resId;
        }

        public void setThemeResId(int resId) {
            mThemeResId = resId;
        }

        public void setButtonTypefacePath(String typefacePath) {
            mButtonTypefacePath = typefacePath;
        }

        public void setDefaultBackgroundColor(@ColorRes int resId) {
            ColorHelper.getColor(mContext, resId);
        }

        public void setDefaultBackgroundColor(BackgroundColor color) {
            mDefaultBackgroundColor = color;
        }

        public void setCanSkip(boolean canSkip) {
            mCanSkip = canSkip;
        }

        public void setBackButtonSkips(boolean backSkips) {
            mBackButtonSkips = backSkips;
        }

        public void add(Fragment fragment, @ColorRes int resId) {
            addPage(new WelcomeScreenPage(fragment, new BackgroundColor(getColor(resId), mDefaultBackgroundColor.value())));
        }

        public void setExitAnimation(@AnimRes int resId) {
            mExitAnimationResId = resId;
        }

        public void addPage(WelcomeScreenPage page) {
            if (isRtl()) {
                mPages.add(0, page);
            } else {
                mPages.add(page);
            }
        }

        private boolean isRtl() {
            return mContext.getResources().getBoolean(R.bool.isRtl);
        }

        public void setSwipeToDismiss(boolean swipe) {
            mSwipeToDismiss = swipe;
        }

        public void setAnimateButtons(boolean animateButtons) {
            this.animateButtons = animateButtons;
        }

        private Integer getColor(@ColorRes int resId) {
            if (resId == 0)
                return null;
            return ColorHelper.getColor(mContext, resId);
        }

    }

}