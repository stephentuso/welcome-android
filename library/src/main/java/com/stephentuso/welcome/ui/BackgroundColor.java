package com.stephentuso.welcome.ui;

import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

/**
 * Created by stephentuso on 11/15/15.
 *
 * Wrapper for a color int
 */
public class BackgroundColor {

    private int color = 0;

    public BackgroundColor(@ColorInt int color) {
        this.color = color;
    }

    public BackgroundColor(@Nullable @ColorInt Integer color, @ColorInt int fallbackColor) {
        this.color = color != null ? color : fallbackColor;
    }

    public int value() {
        return this.color;
    }

}
