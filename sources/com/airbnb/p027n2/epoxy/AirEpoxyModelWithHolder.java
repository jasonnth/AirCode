package com.airbnb.p027n2.epoxy;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.airbnb.p027n2.N2Context;
import com.airbnb.p027n2.epoxy.AirViewHolder;
import java.util.List;

/* renamed from: com.airbnb.n2.epoxy.AirEpoxyModelWithHolder */
public abstract class AirEpoxyModelWithHolder<T extends AirViewHolder> extends EpoxyModelWithHolder<T> implements AirModel {
    public Boolean showDivider;

    public void bind(T holder) {
        updateDivider(holder);
    }

    public void bind(T holder, List<Object> list) {
        updateDivider(holder);
    }

    private void updateDivider(T holder) {
        if (supportsDividers()) {
            holder.showDivider(this.showDivider != null && this.showDivider.booleanValue());
        }
    }

    public final boolean supportsDividers() {
        return (getDividerViewType() == -1 || getDividerViewType() == 2) ? false : true;
    }

    public int getDividerViewType() {
        return -1;
    }

    public AirModel showDivider(boolean showDivider2) {
        if (!supportsDividers()) {
            N2Context.instance().graph().mo11971n2().throwOrNotify(new IllegalStateException("Model does not support dividers"));
        }
        this.showDivider = Boolean.valueOf(showDivider2);
        return this;
    }

    public final Boolean isShowingDivider() {
        return this.showDivider;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return EpoxyUtils.getDefaultSpanForDividerType(getDividerViewType(), totalSpanCount, position, itemCount);
    }

    public EpoxyModel<T> reset() {
        this.showDivider = null;
        return super.reset();
    }

    public boolean canReuseUpdatedView(List<Object> list) {
        return true;
    }
}
