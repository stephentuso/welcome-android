package com.tusoapps.welcome.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.tusoapps.welcome.config.WelcomeScreenConfig;
import com.tusoapps.welcome.ui.OnWelcomeScreenPageChangeListener;

/**
 * Created by stephentuso on 11/16/15.
 */
public class WelcomeScreenViewPagerIndicator extends SimpleViewPagerIndicator implements OnWelcomeScreenPageChangeListener {

    public WelcomeScreenViewPagerIndicator(Context context) {
        super(context);
    }

    public WelcomeScreenViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WelcomeScreenViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setup(WelcomeScreenConfig config) {
        setTotalPages(config.viewablePageCount());
    }
}
