package com.stephentuso.welcome.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.stephentuso.welcome.util.WelcomeScreenConfiguration;
import com.stephentuso.welcome.ui.OnWelcomeScreenPageChangeListener;

/**
 * Created by stephentuso on 11/16/15.
 */
public class WelcomeScreenBackgroundView extends ColorChangingBackgroundView implements OnWelcomeScreenPageChangeListener {

    public WelcomeScreenBackgroundView(Context context) {
        super(context);
    }

    public WelcomeScreenBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WelcomeScreenBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setup(WelcomeScreenConfiguration config) {
        setColors(config.getBackgroundColors());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        setPosition(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {}

}
