package com.airbnb.p027n2.epoxy;

import android.support.p002v7.widget.DefaultItemAnimator;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import java.util.List;

/* renamed from: com.airbnb.n2.epoxy.EpoxyItemAnimator */
public class EpoxyItemAnimator extends DefaultItemAnimator {
    public boolean canReuseUpdatedViewHolder(ViewHolder viewHolder, List<Object> payloads) {
        if (!(viewHolder instanceof EpoxyViewHolder)) {
            throw new IllegalStateException("This animator can only be used with EpoxyAdapter");
        }
        EpoxyModel<?> model = ((EpoxyViewHolder) viewHolder).getModel();
        if (model instanceof AirEpoxyModel) {
            return ((AirEpoxyModel) model).canReuseUpdatedView(payloads);
        }
        if (model instanceof AirEpoxyModelWithHolder) {
            return ((AirEpoxyModelWithHolder) model).canReuseUpdatedView(payloads);
        }
        return super.canReuseUpdatedViewHolder(viewHolder, payloads);
    }
}
