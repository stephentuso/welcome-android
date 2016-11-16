package com.stephentuso.welcome;

import android.view.View;
import android.widget.TextView;

/**
 * Created by stephentuso on 11/15/15.
 */
/* package */ class DoneButton extends WelcomeViewWrapper {

    private boolean shouldShow = true;

    public DoneButton(View button) {
        super(button);
        if (button != null) hideImmediately();
    }

    @Override
    public void setup(WelcomeConfiguration config) {
        super.setup(config);
        shouldShow = !config.getUseCustomDoneButton();
        if (this.getView() instanceof TextView) {
            WelcomeUtils.setTypeface((TextView) this.getView(), config.getDoneButtonTypefacePath(), config.getContext());
        }
    }

    @Override
    public void onPageSelected(int pageIndex, int firstPageIndex, int lastPageIndex) {
        setVisibility(shouldShow && !WelcomeUtils.isIndexBeforeLastPage(pageIndex, lastPageIndex, isRtl));
    }


}
