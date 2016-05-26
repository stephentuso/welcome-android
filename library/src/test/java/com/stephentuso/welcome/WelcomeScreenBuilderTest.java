package com.stephentuso.welcome;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;

/**
 * Created by Brenda on 5/26/2016.
 */
@RunWith(AndroidJUnit4.class)
public class WelcomeScreenBuilderTest {

    @Rule
    public ActivityTestRule<MainActivityTest> mActivityRule =
            new ActivityTestRule<>(MainActivityTest.class);
    @Rule
    WelcomeScreenBuilder w = new WelcomeScreenBuilder(mActivityRule.getActivity());

    @Test
    public void testSwipeToDismiss() throws Exception {
        w.swipeToDismiss(true);
        onView(withId(R.id.background_view)).perform(swipeRight());
    }

    @Test
    public void testCanSkip() throws Exception {

    }

    @Test
    public void testBackButtonSkips() throws Exception {

    }

    @Test
    public void testBackButtonNavigatesPages() throws Exception {

    }

    @Test
    public void testAnimateButtons() throws Exception {

    }

    @Test
    public void testUseCustomDoneButton() throws Exception {

    }

    @Test
    public void testShowNextButton() throws Exception {

    }

    @Test
    public void testShowPrevButton() throws Exception {

    }

    @Test
    public void testShowActionBarBackButton() throws Exception {

    }

    @Test
    public void testSkipButtonTypefacePath() throws Exception {

    }

    @Test
    public void testDoneButtonTypefacePath() throws Exception {

    }

    @Test
    public void testDefaultTitleTypefacePath() throws Exception {

    }

    @Test
    public void testDefaultHeaderTypefacePath() throws Exception {

    }

    @Test
    public void testDefaultDescriptionTypefacePath() throws Exception {

    }

    @Test
    public void testExitAnimation() throws Exception {

    }

    @Test
    public void testTheme() throws Exception {

    }

    @Test
    public void testTheme1() throws Exception {

    }

    @Test
    public void testDefaultBackgroundColor() throws Exception {

    }

    @Test
    public void testDefaultBackgroundColor1() throws Exception {

    }

    @Test
    public void testBasicPage() throws Exception {

    }

    @Test
    public void testBasicPage1() throws Exception {

    }

    @Test
    public void testBasicPage2() throws Exception {

    }

    @Test
    public void testBasicPage3() throws Exception {

    }

    @Test
    public void testTitlePage() throws Exception {

    }

    @Test
    public void testTitlePage1() throws Exception {

    }

    @Test
    public void testTitlePage2() throws Exception {

    }

    @Test
    public void testTitlePage3() throws Exception {

    }

    @Test
    public void testParallaxPage() throws Exception {

    }

    @Test
    public void testParallaxPage1() throws Exception {

    }

    @Test
    public void testParallaxPage2() throws Exception {

    }

    @Test
    public void testParallaxPage3() throws Exception {

    }

    @Test
    public void testFullScreenParallaxPage() throws Exception {

    }

    @Test
    public void testFullScreenParallaxPage1() throws Exception {

    }

    @Test
    public void testFullScreenParallaxPage2() throws Exception {

    }

    @Test
    public void testPage() throws Exception {

    }

    @Test
    public void testPage1() throws Exception {

    }

    @Test
    public void testBuild() throws Exception {

    }
}