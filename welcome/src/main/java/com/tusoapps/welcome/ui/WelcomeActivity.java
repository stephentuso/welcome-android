package com.tusoapps.welcome.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tusoapps.welcome.R;
import com.tusoapps.welcome.config.WelcomeScreenConfig;
import com.tusoapps.welcome.ui.view.WelcomeScreenBackgroundView;
import com.tusoapps.welcome.ui.view.WelcomeScreenViewPagerIndicator;
import com.tusoapps.welcome.util.SharedPreferencesHelper;
import com.tusoapps.welcome.util.WelcomeScreenHider;

public abstract class WelcomeActivity extends AppCompatActivity {

    ViewPager mViewPager;
    WelcomeFragmentPagerAdapter mAdapter;
    WelcomeScreenConfig mConfiguration;

    WelcomeScreenItemList mItems = new WelcomeScreenItemList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mConfiguration = configuration();
        super.setTheme(mConfiguration.getThemeResId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAdapter = new WelcomeFragmentPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mAdapter);

        SkipButton skip = new SkipButton(findViewById(R.id.button_skip), mConfiguration.getCanSkip());
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWelcomeScreen();
            }
        });

        NextButton next = new NextButton(findViewById(R.id.button_next));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage();
            }
        });

        DoneButton done = new DoneButton(findViewById(R.id.button_done));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWelcomeScreen();
            }
        });

        WelcomeScreenViewPagerIndicator indicator = (WelcomeScreenViewPagerIndicator) findViewById(R.id.pager_indicator);
        WelcomeScreenBackgroundView background = (WelcomeScreenBackgroundView) findViewById(R.id.background_view);

        WelcomeScreenHider hider = new WelcomeScreenHider(findViewById(R.id.root));
        hider.setOnViewHiddenListener(new WelcomeScreenHider.OnViewHiddenListener() {
            @Override
            public void onViewHidden() {
                finishWelcomeScreen();
            }
        });

        mItems = new WelcomeScreenItemList(background, indicator, skip, next, done, hider);
        mViewPager.addOnPageChangeListener(mItems);
        mViewPager.setCurrentItem(mConfiguration.firstPageIndex());
        mItems.setup(mConfiguration);
        mItems.onPageSelected(mViewPager.getCurrentItem());
    }

    private boolean nextPage() { //TODO: Fix this for RTL
        int difference = mConfiguration.isRtl() ? -1 : 1;
        int pageToScrollTo = mViewPager.getCurrentItem() + difference;
        if (pageToScrollTo > mConfiguration.lastViewablePageIndex())
            return false;
        mViewPager.setCurrentItem(pageToScrollTo);
        return true;
    }

    private boolean previousPage() { //TODO: Fix this for RTL
        int difference = mConfiguration.isRtl() ? 1 : -1;
        int pageToScrollTo = mViewPager.getCurrentItem() + difference;
        if (pageToScrollTo < mConfiguration.firstPageIndex())
            return false;
        mViewPager.setCurrentItem(pageToScrollTo);
        return true;
    }

    private void finishWelcomeScreen() {
        SharedPreferencesHelper.storeWelcomeCompleted(this);
        super.finish();
    }

    @Override
    public void onBackPressed() {
        if (mConfiguration.getCanSkip()) {
            finishWelcomeScreen();
        } else if (!previousPage()){
            //TODO: Figure out a way to leave app
        }

    }

    protected abstract WelcomeScreenConfig configuration();

    private class WelcomeFragmentPagerAdapter extends FragmentPagerAdapter {

        public WelcomeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mConfiguration.getFragment(position);
        }

        @Override
        public int getCount() {
            return mConfiguration.pageCount();
        }
    }
}
