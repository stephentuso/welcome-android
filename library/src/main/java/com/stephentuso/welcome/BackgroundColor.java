package com.stephentuso.welcome;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

/**
 * Created by stephentuso on 11/15/15.
 *
 * Wrapper for a color int. Used to distinguish between
 * color resource ids and color ints.
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BackgroundColor) {
            BackgroundColor otherColor = (BackgroundColor) obj;
            if (otherColor.value() == this.value()) {
                return true;
            }
        }
        return false;
    }

}
