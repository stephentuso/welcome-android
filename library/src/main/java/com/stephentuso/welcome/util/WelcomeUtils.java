package com.stephentuso.welcome.util;

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

}
