package com.stephentuso.welcome;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by stephentuso on 11/16/15.
 */
/* package */ class ColorChangingBackgroundView extends View {

    private BackgroundColor[] mColors = new BackgroundColor[0];

    private int mCurrentPosition = 0;
    private float mOffset = 0;

    private Paint mPaint = null;
    private Rect mRect = new Rect();

    public ColorChangingBackgroundView(Context context) {
        super(context);
    }

    public ColorChangingBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorChangingBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPosition(int position, float offset) {
        mCurrentPosition = position;
        mOffset = offset;
        invalidate();
    }

    public void setColors(BackgroundColor[] colors) {
        mColors = colors;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mPaint == null) {
            mPaint = new Paint();
        }

        if (mColors == null || mColors.length == 0)
            return;

        getDrawingRect(mRect);
        mPaint.setColor(mColors[mCurrentPosition].value());
        mPaint.setAlpha(255);
        canvas.drawRect(mRect, mPaint);

        if (mCurrentPosition != mColors.length - 1) {
            mPaint.setColor(mColors[mCurrentPosition + 1].value());
            mPaint.setAlpha((int) (mOffset * 255f));
            canvas.drawRect(mRect, mPaint);
        }

    }

}
