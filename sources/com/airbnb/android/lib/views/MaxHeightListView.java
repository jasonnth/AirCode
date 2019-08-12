package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ListView;

public class MaxHeightListView extends ListView {
    protected int mMaxHeight;

    public MaxHeightListView(Context context) {
        super(context);
        init(null);
    }

    public MaxHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MaxHeightListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, new int[]{16843040});
            this.mMaxHeight = a.getDimensionPixelSize(0, -1);
            a.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (this.mMaxHeight > 0 && this.mMaxHeight < measuredHeight) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(this.mMaxHeight, MeasureSpec.getMode(heightMeasureSpec));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
