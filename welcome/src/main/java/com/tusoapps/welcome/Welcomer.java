package com.tusoapps.welcome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.tusoapps.welcome.config.WelcomeScreenConfig;
import com.tusoapps.welcome.ui.WelcomeActivity;
import com.tusoapps.welcome.util.SharedPreferencesHelper;

/**
 * Created by stephentuso on 11/15/15.
 * TODO: Think of a better name
 */
public class Welcomer {

    Context mContext;
    Class<?> mActivityClass;

    public Welcomer(Context context, Class<?> activityClass) {
        mContext = context;
        mActivityClass = activityClass;
    }

    public void show() {
        if (!SharedPreferencesHelper.welcomeScreenCompleted(mContext))
            startActivity();
    }

    public void forceShow() {
        startActivity();
    }

    private void startActivity() {
        Intent intent = new Intent(mContext, mActivityClass);
        mContext.startActivity(intent);
    }

}
