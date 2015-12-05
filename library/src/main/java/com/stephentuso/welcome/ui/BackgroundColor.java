package com.stephentuso.welcome.ui;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

/**
 * Created by stephentuso on 11/15/15.
 *
 * Wrapper for a color int
 */
public class BackgroundColor {

    private int mColor = Color.GRAY;

    public BackgroundColor(@Nullable @ColorInt Integer color) {
        if (color != null)
            this.mColor = color;
    }

    public BackgroundColor(@Nullable @ColorInt Integer color, int defaultColor) {
        mColor = defaultColor;
        if (color != null)
            mColor = color;
    }

    public int value() {
        return this.mColor;
    }

}
