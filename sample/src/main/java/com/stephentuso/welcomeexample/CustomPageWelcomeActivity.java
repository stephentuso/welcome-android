package com.stephentuso.welcomeexample;

import android.support.v4.app.Fragment;

import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.ui.WelcomeFragmentHolder;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;
import com.stephentuso.welcomeexample.fragment.DoneFragment;
import com.stephentuso.welcomeexample.fragment.ExampleFragment;

/**
 * Created by stephentuso on 10/5/16.
 */

public class CustomPageWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .theme(R.style.GeneralWelcomeScreenTheme)
                .page(new WelcomeFragmentHolder() {
                    @Override
                    protected Fragment fragment() {
                        return new ExampleFragment();
                    }
                })
                .page(new WelcomeFragmentHolder() {
                    @Override
                    protected Fragment fragment() {
                        return new DoneFragment();
                    }
                })
                .useCustomDoneButton(true)
                .build();
    }

}
