package com.stephentuso.welcome;

import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by stephentuso on 10/10/16.
 */

public class WelcomeUtilsTest {

    private static final String WELCOME_KEY = "welcome_screen";

    @Test
    public void testGetKey() {
        assertEquals(WELCOME_KEY, WelcomeUtils.getKey(KeyTestActivity.class));
        assertEquals("", WelcomeUtils.getKey(EmptyKeyTestActivity.class));
        assertEquals("", WelcomeUtils.getKey(NoKeyTestActivity.class));
    }

    public static class KeyTestActivity extends WelcomeActivity {

        @Override
        protected WelcomeConfiguration configuration() {
            return null;
        }

        public static String welcomeKey() {
            return WELCOME_KEY;
        }

    }

    public static class EmptyKeyTestActivity extends WelcomeActivity {

        @Override
        protected WelcomeConfiguration configuration() {
            return null;
        }

        public static String welcomeKey() {
            return "";
        }

    }

    public static class NoKeyTestActivity extends WelcomeActivity {

        @Override
        protected WelcomeConfiguration configuration() {
            return null;
        }

    }

}
