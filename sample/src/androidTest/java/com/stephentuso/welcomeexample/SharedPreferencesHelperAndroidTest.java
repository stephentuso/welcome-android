package com.stephentuso.welcomeexample;

import android.support.test.runner.AndroidJUnit4;

import com.stephentuso.welcome.util.SharedPreferencesHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by stephentuso on 10/10/16.
 */

@RunWith(AndroidJUnit4.class)
public class SharedPreferencesHelperAndroidTest extends ActivityTest {

    private static String KEY1 = "welcome_screen_one";
    private static String KEY2 = "welcome_screen_two";

    @Before
    public void setUp() {
        SharedPreferencesHelper.removeWelcomeCompleted(activity, KEY1);
        SharedPreferencesHelper.removeWelcomeCompleted(activity, KEY2);
    }

    @Test
    public void testWelcomeScreenCompleted() {
        assertFalse(SharedPreferencesHelper.welcomeScreenCompleted(activity, KEY1));
        assertFalse(SharedPreferencesHelper.welcomeScreenCompleted(activity, KEY2));

        SharedPreferencesHelper.storeWelcomeCompleted(activity, KEY1);
        assertTrue(SharedPreferencesHelper.welcomeScreenCompleted(activity, KEY1));
        assertFalse(SharedPreferencesHelper.welcomeScreenCompleted(activity, KEY2));

        SharedPreferencesHelper.storeWelcomeCompleted(activity, KEY2);
        assertTrue(SharedPreferencesHelper.welcomeScreenCompleted(activity, KEY2));
    }

}
