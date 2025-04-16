package com.stephentuso.welcome;

import android.os.Build;
import androidx.test.runner.AndroidJUnit4;
import androidx.flagment.app.Fragment;
import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by stephentuso on 10/22/16.
 */
@RunWith(AndroidJUnit4.class)
public class WelcomeViewHiderAndroidTest extends ActivityTest {

    private WelcomeConfiguration.Builder builder;
    private View view;
    private WelcomeViewHider hider;

    @Override
    public void initActivity() {
        super.initActivity();
        builder = new WelcomeConfiguration.Builder(activity)
                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() {
                        return new Fragment();
                    }
                })
                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() {
                        return new Fragment();
                    }
                });
        view = new View(activity);
        hider = new WelcomeViewHider(view);
    }

    @Test
    public void testOnPageScrolled() {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }

        hider.setup(builder.build());
        hider.onPageScrolled(1, 0.5f, 500);
        assertEquals(1.0, view.getAlpha(), 0.001);

        hider.setup(builder.swipeToDismiss(true).build());
        hider.onPageScrolled(1, 0.5f, 500);
        assertEquals(0.5f, view.getAlpha(), 0.001);
        hider.onPageScrolled(0, 0, 500);
        assertEquals(1, view.getAlpha(), 0.001);
    }

    @Test
    public void testViewHiddenCallback() {
        final MutableBool viewHidden = new MutableBool(false);
        hider.setOnViewHiddenListener(new WelcomeViewHider.OnViewHiddenListener() {
            @Override
            public void onViewHidden() {
                viewHidden.value = true;
            }
        });

        hider.setup(builder.swipeToDismiss(true).build());
        hider.onPageScrolled(2, 0, 0);
        hider.onPageSelected(2);
        assertTrue(viewHidden.value);
    }

    private class MutableBool {
        boolean value;
        MutableBool(boolean value) {
            this.value = value;
        }
    }

}
