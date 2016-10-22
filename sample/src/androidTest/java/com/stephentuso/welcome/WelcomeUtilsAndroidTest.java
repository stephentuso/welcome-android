package com.stephentuso.welcome;

import android.os.Build;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by stephentuso on 10/22/16.
 */
@RunWith(AndroidJUnit4.class)
public class WelcomeUtilsAndroidTest extends ActivityTest {

    private ViewGroup getLayoutWithChildren(int childCount) {
        ViewGroup parent = new FrameLayout(activity);
        for (int i = 0; i < childCount; i++) {
            parent.addView(new View(activity));
        }
        return parent;
    }

    @Test
    public void testParallaxFunctions() {
        ViewGroup parent = getLayoutWithChildren(3);
        parent.addView(getLayoutWithChildren(3));

        assertEquals(4, WelcomeUtils.calculateParallaxLayers(parent, false));
        assertEquals(6, WelcomeUtils.calculateParallaxLayers(parent, true));

        if (Build.VERSION.SDK_INT < 11) {
            return;
        }

        //Non recursive
        WelcomeUtils.applyParallaxEffect(parent, false, 50, 1, 1);
        assertEquals(-50, parent.getChildAt(0).getTranslationX(), 0.001);
        assertEquals(-100, parent.getChildAt(1).getTranslationX(), 0.001);
        assertEquals(-150, parent.getChildAt(2).getTranslationX(), 0.001);
        assertEquals(-200, parent.getChildAt(3).getTranslationX(), 0.001);

        //Recursive
        WelcomeUtils.applyParallaxEffect(parent, true, 50, 1, 1);
        assertEquals(-50, parent.getChildAt(0).getTranslationX(), 0.001);
        assertEquals(-100, parent.getChildAt(1).getTranslationX(), 0.001);
        assertEquals(-150, parent.getChildAt(2).getTranslationX(), 0.001);
        ViewGroup subParent = (ViewGroup) parent.getChildAt(3);
        assertEquals(-200, subParent.getChildAt(0).getTranslationX(), 0.001);
        assertEquals(-250, subParent.getChildAt(1).getTranslationX(), 0.001);
        assertEquals(-300, subParent.getChildAt(2).getTranslationX(), 0.001);

        //Single view
        WelcomeUtils.applyParallaxEffect(parent.getChildAt(0), false, 50, 1, 1);
        assertEquals(-50, parent.getChildAt(0).getTranslationX(), 0.001);
        WelcomeUtils.applyParallaxEffect(parent.getChildAt(0), true, 50, 1, 1);
        assertEquals(-50, parent.getChildAt(0).getTranslationX(), 0.001);

    }

}
