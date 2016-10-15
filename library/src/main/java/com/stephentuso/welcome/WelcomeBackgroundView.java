package com.stephentuso.welcome;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by stephentuso on 11/16/15.
 */
public class WelcomeBackgroundView extends ColorChangingBackgroundView implements OnWelcomeScreenPageChangeListener {

    public WelcomeBackgroundView(Context context) {
        super(context);
    }

    public WelcomeBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WelcomeBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setup(WelcomeConfiguration config) {
        setColors(config.getBackgroundColors());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        setPosition(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        //Not used
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Not used
    }

}
