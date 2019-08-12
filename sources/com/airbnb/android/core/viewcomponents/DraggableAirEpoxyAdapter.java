package com.airbnb.android.core.viewcomponents;

import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.helper.ItemTouchHelper;

public class DraggableAirEpoxyAdapter extends AirEpoxyAdapter {
    private final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new DraggableAirEpoxyItemTouchHelperCallback(DraggableAirEpoxyAdapter$$Lambda$1.lambdaFactory$(this)));

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    /* access modifiers changed from: protected */
    public boolean onMove(int fromPosition, int toPosition) {
        this.models.add(toPosition, this.models.remove(fromPosition));
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
}
