package com.airbnb.epoxy;

import android.os.Bundle;
import android.support.p002v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.p002v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.List;

public final class EpoxyControllerAdapter extends BaseEpoxyAdapter {
    private List<EpoxyModel<?>> copyOfCurrentModels;
    private List<EpoxyModel<?>> currentModels = Collections.emptyList();
    private final DiffHelper diffHelper = new DiffHelper(this, true);
    private final EpoxyController epoxyController;
    private int itemCount;
    private final NotifyBlocker notifyBlocker = new NotifyBlocker();

    public /* bridge */ /* synthetic */ long getItemId(int i) {
        return super.getItemId(i);
    }

    public /* bridge */ /* synthetic */ int getItemViewType(int i) {
        return super.getItemViewType(i);
    }

    public /* bridge */ /* synthetic */ int getSpanCount() {
        return super.getSpanCount();
    }

    public /* bridge */ /* synthetic */ SpanSizeLookup getSpanSizeLookup() {
        return super.getSpanSizeLookup();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public /* bridge */ /* synthetic */ boolean isMultiSpan() {
        return super.isMultiSpan();
    }

    public /* bridge */ /* synthetic */ void onBindViewHolder(EpoxyViewHolder epoxyViewHolder, int i) {
        super.onBindViewHolder(epoxyViewHolder, i);
    }

    public /* bridge */ /* synthetic */ void onBindViewHolder(EpoxyViewHolder epoxyViewHolder, int i, List list) {
        super.onBindViewHolder(epoxyViewHolder, i, list);
    }

    public /* bridge */ /* synthetic */ EpoxyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return super.onCreateViewHolder(viewGroup, i);
    }

    public /* bridge */ /* synthetic */ boolean onFailedToRecycleView(EpoxyViewHolder epoxyViewHolder) {
        return super.onFailedToRecycleView(epoxyViewHolder);
    }

    public /* bridge */ /* synthetic */ void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    public /* bridge */ /* synthetic */ void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public /* bridge */ /* synthetic */ void onViewRecycled(EpoxyViewHolder epoxyViewHolder) {
        super.onViewRecycled(epoxyViewHolder);
    }

    public /* bridge */ /* synthetic */ void setSpanCount(int i) {
        super.setSpanCount(i);
    }

    EpoxyControllerAdapter(EpoxyController epoxyController2) {
        this.epoxyController = epoxyController2;
        registerAdapterDataObserver(this.notifyBlocker);
    }

    /* access modifiers changed from: protected */
    public void onExceptionSwallowed(RuntimeException exception) {
        this.epoxyController.onExceptionSwallowed(exception);
    }

    /* access modifiers changed from: 0000 */
    public List<EpoxyModel<?>> getCurrentModels() {
        return this.currentModels;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    /* access modifiers changed from: 0000 */
    public void setModels(List<EpoxyModel<?>> models) {
        this.itemCount = models.size();
        this.copyOfCurrentModels = null;
        this.currentModels = models;
        this.notifyBlocker.allowChanges();
        this.diffHelper.notifyModelChanges();
        this.notifyBlocker.blockChanges();
    }

    /* access modifiers changed from: 0000 */
    public boolean diffPayloadsEnabled() {
        return true;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.epoxyController.onAttachedToRecyclerViewInternal(recyclerView);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.epoxyController.onDetachedFromRecyclerViewInternal(recyclerView);
    }

    public void onViewAttachedToWindow(EpoxyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        this.epoxyController.onViewAttachedToWindow(holder, holder.getModel());
    }

    public void onViewDetachedFromWindow(EpoxyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        this.epoxyController.onViewDetachedFromWindow(holder, holder.getModel());
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> model, int position, EpoxyModel<?> previouslyBoundModel) {
        this.epoxyController.onModelBound(holder, model, position, previouslyBoundModel);
    }

    /* access modifiers changed from: protected */
    public void onModelUnbound(EpoxyViewHolder holder, EpoxyModel<?> model) {
        this.epoxyController.onModelUnbound(holder, model);
    }

    public List<EpoxyModel<?>> getCopyOfModels() {
        if (this.copyOfCurrentModels == null) {
            this.copyOfCurrentModels = new UnmodifiableList(this.currentModels);
        }
        return this.copyOfCurrentModels;
    }

    public EpoxyModel<?> getModelById(long id) {
        for (EpoxyModel<?> model : this.currentModels) {
            if (model.mo11715id() == id) {
                return model;
            }
        }
        return null;
    }

    public int getModelPosition(EpoxyModel<?> targetModel) {
        int size = this.currentModels.size();
        for (int i = 0; i < size; i++) {
            if (((EpoxyModel) this.currentModels.get(i)).mo11715id() == targetModel.mo11715id()) {
                return i;
            }
        }
        return -1;
    }

    public BoundViewHolders getBoundViewHolders() {
        return super.getBoundViewHolders();
    }
}
