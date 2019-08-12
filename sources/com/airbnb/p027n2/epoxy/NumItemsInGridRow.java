package com.airbnb.p027n2.epoxy;

import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;

/* renamed from: com.airbnb.n2.epoxy.NumItemsInGridRow */
public class NumItemsInGridRow implements SpanSizeOverrideCallback {
    public final int numItemsForCurrentScreen;

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (totalSpanCount % this.numItemsForCurrentScreen == 0) {
            return totalSpanCount / this.numItemsForCurrentScreen;
        }
        throw new IllegalStateException("Total Span Count of : " + totalSpanCount + " can not evenly fit: " + this.numItemsForCurrentScreen + " cards per row");
    }
}
