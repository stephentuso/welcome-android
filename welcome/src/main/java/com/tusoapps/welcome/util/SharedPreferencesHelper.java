package com.tusoapps.welcome.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by stephentuso on 11/15/15.
 */
public class SharedPreferencesHelper {

    private static final String KEY_SHARED_PREFS = "com.tusoapps.welcome";
    private static final String KEY_HAS_RUN = "welcome_screen_has_run";

    public static boolean welcomeScreenCompleted(Context context) {
        return getCompletedFromPreferences(getSharedPrefs(context));
    }

    public static void storeWelcomeCompleted(Context context) {
        storeWelcomeCompleted(getSharedPrefs(context));
    }

    private static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(KEY_SHARED_PREFS, Context.MODE_PRIVATE);
    }

    private static boolean getCompletedFromPreferences(SharedPreferences preferences) {
        return preferences.getBoolean(KEY_HAS_RUN, false);
    }

    private static void storeWelcomeCompleted(SharedPreferences preferences) {
        preferences.edit().putBoolean(KEY_HAS_RUN, true).commit();
    }

}
