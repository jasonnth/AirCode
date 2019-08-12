package com.airbnb.epoxy;

import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import java.util.List;

public class EpoxyViewHolder extends ViewHolder {
    private EpoxyHolder epoxyHolder;
    private EpoxyModel epoxyModel;
    private List<Object> payloads;

    public EpoxyViewHolder(View view) {
        super(view);
    }

    public void bind(EpoxyModel model, EpoxyModel<?> previouslyBoundModel, List<Object> payloads2, int position) {
        this.payloads = payloads2;
        if (this.epoxyHolder == null && (model instanceof EpoxyModelWithHolder)) {
            this.epoxyHolder = ((EpoxyModelWithHolder) model).createNewHolder();
            this.epoxyHolder.bindView(this.itemView);
        }
        if (model instanceof GeneratedModel) {
            ((GeneratedModel) model).handlePreBind(this, objectToBind(), position);
        }
        if (previouslyBoundModel != null) {
            model.bind(objectToBind(), previouslyBoundModel);
        } else if (payloads2.isEmpty()) {
            model.bind(objectToBind());
        } else {
            model.bind(objectToBind(), payloads2);
        }
        if (model instanceof GeneratedModel) {
            ((GeneratedModel) model).handlePostBind(objectToBind(), position);
        }
        this.epoxyModel = model;
    }

    /* access modifiers changed from: 0000 */
    public Object objectToBind() {
        return this.epoxyHolder != null ? this.epoxyHolder : this.itemView;
    }

    public void unbind() {
        assertBound();
        this.epoxyModel.unbind(objectToBind());
        this.epoxyModel = null;
        this.payloads = null;
    }

    public EpoxyModel<?> getModel() {
        assertBound();
        return this.epoxyModel;
    }

    private void assertBound() {
        if (this.epoxyModel == null) {
            throw new IllegalStateException("This holder is not currently bound.");
        }
    }

    public String toString() {
        return "EpoxyViewHolder{epoxyModel=" + this.epoxyModel + ", view=" + this.itemView + ", super=" + super.toString() + '}';
    }
}
