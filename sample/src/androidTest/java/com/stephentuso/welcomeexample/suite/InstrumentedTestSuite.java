package com.stephentuso.welcomeexample.suite;

import com.stephentuso.welcomeexample.WelcomeScreenHelperAndroidTest;
import com.stephentuso.welcomeexample.WelcomeSharedPreferencesHelperAndroidTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by stephentuso on 10/10/16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        WelcomeScreenHelperAndroidTest.class,
        WelcomeSharedPreferencesHelperAndroidTest.class
})
public class InstrumentedTestSuite {}
