package com.timehop.stickyheadersrecyclerview;

import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration.State;

public interface StickyRecyclerHeadersAdapter<VH extends ViewHolder> {
    long getHeaderId(int i);

    int getItemCount();

    void onBindHeaderViewHolder(VH vh, int i);

    VH onCreateHeaderViewHolder(ViewGroup viewGroup, int i);

    void onStickyHeaderStateChange(ViewHolder viewHolder, State state, int i);
}
