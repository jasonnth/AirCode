package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class StaticHeightGridView extends GridView {
    public StaticHeightGridView(Context context) {
        super(context);
    }

    public StaticHeightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StaticHeightGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
        getLayoutParams().height = getMeasuredHeight();
    }
}
