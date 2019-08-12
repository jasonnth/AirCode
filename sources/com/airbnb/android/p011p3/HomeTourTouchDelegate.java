package com.airbnb.android.p011p3;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;

/* renamed from: com.airbnb.android.p3.HomeTourTouchDelegate */
public class HomeTourTouchDelegate extends TouchDelegate {
    private View delegateView;
    private Rect mBounds;
    private boolean mDelegateTargeted;
    private int mSlop;
    private Rect mSlopBounds;

    public HomeTourTouchDelegate(Rect bounds, View delegateView2) {
        super(bounds, delegateView2);
        this.mBounds = bounds;
        this.mSlop = ViewConfiguration.get(delegateView2.getContext()).getScaledTouchSlop();
        this.mSlopBounds = new Rect(bounds);
        this.mSlopBounds.inset(-this.mSlop, -this.mSlop);
        this.delegateView = delegateView2;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        boolean sendToDelegate = false;
        boolean hit = true;
        switch (event.getAction()) {
            case 0:
                if (this.mBounds.contains(x, y)) {
                    this.mDelegateTargeted = true;
                    sendToDelegate = true;
                    break;
                }
                break;
            case 1:
            case 2:
                sendToDelegate = this.mDelegateTargeted;
                if (sendToDelegate && !this.mSlopBounds.contains(x, y)) {
                    hit = false;
                    break;
                }
            case 3:
                sendToDelegate = this.mDelegateTargeted;
                this.mDelegateTargeted = false;
                break;
        }
        if (!sendToDelegate) {
            return false;
        }
        View delegateView2 = this.delegateView;
        if (hit) {
            event.setLocation((float) x, (float) (delegateView2.getHeight() / 2));
        } else {
            int slop = this.mSlop;
            event.setLocation((float) (-(slop * 2)), (float) (-(slop * 2)));
        }
        return delegateView2.dispatchTouchEvent(event);
    }
}
