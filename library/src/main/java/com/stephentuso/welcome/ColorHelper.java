package com.stephentuso.welcome;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

/**
 * Created by stephentuso on 11/16/15.
 */
public class ColorHelper {

    public static int getColor(Context context, @ColorRes int resId) {
        return ContextCompat.getColor(context, resId);
    }

    public static int resolveColorAttribute(Context context, @AttrRes int resId, int fallback) {
        TypedValue value = new TypedValue();
        boolean colorFound = context.getTheme().resolveAttribute(resId, value, true);
        return colorFound ? value.data : fallback;
    }


}
