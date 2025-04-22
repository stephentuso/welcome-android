package com.stephentuso.welcome;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by stephentuso on 11/15/15.
 * A simple fragment that shows an image, a heading, and a description.
 */
public class WelcomeBasicFragment extends Fragment implements WelcomePage.OnChangeListener {

    public static final String KEY_DRAWABLE_ID = "drawable_id";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_TITLE = "title";
    public static final String KEY_SHOW_ANIM = "show_anim";
    public static final String KEY_HEADER_TYPEFACE_PATH = "header_typeface";
    public static final String KEY_DESCRIPTION_TYPEFACE_PATH = "description_typeface";
    public static final String KEY_HEADER_COLOR = "header_color";
    public static final String KEY_DESCRIPTION_COLOR = "description_color";

    private ImageView imageView = null;
    private TextView titleView = null;
    private TextView descriptionView = null;
    private boolean showParallaxAnim = true;

    public static WelcomeBasicFragment newInstance(@DrawableRes int drawableId,
                                                   String title,
                                                   String description,
                                                   boolean showParallaxAnim,
                                                   String headerTypefacePath,
                                                   String descriptionTypefacePath,
                                                   @ColorInt int headerColor,
                                                   @ColorInt int descriptionColor)
    {
        Bundle args = new Bundle();
        args.putInt(KEY_DRAWABLE_ID, drawableId);
        args.putString(KEY_TITLE, title);
        args.putString(KEY_DESCRIPTION, description);
        args.putBoolean(KEY_SHOW_ANIM, showParallaxAnim);
        args.putString(KEY_HEADER_TYPEFACE_PATH, headerTypefacePath);
        args.putString(KEY_DESCRIPTION_TYPEFACE_PATH, descriptionTypefacePath);
        args.putInt(KEY_HEADER_COLOR, headerColor);
        args.putInt(KEY_DESCRIPTION_COLOR, descriptionColor);
        WelcomeBasicFragment fragment = new WelcomeBasicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wel_fragment_basic, container, false);

        Bundle args = getArguments();

        imageView = (ImageView) view.findViewById(R.id.wel_image);
        titleView = (TextView) view.findViewById(R.id.wel_title);
        descriptionView = (TextView) view.findViewById(R.id.wel_description);

        if (args == null)
            return view;

        showParallaxAnim = args.getBoolean(KEY_SHOW_ANIM, showParallaxAnim);

        imageView.setImageResource(args.getInt(KEY_DRAWABLE_ID));

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
    public void onWelcomeScreenPageScrolled(int pageIndex, float offset, int offsetPixels) {
        if (showParallaxAnim && Build.VERSION.SDK_INT >= 11 && imageView != null) {
            imageView.setTranslationX(-offsetPixels * 0.8f);
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
