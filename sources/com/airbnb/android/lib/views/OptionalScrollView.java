package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class OptionalScrollView extends ScrollView {
    private boolean scrollingEnabled = true;

    public OptionalScrollView(Context context) {
        super(context);
    }

    public OptionalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OptionalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollEnabled(boolean enabled) {
        this.scrollingEnabled = enabled;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!this.scrollingEnabled) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
