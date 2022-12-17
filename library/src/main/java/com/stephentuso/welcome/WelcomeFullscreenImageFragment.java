package com.stephentuso.welcome;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.wonderland.R;
import com.stephentuso.welcome.WelcomePage;

public class WelcomeFullscreenImageFragment extends Fragment implements WelcomePage.OnChangeListener {

    private static final String ARG_DRAWABLE_ID = "drawable_id";
    private int drawableId;
    private ImageView imageView = null;


    public staticWelcomeFullscreenImageFragment newInstance(@DrawableRes int resId) {
        WelcomeFullImageFragment fragment = new WelcomeFullImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_DRAWABLE_ID, resId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wel_fragment_image, container, false);
        imageView = (ImageView) view.findViewById(R.id.wel_image);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args == null)
            return;
        drawableId = args.getInt(ARG_DRAWABLE_ID);
        // I use Glide loading the image
        Glide.with(view).load(drawableId).into(imageView);

    }

    @Override
    public void onWelcomeScreenPageScrolled(int pageIndex, float offset, int offsetPixels) {
        if (Build.VERSION.SDK_INT >= 11 && imageView != null) {
            imageView.setTranslationX(-offsetPixels * 0.8f);
        }
    }

    @Override
    public void onWelcomeScreenPageSelected(int pageIndex, int selectedPageIndex) {

    }

    @Override
    public void onWelcomeScreenPageScrollStateChanged(int pageIndex, int state) {

    }
}
