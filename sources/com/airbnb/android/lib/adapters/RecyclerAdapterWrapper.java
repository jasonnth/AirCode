package com.airbnb.android.lib.adapters;

import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.AdapterDataObserver;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

public class RecyclerAdapterWrapper<VH extends ViewHolder> extends Adapter<VH> {
    private final Adapter<VH> mWrappedAdapter;
    /* access modifiers changed from: private */
    public int mWrappedExtraPositions;

    public RecyclerAdapterWrapper(Adapter<VH> adapter) {
        this.mWrappedAdapter = adapter;
        this.mWrappedAdapter.registerAdapterDataObserver(new AdapterDataObserver() {
            public void onChanged() {
                RecyclerAdapterWrapper.this.notifyDataSetChanged();
            }

            public void onItemRangeChanged(int positionStart, int itemCount) {
                RecyclerAdapterWrapper.this.notifyItemRangeChanged(RecyclerAdapterWrapper.this.mWrappedExtraPositions + positionStart, itemCount);
            }

            public void onItemRangeInserted(int positionStart, int itemCount) {
                RecyclerAdapterWrapper.this.notifyItemRangeInserted(RecyclerAdapterWrapper.this.mWrappedExtraPositions + positionStart, itemCount);
            }

            public void onItemRangeRemoved(int positionStart, int itemCount) {
                RecyclerAdapterWrapper.this.notifyItemRangeRemoved(RecyclerAdapterWrapper.this.mWrappedExtraPositions + positionStart, itemCount);
            }

            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                for (int i = 0; i < itemCount; i++) {
                    RecyclerAdapterWrapper.this.notifyItemMoved(fromPosition + i + RecyclerAdapterWrapper.this.mWrappedExtraPositions, toPosition + i);
                }
            }
        });
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return this.mWrappedAdapter.onCreateViewHolder(parent, viewType);
    }

    public void onBindViewHolder(VH holder, int position) {
        this.mWrappedAdapter.onBindViewHolder(holder, position);
    }

    public int getItemViewType(int position) {
        return this.mWrappedAdapter.getItemViewType(position);
    }

    public void setHasStableIds(boolean hasStableIds) {
        this.mWrappedAdapter.setHasStableIds(hasStableIds);
    }

    public long getItemId(int position) {
        return this.mWrappedAdapter.getItemId(position);
    }

    public int getItemCount() {
        return this.mWrappedAdapter.getItemCount();
    }

    public void onViewRecycled(VH holder) {
        this.mWrappedAdapter.onViewRecycled(holder);
    }

    public void onViewAttachedToWindow(VH holder) {
        this.mWrappedAdapter.onViewAttachedToWindow(holder);
    }

    public void onViewDetachedFromWindow(VH holder) {
        this.mWrappedAdapter.onViewDetachedFromWindow(holder);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.mWrappedAdapter.onAttachedToRecyclerView(recyclerView);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.mWrappedAdapter.onDetachedFromRecyclerView(recyclerView);
    }

    /* access modifiers changed from: protected */
    public Adapter<VH> getWrappedAdapter() {
        return this.mWrappedAdapter;
    }

    /* access modifiers changed from: protected */
    public void setWrappedExtraPositions(int count) {
        if (count != this.mWrappedExtraPositions) {
            int oldCount = this.mWrappedExtraPositions;
            this.mWrappedExtraPositions = count;
            if (oldCount == 0) {
                notifyItemRangeInserted(0, count);
            } else if (count > oldCount) {
                notifyItemRangeInserted(oldCount, count - oldCount);
            } else {
                notifyItemRangeRemoved(0, oldCount);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getWrappedExtraPositions() {
        return this.mWrappedExtraPositions;
    }
}
