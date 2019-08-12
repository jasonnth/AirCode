package com.airbnb.android.lib.views;

import android.graphics.Rect;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.ItemDecoration;
import android.support.p002v7.widget.RecyclerView.State;
import android.view.View;

public class SpacesItemDecoration extends ItemDecoration {
    private final int mPadding;

    public SpacesItemDecoration(int padding) {
        this.mPadding = padding;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        outRect.top = this.mPadding;
        outRect.left = this.mPadding;
        outRect.right = this.mPadding;
        outRect.bottom = this.mPadding;
    }
}
