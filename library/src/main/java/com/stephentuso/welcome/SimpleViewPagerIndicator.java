package com.stephentuso.welcome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by stephentuso on 11/16/15.
 * A quick and dirty ViewPager indicator.
 */
/* package */ class SimpleViewPagerIndicator extends View implements ViewPager.OnPageChangeListener {

    public enum Animation {
        NONE, SLIDE, FADE
    }

    private Paint paint;

    private int currentPageColor = 0x99ffffff;
    private int otherPageColor = 0x22000000;

    private float currentMaxAlpha;
    private float otherMaxAlpha;

    private int totalPages = 0;
    private int currentPage = 0;
    private int displayedPage = 0;
    private float currentPageOffset = 0;

    //Used to show correct position when rtl and swipeToDismiss
    private int pageIndexOffset = 0;

    private int spacing = 16;
    private int size = 4;

    private Animation animation = Animation.FADE;
    private boolean isRtl = false;

    public SimpleViewPagerIndicator(Context context) {
        this(context, null);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.welcomeIndicatorStyle);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SimpleViewPagerIndicator, defStyleAttr, 0);

            currentPageColor = a.getColor(R.styleable.SimpleViewPagerIndicator_currentPageColor, currentPageColor);
            otherPageColor = a.getColor(R.styleable.SimpleViewPagerIndicator_indicatorColor, otherPageColor);
            int animationInt = a.getInt(R.styleable.SimpleViewPagerIndicator_animation, animation.ordinal());
            animation = animationFromInt(animationInt, animation);

            a.recycle();
        }

        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);

        float density = context.getResources().getDisplayMetrics().density;
        spacing *= density;
        size *= density;

        currentMaxAlpha = Color.alpha(currentPageColor);
        otherMaxAlpha = Color.alpha(otherPageColor);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (animation != Animation.NONE) {
            setPosition(position);
            currentPageOffset = canShowAnimation() ? 0 : positionOffset;
            invalidate();
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (animation == Animation.NONE) {
            setPosition(position);
            currentPageOffset = 0;
            invalidate();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Not used
    }

    private Animation animationFromInt(int i, Animation fallback) {
        for (Animation animation : Animation.values()) {
            if (animation.ordinal() == i) {
                return animation;
            }
        }
        return fallback;
    }

    private boolean canShowAnimation() {
        return isRtl ? currentPage < 0 : currentPage == totalPages - 1;
    }

    private Point getCenter() {
        return new Point((getRight() - getLeft())/2, (getBottom() - getTop())/2);
    }

    public void setPosition(int position) {
        currentPage = position + pageIndexOffset;
        displayedPage = isRtl ? Math.max(currentPage, 0) : Math.min(currentPage, totalPages - 1);
        invalidate();
    }

    public int getPosition() {
        return currentPage;
    }

    public int getDisplayedPosition() {
        return displayedPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        invalidate();
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setRtl(boolean rtl) {
        this.isRtl = rtl;
    }

    public boolean isRtl() {
        return isRtl;
    }

    public void setPageIndexOffset(int offset) {
        this.pageIndexOffset = offset;
    }

    public int getPageIndexOffset() {
        return pageIndexOffset;
    }

    public void setIndicatorAnimation(Animation animation) {
        this.animation = animation;
    }

    public Animation getIndicatorAnimation() {
        return animation;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Point center = getCenter();
        float startX = getFirstDotPosition(center.x);

        paint.setColor(otherPageColor);
        for (int i = 0; i < totalPages; i++) {
            canvas.drawCircle(startX + spacing * i, center.y, size, paint);
        }

        paint.setColor(currentPageColor);

        if (animation == Animation.NONE || animation == Animation.SLIDE) {
            canvas.drawCircle(startX + (spacing * (displayedPage + currentPageOffset)), center.y, size, paint);
        }

        else if (animation == Animation.FADE) {
            paint.setAlpha((int) (currentMaxAlpha * (1f - currentPageOffset)));
            canvas.drawCircle(startX + (spacing * displayedPage), center.y, size, paint);
            paint.setAlpha((int) (currentMaxAlpha * currentPageOffset));
            canvas.drawCircle(startX + (spacing * (displayedPage + 1)), center.y, size, paint);
        }

    }

    private float getFirstDotPosition(float centerX) {
        float centerIndex = totalPages % 2 == 0 ? (totalPages -1)/2 : totalPages /2;
        float spacingMult = (float) Math.floor(centerIndex);
        if (totalPages % 2 == 0)
            spacingMult += 0.5;
        return centerX - (spacing * spacingMult);
    }

}
