package com.airbnb.android.contentframework.imagepicker;

import android.graphics.Rect;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.ItemDecoration;
import android.support.p002v7.widget.RecyclerView.State;
import android.view.View;

public class MediaGridItemDecoration extends ItemDecoration {
    private final int colCount;
    private final int innerPaddingPx;

    public MediaGridItemDecoration(int colCount2, int innerPaddingPx2) {
        this.colCount = colCount2;
        this.innerPaddingPx = innerPaddingPx2;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % this.colCount;
        outRect.left = (this.innerPaddingPx * column) / this.colCount;
        outRect.right = this.innerPaddingPx - (((column + 1) * this.innerPaddingPx) / this.colCount);
        if (position >= this.colCount) {
            outRect.top = this.innerPaddingPx;
        }
    }
}
