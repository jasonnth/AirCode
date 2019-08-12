package com.airbnb.epoxy;

import android.support.p002v7.widget.RecyclerView.AdapterDataObserver;
import android.util.Log;

public class EpoxyDiffLogger extends AdapterDataObserver {
    private final String tag;

    public EpoxyDiffLogger(String tag2) {
        this.tag = tag2;
    }

    public void onItemRangeChanged(int positionStart, int itemCount) {
        Log.d(this.tag, "Item range changed. Start: " + positionStart + " Count: " + itemCount);
    }

    public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
        if (payload == null) {
            onItemRangeChanged(positionStart, itemCount);
        } else {
            Log.d(this.tag, "Item range changed with payloads. Start: " + positionStart + " Count: " + itemCount);
        }
    }

    public void onItemRangeInserted(int positionStart, int itemCount) {
        Log.d(this.tag, "Item range inserted. Start: " + positionStart + " Count: " + itemCount);
    }

    public void onItemRangeRemoved(int positionStart, int itemCount) {
        Log.d(this.tag, "Item range removed. Start: " + positionStart + " Count: " + itemCount);
    }

    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        Log.d(this.tag, "Item moved. From: " + fromPosition + " To: " + toPosition);
    }
}
