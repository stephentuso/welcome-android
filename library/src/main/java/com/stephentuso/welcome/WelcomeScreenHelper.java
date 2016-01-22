package com.stephentuso.welcome;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.TextView;

import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.SharedPreferencesHelper;
import com.stephentuso.welcome.util.WelcomeUtils;

import java.io.InvalidClassException;

import de.greenrobot.event.EventBus;

/**
 * Created by stephentuso on 11/15/15.
 *
 */
public class WelcomeScreenHelper {

    public static final String ACTION_WELCOME_COMPLETED = "com.stephentuso.welcome.action_welcome_completed";
    public static final String ACTION_WELCOME_FAILED = "com.stephentuso.welcome.action_welcome_failed";

    /**
     * @deprecated
     */
    @Deprecated
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
    Class<? extends WelcomeActivity> mActivityClass;

    /**
     * @param context Context
     * @param activityClass Class of an Activity that extends {@link com.stephentuso.welcome.ui.WelcomeActivity WelcomeActivity}
     */
    public WelcomeScreenHelper(Context context, Class<? extends WelcomeActivity> activityClass) {
        mContext = context;
        mActivityClass = activityClass;
    }

    private boolean shouldShow() {
        return !SharedPreferencesHelper.welcomeScreenCompleted(mContext, WelcomeUtils.getKey(mActivityClass));
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
     * @deprecated
     * Register a broadcast receiver in the provided Activity that listens to messages from the welcome screen and
     * calls the corresponding method in the provided listener when it receives one.
     * @param activity
     * @param listener
     * @return
     */
    @Deprecated
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
     * @deprecated
     * Unregisters the provided BroadcastReceiver from the provided Activity
     * @param activity Activity to unregister the receiver from
     * @param receiver Receiver to unregister
     */
    @Deprecated
    public static void unregisterReceiver(Activity activity, BroadcastReceiver receiver) {
        activity.unregisterReceiver(receiver);
        //LocalBroadcastManager.getInstance(activity).unregisterReceiver(receiver);
    }

    /**
     * Registers the given object to the event bus.
     * This should usually be called in an {@link android.app.Activity Activity} or
     * {@link android.app.Fragment Fragment}'s onCreate method.
     * @param object The subscriber to register to the event bus.
     */
    public static void register(Object object) {
        EventBus.getDefault().register(object);
    }

    /**
     * Unregisters the given object from the event bus.
     * This should usually be called in an {@link android.app.Activity Activity} or
     * {@link android.app.Fragment Fragment}'s onDestroy method.
     * @param object The subscriber to unregister from the event bus.
     */
    public static void unregister(Object object) {
        EventBus.getDefault().unregister(object);
    }

}
