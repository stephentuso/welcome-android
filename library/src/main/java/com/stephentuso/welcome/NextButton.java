package com.stephentuso.welcome;

import android.view.View;

/**
 * Created by stephentuso on 11/15/15.
 */
/* package */ class NextButton extends WelcomeViewWrapper {

    private boolean shouldShow = true;

    public NextButton(View button) {
        super(button);
    }

    @Override
    public void setup(WelcomeConfiguration config) {
        super.setup(config);
        this.shouldShow = config.getShowNextButton();
    }

    @Override
    public void onPageSelected(int pageIndex, int firstPageIndex, int lastPageIndex) {
        setVisibility(shouldShow && WelcomeUtils.isIndexBeforeLastPage(pageIndex, lastPageIndex, isRtl));
    }
}
