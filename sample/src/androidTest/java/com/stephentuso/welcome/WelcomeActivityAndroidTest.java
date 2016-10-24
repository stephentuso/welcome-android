package com.stephentuso.welcome;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.stephentuso.welcomeexample.DefaultWelcomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by stephentuso on 10/21/16.
 */

@RunWith(AndroidJUnit4.class)
public class WelcomeActivityAndroidTest extends ActivityTest {

    private static int pageCount = 3;

    private Instrumentation.ActivityMonitor welcomeMonitor;
    private WelcomeActivity welcomeActivity;

    @Rule
    public UiThreadTestRule uiThreadTestRule = new UiThreadTestRule();

    @Override
    public void initActivity() {
        super.initActivity();
        welcomeMonitor = new Instrumentation.ActivityMonitor(DefaultWelcomeActivity.class.getName(), null, false);
        instrumentation.addMonitor(welcomeMonitor);
        new WelcomeHelper(activity, DefaultWelcomeActivity.class).forceShow();
        welcomeActivity = (WelcomeActivity) instrumentation.waitForMonitor(welcomeMonitor);
    }

    @Override
    public void finishActivity() {
        super.finishActivity();
        instrumentation.removeMonitor(welcomeMonitor);
        if (welcomeActivity != null) {
            welcomeActivity.finish();
        }
    }

    // From http://stackoverflow.com/a/5759318
    private Intent assertResultEquals(int resultCode) {
        try {
            Field f = Activity.class.getDeclaredField("mResultCode");
            f.setAccessible(true);
            int actualResultCode = (Integer)f.get(welcomeActivity);
            assertThat(actualResultCode, is(resultCode));
            f = Activity.class.getDeclaredField("mResultData");
            f.setAccessible(true);
            return (Intent)f.get(welcomeActivity);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Looks like the Android Activity class has changed it's   private fields for mResultCode or mResultData.  Time to update the reflection code.", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testScrollMethods() throws Throwable {

        uiThreadTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < pageCount - 1; i++) {
                    assertTrue(welcomeActivity.scrollToNextPage());
                }
                assertFalse(welcomeActivity.scrollToNextPage());

                for (int i = 0; i < pageCount - 1; i++) {
                    assertTrue(welcomeActivity.scrollToPreviousPage());
                }
                assertFalse(welcomeActivity.scrollToPreviousPage());
            }
        });

    }

    @Test
    public void testCompletedResult() throws InterruptedException {
        welcomeActivity.completeWelcomeScreen();
        assertTrue(welcomeActivity.isFinishing());
        assertResultEquals(Activity.RESULT_OK);
    }

    @Test
    public void testCanceledResult() {
        welcomeActivity.cancelWelcomeScreen();
        assertTrue(welcomeActivity.isFinishing());
        assertResultEquals(Activity.RESULT_CANCELED);
    }

    @Test
    public void testOnBackPressed() throws Throwable {
        //TODO: Test all conditions
        uiThreadTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                welcomeActivity.scrollToNextPage();
                welcomeActivity.onBackPressed();
                assertFalse(welcomeActivity.scrollToPreviousPage());
                welcomeActivity.onBackPressed();
                assertResultEquals(Activity.RESULT_OK);
            }
        });
    }

    @Test
    public void testNavButtonClicks() throws Throwable {
        uiThreadTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                // Previous button
                View prevButton = welcomeActivity.findViewById(R.id.wel_button_prev);
                welcomeActivity.scrollToNextPage();
                prevButton.performClick();
                assertFalse(welcomeActivity.scrollToPreviousPage());

                // Next button
                View nextButton = welcomeActivity.findViewById(R.id.wel_button_next);
                for (int i = 0; i < pageCount - 1; i++) {
                    welcomeActivity.scrollToNextPage();
                }
                nextButton.performClick();
                assertFalse(welcomeActivity.scrollToNextPage());


            }
        });
    }

    @Test
    public void testDoneButtonClick() throws Throwable {
        uiThreadTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View doneButton = welcomeActivity.findViewById(R.id.wel_button_done);
                doneButton.performClick();
                assertResultEquals(Activity.RESULT_OK);
            }
        });
    }

    @Test
    public void testSkipButtonClick() throws Throwable {
        uiThreadTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View skipButton = welcomeActivity.findViewById(R.id.wel_button_skip);
                skipButton.performClick();
                assertResultEquals(Activity.RESULT_OK);
            }
        });
    }

}
