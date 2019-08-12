package com.airbnb.android.core.viewcomponents;

import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.support.p002v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public class DraggableAirEpoxyItemTouchHelperCallback extends Callback {
    private static final int DRAGABLE_FLAGS = 15;
    private static final int NON_DRAGABLE_FLAGS = 0;
    private final ViewHolderMovedListener listener;

    public interface ViewHolderMovedListener {
        boolean onMove(int i, int i2);
    }

    public DraggableAirEpoxyItemTouchHelperCallback(ViewHolderMovedListener listener2) {
        this.listener = listener2;
    }

    public boolean isLongPressDragEnabled() {
        return true;
    }

    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
        int dragFlags;
        if (isRearrangable(viewHolder)) {
            dragFlags = 15;
        } else {
            dragFlags = 0;
        }
        return makeMovementFlags(dragFlags, 0);
    }

    public void onSwiped(ViewHolder viewHolder, int direction) {
    }

    public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder target) {
        boolean itemsAreMovable;
        if (!isRearrangable(viewHolder) || !isRearrangable(target)) {
            itemsAreMovable = false;
        } else {
            itemsAreMovable = true;
        }
        return itemsAreMovable && this.listener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    public void onSelectedChanged(ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (viewHolder != null) {
            notifyModel(getModel(viewHolder), viewHolder.itemView, true);
        }
    }

    public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        notifyModel(getModel(viewHolder), viewHolder.itemView, false);
    }

    private <T extends View> void notifyModel(AirEpoxyModel<T> model, View view, boolean isDragging) {
        if (model != null) {
            model.onDraggingStateChanged(view, isDragging);
        }
    }

    private static boolean isRearrangable(ViewHolder viewHolder) {
        AirEpoxyModel<?> model = getModel(viewHolder);
        return model != null && model.isRearrangeable();
    }

    private static AirEpoxyModel<?> getModel(ViewHolder uncastViewHolder) {
        if (uncastViewHolder == null) {
            return null;
        }
        EpoxyModel<?> epoxyModel = ((EpoxyViewHolder) uncastViewHolder).getModel();
        if (!(epoxyModel instanceof AirEpoxyModel)) {
            return null;
        }
        return (AirEpoxyModel) epoxyModel;
    }
}
