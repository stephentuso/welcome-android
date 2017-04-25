package com.stephentuso.welcome;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by stephentuso on 1/23/16.
 */
public class WelcomeParallaxFragment extends Fragment implements WelcomePage.OnChangeListener {

    public static final String KEY_LAYOUT_ID = "drawable_id";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_TITLE = "title";
    public static final String KEY_START_FACTOR = "start_factor";
    public static final String KEY_END_FACTOR = "end_factor";
    public static final String KEY_PARALLAX_RECURSIVE = "parallax_recursive";
    public static final String KEY_HEADER_TYPEFACE_PATH = "header_typeface";
    public static final String KEY_DESCRIPTION_TYPEFACE_PATH = "description_typeface";
    public static final String KEY_HEADER_COLOR = "header_color";
    public static final String KEY_DESCRIPTION_COLOR = "description_color";

    private FrameLayout frameLayout = null;
    private TextView titleView = null;
    private TextView descriptionView = null;

    private float startFactor = 0.2f;
    private float endFactor = 1.0f;
    private float parallaxInterval = 0f;
    private boolean parallaxRecursive = false;

    public static WelcomeParallaxFragment newInstance(@LayoutRes int layoutId,
                                                      String title,
                                                      String description,
                                                      float startParallaxFactor,
                                                      float endParallaxFactor,
                                                      boolean parallaxRecursive,
                                                      String headerTypefacePath,
                                                      String descriptionTypefacePath,
                                                      @ColorInt int headerColor,
                                                      @ColorInt int descriptionColor)
    {
        Bundle args = new Bundle();
        args.putInt(KEY_LAYOUT_ID, layoutId);
        args.putString(KEY_TITLE, title);
        args.putString(KEY_DESCRIPTION, description);
        args.putFloat(KEY_START_FACTOR, startParallaxFactor);
        args.putFloat(KEY_END_FACTOR, endParallaxFactor);
        args.putBoolean(KEY_PARALLAX_RECURSIVE, parallaxRecursive);
        args.putString(KEY_HEADER_TYPEFACE_PATH, headerTypefacePath);
        args.putString(KEY_DESCRIPTION_TYPEFACE_PATH, descriptionTypefacePath);
        args.putInt(KEY_HEADER_COLOR, headerColor);
        args.putInt(KEY_DESCRIPTION_COLOR, descriptionColor);
        WelcomeParallaxFragment fragment = new WelcomeParallaxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wel_fragment_parallax, container, false);

        Bundle args = getArguments();

        frameLayout = (FrameLayout) view.findViewById(R.id.wel_parallax_frame);
        titleView = (TextView) view.findViewById(R.id.wel_title);
        descriptionView = (TextView) view.findViewById(R.id.wel_description);

        if (args == null)
            return view;

        startFactor = args.getFloat(KEY_START_FACTOR, startFactor);
        endFactor = args.getFloat(KEY_END_FACTOR, endFactor);
        parallaxRecursive = args.getBoolean(KEY_PARALLAX_RECURSIVE, parallaxRecursive);

        inflater.inflate(args.getInt(KEY_LAYOUT_ID), frameLayout, true);

        if (args.getString(KEY_TITLE) != null)
            titleView.setText(args.getString(KEY_TITLE));

        if (args.getString(KEY_DESCRIPTION) != null)
            descriptionView.setText(args.getString(KEY_DESCRIPTION));

        int headerColor = args.getInt(KEY_HEADER_COLOR, WelcomeUtils.NO_COLOR_SET);
        if (headerColor != WelcomeUtils.NO_COLOR_SET)
            titleView.setTextColor(headerColor);

        int descriptionColor = args.getInt(KEY_DESCRIPTION_COLOR, WelcomeUtils.NO_COLOR_SET);
        if (descriptionColor != WelcomeUtils.NO_COLOR_SET)
            descriptionView.setTextColor(descriptionColor);

        WelcomeUtils.setTypeface(titleView, args.getString(KEY_HEADER_TYPEFACE_PATH), getActivity());
        WelcomeUtils.setTypeface(descriptionView, args.getString(KEY_DESCRIPTION_TYPEFACE_PATH), getActivity());

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
