package com.stephentuso.welcome.ui.fragments;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stephentuso.welcome.R;

/**
 * Created by stephentuso on 11/15/15.
 * A simple fragment that shows an image, a heading, and a description.
 */
public class BasicWelcomeFragment extends Fragment {

    public static final String KEY_DRAWABLE_ID = "drawable_id";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_TITLE = "title";

    public static BasicWelcomeFragment newInstance(@DrawableRes int drawableId, String title, String description) {
        Bundle args = new Bundle();
        args.putInt(KEY_DRAWABLE_ID, drawableId);
        args.putString(KEY_TITLE, title);
        args.putString(KEY_DESCRIPTION, description);
        BasicWelcomeFragment fragment = new BasicWelcomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic, container, false);

        Bundle args = getArguments();

        if (args == null)
            return view;

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setImageResource(args.getInt(KEY_DRAWABLE_ID));

        TextView title = (TextView) view.findViewById(R.id.title);
        if (args.getString(KEY_TITLE) != null)
            title.setText(args.getString(KEY_TITLE));

        TextView description = (TextView) view.findViewById(R.id.description);
        if (args.getString(KEY_DESCRIPTION) != null)
            description.setText(args.getString(KEY_DESCRIPTION));


        return view;
    }
}
