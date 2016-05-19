package com.stephentuso.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.SharedPreferencesHelper;
import com.stephentuso.welcome.util.WelcomeUtils;

/**
 * Created by stephentuso on 11/15/15.
 * Class that handles showing a welcome screen
 */
public class WelcomeScreenHelper {

    public static final int DEFAULT_WELCOME_SCREEN_REQUEST = 1;

    private static final String KEY_WELCOME_SCREEN_STARTED = "com.stephentuso.welcome.welcome_screen_started";

    private Activity mActivity;
    private Class<? extends WelcomeActivity> mActivityClass;
    private boolean welcomeScreenStarted = false;

    /**
     * @param activity An activity
     * @param activityClass Class of your welcome screen. An Activity that extends {@link com.stephentuso.welcome.ui.WelcomeActivity WelcomeActivity}
     */
    public WelcomeScreenHelper(Activity activity, Class<? extends WelcomeActivity> activityClass) {
        mActivity = activity;
        mActivityClass = activityClass;
    }

    private boolean getWelcomeScreenStarted(Bundle savedInstanceState) {
        if (!welcomeScreenStarted) {
            welcomeScreenStarted = savedInstanceState != null && savedInstanceState.getBoolean(KEY_WELCOME_SCREEN_STARTED, false);
        }
        return welcomeScreenStarted;
    }

    private boolean shouldShow(Bundle savedInstanceState) {
        return !getWelcomeScreenStarted(savedInstanceState) &&
                !SharedPreferencesHelper.welcomeScreenCompleted(mActivity, WelcomeUtils.getKey(mActivityClass));
    }

    /**
     * @deprecated Use show(Bundle savedInstanceState) instead and call WelcomeScreenHelper.onSaveInstanceState in your Activity's onSaveInstanceState
     * Shows the welcome screen if it hasn't been completed yet
     * @return true if the welcome screen was shown, false if it wasn't
     */
    @Deprecated
    public boolean show() {
        return show(DEFAULT_WELCOME_SCREEN_REQUEST);
    }

    /**
     * @deprecated Use show(Bundle savedInstanceState, int requestCode) instead and call WelcomeScreenHelper.onSaveInstanceState in your Activity's onSaveInstanceState
     * Shows the welcome screen if it hasn't been completed yet
     * @param requestCode The request code that will be returned with the result of the welcome screen
     *                    in your Activity's onActivityResult
     * @return true if the welcome screen was shown, false if it wasn't
     */
    @Deprecated
    public boolean show(int requestCode) {
        return show(null, requestCode);
    }

    /**
     * Shows the welcome screen if it hasn't already been started or completed yet
     * @param savedInstanceState Saved instance state Bundle
     * @return true if the welcome screen was shown, false if it wasn't
     */
    public boolean show(Bundle savedInstanceState) {
        return show(savedInstanceState, DEFAULT_WELCOME_SCREEN_REQUEST);
    }

    /**
     * Shows the welcome screen if it hasn't already been started or completed yet
     * @param savedInstanceState Saved instance state Bundle
     * @param requestCode The request code that will be returned with the result of the welcome screen
     *                    in your Activity's onActivityResult
     * @return true if the welcome screen was shown, false if it wasn't
     */
    public boolean show(Bundle savedInstanceState, int requestCode) {
        boolean shouldShow = shouldShow(savedInstanceState);
        if (shouldShow) {
            welcomeScreenStarted = true;
            startActivity(requestCode);
        }
        return shouldShow;
    }

    /**
     * Always shows the welcome screen
     */
    public void forceShow() {
        forceShow(DEFAULT_WELCOME_SCREEN_REQUEST);
    }

    /**
     * Always show the welcome screen
     * @param requestCode The request code that will be returned with the result of the welcome screen
     *                    in your Activity's onActivityResult
     */
    public void forceShow(int requestCode) {
        startActivity(requestCode);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(KEY_WELCOME_SCREEN_STARTED, welcomeScreenStarted);
    }

    private void startActivity(int requestCode) {
        Intent intent = new Intent(mActivity, mActivityClass);
        mActivity.startActivityForResult(intent, requestCode);
    }

}
