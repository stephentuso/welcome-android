package com.stephentuso.welcome.ui.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.stephentuso.welcome.R;
import com.stephentuso.welcome.ui.WelcomeScreenPage;

/**
 * Created by stephentuso on 1/23/16.
 */
public class ParallaxWelcomeFragment extends Fragment implements WelcomeScreenPage.OnChangeListener {

    public static final String KEY_LAYOUT_ID = "drawable_id";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_TITLE = "title";
    public static final String KEY_START_FACTOR = "start_factor";
    public static final String KEY_INTERVAL = "parallax_interval";

    private FrameLayout frameLayout = null;
    private TextView titleView = null;
    private TextView descriptionView = null;

    private float startFactor = 0.1f;
    private float parallaxInterval = 0.3f;

    public static ParallaxWelcomeFragment newInstance(@LayoutRes int layoutId, String title, String description, float startParallaxFactor, float parallaxInterval) {
        Bundle args = new Bundle();
        args.putInt(KEY_LAYOUT_ID, layoutId);
        args.putString(KEY_TITLE, title);
        args.putString(KEY_DESCRIPTION, description);
        args.putFloat(KEY_START_FACTOR, startParallaxFactor);
        args.putFloat(KEY_INTERVAL, parallaxInterval);
        ParallaxWelcomeFragment fragment = new ParallaxWelcomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parallax, container, false);

        Bundle args = getArguments();

        frameLayout = (FrameLayout) view.findViewById(R.id.parallax_frame);
        titleView = (TextView) view.findViewById(R.id.title);
        descriptionView = (TextView) view.findViewById(R.id.description);

        if (args == null)
            return view;

        startFactor = args.getFloat(KEY_START_FACTOR, startFactor);
        parallaxInterval = args.getFloat(KEY_INTERVAL, parallaxInterval);

        inflater.inflate(args.getInt(KEY_LAYOUT_ID), frameLayout, true);

        if (args.getString(KEY_TITLE) != null)
            titleView.setText(args.getString(KEY_TITLE));

        if (args.getString(KEY_DESCRIPTION) != null)
            descriptionView.setText(args.getString(KEY_DESCRIPTION));


        return view;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private int applyParallaxEffect(View view, int startIndex, int offsetPixels) {
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                startIndex = applyParallaxEffect(group.getChildAt(i), startIndex, offsetPixels);
            }
        } else {
            applyParallaxEffectToView(view, startIndex, offsetPixels);
            startIndex++;
        }
        return startIndex;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void applyParallaxEffectToView(View view, int index, int offsetPixels) {
        view.setTranslationX(calculateOffsetAmount(index, offsetPixels));
    }

    private float calculateOffsetAmount(int index, int offsetPixels) {
        return (startFactor + (index * parallaxInterval)) * -offsetPixels;
    }

    @Override
    public void onScrolled(int pageIndex, float offset, int offsetPixels) {
        if (Build.VERSION.SDK_INT >= 11 && frameLayout != null) {
            applyParallaxEffect(frameLayout, 0, offsetPixels);
        }
    }

    @Override
    public void onSelected(int pageIndex) {

    }

    @Override
    public void onScrollStateChanged(int state) {

    }


}
