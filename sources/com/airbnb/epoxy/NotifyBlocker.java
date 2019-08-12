package com.airbnb.epoxy;

import android.support.p002v7.widget.RecyclerView.AdapterDataObserver;

class NotifyBlocker extends AdapterDataObserver {
    private boolean changesAllowed;

    NotifyBlocker() {
    }

    /* access modifiers changed from: 0000 */
    public void allowChanges() {
        this.changesAllowed = true;
    }

    /* access modifiers changed from: 0000 */
    public void blockChanges() {
        this.changesAllowed = false;
    }

    public void onChanged() {
        if (!this.changesAllowed) {
            throw new IllegalStateException("You cannot notify item changes directly. Call `requestModelBuild` instead.");
        }
    }

    public void onItemRangeChanged(int positionStart, int itemCount) {
        onChanged();
    }

    public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
        onChanged();
    }

    public void onItemRangeInserted(int positionStart, int itemCount) {
        onChanged();
    }

    public void onItemRangeRemoved(int positionStart, int itemCount) {
        onChanged();
    }

    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        onChanged();
    }
}
