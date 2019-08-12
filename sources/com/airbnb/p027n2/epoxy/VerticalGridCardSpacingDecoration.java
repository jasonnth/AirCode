package com.airbnb.p027n2.epoxy;

import android.graphics.Rect;
import android.support.p002v7.widget.GridLayoutManager;
import android.support.p002v7.widget.GridLayoutManager.LayoutParams;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.ItemDecoration;
import android.support.p002v7.widget.RecyclerView.State;
import android.view.View;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.epoxy.VerticalGridCardSpacingDecoration */
public class VerticalGridCardSpacingDecoration extends ItemDecoration {
    private static int innerPadding = -1;
    public static final int innerPaddingRes = R.dimen.n2_grid_card_inner_horizontal_padding;

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        if (innerPadding == -1) {
            innerPadding = view.getResources().getDimensionPixelSize(innerPaddingRes);
        }
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int itemSpanSize = ((LayoutParams) view.getLayoutParams()).getSpanSize();
        outRect.setEmpty();
        int spanCount = layoutManager.getSpanCount();
        if (itemSpanSize == spanCount) {
            outRect.left = -parent.getPaddingLeft();
            outRect.right = -parent.getPaddingRight();
        } else if (itemSpanSize < spanCount) {
            outRect.left = innerPadding;
            outRect.right = innerPadding;
        }
    }
}
