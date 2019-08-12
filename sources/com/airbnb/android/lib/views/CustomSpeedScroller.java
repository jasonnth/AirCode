package com.airbnb.android.lib.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class CustomSpeedScroller extends Scroller {
    private int mDuration = 400;

    public CustomSpeedScroller(Context context) {
        super(context);
    }

    public CustomSpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @TargetApi(11)
    public CustomSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, this.mDuration);
    }

    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, this.mDuration);
    }

    public void setScrollDuration(int duration) {
        this.mDuration = duration;
    }
}
