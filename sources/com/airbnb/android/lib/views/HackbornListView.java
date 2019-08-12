package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;
import com.airbnb.android.core.interfaces.AirAbsListView;
import com.airbnb.android.core.interfaces.ViewPagerAbsListView;
import com.airbnb.android.utils.HackBornViewTouchEventHandler;

public class HackbornListView extends ListView implements AirAbsListView, ViewPagerAbsListView {
    private HackBornViewTouchEventHandler mEventHandler;

    public HackbornListView(Context context) {
        super(context);
        init();
    }

    public HackbornListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HackbornListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        this.mEventHandler = new HackBornViewTouchEventHandler();
    }

    public AbsListView getListView() {
        return this;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        this.mEventHandler.handleOnInterceptTouchEvent(ev);
        return super.onInterceptTouchEvent(ev);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (this.mEventHandler.handleTouchEvent(ev)) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    public AbsListView getAbsListView() {
        return this;
    }
}
