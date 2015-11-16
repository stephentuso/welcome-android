package com.tusoapps.welcome.ui;

import android.view.View;
import android.widget.Button;

/**
 * Created by stephentuso on 11/15/15.
 */
public class SkipButton extends WelcomeScreenButton{

    private boolean enabled = true;

    public SkipButton(View button, boolean enabled) {
        super(button);
        this.enabled = enabled;
        if (!enabled)
            button.setVisibility(View.GONE);
    }

    @Override
    public void onPageSelected(int pageIndex, int maxPageIndex) {
        setVisibility(enabled && pageIndex != maxPageIndex);
    }

}
