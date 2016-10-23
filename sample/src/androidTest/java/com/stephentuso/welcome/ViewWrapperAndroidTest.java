package com.stephentuso.welcome;

import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by stephentuso on 10/23/16.
 */
@RunWith(AndroidJUnit4.class)
public class ViewWrapperAndroidTest extends ActivityTest {

    WelcomeConfiguration.Builder builder;
    WelcomeViewWrapper viewWrapper;
    View view;

    @Override
    public void initActivity() {
        super.initActivity();
        builder =  new WelcomeConfiguration.Builder(activity)
                .animateButtons(false)
                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() {
                        return null;
                    }
                })
                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() {
                        return null;
                    }
                })
                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() {
                        return null;
                    }
                });
        view = new View(activity);
        viewWrapper = new WelcomeViewWrapper(view) {
            @Override
            public void onPageSelected(int pageIndex, int firstPageIndex, int lastPageIndex) {

            }
        };
        viewWrapper.setup(builder.build());
    }

    private void assertVisible() {
        assertVisible(view);
    }

    private void assertVisible(View view) {
        assertEquals(View.VISIBLE, view.getVisibility());
    }

    private void assertInvisible() {
        assertInvisible(view);
    }

    private void assertInvisible(View view) {
        assertEquals(View.INVISIBLE, view.getVisibility());
    }

    @Test
    public void testGetView() {
        assertSame(view, viewWrapper.getView());
    }

    @Test
    public void testVisibility() {
        viewWrapper.setVisibility(false);
        assertInvisible();
        assertFalse(view.isEnabled());
        viewWrapper.setVisibility(true);
        assertVisible();
        assertTrue(view.isEnabled());
    }

    @Test
    public void testDoneButton() {
        DoneButton doneButton = new DoneButton(view);
        doneButton.setup(builder.build());
        doneButton.onPageSelected(2, 0, 4);
        assertInvisible();
        doneButton.onPageSelected(3, 0, 3);
        assertVisible();
        doneButton.onPageSelected(4, 0, 3);
        assertVisible();

        doneButton.setup(builder.useCustomDoneButton(true).build());
        doneButton.onPageSelected(2, 0, 2);
        assertInvisible();
        doneButton.onPageSelected(3, 0, 2);
        assertInvisible();

    }

    @Test
    public void testNextButton() {
        NextButton nextButton = new NextButton(view);
        nextButton.setup(builder.build());
        nextButton.onPageSelected(1, 0, 4);
        assertVisible();
        nextButton.onPageSelected(0, 0, 4);
        assertVisible();
        nextButton.onPageSelected(3, 0, 3);
        assertInvisible();
        nextButton.onPageSelected(5, 0, 3);
        assertInvisible();

        nextButton.setup(builder.showNextButton(false).build());
        nextButton.onPageSelected(1, 0, 4);
        assertInvisible();
        nextButton.onPageSelected(0, 0, 4);
        assertInvisible();
        nextButton.onPageSelected(3, 0, 3);
        assertInvisible();
        nextButton.onPageSelected(5, 0, 3);
        assertInvisible();
    }

    @Test
    public void testSkipButton() {
        SkipButton skipButton = new SkipButton(view);
        skipButton.setup(builder.build());
        skipButton.onPageSelected(1, 0, 4);
        assertVisible();
        skipButton.onPageSelected(0, 0, 4);
        assertVisible();
        skipButton.onPageSelected(3, 0, 3);
        assertInvisible();
        skipButton.onPageSelected(5, 0, 3);
        assertInvisible();

        skipButton.setup(builder.showPrevButton(true).build());
        skipButton.onPageSelected(0, 0, 4);
        assertVisible();
        skipButton.onPageSelected(2, 0, 4);
        assertInvisible();

        skipButton.setup(builder.showPrevButton(false).canSkip(false).build());
        skipButton.onPageSelected(1, 0, 4);
        assertInvisible();
        skipButton.onPageSelected(0, 0, 4);
        assertInvisible();
        skipButton.onPageSelected(3, 0, 3);
        assertInvisible();
        skipButton.onPageSelected(5, 0, 3);
        assertInvisible();
    }

    @Test
    public void testPrevButton() {
        PreviousButton prevButton = new PreviousButton(view);
        prevButton.setup(builder.build());
        prevButton.onPageSelected(0, 0, 3);
        assertInvisible();
        prevButton.onPageSelected(2, 0, 4);
        assertInvisible();

        prevButton.setup(builder.showPrevButton(true).build());
        prevButton.onPageSelected(0, 0, 4);
        assertInvisible();
        prevButton.onPageSelected(1, 0, 3);
        assertVisible();
        prevButton.onPageSelected(4, 0, 3);
        assertVisible();
    }

}
