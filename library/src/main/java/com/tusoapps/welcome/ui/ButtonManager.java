package com.tusoapps.welcome.ui;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

/**
 * Created by stephentuso on 11/15/15.
 */
public class ButtonManager implements OnPageChangeListener {

    ViewPager mPager;
    ButtonSet mButtonSet;

    public ButtonManager(ViewPager pager, ButtonSet buttonSet) {
        mPager = pager;
        mButtonSet = buttonSet;
        mPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mButtonSet.onPageSelected(position, mPager.getAdapter().getCount() - 1);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
