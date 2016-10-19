package com.stephentuso.welcome;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.os.Bundle;
import android.support.test.runner.AndroidJUnit4;

import com.stephentuso.welcomeexample.DefaultWelcomeActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by stephentuso on 10/8/16.
 */

@RunWith(AndroidJUnit4.class)
public class WelcomeScreenHelperAndroidTest extends ActivityTest {

    private WelcomeHelper helper;

    @Override
    public void getActivity() {
        super.getActivity();
        helper = new WelcomeHelper(activity, DefaultWelcomeActivity.class);
    }

    @Test
    public void testShow() {
        ActivityMonitor monitor = new ActivityMonitor(DefaultWelcomeActivity.class.getName(), null, false);
        instrumentation.addMonitor(monitor);

        String key = WelcomeUtils.getKey(DefaultWelcomeActivity.class);

        WelcomeSharedPreferencesHelper.storeWelcomeCompleted(activity, key);
        assertFalse(helper.show(null));
        assertFalse(helper.show(new Bundle()));

        WelcomeSharedPreferencesHelper.removeWelcomeCompleted(activity, key);

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
