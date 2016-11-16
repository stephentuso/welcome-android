package com.stephentuso.welcome;

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
import android.widget.FrameLayout;

public abstract class WelcomeActivity extends AppCompatActivity {

    public static final String WELCOME_SCREEN_KEY = "welcome_screen_key";

    protected ViewPager viewPager;
    private WelcomeFragmentPagerAdapter adapter;
    private WelcomeConfiguration configuration;
    private WelcomeItemList responsiveItems = new WelcomeItemList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        configuration = configuration();

        /* Passing null for savedInstanceState fixes issue with fragments in list not matching
           the displayed ones after the screen was rotated. (Parallax animations would stop working)
           TODO: Look into this more
         */
        super.onCreate(null);
        setContentView(R.layout.wel_activity_welcome);

        adapter = new WelcomeFragmentPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.wel_view_pager);
        viewPager.setAdapter(adapter);

        responsiveItems = new WelcomeItemList();

        // -- Inflate the bottom layout -- //

        FrameLayout bottomFrame = (FrameLayout) findViewById(R.id.wel_bottom_frame);
        View.inflate(this, configuration.getBottomLayoutResId(), bottomFrame);

        if (configuration.getShowActionBarBackButton()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // -- Add buttons to responsiveItems -- //
        // addViewWrapper() will filter out any that aren't in the current layout

        SkipButton skip = new SkipButton(findViewById(R.id.wel_button_skip));
        addViewWrapper(skip, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeWelcomeScreen();
            }
        });

        PreviousButton prev = new PreviousButton(findViewById(R.id.wel_button_prev));
        addViewWrapper(prev, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToPreviousPage();
            }
        });

        NextButton next = new NextButton(findViewById(R.id.wel_button_next));
        addViewWrapper(next, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToNextPage();
            }
        });

        DoneButton done = new DoneButton(findViewById(R.id.wel_button_done));
        addViewWrapper(done, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeWelcomeScreen();
            }
        });

        View firstBarButton = findViewById(R.id.wel_button_bar_first);
        if (firstBarButton != null) {
            firstBarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonBarFirstPressed();
                }
            });
        }

        View secondBarButton = findViewById(R.id.wel_button_bar_second);
        if (secondBarButton != null) {
            secondBarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonBarSecondPressed();
                }
            });
        }

        WelcomeViewPagerIndicator indicator = (WelcomeViewPagerIndicator) findViewById(R.id.wel_pager_indicator);
        if (indicator != null) {
            responsiveItems.add(indicator);
        }

        WelcomeBackgroundView background = (WelcomeBackgroundView) findViewById(R.id.wel_background_view);

        WelcomeViewHider hider = new WelcomeViewHider(findViewById(R.id.wel_root));
        hider.setOnViewHiddenListener(new WelcomeViewHider.OnViewHiddenListener() {
            @Override
            public void onViewHidden() {
                completeWelcomeScreen();
            }
        });

        responsiveItems.addAll(background, hider, configuration.getPages());

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

    /**
     * Helper method to add button to list,
     * checks for null first
     */
    private void addViewWrapper(WelcomeViewWrapper wrapper, View.OnClickListener onClickListener) {
        if (wrapper.getView() != null) {
            wrapper.setOnClickListener(onClickListener);
            responsiveItems.add(wrapper);
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
     * Called when the first (left in LTR layout direction) button
     * is pressed when using the button bar bottom layout
     */
    protected void onButtonBarFirstPressed() {

    }

    /**
     * Called when the second (right in LTR layout direction) button
     * is pressed when using the button bar bottom layout
     */
    protected void onButtonBarSecondPressed() {

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
