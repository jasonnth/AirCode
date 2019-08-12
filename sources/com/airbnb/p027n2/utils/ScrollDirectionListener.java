package com.airbnb.p027n2.utils;

import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;

/* renamed from: com.airbnb.n2.utils.ScrollDirectionListener */
public abstract class ScrollDirectionListener extends OnScrollListener {
    public static final String SCROLL_DOWN = "scroll_down";
    public static final String SCROLL_LEFT = "scroll_left";
    public static final String SCROLL_RIGHT = "scroll_right";
    public static final String SCROLL_UP = "scroll_up";
    protected int totalDx;
    protected int totalDy;

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == 0) {
            if (this.totalDx > 0) {
                onScrollEnd(recyclerView, SCROLL_RIGHT);
            } else if (this.totalDx < 0) {
                onScrollEnd(recyclerView, SCROLL_LEFT);
            } else if (this.totalDy > 0) {
                onScrollEnd(recyclerView, SCROLL_DOWN);
            } else if (this.totalDy < 0) {
                onScrollEnd(recyclerView, SCROLL_UP);
            }
            this.totalDx = 0;
            this.totalDy = 0;
        }
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (recyclerView.getScrollState() == 1) {
            this.totalDx += dx;
            this.totalDy += dy;
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollEnd(RecyclerView recyclerView, String scrollType) {
    }
}
