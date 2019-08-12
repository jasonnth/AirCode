package com.airbnb.p027n2.components.photorearranger;

import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.support.p002v7.widget.helper.ItemTouchHelper.Callback;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangingCallback */
class RearrangingCallback extends Callback {
    private final PhotoRearrangerAdapter<? extends PhotoRearrangerItem> adapter;
    private boolean enabled;

    /* renamed from: com.airbnb.n2.components.photorearranger.RearrangingCallback$ViewHolderOperator */
    interface ViewHolderOperator {
        /* renamed from: op */
        void mo26162op(PhotoRearrangerViewHolder photoRearrangerViewHolder);
    }

    RearrangingCallback(PhotoRearrangerAdapter<? extends PhotoRearrangerItem> adapter2) {
        this.adapter = adapter2;
    }

    public void setEnabled(boolean enabled2) {
        this.enabled = enabled2;
    }

    public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
        int dragFlags;
        if (isValidAndRearrangable(viewHolder)) {
            dragFlags = 15;
        } else {
            dragFlags = 0;
        }
        return makeMovementFlags(dragFlags, 0);
    }

    public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder target) {
        if (!isValidAndRearrangable(viewHolder) || !isValidAndRearrangable(target)) {
            return false;
        }
        this.adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            operateIfRearrangable(recyclerView.getChildViewHolder(recyclerView.getChildAt(i)), RearrangingCallback$$Lambda$1.lambdaFactory$());
        }
        return true;
    }

    public boolean isLongPressDragEnabled() {
        return this.enabled;
    }

    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    public void onSwiped(ViewHolder viewHolder, int direction) {
    }

    public void onSelectedChanged(ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != 0) {
            operateIfRearrangable(viewHolder, RearrangingCallback$$Lambda$2.lambdaFactory$());
        }
    }

    public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        operateIfRearrangable(viewHolder, RearrangingCallback$$Lambda$3.lambdaFactory$());
    }

    private boolean isValidAndRearrangable(ViewHolder viewHolder) {
        if (viewHolder == null) {
            return false;
        }
        int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition == -1 || !this.adapter.canRearrangeItemAt(adapterPosition)) {
            return false;
        }
        return true;
    }

    private static void operateIfRearrangable(ViewHolder viewHolder, ViewHolderOperator operator) {
        if (viewHolder != null && (viewHolder instanceof PhotoRearrangerViewHolder)) {
            operator.mo26162op((PhotoRearrangerViewHolder) viewHolder);
        }
    }
}
