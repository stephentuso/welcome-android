package com.tusoapps.welcome.ui;

import android.view.View;
import android.widget.Button;

/**
 * Created by stephentuso on 11/15/15.
 */
public abstract class WelcomeScreenButton {

    private View mButton;

    public WelcomeScreenButton(View button) {
        mButton = button;
    }

    public abstract void onPageSelected(int pageIndex, int maxPageIndex);

    protected void setVisibility(boolean visible) {
        mButton.setEnabled(visible);
        if (visible) {
            show();
        } else {
            hide();
        }
    }

    protected void hide() {
        mButton.setVisibility(View.INVISIBLE);
    }

    protected void show() {
        mButton.setVisibility(View.VISIBLE);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        mButton.setOnClickListener(listener);
    }

}
