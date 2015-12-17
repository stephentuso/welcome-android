package com.stephentuso.welcome.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.stephentuso.welcome.ui.WelcomeActivity;

/**
 * Created by stephentuso on 11/15/15.
 */
public class SharedPreferencesHelper {

    private static final String KEY_SHARED_PREFS = "com.stephentuso.welcome";
    private static final String KEY_HAS_RUN = "welcome_screen_has_run";

    private static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(KEY_SHARED_PREFS, Context.MODE_PRIVATE);
    }

    public static boolean welcomeScreenCompleted(Context context, String welcomeScreenKey) {
        return getCompletedFromPreferences(getSharedPrefs(context), welcomeScreenKey);
    }

    public static void storeWelcomeCompleted(Context context, String welcomeScreenKey) {
        storeWelcomeCompleted(getSharedPrefs(context), welcomeScreenKey);
    }

    private static boolean getCompletedFromPreferences(SharedPreferences preferences, String welcomeScreenKey) {
        return preferences.getBoolean(KEY_HAS_RUN + welcomeScreenKey, false);
    }

    private static void storeWelcomeCompleted(SharedPreferences preferences, String welcomeScreenKey) {
        preferences.edit().putBoolean(KEY_HAS_RUN + welcomeScreenKey, true).commit();
    }

}
