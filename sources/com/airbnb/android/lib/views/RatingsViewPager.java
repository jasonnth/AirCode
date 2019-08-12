package com.airbnb.android.lib.views;

import android.content.Context;
import android.support.p000v4.view.PagerAdapter;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.lib.adapters.ReviewRatingsAdapter;

public class RatingsViewPager extends ViewPager {
    private ReviewRatingsAdapter mAdapter;
    private float mLastX;

    public RatingsViewPager(Context context) {
        super(context);
    }

    public RatingsViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        if (!BuildHelper.isDevelopmentBuild() || (adapter instanceof ReviewRatingsAdapter)) {
            this.mAdapter = (ReviewRatingsAdapter) adapter;
            return;
        }
        throw new IllegalArgumentException("Adapter must be ReviewRatingsAdapter");
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (shouldBlock(ev)) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (shouldBlock(ev)) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean shouldBlock(MotionEvent ev) {
        boolean shouldBlock = false;
        if (ev.getAction() == 2) {
            boolean swipeLeft = ev.getX() < this.mLastX;
            this.mLastX = ev.getX();
            if (swipeLeft) {
                shouldBlock = this.mAdapter != null ? !this.mAdapter.canAdvance(getCurrentItem()) : false;
            }
        }
        this.mLastX = ev.getX();
        return shouldBlock;
    }
}
