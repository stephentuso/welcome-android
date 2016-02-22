package com.stephentuso.welcome;

import android.app.Activity;
import android.content.Intent;

import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.SharedPreferencesHelper;
import com.stephentuso.welcome.util.WelcomeUtils;

/**
 * Created by stephentuso on 11/15/15.
 *
 */
public class WelcomeScreenHelper {

    public static final int DEFAULT_WELCOME_SCREEN_REQUEST = 1;

    Activity mActivity;
    Class<? extends WelcomeActivity> mActivityClass;

    /**
     * @param activity An activity
     * @param activityClass Class of your welcome screen. An Activity that extends {@link com.stephentuso.welcome.ui.WelcomeActivity WelcomeActivity}
     */
    public WelcomeScreenHelper(Activity activity, Class<? extends WelcomeActivity> activityClass) {
        mActivity = activity;
        mActivityClass = activityClass;
    }

    private boolean shouldShow() {
        return !SharedPreferencesHelper.welcomeScreenCompleted(mActivity, WelcomeUtils.getKey(mActivityClass));
    }

    /**
     * Shows the welcome screen if it hasn't been completed yet
     * @return true if the welcome screen was shown, false if it wasn't
     */
    public boolean show() {
        return show(DEFAULT_WELCOME_SCREEN_REQUEST);
    }

    /**
     * Shows the welcome screen if it hasn't been completed yet
     * @param requestCode The request code that will be returned with the result of the welcome screen
     *                    in your Activity's onActivityResult
     * @return true if the welcome screen was shown, false if it wasn't
     */
    public boolean show(int requestCode) {
        boolean shouldShow = shouldShow();
        if (shouldShow) {
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

    private void startActivity(int requestCode) {
        Intent intent = new Intent(mActivity, mActivityClass);
        mActivity.startActivityForResult(intent, requestCode);
    }

}
