package com.airbnb.android.utils;

import android.graphics.PointF;
import android.support.p000v4.view.ViewPager;
import android.view.MotionEvent;
import java.lang.ref.WeakReference;

public class HackBornViewTouchEventHandler {
    private WeakReference<ViewPager> mCurrentViewPager;
    private PointF mDragStartLoc;

    public void setCurrentViewPager(ViewPager viewPager) {
        this.mCurrentViewPager = new WeakReference<>(viewPager);
    }

    public void handleOnInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.mDragStartLoc = new PointF(ev.getX(), ev.getY());
        }
    }

    public boolean handleTouchEvent(MotionEvent ev) {
        if (!(ev.getAction() != 2 || this.mCurrentViewPager == null || this.mCurrentViewPager.get() == null)) {
            int[] location = new int[2];
            ((ViewPager) this.mCurrentViewPager.get()).getLocationOnScreen(location);
            float x = ev.getX();
            float y = ev.getY();
            if ((this.mDragStartLoc.x - x) * (this.mDragStartLoc.x - x) > (this.mDragStartLoc.y - y) * (this.mDragStartLoc.y - y)) {
                try {
                    if (((ViewPager) this.mCurrentViewPager.get()).dispatchTouchEvent(MotionEvent.obtain(ev.getDownTime(), ev.getEventTime(), ev.getAction(), ev.getX() - ((float) location[0]), ev.getY(), ev.getPressure(), ev.getSize(), ev.getMetaState(), ev.getXPrecision(), ev.getYPrecision(), ev.getDeviceId(), ev.getEdgeFlags()))) {
                        return true;
                    }
                    this.mCurrentViewPager.clear();
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    this.mCurrentViewPager.clear();
                }
            }
        }
        return false;
    }
}
