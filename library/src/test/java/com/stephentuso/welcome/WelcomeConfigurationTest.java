package com.stephentuso.welcome;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.TypedValue;

import com.stephentuso.welcome.ui.WelcomeFragmentHolder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by stephentuso on 10/6/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class WelcomeConfigurationTest {

    @Mock private Resources resources;
    @Mock private Theme theme;
    @Mock private Context context;

    private WelcomeConfiguration.Builder builder;

    private static int DEFAULT_COLOR = 0x1e88e5;

    @Before
    public void setUp() {

        when(resources.getColor(R.color.default_background_color)).thenReturn(DEFAULT_COLOR);
        when(resources.getColor(R.color.white)).thenReturn(Color.WHITE);
        when(theme.resolveAttribute(R.attr.colorPrimary, new TypedValue(), true)).thenReturn(false);
        when(context.getResources()).thenReturn(resources);
        when(context.getTheme()).thenReturn(theme);

        builder = new WelcomeConfiguration.Builder(context);
    }

    private void useRtl(boolean rtl) {
        when(resources.getBoolean(R.bool.isRtl)).thenReturn(rtl);
    }

    @Test
    public void testSwipeToDismiss() {
        builder.swipeToDismiss(true);

        if (Build.VERSION.SDK_INT < 11) {
            assertFalse(builder.build().getSwipeToDismiss());
        } else {
            assertTrue(builder.build().getSwipeToDismiss());
        }

        assertTrue(builder.build().shouldSwipeToDismiss(20));
        assertTrue(builder.build().shouldSwipeToDismiss(11));
        assertFalse(builder.build().shouldSwipeToDismiss(8));

        useRtl(true);

        assertFalse(builder.build().shouldSwipeToDismiss(20));

        builder.swipeToDismiss(false);
        assertFalse(builder.build().getSwipeToDismiss());
    }

    @Test
    public void testCanSkip() {
        builder.canSkip(true);
        assertTrue(builder.build().getCanSkip());
        builder.canSkip(false);
        assertFalse(builder.build().getCanSkip());
    }

    @Test
    public void testBackButtonSkips() {
        builder.backButtonSkips(true);
        assertTrue(builder.build().getBackButtonSkips());
        builder.backButtonSkips(false);
        assertFalse(builder.build().getBackButtonSkips());
    }

    @Test
    public void testBackButtonNavigatesPages() {
        builder.backButtonNavigatesPages(true);
        assertTrue(builder.build().getBackButtonNavigatesPages());
        builder.backButtonNavigatesPages(false);
        assertFalse(builder.build().getBackButtonNavigatesPages());
    }

    @Test
    public void testAnimateButtons() {
        builder.animateButtons(true);
        assertTrue(builder.build().getAnimateButtons());
        builder.animateButtons(false);
        assertFalse(builder.build().getAnimateButtons());
    }

    @Test
    public void testUseCustomDoneButton() {
        builder.useCustomDoneButton(true);
        assertTrue(builder.build().getUseCustomDoneButton());
        builder.useCustomDoneButton(false);
        assertFalse(builder.build().getUseCustomDoneButton());
    }

    @Test
    public void testShowNextButton() {
        builder.showNextButton(true);
        assertTrue(builder.build().getShowNextButton());
        builder.showNextButton(false);
        assertFalse(builder.build().getShowNextButton());
    }

    @Test
    public void testShowPrevButton() {
        builder.showPrevButton(true);
        assertTrue(builder.build().getShowPrevButton());
        builder.showPrevButton(false);
        assertFalse(builder.build().getShowPrevButton());
    }

    @Test
    public void testShowActionBarBackButton() {
        builder.showActionBarBackButton(true);
        assertTrue(builder.build().getShowActionBarBackButton());
        builder.showActionBarBackButton(false);
        assertFalse(builder.build().getShowActionBarBackButton());
    }

    @Test
    public void testSkipButtonTypeface() {
        builder.skipButtonTypefacePath("skipButton");
        assertEquals("skipButton", builder.build().getSkipButtonTypefacePath());
    }

    @Test
    public void testDoneButtonTypeface() {
        builder.doneButtonTypefacePath("doneButton");
        assertEquals("doneButton", builder.build().getDoneButtonTypefacePath());
    }

    @Test
    public void testDefaultTitleTypefacePath() {
        builder.defaultTitleTypefacePath("title");
        assertEquals("title", builder.build().getDefaultTitleTypefacePath());
    }

    @Test
    public void testDefaultHeaderTypefacePath() {
        builder.defaultHeaderTypefacePath("header");
        assertEquals("header", builder.build().getDefaultHeaderTypefacePath());
    }

    @Test
    public void testDefaultDescriptionTypefacePath() {
        builder.defaultDescriptionTypefacePath("description");
        assertEquals("description", builder.build().getDefaultDescriptionTypefacePath());
    }

    @Test
    public void testExitAnimation() {
        builder.exitAnimation(R.anim.fade_out);
        assertEquals(R.anim.fade_out, builder.build().getExitAnimation());
    }

    @Test
    public void testTheme() {
        builder.theme(WelcomeConfiguration.Theme.DARK);
        assertEquals(WelcomeConfiguration.Theme.DARK.resId, builder.build().getThemeResId());
        builder.theme(R.style.WelcomeScreenTheme_Light);
        assertEquals(R.style.WelcomeScreenTheme_Light, builder.build().getThemeResId());
    }

    @Test
    public void testPageOrder() {

        final Fragment fragment1 = new Fragment();
        final Fragment fragment2 = new Fragment();
        final Fragment fragment3 = new Fragment();

        builder
                .page(new WelcomeFragmentHolder() {
                    @Override
                    protected Fragment fragment() {
                        return fragment1;
                    }
                })
                .page(new WelcomeFragmentHolder() {
                    @Override
                    protected Fragment fragment() {
                        return fragment2;
                    }
                })
                .page(new WelcomeFragmentHolder() {
                    @Override
                    protected Fragment fragment() {
                        return fragment3;
                    }
                });

        assertTrue(builder.build().createFragment(0) == fragment1);
        assertTrue(builder.build().createFragment(1) == fragment2);

        useRtl(true);

        assertTrue(builder.build().createFragment(0) == fragment3);
        assertTrue(builder.build().createFragment(1) == fragment2);
    }

    @Test
    public void testInitDefaultBackgroundColor() {
        assertEquals(DEFAULT_COLOR, builder.build().getDefaultBackgroundColor().value());
    }

    @Test
    public void testDefaultBackgroundColor() {
        builder.defaultBackgroundColor(R.color.white);
        assertEquals(Color.WHITE, builder.build().getDefaultBackgroundColor().value());
    }

}
