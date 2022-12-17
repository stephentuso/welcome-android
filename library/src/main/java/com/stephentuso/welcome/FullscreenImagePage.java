package com.stephentuso.welcome;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;

import com.stephentuso.welcome.WelcomePage;

public class FullscreenImagePage extends WelcomePage<FullImagePage> {

    private int drawableResId;

    public FullscreenImagePage(@DrawableRes int drawableResId) {
        this.drawableResId = drawableResId;
    }

    @Override
    protected Fragment fragment() {
        return WelcomeFullscreenImageFragment.newInstance(this.drawableResId);
    }
}
