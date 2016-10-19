package com.stephentuso.welcome.suite;

import com.stephentuso.welcome.WelcomeScreenHelperAndroidTest;
import com.stephentuso.welcome.WelcomeSharedPreferencesHelperAndroidTest;

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
