package com.stephentuso.welcome;

import android.graphics.Color;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by stephentuso on 10/19/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class PagesTest extends ConfigurationTest {

    @DrawableRes
    private static final int DRAWABLE_RES = 24;

    @LayoutRes
    private static final int LAYOUT_RES = 25;

    private static final String DEFAULT_TITLE_TF = "default_title_typeface";
    private static final String DEFAULT_HEADER_TF = "default_header_typeface";
    private static final String DEFAULT_DESCRIPTION_TF = "default_description_typeface";

    WelcomeConfiguration.Builder builder;

    @Before
    public void setUp() {
        initContext();
        builder = new WelcomeConfiguration.Builder(context);
        builder.page(new BasicPage(DRAWABLE_RES, "", ""))
                .defaultTitleTypefacePath(DEFAULT_TITLE_TF)
                .defaultHeaderTypefacePath(DEFAULT_HEADER_TF)
                .defaultDescriptionTypefacePath(DEFAULT_DESCRIPTION_TF);
    }

    @Test
    public void testBackgroundColor() {
        WelcomePage page = new BasicPage(DRAWABLE_RES, "Title", "Description");
        assertFalse(page.backgroundIsSet());

        page.background(R.color.wel_default_background_color);
        assertTrue(page.backgroundIsSet());
        assertEquals(new BackgroundColor(DEFAULT_COLOR), page.getBackground(context));

        page.background(new BackgroundColor(DEFAULT_COLOR));
        assertTrue(page.backgroundIsSet());
        assertEquals(new BackgroundColor(DEFAULT_COLOR), page.getBackground(context));
    }

    @Test
    public void titlePage() {

        TitlePage page = new TitlePage(DRAWABLE_RES, "Title");

        assertEquals(DRAWABLE_RES, page.getDrawableResId());
        assertEquals("Title", page.getTitle());

        //Defaults
        assertTrue(page.getShowParallax());
        assertNull(page.getTitleTypefacePath());
        assertEquals(WelcomeUtils.NO_COLOR_SET, page.getTitleColor());

        page.setup(builder.build());
        assertEquals(DEFAULT_TITLE_TF, page.getTitleTypefacePath());

        page.parallax(false).titleTypeface("title_typeface");
        assertFalse(page.getShowParallax());
        assertEquals("title_typeface", page.getTitleTypefacePath());

        page.titleColor(Color.RED);
        assertEquals(Color.RED, page.getTitleColor());
        page.titleColorResource(context, R.color.wel_default_background_color);
        assertEquals(DEFAULT_COLOR, page.getTitleColor());

        page.setup(builder.build());
        assertEquals("title_typeface", page.getTitleTypefacePath());
    }

    @Test
    public void basicPage() {

        BasicPage page = new BasicPage(DRAWABLE_RES, "Title", "Description");

        assertEquals(DRAWABLE_RES, page.getDrawableResId());
        assertEquals("Title", page.getTitle());
        assertEquals("Description", page.getDescription());

        //Defaults
        assertTrue(page.getShowParallax());
        assertNull(page.getHeaderTypefacePath());
        assertNull(page.getDescriptionTypefacePath());
        assertEquals(WelcomeUtils.NO_COLOR_SET, page.getHeaderColor());

        page.setup(builder.build());
        assertEquals(DEFAULT_HEADER_TF, page.getHeaderTypefacePath());
        assertEquals(DEFAULT_DESCRIPTION_TF, page.getDescriptionTypefacePath());

        page.parallax(false)
                .headerTypeface("header_typeface")
                .descriptionTypeface("description_typeface");
        assertFalse(page.getShowParallax());
        assertEquals("header_typeface", page.getHeaderTypefacePath());
        assertEquals("description_typeface", page.getDescriptionTypefacePath());

        page.headerColor(Color.RED);
        assertEquals(Color.RED, page.getHeaderColor());
        page.headerColorResource(context, R.color.wel_default_background_color);
        assertEquals(DEFAULT_COLOR, page.getHeaderColor());

        page.descriptionColor(Color.BLUE);
        assertEquals(Color.BLUE, page.getDescriptionColor());
        page.descriptionColorResource(context, R.color.wel_default_background_color);
        assertEquals(DEFAULT_COLOR, page.getDescriptionColor());

        page.setup(builder.build());
        assertEquals("header_typeface", page.getHeaderTypefacePath());
        assertEquals("description_typeface", page.getDescriptionTypefacePath());
    }

    @Test
    public void parallaxPage() {

        ParallaxPage page = new ParallaxPage(LAYOUT_RES, "Title", "Description");

        assertEquals(LAYOUT_RES, page.getLayoutResId());
        assertEquals("Title", page.getTitle());
        assertEquals("Description", page.getDescription());

        //Defaults
        assertEquals(0.2, page.getFirstParallaxFactor(), 0.001);
        assertEquals(1.0, page.getLastParallaxFactor(), 0.001);
        assertFalse(page.getParallaxRecursive());
        assertNull(page.getHeaderTypefacePath());
        assertNull(page.getDescriptionTyefacePath());
        assertEquals(WelcomeUtils.NO_COLOR_SET, page.getHeaderColor());

        page.setup(builder.build());
        assertEquals(DEFAULT_HEADER_TF, page.getHeaderTypefacePath());
        assertEquals(DEFAULT_DESCRIPTION_TF, page.getDescriptionTyefacePath());


        page.firstParallaxFactor(-0.4f)
                .lastParallaxFactor(2.0f)
                .recursive(true)
                .headerTypeface("header_typeface")
                .descriptionTypefacePath("description_typeface");

        assertEquals(-0.4f, page.getFirstParallaxFactor(), 0.001);
        assertEquals(2.0f, page.getLastParallaxFactor(), 0.001);
        assertTrue(page.getParallaxRecursive());
        assertEquals("header_typeface", page.getHeaderTypefacePath());
        assertEquals("description_typeface", page.getDescriptionTyefacePath());

        page.headerColor(Color.RED);
        assertEquals(Color.RED, page.getHeaderColor());
        page.headerColorResource(context, R.color.wel_default_background_color);
        assertEquals(DEFAULT_COLOR, page.getHeaderColor());

        page.descriptionColor(Color.BLUE);
        assertEquals(Color.BLUE, page.getDescriptionColor());
        page.descriptionColorResource(context, R.color.wel_default_background_color);
        assertEquals(DEFAULT_COLOR, page.getDescriptionColor());

        page.setup(builder.build());
        assertEquals("header_typeface", page.getHeaderTypefacePath());
        assertEquals("description_typeface", page.getDescriptionTyefacePath());
    }

    @Test
    public void fullscreenParallaxPage() {

        FullscreenParallaxPage page = new FullscreenParallaxPage(LAYOUT_RES);

        assertEquals(LAYOUT_RES, page.getLayoutResId());

        //Defaults
        assertEquals(0.2, page.getFirstParallaxFactor(), 0.001);
        assertEquals(1.0, page.getLastParallaxFactor(), 0.001);
        assertFalse(page.getParallaxRecursive());

        page.firstParallaxFactor(0.6f)
                .lastParallaxFactor(1.8f)
                .recursive(true);

        assertEquals(0.6f, page.getFirstParallaxFactor(), 0.001);
        assertEquals(1.8f, page.getLastParallaxFactor(), 0.001);
        assertTrue(page.getParallaxRecursive());
    }

}
