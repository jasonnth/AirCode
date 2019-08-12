package com.airbnb.p027n2.collections;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

/* renamed from: com.airbnb.n2.collections.MaxWidthFrameLayout */
public class MaxWidthFrameLayout extends FrameLayout {
    protected int maxWidth;

    public MaxWidthFrameLayout(Context context) {
        this(context, null);
    }

    public MaxWidthFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaxWidthFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    @TargetApi(21)
    public MaxWidthFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, new int[]{16843039});
            this.maxWidth = a.getDimensionPixelSize(0, -1);
            a.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getMeasuredWidth() > this.maxWidth) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(this.maxWidth, Integer.MIN_VALUE), heightMeasureSpec);
        }
    }

    public void setMaxWidth(int maxWidth2) {
        this.maxWidth = maxWidth2;
        requestLayout();
    }
}
