package com.stephentuso.welcome.util;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;

import com.stephentuso.welcome.R;
import com.stephentuso.welcome.ui.BackgroundColor;
import com.stephentuso.welcome.ui.WelcomeFragmentHolder;
import com.stephentuso.welcome.ui.WelcomeScreenPage;
import com.stephentuso.welcome.ui.WelcomeScreenPageList;

/**
 * Created by stephentuso on 11/15/15.
 * All getters are in WelcomeScreenConfiguration, all setters are in Parameters
 */
public class WelcomeScreenConfiguration {

    public static final int NO_ANIMATION_SET = -1;

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
            mParameters.addPage(new WelcomeScreenPage(new WelcomeFragmentHolder() {
                @Override
                public Fragment fragment() {
                    return new Fragment();
                }
            }, mParameters.mPages.getBackgroundColor(lastPageIndex())));
        }
    }

    public Context getContext() {
        return mParameters.mContext;
    }

    public Fragment getFragment(int index) {
        return mParameters.mPages.getFragment(index);
    }

    public Fragment createFragment(int index) {
        return mParameters.mPages.get(index).createFragment();
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

    public WelcomeScreenPageList getPages() {
        return mParameters.mPages;
    }

    public boolean getBackButtonSkips() {
        return mParameters.mBackButtonSkips;
    }

    public boolean getBackButtonNavigatesPages() {
        return mParameters.mBackButtonNavigatesPages;
    }

    public String getSkipButtonTypefacePath() {
        return mParameters.mSkipButtonTypefacePath;
    }

    public String getDoneButtonTypefacePath() {
        return mParameters.mDoneButtonTypefacePath;
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
        return mParameters.mAnimateButtons;
    }

    public boolean getUseCustomDoneButton() {
        return mParameters.mUseCustomDoneButton;
    }

    public boolean getShowNextButton() {
        return mParameters.mShowNextButton;
    }

    public boolean getShowPrevButton() {
        return mParameters.mShowPrevButton;
    }

    public boolean getShowActionBarBackButton() {
        return mParameters.mShowActionBarBackButton;
    }

    public static class Parameters {

        private WelcomeScreenPageList mPages = new WelcomeScreenPageList();
        private boolean mCanSkip = true;
        private boolean mBackButtonSkips = true;
        private boolean mBackButtonNavigatesPages = true;
        private BackgroundColor mDefaultBackgroundColor;
        private Context mContext;
        private int mThemeResId = Theme.DARK.resId;
        private boolean mSwipeToDismiss = false;
        private int mExitAnimationResId = NO_ANIMATION_SET;
        private String mSkipButtonTypefacePath = "";
        private String mDoneButtonTypefacePath = "";
        private boolean mAnimateButtons = true;
        private boolean mUseCustomDoneButton = false;
        private boolean mShowNextButton = true;
        private boolean mShowPrevButton = false;
        private boolean mShowActionBarBackButton = false;

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

        public void setSkipButtonTypefacePath(String typefacePath) {
            mSkipButtonTypefacePath = typefacePath;
        }

        public void setDoneButtonTypefacePath(String typefacePath) {
            mDoneButtonTypefacePath = typefacePath;
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

        public void setBackButtonNavigatesPages(boolean navigatesPages) {
            mBackButtonNavigatesPages = navigatesPages;
        }

        public void add(WelcomeFragmentHolder fragmentHolder, @ColorRes int resId) {
            addPage(new WelcomeScreenPage(fragmentHolder, new BackgroundColor(getColor(resId), mDefaultBackgroundColor.value())));
        }

        public void setExitAnimation(@AnimRes int resId) {
            mExitAnimationResId = resId;
        }

        public void addPage(WelcomeScreenPage page) {
            page.setIndex(mPages.size());
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
            this.mAnimateButtons = animateButtons;
        }

        public void setUseCustomDoneButton(boolean useCustomDoneButton) {
            this.mUseCustomDoneButton = useCustomDoneButton;
        }

        public void setShowNextButton(boolean showNextButton) {
            this.mShowNextButton = showNextButton;
        }

        public void setShowPrevButton(boolean showPrevButton) {
            this.mShowPrevButton = showPrevButton;
        }

        public void setShowActionBarBAckButton(boolean showBackButton) {
            this.mShowActionBarBackButton = showBackButton;
        }

        private Integer getColor(@ColorRes int resId) {
            if (resId == 0)
                return null;
            return ColorHelper.getColor(mContext, resId);
        }

    }

}