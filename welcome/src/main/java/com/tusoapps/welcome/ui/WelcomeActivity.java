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
import com.tusoapps.welcome.util.SharedPreferencesHelper;

public abstract class WelcomeActivity extends AppCompatActivity {

    ViewPager mViewPager;
    WelcomeFragmentPagerAdapter mAdapter;
    WelcomeScreenConfig mConfiguration;

    ButtonManager buttonManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setTheme(R.style.WelcomeScreenTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mConfiguration = configuration();

        mAdapter = new WelcomeFragmentPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mAdapter);

        SkipButton skip = new SkipButton(findViewById(R.id.button_skip), mConfiguration.getCanSkip());
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomed();
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
                welcomed();
            }
        });

        buttonManager = new ButtonManager(mViewPager, new ButtonSet(skip, next, done));
       buttonManager.onPageSelected(mViewPager.getCurrentItem());
    }

    private void nextPage() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
    }

    private void welcomed() {
        SharedPreferencesHelper.storeWelcomeCompleted(this);
        super.finish();
    }

    @Override
    public void onBackPressed() {
        if (mConfiguration.getCanSkip())
            welcomed();
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
