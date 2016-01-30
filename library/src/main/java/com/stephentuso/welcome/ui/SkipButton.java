package com.stephentuso.welcome.ui;

import android.view.View;
import android.widget.TextView;

import com.stephentuso.welcome.util.WelcomeScreenConfiguration;
import com.stephentuso.welcome.util.WelcomeUtils;

/**
 * Created by stephentuso on 11/15/15.
 */
public class SkipButton extends WelcomeScreenViewWrapper {

    private boolean enabled = true;
    private boolean onlyShowOnFirstPage = false;

    public SkipButton(View button, boolean enabled) {
        super(button);
        this.enabled = enabled;
        if (!enabled)
            button.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setup(WelcomeScreenConfiguration config) {
        super.setup(config);
        onlyShowOnFirstPage = config.getShowNextButton();
        WelcomeUtils.setTypeface((TextView) this.getView(), config.getSkipButtonTypefacePath(), config.getContext());
    }

    @Override
    public void onPageSelected(int pageIndex, int lastPageIndex) {
        if (onlyShowOnFirstPage)
            setVisibility(enabled && pageIndex == 0);
        else
            setVisibility(enabled && pageIndex != lastPageIndex);
    }

}
