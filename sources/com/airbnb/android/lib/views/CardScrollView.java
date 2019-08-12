package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.emilsjolander.components.StickyScrollViewItems.StickyScrollView;

public class CardScrollView extends StickyScrollView {
    private ScrollViewOnScrollListener mScrollListener;

    public interface ScrollViewOnScrollListener {
        void onScroll(int i, int i2);
    }

    public CardScrollView(Context context) {
        super(context);
    }

    public CardScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean inRegion(MotionEvent ev) {
        if (getChildCount() <= 0) {
            return false;
        }
        float x = ev.getRawX();
        float y = ev.getRawY();
        View v = getChildAt(0);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        if (((float) (location[0] + v.getWidth())) <= x || ((float) (location[1] + v.getHeight())) <= y || ((float) location[0]) >= x || ((float) location[1]) >= y) {
            return false;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    public void setOnScrollListener(ScrollViewOnScrollListener listener) {
        this.mScrollListener = listener;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScroll(l, t);
        }
    }
}
