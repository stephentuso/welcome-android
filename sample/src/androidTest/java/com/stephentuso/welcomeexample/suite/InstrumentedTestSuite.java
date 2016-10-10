package com.stephentuso.welcomeexample.suite;

import com.stephentuso.welcomeexample.SharedPreferencesHelperAndroidTest;
import com.stephentuso.welcomeexample.WelcomeScreenHelperAndroidTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by stephentuso on 10/10/16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        WelcomeScreenHelperAndroidTest.class,
        SharedPreferencesHelperAndroidTest.class
})
public class InstrumentedTestSuite {}
