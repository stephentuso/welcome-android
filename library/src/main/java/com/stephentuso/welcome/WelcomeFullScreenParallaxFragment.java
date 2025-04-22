package com.stephentuso.welcome;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by stephentuso on 1/24/16.
 */
public class WelcomeFullScreenParallaxFragment extends Fragment implements WelcomePage.OnChangeListener {

    public static final String KEY_LAYOUT_ID = "drawable_id";
    public static final String KEY_START_FACTOR = "start_factor";
    public static final String KEY_END_FACTOR = "end_factor";
    public static final String KEY_PARALLAX_RECURSIVE = "parallax_recursive";

    private FrameLayout frameLayout = null;

    private float startFactor = 0.2f;
    private float endFactor = 1.0f;
    private float parallaxInterval = 0f;
    private boolean parallaxRecursive = false;

    public static WelcomeFullScreenParallaxFragment newInstance(@LayoutRes int layoutId, float startParallaxFactor, float endParallaxFactor,
                                                                boolean parallaxRecursive) {
        Bundle args = new Bundle();
        args.putInt(KEY_LAYOUT_ID, layoutId);
        args.putFloat(KEY_START_FACTOR, startParallaxFactor);
        args.putFloat(KEY_END_FACTOR, endParallaxFactor);
        args.putBoolean(KEY_PARALLAX_RECURSIVE, parallaxRecursive);
        WelcomeFullScreenParallaxFragment fragment = new WelcomeFullScreenParallaxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wel_fragment_parallax_full_screen, container, false);

        Bundle args = getArguments();

        frameLayout = (FrameLayout) view.findViewById(R.id.wel_parallax_frame);

        if (args == null)
            return view;

        startFactor = args.getFloat(KEY_START_FACTOR, startFactor);
        endFactor = args.getFloat(KEY_END_FACTOR, endFactor);
        parallaxRecursive = args.getBoolean(KEY_PARALLAX_RECURSIVE, parallaxRecursive);

        inflater.inflate(args.getInt(KEY_LAYOUT_ID), frameLayout, true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        parallaxInterval = (endFactor - startFactor)/(WelcomeUtils.calculateParallaxLayers(frameLayout.getChildAt(0), parallaxRecursive) - 1);
    }

    @Override
    public void onWelcomeScreenPageScrolled(int pageIndex, float offset, int offsetPixels) {
        if (Build.VERSION.SDK_INT >= 11 && frameLayout != null) {
            WelcomeUtils.applyParallaxEffect(frameLayout.getChildAt(0), parallaxRecursive, offsetPixels, startFactor, parallaxInterval);
        }
    }

    @Override
    public void onWelcomeScreenPageSelected(int pageIndex, int selectedPageIndex) {
        //Not used
    }

    @Override
    public void onWelcomeScreenPageScrollStateChanged(int pageIndex, int state) {
        //Not used
    }

}
