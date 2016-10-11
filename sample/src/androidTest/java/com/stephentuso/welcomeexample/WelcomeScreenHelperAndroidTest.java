package com.stephentuso.welcomeexample;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.stephentuso.welcome.SharedPreferencesHelper;
import com.stephentuso.welcome.WelcomeScreenHelper;
import com.stephentuso.welcome.WelcomeUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by stephentuso on 10/8/16.
 */

@RunWith(AndroidJUnit4.class)
public class WelcomeScreenHelperAndroidTest {

    private Activity activity;
    private WelcomeScreenHelper helper;
    private Instrumentation instrumentation;

    @Before
    public void getActivity() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        ActivityMonitor monitor = new ActivityMonitor(TestActivity.class.getName(), null, false);
        instrumentation.addMonitor(monitor);

        Intent intent = new Intent(instrumentation.getTargetContext(), TestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        instrumentation.startActivitySync(intent);

        activity = instrumentation.waitForMonitor(monitor);
        assertNotNull(activity);
        helper = new WelcomeScreenHelper(activity, DefaultWelcomeActivity.class);
    }

    @After
    public void finishActivity() {
        if (activity != null) {
            activity.finish();
        }
    }

    @Test
    public void testShow() {
        ActivityMonitor monitor = new ActivityMonitor(DefaultWelcomeActivity.class.getName(), null, false);
        instrumentation.addMonitor(monitor);

        String key = WelcomeUtils.getKey(DefaultWelcomeActivity.class);

        SharedPreferencesHelper.storeWelcomeCompleted(activity, key);
        assertFalse(helper.show(null));
        assertFalse(helper.show(new Bundle()));

        SharedPreferencesHelper.removeWelcomeCompleted(activity, key);

        assertTrue(helper.show(null));
        assertFalse(helper.show(null));

        Activity welcomeActivity = instrumentation.waitForMonitor(monitor);
        assertNotNull(welcomeActivity);

        Bundle state = new Bundle();

        helper.onSaveInstanceState(state);
        assertFalse(helper.show(state));
    }

    @Test
    public void testForceShow() {
        ActivityMonitor monitor = new ActivityMonitor(DefaultWelcomeActivity.class.getName(), null, false);
        instrumentation.addMonitor(monitor);

        helper.forceShow();

        Activity welcomeActivity = instrumentation.waitForMonitor(monitor);

        assertNotNull(welcomeActivity);
    }

}
