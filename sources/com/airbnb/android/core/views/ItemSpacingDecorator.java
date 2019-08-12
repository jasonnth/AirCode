package com.airbnb.android.core.views;

import android.content.Context;
import android.graphics.Rect;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.ItemDecoration;
import android.support.p002v7.widget.RecyclerView.State;
import android.view.View;

public class ItemSpacingDecorator extends ItemDecoration {
    private final int paddingPx;
    private final boolean vertical;

    public ItemSpacingDecorator(Context context, boolean vertical2, int spacingSize) {
        this.vertical = vertical2;
        this.paddingPx = context.getResources().getDimensionPixelOffset(spacingSize);
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        outRect.right = 0;
        outRect.left = 0;
        outRect.top = 0;
        outRect.bottom = 0;
        if (!isLastItem(view, parent)) {
            if (this.vertical) {
                outRect.bottom = this.paddingPx;
            } else {
                outRect.right = this.paddingPx;
            }
        }
    }

    private boolean isLastItem(View view, RecyclerView parent) {
        return parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() + -1;
    }
}
