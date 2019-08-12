package com.airbnb.epoxy;

import android.os.Bundle;
import android.support.p002v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

abstract class BaseEpoxyAdapter extends Adapter<EpoxyViewHolder> {
    private static final String SAVED_STATE_ARG_VIEW_HOLDERS = "saved_state_view_holders";
    private final BoundViewHolders boundViewHolders = new BoundViewHolders();
    /* access modifiers changed from: private */
    public int spanCount = 1;
    private final SpanSizeLookup spanSizeLookup = new SpanSizeLookup() {
        public int getSpanSize(int position) {
            try {
                return BaseEpoxyAdapter.this.getModelForPosition(position).getSpanSizeInternal(BaseEpoxyAdapter.this.spanCount, position, BaseEpoxyAdapter.this.getItemCount());
            } catch (IndexOutOfBoundsException e) {
                BaseEpoxyAdapter.this.onExceptionSwallowed(e);
                return 1;
            }
        }
    };
    private ViewHolderState viewHolderState = new ViewHolderState();
    private final ViewTypeManager viewTypeManager = new ViewTypeManager();

    /* access modifiers changed from: 0000 */
    public abstract List<EpoxyModel<?>> getCurrentModels();

    public BaseEpoxyAdapter() {
        setHasStableIds(true);
        this.spanSizeLookup.setSpanIndexCacheEnabled(true);
    }

    /* access modifiers changed from: protected */
    public void onExceptionSwallowed(RuntimeException exception) {
    }

    public int getItemCount() {
        return getCurrentModels().size();
    }

    public boolean isEmpty() {
        return getCurrentModels().isEmpty();
    }

    public EpoxyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EpoxyViewHolder(this.viewTypeManager.getModelForViewType(this, viewType).buildView(parent));
    }

    public void onBindViewHolder(EpoxyViewHolder holder, int position) {
        onBindViewHolder(holder, position, Collections.emptyList());
    }

    public void onBindViewHolder(EpoxyViewHolder holder, int position, List<Object> payloads) {
        EpoxyViewHolder boundViewHolder = this.boundViewHolders.get(holder);
        if (boundViewHolder != null) {
            this.viewHolderState.save(boundViewHolder);
        }
        EpoxyModel<?> modelToShow = getModelForPosition(position);
        EpoxyModel<?> previouslyBoundModel = null;
        if (diffPayloadsEnabled()) {
            previouslyBoundModel = DiffPayload.getModelFromPayload(payloads, getItemId(position));
        }
        holder.bind(modelToShow, previouslyBoundModel, payloads, position);
        this.viewHolderState.restore(holder);
        this.boundViewHolders.put(holder);
        if (diffPayloadsEnabled()) {
            onModelBound(holder, modelToShow, position, previouslyBoundModel);
        } else {
            onModelBound(holder, modelToShow, position, payloads);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean diffPayloadsEnabled() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> model, int position, List<Object> list) {
        onModelBound(holder, model, position);
    }

    /* access modifiers changed from: 0000 */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> model, int position, EpoxyModel<?> epoxyModel) {
        onModelBound(holder, model, position);
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> epoxyModel, int position) {
    }

    /* access modifiers changed from: protected */
    public BoundViewHolders getBoundViewHolders() {
        return this.boundViewHolders;
    }

    public int getItemViewType(int position) {
        return this.viewTypeManager.getViewType(getModelForPosition(position));
    }

    public long getItemId(int position) {
        return ((EpoxyModel) getCurrentModels().get(position)).mo11715id();
    }

    /* access modifiers changed from: 0000 */
    public EpoxyModel<?> getModelForPosition(int position) {
        return (EpoxyModel) getCurrentModels().get(position);
    }

    public void onViewRecycled(EpoxyViewHolder holder) {
        this.viewHolderState.save(holder);
        this.boundViewHolders.remove(holder);
        EpoxyModel<?> model = holder.getModel();
        holder.unbind();
        onModelUnbound(holder, model);
    }

    /* access modifiers changed from: protected */
    public void onModelUnbound(EpoxyViewHolder holder, EpoxyModel<?> epoxyModel) {
    }

    public boolean onFailedToRecycleView(EpoxyViewHolder holder) {
        return holder.getModel().onFailedToRecycleView(holder.objectToBind());
    }

    public void onViewAttachedToWindow(EpoxyViewHolder holder) {
        holder.getModel().onViewAttachedToWindow(holder.objectToBind());
    }

    public void onViewDetachedFromWindow(EpoxyViewHolder holder) {
        holder.getModel().onViewDetachedFromWindow(holder.objectToBind());
    }

    public void onSaveInstanceState(Bundle outState) {
        Iterator it = this.boundViewHolders.iterator();
        while (it.hasNext()) {
            this.viewHolderState.save((EpoxyViewHolder) it.next());
        }
        if (this.viewHolderState.size() <= 0 || hasStableIds()) {
            outState.putParcelable(SAVED_STATE_ARG_VIEW_HOLDERS, this.viewHolderState);
            return;
        }
        throw new IllegalStateException("Must have stable ids when saving view holder state");
    }

    public void onRestoreInstanceState(Bundle inState) {
        if (this.boundViewHolders.size() > 0) {
            throw new IllegalStateException("State cannot be restored once views have been bound. It should be done before adding the adapter to the recycler view.");
        } else if (inState != null) {
            this.viewHolderState = (ViewHolderState) inState.getParcelable(SAVED_STATE_ARG_VIEW_HOLDERS);
            if (this.viewHolderState == null) {
                throw new IllegalStateException("Tried to restore instance state, but onSaveInstanceState was never called.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getModelPosition(EpoxyModel<?> model) {
        int size = getCurrentModels().size();
        for (int i = 0; i < size; i++) {
            if (model == getCurrentModels().get(i)) {
                return i;
            }
        }
        return -1;
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.spanSizeLookup;
    }

    public void setSpanCount(int spanCount2) {
        this.spanCount = spanCount2;
    }

    public int getSpanCount() {
        return this.spanCount;
    }

    public boolean isMultiSpan() {
        return this.spanCount > 1;
    }
}
