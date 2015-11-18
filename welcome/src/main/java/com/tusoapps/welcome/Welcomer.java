package com.tusoapps.welcome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.tusoapps.welcome.config.WelcomeScreenConfig;
import com.tusoapps.welcome.ui.WelcomeActivity;
import com.tusoapps.welcome.util.SharedPreferencesHelper;

/**
 * Created by stephentuso on 11/15/15.
 * TODO: Think of a better name
 */
public class Welcomer {

    public static final String ACTION_WELCOME_COMPLETED = "com.tusoapps.welcome.action_welcome_completed";
    public static final String ACTION_WELCOME_FAILED = "com.tusoapps.welcome.action_welcome_failed";

    public interface WelcomeActionListener {
        void welcomeCompleted();
        void welcomeFailed();
    }

    Context mContext;
    Class<?> mActivityClass;

    public Welcomer(Context context, Class<?> activityClass) {
        mContext = context;
        mActivityClass = activityClass;
    }

    private boolean shouldShow() {
        return !SharedPreferencesHelper.welcomeScreenCompleted(mContext);
    }

    public void show() {
        if (shouldShow())
            startActivity();
    }

    public void forceShow() {
        startActivity();
    }

    private void startActivity() {
        Intent intent = new Intent(mContext, mActivityClass);
        mContext.startActivity(intent);
    }

    public static BroadcastReceiver registerReceiver(Context context, final WelcomeActionListener listener) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_WELCOME_COMPLETED);
        filter.addAction(ACTION_WELCOME_FAILED);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case ACTION_WELCOME_COMPLETED:
                        listener.welcomeCompleted();
                        break;
                    case ACTION_WELCOME_FAILED:
                        listener.welcomeFailed();
                        break;
                }
            }
        };
        context.registerReceiver(receiver, filter);
        //LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
        return receiver;
    }

    public static void unregisterReceiver(Context context, BroadcastReceiver receiver) {
        context.unregisterReceiver(receiver);
        //LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }

}
