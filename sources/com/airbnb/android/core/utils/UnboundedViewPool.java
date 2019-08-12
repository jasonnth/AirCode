package com.airbnb.android.core.utils;

import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import java.util.LinkedList;
import java.util.Queue;

public class UnboundedViewPool extends RecycledViewPool {
    private final SparseArray<Queue<ViewHolder>> scrapHeaps = new SparseArray<>();

    public void clear() {
        this.scrapHeaps.clear();
    }

    public void setMaxRecycledViews(int viewType, int max) {
        throw new UnsupportedOperationException("UnboundedViewPool does not support setting a maximum number of recycled views");
    }

    public ViewHolder getRecycledView(int viewType) {
        Queue<ViewHolder> scrapHeap = (Queue) this.scrapHeaps.get(viewType);
        if (scrapHeap != null) {
            return (ViewHolder) scrapHeap.poll();
        }
        return null;
    }

    public void putRecycledView(ViewHolder viewHolder) {
        getScrapHeapForType(viewHolder.getItemViewType()).add(viewHolder);
    }

    private Queue<ViewHolder> getScrapHeapForType(int viewType) {
        Queue<ViewHolder> scrapHeap = (Queue) this.scrapHeaps.get(viewType);
        if (scrapHeap != null) {
            return scrapHeap;
        }
        Queue<ViewHolder> scrapHeap2 = new LinkedList<>();
        this.scrapHeaps.put(viewType, scrapHeap2);
        return scrapHeap2;
    }
}
