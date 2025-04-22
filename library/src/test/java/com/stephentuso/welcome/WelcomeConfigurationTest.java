package com.stephentuso.welcome;

import android.graphics.Color;
import android.os.Build;
import androidx.fragment.app.Fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by stephentuso on 10/6/16.
 *
 * Tests for WelcomeConfiguration and WelcomeConfiguration.Builder
 */

@RunWith(MockitoJUnitRunner.class)
public class WelcomeConfigurationTest extends ConfigurationTest {

    private WelcomeConfiguration.Builder builder1; //Will be initialized with one page
    private WelcomeConfiguration.Builder builder2; //Will have no pages

    @Before
    public void setUp() {
        initContext();

        builder1 = new WelcomeConfiguration.Builder(context);
        builder1.page(new FragmentWelcomePage() {
            @Override
            protected Fragment fragment() {
                return new Fragment();
            }
        });

        builder2 = new WelcomeConfiguration.Builder(context);

        setRtl(false);
        setApiLevel(12);
    }


    //Helper methods

    private void setRtl(boolean rtl) {
        when(resources.getBoolean(R.bool.wel_is_rtl)).thenReturn(rtl);
    }

    private void setApiLevel(int level) {
        try {
            Field field = Build.VERSION.class.getField("SDK_INT");

            Field modField = Field.class.getDeclaredField("modifiers");
            modField.setAccessible(true);
            modField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(null, level);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private FragmentWelcomePage newBlankPage() {
        return new FragmentWelcomePage() {
            @Override
            protected Fragment fragment() {
                return new Fragment();
            }
        };
    }

    //Tests

    @Test
    public void testContext() {
        assertTrue(builder1.build().getContext() == context);
    }

    @Test(expected = IllegalStateException.class)
    public void testZeroPagesThrows() {
        builder2.build();
    }

    @Test
    public void testSwipeToDismiss() {
        builder1.swipeToDismiss(true);

        setApiLevel(10);
        assertFalse(builder1.build().getSwipeToDismiss());
        setApiLevel(11);
        assertTrue(builder1.build().getSwipeToDismiss());
        setApiLevel(18);
        assertTrue(builder1.build().getSwipeToDismiss());

        setRtl(true);

        assertTrue(builder1.build().getSwipeToDismiss());

        builder1.swipeToDismiss(false);
        assertFalse(builder1.build().getSwipeToDismiss());
    }

    @Test
    public void testCanSkip() {
        builder1.canSkip(true);
        assertTrue(builder1.build().getCanSkip());
        builder1.canSkip(false);
        assertFalse(builder1.build().getCanSkip());
    }

    @Test
    public void testBackButtonSkips() {
        builder1.backButtonSkips(true);
        assertTrue(builder1.build().getBackButtonSkips());
        builder1.backButtonSkips(false);
        assertFalse(builder1.build().getBackButtonSkips());
    }

    @Test
    public void testBackButtonNavigatesPages() {
        builder1.backButtonNavigatesPages(true);
        assertTrue(builder1.build().getBackButtonNavigatesPages());
        builder1.backButtonNavigatesPages(false);
        assertFalse(builder1.build().getBackButtonNavigatesPages());
    }

    @Test
    public void testAnimateButtons() {
        builder1.animateButtons(true);
        assertTrue(builder1.build().getAnimateButtons());
        builder1.animateButtons(false);
        assertFalse(builder1.build().getAnimateButtons());
    }

    @Test
    public void testUseCustomDoneButton() {
        builder1.useCustomDoneButton(true);
        assertTrue(builder1.build().getUseCustomDoneButton());
        builder1.useCustomDoneButton(false);
        assertFalse(builder1.build().getUseCustomDoneButton());
    }

    @Test
    public void testShowNextButton() {
        builder1.showNextButton(true);
        assertTrue(builder1.build().getShowNextButton());
        builder1.showNextButton(false);
        assertFalse(builder1.build().getShowNextButton());
    }

    @Test
    public void testShowPrevButton() {
        builder1.showPrevButton(true);
        assertTrue(builder1.build().getShowPrevButton());
        builder1.showPrevButton(false);
        assertFalse(builder1.build().getShowPrevButton());
    }

    @Test
    public void testShowActionBarBackButton() {
        builder1.showActionBarBackButton(true);
        assertTrue(builder1.build().getShowActionBarBackButton());
        builder1.showActionBarBackButton(false);
        assertFalse(builder1.build().getShowActionBarBackButton());
    }

    @Test
    public void testSkipButtonTypeface() {
        builder1.skipButtonTypefacePath("skipButton");
        assertEquals("skipButton", builder1.build().getSkipButtonTypefacePath());
    }

    @Test
    public void testDoneButtonTypeface() {
        builder1.doneButtonTypefacePath("doneButton");
        assertEquals("doneButton", builder1.build().getDoneButtonTypefacePath());
    }

    @Test
    public void testDefaultTitleTypefacePath() {
        builder1.defaultTitleTypefacePath("title");
        assertEquals("title", builder1.build().getDefaultTitleTypefacePath());
    }

    @Test
    public void testDefaultHeaderTypefacePath() {
        builder1.defaultHeaderTypefacePath("header");
        assertEquals("header", builder1.build().getDefaultHeaderTypefacePath());
    }

    @Test
    public void testDefaultDescriptionTypefacePath() {
        builder1.defaultDescriptionTypefacePath("description");
        assertEquals("description", builder1.build().getDefaultDescriptionTypefacePath());
    }

    @Test
    public void testExitAnimation() {
        builder1.exitAnimation(android.R.anim.fade_out);
        assertEquals(android.R.anim.fade_out, builder1.build().getExitAnimation());
    }

    @Test
    public void testPageIndexFunctions() {
        builder2.page(newBlankPage()).page(newBlankPage()).page(newBlankPage()).swipeToDismiss(false);


        //SwipeToDismiss false

        //Not rtl
        assertEquals(0, builder2.build().firstPageIndex());
        assertEquals(2, builder2.build().lastPageIndex());
        assertEquals(2, builder2.build().lastViewablePageIndex());

        //Rtl
        setRtl(true);
        assertEquals(2, builder2.build().firstPageIndex());
        assertEquals(0, builder2.build().lastPageIndex());
        assertEquals(0, builder2.build().lastViewablePageIndex());


        //SwipeToDismiss true
        builder2.swipeToDismiss(true);

        //rtl false
        setRtl(false);
        assertEquals(0, builder2.build().firstPageIndex());
        assertEquals(3, builder2.build().lastPageIndex());
        assertEquals(2, builder2.build().lastViewablePageIndex());

        //Rtl true
        setRtl(true);
        assertEquals(3, builder2.build().firstPageIndex());
        assertEquals(0, builder2.build().lastPageIndex());
        assertEquals(1, builder2.build().lastViewablePageIndex());

    }

    @Test
    public void testPageFunctions() {

        final Fragment fragment1 = new Fragment();
        final Fragment fragment2 = new Fragment();
        final Fragment fragment3 = new Fragment();

        builder2
                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() {
                        return fragment1;
                    }
                })
                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() {
                        return fragment2;
                    }
                })
                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() {
                        return fragment3;
                    }
                });


        // Test page order

        assertTrue(builder2.build().createFragment(0) == fragment1);
        assertTrue(builder2.build().createFragment(1) == fragment2);

        setRtl(true);

        assertTrue(builder2.build().createFragment(0) == fragment3);
        assertTrue(builder2.build().createFragment(1) == fragment2);

        setRtl(false);

        // Test page count

        builder2.swipeToDismiss(true);
        assertEquals(3, builder2.build().viewablePageCount());
        assertEquals(4, builder2.build().pageCount());
        assertEquals(4, builder2.build().getPages().size());

        builder2.swipeToDismiss(false);
        assertEquals(3, builder2.build().viewablePageCount());
        assertEquals(3, builder2.build().pageCount());
        assertEquals(3, builder2.build().getPages().size());
    }

    @Test
    public void testCreateGetFragment() {
        final Fragment fragment1 = new Fragment();

        builder2.page(new FragmentWelcomePage() {
            @Override
            protected Fragment fragment() {
                return fragment1;
            }
        });

        WelcomeConfiguration config = builder2.build();

        assertTrue(config.getFragment(0) == null);
        assertTrue(config.createFragment(0) == fragment1);
        assertTrue(config.getFragment(0) == fragment1);
    }

    @Test
    public void testBottomLayout() {
        assertEquals(WelcomeConfiguration.BottomLayout.STANDARD.resId, builder1.build().getBottomLayoutResId());
        builder1.bottomLayout(WelcomeConfiguration.BottomLayout.BUTTON_BAR);
        assertEquals(WelcomeConfiguration.BottomLayout.BUTTON_BAR.resId, builder1.build().getBottomLayoutResId());
    }

    @Test
    public void testInitDefaultBackgroundColor() {
        //TODO: Improve this
        assertEquals(DEFAULT_COLOR, builder1.build().getDefaultBackgroundColor().value());
        setApiLevel(22);
        assertEquals(DEFAULT_COLOR, builder1.build().getDefaultBackgroundColor().value());
    }

    @Test
    public void testDefaultBackgroundColor() {
        builder2.defaultBackgroundColor(R.color.wel_white)
                .page(newBlankPage());

        WelcomeConfiguration config = builder2.build();
        assertEquals(Color.WHITE, config.getDefaultBackgroundColor().value());
        assertEquals(Color.WHITE, config.getBackgroundColors()[0].value());

        builder2.defaultBackgroundColor(new BackgroundColor(Color.WHITE))
                .page(newBlankPage());

        config = builder2.build();
        assertEquals(Color.WHITE, config.getDefaultBackgroundColor().value());
        assertEquals(Color.WHITE, config.getBackgroundColors()[1].value());
    }

    @Test
    public void testBackgroundColors() {

        int[] colors = {
                Color.RED,
                Color.GREEN,
                Color.BLUE
        };

        for (int color : colors) {
            builder2.page(newBlankPage().background(new BackgroundColor(color)));
        }


        //False swipeToDismiss

        builder2.swipeToDismiss(false);

        BackgroundColor[] bgColors = builder2.build().getBackgroundColors();
        assertEquals(colors.length, bgColors.length);

        for (int i = 0; i < colors.length; i++) {
            assertEquals(colors[i], bgColors[i].value());
        }

        setRtl(true);
        BackgroundColor[] reversedBg = builder2.build().getBackgroundColors();
        assertEquals(colors.length, reversedBg.length);
        int maxIndex = colors.length - 1;
        for (int i = maxIndex; i >= 0; i--) {
            assertEquals(colors[maxIndex - i], reversedBg[i].value());
        }


        // True swipeToDismiss - extra page should match color of last page

        setRtl(false);
        builder2.swipeToDismiss(true);

        bgColors = builder2.build().getBackgroundColors();
        assertEquals(colors.length + 1, bgColors.length);

        for (int i = 0; i < bgColors.length; i++) {
            int j = i;
            if (j == colors.length)
                j--;
            assertEquals(colors[j], bgColors[i].value());
        }

        setRtl(true);
        reversedBg = builder2.build().getBackgroundColors();
        assertEquals(colors.length + 1, reversedBg.length);
        maxIndex = reversedBg.length - 1;
        for (int i = maxIndex; i >= 0; i--) {
            int j = maxIndex - i;
            if (j == colors.length) {
                j--;
            }
            assertEquals(colors[j], reversedBg[i].value());
        }

    }

}
