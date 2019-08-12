package com.airbnb.epoxy;

import com.airbnb.epoxy.EpoxyHolder;
import java.util.List;

public abstract class EpoxyModelWithHolder<T extends EpoxyHolder> extends EpoxyModel<T> {
    /* access modifiers changed from: protected */
    public abstract T createNewHolder();

    public EpoxyModelWithHolder() {
    }

    public EpoxyModelWithHolder(long id) {
        super(id);
    }

    public void bind(T holder) {
        super.bind(holder);
    }

    public void bind(T holder, List<Object> payloads) {
        super.bind(holder, payloads);
    }

    public void bind(T holder, EpoxyModel<?> previouslyBoundModel) {
        super.bind(holder, previouslyBoundModel);
    }

    public void unbind(T holder) {
        super.unbind(holder);
    }

    public boolean onFailedToRecycleView(T holder) {
        return super.onFailedToRecycleView(holder);
    }

    public void onViewAttachedToWindow(T holder) {
        super.onViewAttachedToWindow(holder);
    }

    public void onViewDetachedFromWindow(T holder) {
        super.onViewDetachedFromWindow(holder);
    }
}
