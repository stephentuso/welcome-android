package com.stephentuso.welcome.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;

import com.stephentuso.welcome.ui.WelcomeActivity;

/**
 * Created by stephentuso on 12/16/15.
 */
public class WelcomeUtils {

    public static String getKey(Class<? extends WelcomeActivity> activityClass) {
        String key = "";
        try {
            key = (String) activityClass.getMethod("welcomeKey").invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public static void setTypeface(TextView textView, String typefacePath, Context context) {
        if (typefacePath != null && !typefacePath.equals("")) {
            try {
                textView.setTypeface(Typeface.createFromAsset(context.getAssets(), typefacePath));
            } catch (Exception e) {
                Log.w("WelcomeScreen", "Error setting typeface");
            }
        }
    }

}
