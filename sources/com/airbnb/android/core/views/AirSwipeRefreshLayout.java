package com.airbnb.android.core.views;

import android.content.Context;
import android.support.p000v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.airbnb.android.core.utils.MiscUtils;

public class AirSwipeRefreshLayout extends SwipeRefreshLayout {
    private CanChildScrollUpListener mCanChildScrollUpListener;
    private View mScrollableChild;
    private float prevX;
    private int touchSlop;

    public interface CanChildScrollUpListener {
        boolean canChildScrollUp();
    }

    public AirSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public AirSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setColorSchemeResources(MiscUtils.getSwipeRefreshColors());
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public boolean canChildScrollUp() {
        if (this.mCanChildScrollUpListener != null) {
            return this.mCanChildScrollUpListener.canChildScrollUp();
        }
        return super.canChildScrollUp();
    }

    public void setCanChildScrollUpListener(CanChildScrollUpListener listener) {
        this.mCanChildScrollUpListener = listener;
    }

    public void setScrollableChild(View view) {
        this.mScrollableChild = view;
        setCanChildScrollUpListener(AirSwipeRefreshLayout$$Lambda$1.lambdaFactory$(this));
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.prevX = event.getX();
                break;
            case 2:
                if (Math.abs(event.getX() - this.prevX) > ((float) this.touchSlop)) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
}
