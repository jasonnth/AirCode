package com.airbnb.android.core.views;

import android.content.Context;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.utils.AutoScrollingController;

public class ClickableViewPager extends ViewPager {
    private AutoScrollingController autoScrollingController;
    private GestureDetector gestureDetector;
    /* access modifiers changed from: private */
    public OnClickListener onClickListener;

    public ClickableViewPager(Context context) {
        super(context);
        init(context);
    }

    public ClickableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.gestureDetector = new GestureDetector(context, new SimpleOnGestureListener() {
            public boolean onSingleTapUp(MotionEvent e) {
                if (ClickableViewPager.this.onClickListener == null) {
                    return false;
                }
                ClickableViewPager.this.onClickListener.onClick(ClickableViewPager.this);
                return true;
            }
        });
        this.autoScrollingController = new AutoScrollingController(ClickableViewPager$$Lambda$1.lambdaFactory$(this));
    }

    public boolean onTouchEvent(MotionEvent ev) {
        boolean override = (captureTouchEvents() && this.gestureDetector.onTouchEvent(ev)) || super.onTouchEvent(ev);
        if (override) {
            this.autoScrollingController.cancel();
        }
        return override;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean override = (captureTouchEvents() && this.gestureDetector.onTouchEvent(ev)) || super.onInterceptTouchEvent(ev);
        if (override) {
            this.autoScrollingController.cancel();
        }
        return override;
    }

    /* access modifiers changed from: protected */
    public boolean captureTouchEvents() {
        return true;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.autoScrollingController.cancel();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    /* access modifiers changed from: private */
    public boolean autoScrollToPosition(int position) {
        boolean canScroll = getAdapter() != null && getAdapter().getCount() > position;
        if (canScroll) {
            setCurrentItem(position);
        }
        return canScroll;
    }
}
