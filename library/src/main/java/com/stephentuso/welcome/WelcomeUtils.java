package com.stephentuso.welcome;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by stephentuso on 12/16/15.
 */
public class WelcomeUtils {

    private static final String TAG = WelcomeUtils.class.getName();

    public static String getKey(Class<? extends WelcomeActivity> activityClass) {
        String key = null;

        try {
            key = (String) activityClass.getMethod("welcomeKey").invoke(null);
            if (key.isEmpty()) {
                Log.w(TAG, "welcomeKey() from " + activityClass.getSimpleName() + " returned an empty string. Is that an accident?");
            }
        } catch (NoSuchMethodException e) {
            //Method not found, don't worry about it
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (key == null) { key = ""; }
        return key;
    }

    /* package */ static boolean setTypeface(TextView textView, String typefacePath, Context context) {
        if (typefacePath != null && !typefacePath.equals("")) {
            try {
                textView.setTypeface(Typeface.createFromAsset(context.getAssets(), typefacePath));
                return true;
            } catch (Exception e) {
                Log.w(TAG, "Error setting typeface");
            }
        }
        return false;
    }

    /* package */ static boolean isIndexBeforeLastPage(int index, int lastPageIndex, boolean isRtl) {
        return isRtl ? index > lastPageIndex : index < lastPageIndex;
    }

    /* package */ static int calculateParallaxLayers(View view, boolean recursive) {
        if (recursive)
            return calculateParallaxLayersRecursively(view, 0);
        else
            return calculateParallaxLayers(view);
    }

    private static int calculateParallaxLayers(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            return group.getChildCount();
        }
        return 1;
    }

    private static int calculateParallaxLayersRecursively(View view, int startCount) {
        int count = startCount;
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                count = calculateParallaxLayersRecursively(group.getChildAt(i), count);
            }
        } else {
            count++;
        }
        return count;
    }

    /**
     * Apply a parallax effect to a View/ViewGroup
     * @param view A View or ViewGroup
     * @param recursive Applies if {@code view} is a ViewGroup. If true, this will recursively go through any child ViewGroups and apply the effect to their children.
     *                  If false, only {@code view}'s immediate children will move in the parallax effect
     * @param offsetPixels The current pixel offset for the page {@code view} is on.
     * @param startParallaxFactor The speed at which the first child should move. Negative values for slower, positive for faster.
     * @param parallaxInterval The difference in speed between the children.
     */
    public static void applyParallaxEffect(View view, boolean recursive, int offsetPixels, float startParallaxFactor, float parallaxInterval) {
        if (recursive)
            applyParallaxEffectRecursively(view, offsetPixels, startParallaxFactor, parallaxInterval, 0);
        else
            applyParallaxEffectToImmediateChildren(view, offsetPixels, startParallaxFactor, parallaxInterval);
    }

    private static void applyParallaxEffectToImmediateChildren(View view, int offsetPixels, float startParallaxFactor, float parallaxInterval) {
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                translateViewForParallaxEffect(group.getChildAt(i), i, offsetPixels, startParallaxFactor, parallaxInterval);
            }
        } else {
            translateViewForParallaxEffect(view, 0, offsetPixels, startParallaxFactor, parallaxInterval);
        }
    }

    private static int applyParallaxEffectRecursively(View view, int offsetPixels, float startParallaxFactor, float parallaxInterval, int index) {
        int nextIndex = index;
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                nextIndex = applyParallaxEffectRecursively(group.getChildAt(i), offsetPixels, startParallaxFactor, parallaxInterval, nextIndex);
            }
        } else {
            translateViewForParallaxEffect(view, index, offsetPixels, startParallaxFactor, parallaxInterval);
            nextIndex++;
        }
        return nextIndex;
    }

    private static void translateViewForParallaxEffect(View view, int index, int offsetPixels, float startParallaxFactor, float parallaxInterval) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            view.setTranslationX(calculateParallaxOffsetAmount(index, offsetPixels, startParallaxFactor, parallaxInterval));
        }
    }

    private static float calculateParallaxOffsetAmount(int index, int offsetPixels, float startParallaxFactor, float parallaxInterval) {
        return (startParallaxFactor + (index * parallaxInterval)) * -offsetPixels;
    }

}
