package com.airbnb.p027n2.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/* renamed from: com.airbnb.n2.utils.ScalingFrameLayout */
public class ScalingFrameLayout extends FrameLayout {
    public ScalingFrameLayout(Context context) {
        super(context);
    }

    public ScalingFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScalingFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        View child = getChildAt(0);
        float scaleX = child.getScaleX();
        float scaleY = child.getScaleY();
        if (scaleX != 1.0f || scaleY != 1.0f) {
            setMeasuredDimension((int) (((float) getMeasuredWidth()) * scaleX), (int) (((float) getMeasuredHeight()) * scaleY));
        }
    }
}
