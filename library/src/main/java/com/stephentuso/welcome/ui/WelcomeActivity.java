package com.stephentuso.welcome.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.stephentuso.welcome.R;
import com.stephentuso.welcome.WelcomeScreenShower;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;
import com.stephentuso.welcome.ui.view.WelcomeScreenBackgroundView;
import com.stephentuso.welcome.ui.view.WelcomeScreenViewPagerIndicator;
import com.stephentuso.welcome.util.SharedPreferencesHelper;
import com.stephentuso.welcome.util.WelcomeScreenHider;
import com.stephentuso.welcome.util.WelcomeUtils;

public abstract class WelcomeActivity extends AppCompatActivity {

    ViewPager mViewPager;
    WelcomeFragmentPagerAdapter mAdapter;
    WelcomeScreenConfiguration mConfiguration;

    WelcomeScreenItemList mItems = new WelcomeScreenItemList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mConfiguration = configuration();
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
                scrollToNextPage();
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

    private boolean scrollToNextPage() {
        if (!canScrollToNextPage())
            return false;
        mViewPager.setCurrentItem(getNextPageIndex());
        return true;
    }

    private boolean scrollToPreviousPage() {
        if (!canScrollToPreviousPage())
            return false;
        mViewPager.setCurrentItem(getPreviousPageIndex());
        return true;
    }

    private int getNextPageIndex() {
        return mViewPager.getCurrentItem() + (mConfiguration.isRtl() ? -1 : 1);
    }

    private int getPreviousPageIndex() {
        return mViewPager.getCurrentItem() + (mConfiguration.isRtl() ? 1 : -1);
    }

    private boolean canScrollToNextPage() {
        return mConfiguration.isRtl() ? getNextPageIndex() >= mConfiguration.lastViewablePageIndex() : getNextPageIndex() <= mConfiguration.lastViewablePageIndex();
    }

    private boolean canScrollToPreviousPage() {
        return mConfiguration.isRtl() ? getPreviousPageIndex() <= mConfiguration.firstPageIndex() : getPreviousPageIndex() >= mConfiguration.firstPageIndex();
    }

    private void finishWelcomeScreen() {
        SharedPreferencesHelper.storeWelcomeCompleted(this, WelcomeUtils.getKey(this.getClass()));
        sendBroadcast(WelcomeScreenShower.ACTION_WELCOME_COMPLETED);
        super.finish();
        if (mConfiguration.getExitAnimation() != WelcomeScreenConfiguration.NO_ANIMATION_SET)
            overridePendingTransition(R.anim.none, mConfiguration.getExitAnimation());
    }

    private void sendBroadcast(String action) {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(action);
        sendBroadcast(broadcastIntent);
    }

    @Override
    public void onBackPressed() {

        if (mConfiguration.getCanSkip() && mConfiguration.getBackButtonSkips()) {
            finishWelcomeScreen();
            return;
        }

        if (!scrollToPreviousPage()){
            sendBroadcast(WelcomeScreenShower.ACTION_WELCOME_FAILED);
            finish();
        }

    }

    protected abstract WelcomeScreenConfiguration configuration();

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
