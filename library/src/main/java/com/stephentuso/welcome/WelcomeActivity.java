package com.stephentuso.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;

public abstract class WelcomeActivity extends AppCompatActivity {

    public static final String WELCOME_SCREEN_KEY = "welcome_screen_key";

    private ViewPager viewPager;
    private WelcomeFragmentPagerAdapter adapter;
    private WelcomeConfiguration configuration;
    private WelcomeItemList responsiveItems = new WelcomeItemList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        configuration = configuration();

        if (configuration.getThemeResId() != WelcomeConfiguration.NO_THEME_SET) {
            super.setTheme(configuration.getThemeResId());
        }

        /* Passing null for savedInstanceState fixes issue with fragments in list not matching
           the displayed ones after the screen was rotated. (Parallax animations would stop working)
           TODO: Look into this more
         */
        super.onCreate(null);
        setContentView(R.layout.wel_activity_welcome);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        adapter = new WelcomeFragmentPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.wel_view_pager);
        viewPager.setAdapter(adapter);

        if (configuration.getShowActionBarBackButton()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SkipButton skip = new SkipButton(findViewById(R.id.wel_button_skip), configuration.getCanSkip());
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeWelcomeScreen();
            }
        });

        PreviousButton prev = new PreviousButton(findViewById(R.id.wel_button_prev));
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollToPreviousPage();
            }
        });

        NextButton next = new NextButton(findViewById(R.id.wel_button_next));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToNextPage();
            }
        });

        DoneButton done = new DoneButton(findViewById(R.id.wel_button_done));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeWelcomeScreen();
            }
        });

        WelcomeViewPagerIndicator indicator = (WelcomeViewPagerIndicator) findViewById(R.id.wel_pager_indicator);
        WelcomeBackgroundView background = (WelcomeBackgroundView) findViewById(R.id.wel_background_view);

        WelcomeViewHider hider = new WelcomeViewHider(findViewById(R.id.wel_root));
        hider.setOnViewHiddenListener(new WelcomeViewHider.OnViewHiddenListener() {
            @Override
            public void onViewHidden() {
                completeWelcomeScreen();
            }
        });

        responsiveItems = new WelcomeItemList(background, indicator, skip, prev, next, done, hider, configuration.getPages());
        responsiveItems.setup(configuration);
        viewPager.addOnPageChangeListener(responsiveItems);
        viewPager.setCurrentItem(configuration.firstPageIndex());
        responsiveItems.onPageSelected(viewPager.getCurrentItem());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewPager != null) {
            viewPager.clearOnPageChangeListeners();
        }
    }

    /* package */ boolean scrollToNextPage() {
        if (!canScrollToNextPage()) {
            return false;
        }
        viewPager.setCurrentItem(getNextPageIndex());
        return true;
    }

    /* package */ boolean scrollToPreviousPage() {
        if (!canScrollToPreviousPage()) {
            return false;
        }
        viewPager.setCurrentItem(getPreviousPageIndex());
        return true;
    }

    protected int getNextPageIndex() {
        return viewPager.getCurrentItem() + (configuration.isRtl() ? -1 : 1);
    }

    protected int getPreviousPageIndex() {
        return viewPager.getCurrentItem() + (configuration.isRtl() ? 1 : -1);
    }

    protected boolean canScrollToNextPage() {
        return configuration.isRtl() ? getNextPageIndex() >= configuration.lastViewablePageIndex() : getNextPageIndex() <= configuration.lastViewablePageIndex();
    }

    protected boolean canScrollToPreviousPage() {
        return configuration.isRtl() ? getPreviousPageIndex() <= configuration.firstPageIndex() : getPreviousPageIndex() >= configuration.firstPageIndex();
    }

    /**
     * Closes the activity and saves it as completed.
     * A subsequent call to WelcomeScreenHelper.show() would not show this again,
     * unless the key is changed.
     */
    protected void completeWelcomeScreen() {
        WelcomeSharedPreferencesHelper.storeWelcomeCompleted(this, getKey());
        setWelcomeScreenResult(RESULT_OK);
        finish();
        if (configuration.getExitAnimation() != WelcomeConfiguration.NO_ANIMATION_SET)
            overridePendingTransition(R.anim.wel_none, configuration.getExitAnimation());
    }

    /**
     * Closes the activity, doesn't save as completed.
     * A subsequent call to WelcomeScreenHelper.show() would show this again.
     */
    protected void cancelWelcomeScreen() {
        setWelcomeScreenResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {

        // Scroll to previous page and return if back button navigates
        if (configuration.getBackButtonNavigatesPages() && scrollToPreviousPage()) {
            return;
        }

        if (configuration.getCanSkip() && configuration.getBackButtonSkips()) {
            completeWelcomeScreen();
        } else {
            cancelWelcomeScreen();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (configuration.getShowActionBarBackButton() && item.getItemId() == android.R.id.home) {
            onBackPressed();
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

    protected abstract WelcomeConfiguration configuration();

    private class WelcomeFragmentPagerAdapter extends FragmentPagerAdapter {

        public WelcomeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return configuration.createFragment(position);
        }

        @Override
        public int getCount() {
            return configuration.pageCount();
        }
    }
}
