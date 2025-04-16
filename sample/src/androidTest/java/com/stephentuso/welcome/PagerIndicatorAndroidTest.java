package com.stephentuso.welcome;

import androidx.test.runner.AndroidJUnit4;
import androidx.fragment.app.Fragment;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by stephentuso on 10/22/16.
 */
@RunWith(AndroidJUnit4.class)
public class PagerIndicatorAndroidTest extends ActivityTest {

    private WelcomeConfiguration.Builder builder;
    private WelcomeViewPagerIndicator indicator;

    @Override
    public void initActivity() {
        super.initActivity();
        builder = new WelcomeConfiguration.Builder(activity);
        indicator = new WelcomeViewPagerIndicator(activity);
    }

    private WelcomePage emptyPage() {
        return new WelcomePage() {
            @Override
            protected Fragment fragment() {
                return new Fragment();
            }
        };
    }

    @Test
    public void testPageCount() {
        builder.page(emptyPage()).page(emptyPage()).page(emptyPage());
        indicator.setup(builder.build());
        assertEquals(3, indicator.getTotalPages());
    }

    @Test
    public void testDefaults() {
        assertEquals(0, indicator.getTotalPages());
        assertEquals(0, indicator.getPosition());
        assertEquals(0, indicator.getDisplayedPosition());
        assertFalse(indicator.isRtl());
        assertEquals(0, indicator.getPageIndexOffset());
        assertEquals(WelcomeViewPagerIndicator.Animation.FADE, indicator.getIndicatorAnimation());
    }

    @Test
    public void testGettersAndSetters() {
        indicator.setTotalPages(4);
        assertEquals(4, indicator.getTotalPages());

        indicator.setPosition(1);
        assertEquals(1, indicator.getPosition());
        assertEquals(1, indicator.getDisplayedPosition());
        indicator.setPosition(4);
        assertEquals(4, indicator.getPosition());
        assertEquals(3, indicator.getDisplayedPosition());

        indicator.setRtl(true);
        assertTrue(indicator.isRtl());
        indicator.setPosition(-1);
        assertEquals(-1, indicator.getPosition());
        assertEquals(0, indicator.getDisplayedPosition());
        indicator.setRtl(false);

        indicator.setPageIndexOffset(-1);
        assertEquals(-1, indicator.getPageIndexOffset());
        indicator.setPosition(1);
        assertEquals(0, indicator.getPosition());
        assertEquals(0, indicator.getDisplayedPosition());

        indicator.setIndicatorAnimation(SimpleViewPagerIndicator.Animation.SLIDE);
        assertEquals(SimpleViewPagerIndicator.Animation.SLIDE, indicator.getIndicatorAnimation());
    }

    @Test
    public void testAnimationPositions() {
        indicator.setTotalPages(4);

        indicator.setIndicatorAnimation(SimpleViewPagerIndicator.Animation.NONE);
        indicator.onPageSelected(1);
        assertEquals(1, indicator.getPosition());
        indicator.onPageScrolled(2, 0.5f, 200);
        assertEquals(1, indicator.getPosition());

        indicator.setPosition(0);

        indicator.setIndicatorAnimation(SimpleViewPagerIndicator.Animation.FADE);
        indicator.onPageSelected(1);
        assertEquals(0, indicator.getPosition());
        indicator.onPageScrolled(1, 0, 0);
        assertEquals(1, indicator.getPosition());
    }

}
