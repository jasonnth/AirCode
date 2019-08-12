package com.airbnb.android.core.utils;

import android.support.p002v7.widget.GridLayoutManager;
import android.support.p002v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.p002v7.widget.RecyclerView;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.epoxy.VerticalGridCardSpacingDecoration;

public class LayoutManagerUtils {
    public static void setGridLayout(AirEpoxyAdapter adapter, RecyclerView recyclerView, int spanSize) {
        adapter.setSpanCount(spanSize);
        setGridLayout(adapter.getSpanSizeLookup(), recyclerView, spanSize);
    }

    public static void setGridLayout(EpoxyController controller, RecyclerView recyclerView, int spanSize) {
        controller.setSpanCount(spanSize);
        setGridLayout(controller.getSpanSizeLookup(), recyclerView, spanSize);
    }

    private static void setGridLayout(SpanSizeLookup spanSizeLookup, RecyclerView recyclerView, int spanSize) {
        GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(), spanSize);
        layoutManager.setSpanSizeLookup(spanSizeLookup);
        int recyclerViewPaddingForGrids = recyclerView.getResources().getDimensionPixelSize(C0716R.dimen.n2_grid_card_recycler_view_padding);
        recyclerView.setPadding(recyclerViewPaddingForGrids, recyclerView.getPaddingTop(), recyclerViewPaddingForGrids, recyclerView.getPaddingBottom());
        recyclerView.setClipToPadding(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
    }
}
