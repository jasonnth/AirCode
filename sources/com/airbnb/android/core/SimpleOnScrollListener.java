package com.airbnb.android.core;

import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;

public abstract class SimpleOnScrollListener extends OnScrollListener {
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    }
}
