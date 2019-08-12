package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ScrollViewWithCustomListener extends ScrollView {
    private ScrollViewListener listener;

    public interface ScrollViewListener {
        void onScrollChanged(ScrollViewWithCustomListener scrollViewWithCustomListener, int i, int i2, int i3, int i4);
    }

    public ScrollViewWithCustomListener(Context context) {
        super(context);
    }

    public ScrollViewWithCustomListener(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewWithCustomListener(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollViewListener(ScrollViewListener listener2) {
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.listener != null) {
            this.listener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
}
