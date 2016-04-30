package com.stephentuso.welcome.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.stephentuso.welcome.R;
import com.stephentuso.welcome.ui.view.WelcomeScreenBackgroundView;
import com.stephentuso.welcome.ui.view.WelcomeScreenViewPagerIndicator;
import com.stephentuso.welcome.util.SharedPreferencesHelper;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;
import com.stephentuso.welcome.util.WelcomeUtils;

public abstract class WelcomeActivity extends AppCompatActivity {

    public static final String WELCOME_SCREEN_KEY = "welcome_screen_key";

    ViewPager mViewPager;
    WelcomeFragmentPagerAdapter mAdapter;
    WelcomeScreenConfiguration mConfiguration;

    WelcomeScreenItemList mItems = new WelcomeScreenItemList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mConfiguration = configuration();
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        super.setTheme(mConfiguration.getThemeResId());
        /* Passing null for savedInstanceState fixes issue with fragments in list not matching
           the displayed ones after the screen was rotated. (Parallax animations would stop working)
           TODO: Look into this more
         */
        super.onCreate(null);
        setContentView(R.layout.activity_welcome);

        mAdapter = new WelcomeFragmentPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mAdapter);

        if (mConfiguration.getShowActionBarBackButton()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SkipButton skip = new SkipButton(findViewById(R.id.button_skip), mConfiguration.getCanSkip());
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeWelcomeScreen();
            }
        });

        PreviousButton prev = new PreviousButton(findViewById(R.id.button_prev));
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollToPreviousPage();
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
                completeWelcomeScreen();
            }
        });

        WelcomeScreenViewPagerIndicator indicator = (WelcomeScreenViewPagerIndicator) findViewById(R.id.pager_indicator);
        WelcomeScreenBackgroundView background = (WelcomeScreenBackgroundView) findViewById(R.id.background_view);

        WelcomeScreenHider hider = new WelcomeScreenHider(findViewById(R.id.root));
        hider.setOnViewHiddenListener(new WelcomeScreenHider.OnViewHiddenListener() {
            @Override
            public void onViewHidden() {
                completeWelcomeScreen();
            }
        });

        mItems = new WelcomeScreenItemList(background, indicator, skip, prev, next, done, hider, mConfiguration.getPages());
        mViewPager.addOnPageChangeListener(mItems);
        mViewPager.setCurrentItem(mConfiguration.firstPageIndex());
        mItems.setup(mConfiguration);
        mItems.onPageSelected(mViewPager.getCurrentItem());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mViewPager != null) {
            mViewPager.clearOnPageChangeListeners();
        }
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

    /**
     * Closes the activity and saves it as completed.
     * A subsequent call to WelcomeScreenHelper.show() would not show this again,
     * unless the key is changed.
     */
    protected void completeWelcomeScreen() {
        SharedPreferencesHelper.storeWelcomeCompleted(this, getKey());
        setWelcomeScreenResult(RESULT_OK);
        super.finish();
        if (mConfiguration.getExitAnimation() != WelcomeScreenConfiguration.NO_ANIMATION_SET)
            overridePendingTransition(R.anim.none, mConfiguration.getExitAnimation());
    }

    /**
     * Closes the activity, doesn't save as completed.
     * A subsequent call to WelcomeScreenHelper.show() would show this again.
     */
    protected void cancelWelcomeScreen() {
        setWelcomeScreenResult(RESULT_CANCELED);
        super.finish();
    }

    @Override
    public void onBackPressed() {

        boolean scrolledBack = false;
        if (mConfiguration.getBackButtonNavigatesPages()) {
            scrolledBack = scrollToPreviousPage();
        }

        if (!scrolledBack) {

            if (mConfiguration.getCanSkip() && mConfiguration.getBackButtonSkips()) {
                completeWelcomeScreen();
            } else {
                cancelWelcomeScreen();
            }

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mConfiguration.getShowActionBarBackButton() && item.getItemId() == android.R.id.home) {
            cancelWelcomeScreen();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setWelcomeScreenResult(int resultCode) {
        Intent intent = this.getIntent();
        intent.putExtra(WELCOME_SCREEN_KEY, getKey());
        this.setResult(resultCode, intent);
    }

    private String getKey() {
        return WelcomeUtils.getKey(this.getClass());
    }

    protected abstract WelcomeScreenConfiguration configuration();

    private class WelcomeFragmentPagerAdapter extends FragmentPagerAdapter {

        public WelcomeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mConfiguration.createFragment(position);
        }

        @Override
        public int getCount() {
            return mConfiguration.pageCount();
        }
    }
}
