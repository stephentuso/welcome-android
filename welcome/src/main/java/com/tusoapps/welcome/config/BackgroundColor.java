package com.tusoapps.welcome.config;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

/**
 * Created by stephentuso on 11/15/15.
 */
public class BackgroundColor {

    private int color = Color.GRAY;

    public BackgroundColor(@Nullable @ColorInt Integer color) {
        if (color != null)
            this.color = color;
    }

    public int value() {
        return this.color;
    }

}
