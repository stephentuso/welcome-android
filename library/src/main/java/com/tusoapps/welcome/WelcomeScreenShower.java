package com.tusoapps.welcome;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.TextView;

import com.tusoapps.welcome.util.SharedPreferencesHelper;

/**
 * Created by stephentuso on 11/15/15.
 *
 */
public class WelcomeScreenShower {

    public static final String ACTION_WELCOME_COMPLETED = "com.tusoapps.welcome.action_welcome_completed";
    public static final String ACTION_WELCOME_FAILED = "com.tusoapps.welcome.action_welcome_failed";

    public interface WelcomeActionListener {
        /**
         * Called when the welcome screen is skipped (if enabled) or completed
         */
        void welcomeCompleted();

        /**
         * Called when back is pressed on the first page of the welcome screen
         */
        void welcomeFailed();
    }

    Context mContext;
    Class<?> mActivityClass;

    /**
     * @param context Context
     * @param activityClass Class of an Activity that extends {@link com.tusoapps.welcome.ui.WelcomeActivity WelcomeActivity}
     */
    public WelcomeScreenShower(Context context, Class<?> activityClass) {
        mContext = context;
        mActivityClass = activityClass;
        new TextView(context);
    }

    private boolean shouldShow() {
        return !SharedPreferencesHelper.welcomeScreenCompleted(mContext);
    }

    /**
     * Shows the welcome screen if it hasn't been completed yet
     */
    public void show() {
        if (shouldShow())
            startActivity();
    }

    /**
     * Shows the welcome screen
     */
    public void forceShow() {
        startActivity();
    }

    private void startActivity() {
        Intent intent = new Intent(mContext, mActivityClass);
        mContext.startActivity(intent);
    }

    /**
     * Register a broadcast receiver in the provided Activity that listens to messages from the welcome screen and
     * calls the corresponding method in the provided listener when it receives one.
     * @param activity
     * @param listener
     * @return
     */
    public static BroadcastReceiver registerReceiver(Activity activity, final WelcomeActionListener listener) {
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
        activity.registerReceiver(receiver, filter);
        //LocalBroadcastManager.getInstance(activity).registerReceiver(receiver, filter);
        return receiver;
    }

    /**
     * Unregisters the provided BroadcastReceiver from the provided Activity
     * @param activity Activity to unregister the receiver from
     * @param receiver Receiver to unregister
     */
    public static void unregisterReceiver(Activity activity, BroadcastReceiver receiver) {
        activity.unregisterReceiver(receiver);
        //LocalBroadcastManager.getInstance(activity).unregisterReceiver(receiver);
    }

}
