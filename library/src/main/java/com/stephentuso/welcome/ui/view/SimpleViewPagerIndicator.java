package com.stephentuso.welcome.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.stephentuso.welcome.R;

/**
 * Created by stephentuso on 11/16/15.
 * A quick and dirty ViewPager indicator.
 */
public class SimpleViewPagerIndicator extends View implements ViewPager.OnPageChangeListener {

    Paint paint;

    int currentPageColor = 0x99ffffff;
    int otherPageColor = 0x22000000;

    int mTotalPages = 0;
    int mCurrentPage = 0;
    float mCurrentPageOffset = 0;

    int spacing = 16;
    int size = 4;

    private boolean animated = false;

    public SimpleViewPagerIndicator(Context context) {
        this(context, null);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.welcomeIndicatorStyle);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SimpleViewPagerIndicator, defStyleAttr, 0);

        currentPageColor = a.getColor(R.styleable.SimpleViewPagerIndicator_currentPageColor, currentPageColor);
        otherPageColor = a.getColor(R.styleable.SimpleViewPagerIndicator_indicatorColor, otherPageColor);
        animated = a.getBoolean(R.styleable.SimpleViewPagerIndicator_animated, animated);

        a.recycle();

        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);

        float density = context.getResources().getDisplayMetrics().density;
        spacing *= density;
        size *= density;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (animated) {
            mCurrentPage = position;
            mCurrentPageOffset = mCurrentPage == mTotalPages - 1 ? 0 : positionOffset;
            invalidate();
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (!animated) {
            mCurrentPage = position;
            mCurrentPageOffset = 0;
            invalidate();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private Point getCenter() {
        return new Point((getRight() - getLeft())/2, (getBottom() - getTop())/2);
    }

    public void setPosition(int position) {
        mCurrentPage = position;
        invalidate();
    }

    public void setTotalPages(int totalPages) {
        mTotalPages = totalPages;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Point center = getCenter();
        float startX = getFirstDotPosition(center.x);

        paint.setColor(otherPageColor);
        for (int i = 0; i < mTotalPages; i++) {
            canvas.drawCircle(startX + spacing * i, center.y, size, paint);
        }

        paint.setColor(currentPageColor);
        canvas.drawCircle(startX + (spacing * (mCurrentPage + mCurrentPageOffset)), center.y, size, paint);
    }

    private float getFirstDotPosition(float centerX) {
        float centerIndex = mTotalPages % 2 == 0 ? (mTotalPages-1)/2 : mTotalPages/2;
        float spacingMult = (float) Math.floor(centerIndex);
        if (mTotalPages % 2 == 0)
            spacingMult += 0.5;
        return centerX - (spacing * spacingMult);
    }

}
