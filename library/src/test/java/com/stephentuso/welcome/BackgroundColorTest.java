package com.stephentuso.welcome;

import android.graphics.Color;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by stephentuso on 10/6/16.
 */

public class BackgroundColorTest {

    @Test
    public void testBackgroundColor() {
        BackgroundColor color = new BackgroundColor(Color.RED);
        assertEquals(Color.RED, color.value());
        color = new BackgroundColor(Color.BLACK, Color.WHITE);
        assertEquals(Color.BLACK, color.value());
        color = new BackgroundColor(null, Color.WHITE);
        assertEquals(Color.WHITE, color.value());
    }

}
