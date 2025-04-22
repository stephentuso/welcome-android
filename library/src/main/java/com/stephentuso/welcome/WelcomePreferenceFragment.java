package com.stephentuso.welcome;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

/**
 * Created by stephentuso on 11/16/15.
 */
public class WelcomePreferenceFragment extends PreferenceFragmentCompat {

    public static final String KEY_XML_ID = "preference_xml_id";

    public static WelcomePreferenceFragment newInstance(int preferencesResId) {
        Bundle args = new Bundle();
        args.putInt(KEY_XML_ID, preferencesResId);

        WelcomePreferenceFragment fragment = new WelcomePreferenceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() == null)
            return;

        int preferencesId = getArguments().getInt(KEY_XML_ID);
        addPreferencesFromResource(preferencesId);
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        //TODO
    }

}
