package com.stephentuso.welcome;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testIsIndexBeforeLastPage() {
        assertTrue(WelcomeUtils.isIndexBeforeLastPage(1, 3, false));
        assertFalse(WelcomeUtils.isIndexBeforeLastPage(3, 3, false));
        assertFalse(WelcomeUtils.isIndexBeforeLastPage(4, 3, false));
        assertTrue(WelcomeUtils.isIndexBeforeLastPage(2, 0, true));
        assertFalse(WelcomeUtils.isIndexBeforeLastPage(0, 0, true));
        assertFalse(WelcomeUtils.isIndexBeforeLastPage(0, 1, true));
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
