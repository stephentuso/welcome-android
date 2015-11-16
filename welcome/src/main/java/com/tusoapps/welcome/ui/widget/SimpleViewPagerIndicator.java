package com.tusoapps.welcome.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by stephentuso on 11/16/15.
 */
public class SimpleViewPagerIndicator extends View {

    public SimpleViewPagerIndicator(Context context) {
        super(context);
        init(context);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    Paint paint;

    int currentPageColor = 0x99ffffff;
    int otherPageColor = 0x22000000;

    int mTotalPages = 0;
    int mCurrentPage = 0;

    int spacing = 16;
    int size = 4;

    private void init(Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);

        float density = context.getResources().getDisplayMetrics().density;
        spacing *= density;
        size *= density;
    }

    public void setViewPager(final ViewPager viewPager) {
        updatePage(viewPager.getCurrentItem(), viewPager.getAdapter().getCount());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updatePage(position, viewPager.getAdapter().getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private Point getCenter() {
        return new Point((getRight() - getLeft())/2, (getBottom() - getTop())/2);
    }

    public void updatePage(int currentPageIndex, int totalPages) {
        mTotalPages = totalPages;
        mCurrentPage = currentPageIndex;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Point center = getCenter();
        float startX = getFirstDotPosition(center.x);
        for (int i = 0; i < mTotalPages; i++) {
            paint.setColor(i == mCurrentPage ? currentPageColor : otherPageColor);
            canvas.drawCircle(startX + spacing * i, center.y, size, paint);
        }
    }

    private float getFirstDotPosition(float centerX) {
        float centerIndex = mTotalPages % 2 == 0 ? (mTotalPages-1)/2 : mTotalPages/2;
        float spacingMult = (float) Math.floor(centerIndex);
        if (mTotalPages % 2 == 0)
            spacingMult += 0.5;
        return centerX - (spacing * spacingMult);
    }

}
