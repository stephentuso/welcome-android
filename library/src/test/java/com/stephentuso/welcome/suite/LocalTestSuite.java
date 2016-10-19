package com.stephentuso.welcome.suite;

import com.stephentuso.welcome.BackgroundColorTest;
import com.stephentuso.welcome.PagesTest;
import com.stephentuso.welcome.WelcomeConfigurationTest;
import com.stephentuso.welcome.WelcomeUtilsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by stephentuso on 10/19/16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BackgroundColorTest.class,
        PagesTest.class,
        WelcomeConfigurationTest.class,
        WelcomeUtilsTest.class
})
public class LocalTestSuite {}
