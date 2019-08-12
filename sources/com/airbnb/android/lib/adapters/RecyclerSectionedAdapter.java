package com.airbnb.android.lib.adapters;

import android.support.p000v4.util.ArrayMap;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.AdapterDataObserver;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecyclerSectionedAdapter extends Adapter<ViewHolder> implements StickyRecyclerHeadersAdapter {
    private final List<Adapter<?>> mAdapters = new ArrayList();
    private final Map<Integer, Adapter<?>> mViewTypeMap = new ArrayMap();

    public void addAdapter(Adapter<?> adapter) {
        this.mAdapters.add(adapter);
        registerObserver(adapter);
    }

    private void registerObserver(final Adapter<?> adapter) {
        adapter.registerAdapterDataObserver(new AdapterDataObserver() {
            public void onChanged() {
                RecyclerSectionedAdapter.this.notifyDataSetChanged();
            }

            public void onItemRangeChanged(int positionStart, int itemCount) {
                RecyclerSectionedAdapter.this.notifyItemRangeChanged(RecyclerSectionedAdapter.this.getPositionInSectionedAdapter(adapter, positionStart), itemCount);
            }

            public void onItemRangeInserted(int positionStart, int itemCount) {
                RecyclerSectionedAdapter.this.notifyItemRangeInserted(RecyclerSectionedAdapter.this.getPositionInSectionedAdapter(adapter, positionStart), itemCount);
            }

            public void onItemRangeRemoved(int positionStart, int itemCount) {
                RecyclerSectionedAdapter.this.notifyItemRangeRemoved(RecyclerSectionedAdapter.this.getPositionInSectionedAdapter(adapter, positionStart), itemCount);
            }

            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                int offset = RecyclerSectionedAdapter.this.getPositionInSectionedAdapter(adapter, 0);
                for (int i = 0; i < itemCount; i++) {
                    RecyclerSectionedAdapter.this.notifyItemMoved(offset + i, offset + toPosition + i);
                }
            }
        });
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getAdapterForViewType(viewType).onCreateViewHolder(parent, viewType);
    }

    private Adapter<?> getAdapterForViewType(int viewType) {
        if (viewType == 0) {
            throw new IllegalStateException("you must specify a non 0 viewtype");
        } else if (this.mViewTypeMap.containsKey(Integer.valueOf(viewType))) {
            return (Adapter) this.mViewTypeMap.get(Integer.valueOf(viewType));
        } else {
            int count = getItemCount();
            for (int i = 0; i < count; i++) {
                if (viewType == getItemViewType(i)) {
                    Adapter<?> adapter = getAdapterForPosition(i);
                    this.mViewTypeMap.put(Integer.valueOf(viewType), adapter);
                    return adapter;
                }
            }
            return null;
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Adapter adapter = getAdapterForPosition(position);
        adapter.onBindViewHolder(holder, getPositionOffset(adapter, position));
    }

    public long getHeaderId(int position) {
        Adapter<?> stickyAdapter = getAdapterForPosition(position);
        if (stickyAdapter instanceof StickyRecyclerHeadersAdapter) {
            return ((StickyRecyclerHeadersAdapter) stickyAdapter).getHeaderId(getPositionOffset(stickyAdapter, position));
        }
        return -1;
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int position) {
        Adapter<?> stickyAdapter = getAdapterForPosition(position);
        if (stickyAdapter instanceof StickyRecyclerHeadersAdapter) {
            return ((StickyRecyclerHeadersAdapter) stickyAdapter).onCreateHeaderViewHolder(parent, getPositionOffset(stickyAdapter, position));
        }
        return null;
    }

    public void onBindHeaderViewHolder(ViewHolder holder, int position) {
        Adapter<?> stickyAdapter = getAdapterForPosition(position);
        if (stickyAdapter instanceof StickyRecyclerHeadersAdapter) {
            ((StickyRecyclerHeadersAdapter) stickyAdapter).onBindHeaderViewHolder(holder, getPositionOffset(stickyAdapter, position));
        }
    }

    public int getItemCount() {
        int count = 0;
        for (Adapter<?> adapter : this.mAdapters) {
            count += adapter.getItemCount();
        }
        return count;
    }

    public void onStickyHeaderStateChange(ViewHolder viewHolder, State state, int position) {
        Adapter<?> stickyAdapter = getAdapterForPosition(position);
        if (stickyAdapter instanceof StickyRecyclerHeadersAdapter) {
            ((StickyRecyclerHeadersAdapter) stickyAdapter).onStickyHeaderStateChange(viewHolder, state, getPositionOffset(stickyAdapter, position));
        }
    }

    public int getItemViewType(int position) {
        Adapter<?> adapter = getAdapterForPosition(position);
        return adapter.getItemViewType(getPositionOffset(adapter, position));
    }

    public void setHasStableIds(boolean hasStableIds) {
        for (Adapter<?> adapter : this.mAdapters) {
            adapter.setHasStableIds(hasStableIds);
        }
        super.setHasStableIds(hasStableIds);
    }

    public long getItemId(int position) {
        Adapter<?> adapter = getAdapterForPosition(position);
        return adapter.getItemId(getPositionOffset(adapter, position));
    }

    private Adapter<?> getAdapterForPosition(int position) {
        for (Adapter<?> adapter : this.mAdapters) {
            position -= adapter.getItemCount();
            if (position < 0) {
                return adapter;
            }
        }
        return null;
    }

    private int getPositionOffset(Adapter<?> currentAdapter, int position) {
        for (Adapter<?> adapter : this.mAdapters) {
            if (adapter.equals(currentAdapter)) {
                return position;
            }
            position -= adapter.getItemCount();
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public int getPositionInSectionedAdapter(Adapter<?> currentAdapter, int position) {
        for (Adapter<?> adapter : this.mAdapters) {
            if (adapter.equals(currentAdapter)) {
                return position;
            }
            position += adapter.getItemCount();
        }
        return -1;
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }
}
