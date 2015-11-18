package com.tusoapps.welcome.config;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;

import com.tusoapps.welcome.R;
import com.tusoapps.welcome.util.ColorHelper;

/**
 * Created by stephentuso on 11/15/15.
 */
public class WelcomeScreenConfig {

    public enum Theme {
        DARK(R.style.WelcomeScreenTheme),
        LIGHT(R.style.WelcomeScreenTheme_Light);

        private int resId;

        Theme(int resId) {
            this.resId = resId;
        }

    }

    private WelcomeScreenPageList mPages = new WelcomeScreenPageList();
    private boolean mCanSkip = true;
    private boolean mBackButtonSkips = true;
    private BackgroundColor mDefaultBackgroundColor;
    private Context mContext;
    private int mThemeResId = Theme.DARK.resId;
    private boolean mSwipeToDismiss = false;
    private int mExitAnimationResId = R.anim.fade_out;

    public WelcomeScreenConfig(Context context) {
        mContext = context;
        setDefaultBackgroundColor(context);
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

    public void setDefaultBackgroundColor(@ColorRes int resId) {
        ColorHelper.getColor(mContext, resId);
    }

    public void setDefaultBackgroundColor(BackgroundColor color) {
        mDefaultBackgroundColor = color;
    }

    public void add(Fragment fragment, @ColorRes int resId) {
        addPage(new WelcomeScreenPage(fragment, new BackgroundColor(getColor(resId), mDefaultBackgroundColor.value())));
    }

    public Fragment getFragment(int index) {
        return mPages.getFragment(index);
    }

    public BackgroundColor[] getBackgroundColors() {
        return mPages.getBackgroundColors();
    }

    public int pageCount() {
        return mPages.size();
    }

    public int viewablePageCount() {
        return mSwipeToDismiss ? mPages.size() - 1 : mPages.size();
    }

    public void addPage(WelcomeScreenPage page) {
        if (isRtl()) {
            mPages.add(0, page);
        } else {
            mPages.add(page);
        }
    }

    public void setCanSkip(boolean canSkip) {
        mCanSkip = canSkip;
    }

    public void setBackButtonSkips(boolean backSkips) {
        mBackButtonSkips = backSkips;
    }

    public boolean getBackButtonSkips() {
        return mBackButtonSkips;
    }

    public boolean getCanSkip() {
        return mCanSkip;
    }

    private Integer getColor(@ColorRes int resId) {
        if (resId == 0)
            return null;
        return ColorHelper.getColor(mContext, resId);
    }

    public void setSwipeToDismiss(boolean swipe) {
        mSwipeToDismiss = swipe;
    }

    public boolean getSwipeToDismiss() {
        return mSwipeToDismiss;
    }

    public boolean isRtl() {
        return mContext.getResources().getBoolean(R.bool.isRtl);
    }

    public int firstPageIndex() {
        return isRtl() ? mPages.size() - 1 : 0;
    }

    public int lastPageIndex() {
        return isRtl() ? 0 : mPages.size() - 1;
    }

    public int lastViewablePageIndex() {
        return mSwipeToDismiss ? Math.abs(lastPageIndex() - 1) : lastPageIndex();
    }

    public int getThemeResId() {
        return mThemeResId;
    }

    public void setExitAnimation(@AnimRes int resId) {
        mExitAnimationResId = resId;
    }

    public int getExitAnimation() {
        return mExitAnimationResId;
    }

    public void finish() {

        if (isRtl() || Build.VERSION.SDK_INT < 11)
            mSwipeToDismiss = false;

        if (mSwipeToDismiss) {
            addPage(new WelcomeScreenPage(new Fragment(), mPages.getBackgroundColor(lastPageIndex())));
        }
    }

}