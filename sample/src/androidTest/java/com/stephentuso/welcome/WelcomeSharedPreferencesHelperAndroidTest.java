package com.stephentuso.welcome;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by stephentuso on 10/10/16.
 */

@RunWith(AndroidJUnit4.class)
public class WelcomeSharedPreferencesHelperAndroidTest extends ActivityTest {

    private static String KEY1 = "welcome_screen_one";
    private static String KEY2 = "welcome_screen_two";

    @Before
    public void setUp() {
        WelcomeSharedPreferencesHelper.removeWelcomeCompleted(activity, KEY1);
        WelcomeSharedPreferencesHelper.removeWelcomeCompleted(activity, KEY2);
    }

    @Test
    public void testWelcomeScreenCompleted() {
        assertFalse(WelcomeSharedPreferencesHelper.welcomeScreenCompleted(activity, KEY1));
        assertFalse(WelcomeSharedPreferencesHelper.welcomeScreenCompleted(activity, KEY2));

        WelcomeSharedPreferencesHelper.storeWelcomeCompleted(activity, KEY1);
        assertTrue(WelcomeSharedPreferencesHelper.welcomeScreenCompleted(activity, KEY1));
        assertFalse(WelcomeSharedPreferencesHelper.welcomeScreenCompleted(activity, KEY2));

        WelcomeSharedPreferencesHelper.storeWelcomeCompleted(activity, KEY2);
        assertTrue(WelcomeSharedPreferencesHelper.welcomeScreenCompleted(activity, KEY2));
    }

}
