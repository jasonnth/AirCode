package com.airbnb.epoxy;

import android.os.Bundle;
import android.support.p002v7.widget.GridLayoutManager.SpanSizeLookup;
import android.view.ViewGroup;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class EpoxyAdapter extends BaseEpoxyAdapter {
    private DiffHelper diffHelper;
    private final HiddenEpoxyModel hiddenModel = new HiddenEpoxyModel();
    /* access modifiers changed from: protected */
    public final List<EpoxyModel<?>> models = new ModelList();

    public /* bridge */ /* synthetic */ int getItemCount() {
        return super.getItemCount();
    }

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

    public /* bridge */ /* synthetic */ void onViewAttachedToWindow(EpoxyViewHolder epoxyViewHolder) {
        super.onViewAttachedToWindow(epoxyViewHolder);
    }

    public /* bridge */ /* synthetic */ void onViewDetachedFromWindow(EpoxyViewHolder epoxyViewHolder) {
        super.onViewDetachedFromWindow(epoxyViewHolder);
    }

    public /* bridge */ /* synthetic */ void onViewRecycled(EpoxyViewHolder epoxyViewHolder) {
        super.onViewRecycled(epoxyViewHolder);
    }

    public /* bridge */ /* synthetic */ void setSpanCount(int i) {
        super.setSpanCount(i);
    }

    /* access modifiers changed from: 0000 */
    public List<EpoxyModel<?>> getCurrentModels() {
        return this.models;
    }

    /* access modifiers changed from: protected */
    public void enableDiffing() {
        if (this.diffHelper != null) {
            throw new IllegalStateException("Diffing was already enabled");
        } else if (!this.models.isEmpty()) {
            throw new IllegalStateException("You must enable diffing before modifying models");
        } else if (!hasStableIds()) {
            throw new IllegalStateException("You must have stable ids to use diffing");
        } else {
            this.diffHelper = new DiffHelper(this, false);
        }
    }

    /* access modifiers changed from: 0000 */
    public EpoxyModel<?> getModelForPosition(int position) {
        EpoxyModel<?> model = (EpoxyModel) this.models.get(position);
        return model.isShown() ? model : this.hiddenModel;
    }

    /* access modifiers changed from: protected */
    public void notifyModelsChanged() {
        if (this.diffHelper == null) {
            throw new IllegalStateException("You must enable diffing before notifying models changed");
        }
        this.diffHelper.notifyModelChanges();
    }

    /* access modifiers changed from: protected */
    public void notifyModelChanged(EpoxyModel<?> model) {
        notifyModelChanged(model, null);
    }

    /* access modifiers changed from: protected */
    public void notifyModelChanged(EpoxyModel<?> model, Object payload) {
        int index = getModelPosition(model);
        if (index != -1) {
            notifyItemChanged(index, payload);
        }
    }

    /* access modifiers changed from: protected */
    public void addModel(EpoxyModel<?> modelToAdd) {
        int initialSize = this.models.size();
        pauseModelListNotifications();
        this.models.add(modelToAdd);
        resumeModelListNotifications();
        notifyItemRangeInserted(initialSize, 1);
    }

    /* access modifiers changed from: protected */
    public void addModels(EpoxyModel<?>... modelsToAdd) {
        int initialSize = this.models.size();
        int numModelsToAdd = modelsToAdd.length;
        ((ModelList) this.models).ensureCapacity(initialSize + numModelsToAdd);
        pauseModelListNotifications();
        Collections.addAll(this.models, modelsToAdd);
        resumeModelListNotifications();
        notifyItemRangeInserted(initialSize, numModelsToAdd);
    }

    /* access modifiers changed from: protected */
    public void addModels(Collection<? extends EpoxyModel<?>> modelsToAdd) {
        int initialSize = this.models.size();
        pauseModelListNotifications();
        this.models.addAll(modelsToAdd);
        resumeModelListNotifications();
        notifyItemRangeInserted(initialSize, modelsToAdd.size());
    }

    /* access modifiers changed from: protected */
    public void insertModelBefore(EpoxyModel<?> modelToInsert, EpoxyModel<?> modelToInsertBefore) {
        int targetIndex = getModelPosition(modelToInsertBefore);
        if (targetIndex == -1) {
            throw new IllegalStateException("Model is not added: " + modelToInsertBefore);
        }
        pauseModelListNotifications();
        this.models.add(targetIndex, modelToInsert);
        resumeModelListNotifications();
        notifyItemInserted(targetIndex);
    }

    /* access modifiers changed from: protected */
    public void insertModelAfter(EpoxyModel<?> modelToInsert, EpoxyModel<?> modelToInsertAfter) {
        int modelIndex = getModelPosition(modelToInsertAfter);
        if (modelIndex == -1) {
            throw new IllegalStateException("Model is not added: " + modelToInsertAfter);
        }
        int targetIndex = modelIndex + 1;
        pauseModelListNotifications();
        this.models.add(targetIndex, modelToInsert);
        resumeModelListNotifications();
        notifyItemInserted(targetIndex);
    }

    /* access modifiers changed from: protected */
    public void removeModel(EpoxyModel<?> model) {
        int index = getModelPosition(model);
        if (index != -1) {
            pauseModelListNotifications();
            this.models.remove(index);
            resumeModelListNotifications();
            notifyItemRemoved(index);
        }
    }

    /* access modifiers changed from: protected */
    public void removeAllModels() {
        int numModelsRemoved = this.models.size();
        pauseModelListNotifications();
        this.models.clear();
        resumeModelListNotifications();
        notifyItemRangeRemoved(0, numModelsRemoved);
    }

    /* access modifiers changed from: protected */
    public void removeAllAfterModel(EpoxyModel<?> model) {
        List<EpoxyModel<?>> modelsToRemove = getAllModelsAfter(model);
        int numModelsRemoved = modelsToRemove.size();
        int initialModelCount = this.models.size();
        pauseModelListNotifications();
        modelsToRemove.clear();
        resumeModelListNotifications();
        notifyItemRangeRemoved(initialModelCount - numModelsRemoved, numModelsRemoved);
    }

    /* access modifiers changed from: protected */
    public void showModel(EpoxyModel<?> model, boolean show) {
        if (model.isShown() != show) {
            model.show(show);
            notifyModelChanged(model);
        }
    }

    /* access modifiers changed from: protected */
    public void showModel(EpoxyModel<?> model) {
        showModel(model, true);
    }

    /* access modifiers changed from: protected */
    public void showModels(EpoxyModel<?>... models2) {
        showModels((Iterable<EpoxyModel<?>>) Arrays.asList(models2));
    }

    /* access modifiers changed from: protected */
    public void showModels(boolean show, EpoxyModel<?>... models2) {
        showModels((Iterable<EpoxyModel<?>>) Arrays.asList(models2), show);
    }

    /* access modifiers changed from: protected */
    public void showModels(Iterable<EpoxyModel<?>> models2) {
        showModels(models2, true);
    }

    /* access modifiers changed from: protected */
    public void showModels(Iterable<EpoxyModel<?>> models2, boolean show) {
        for (EpoxyModel<?> model : models2) {
            showModel(model, show);
        }
    }

    /* access modifiers changed from: protected */
    public void hideModel(EpoxyModel<?> model) {
        showModel(model, false);
    }

    /* access modifiers changed from: protected */
    public void hideModels(Iterable<EpoxyModel<?>> models2) {
        showModels(models2, false);
    }

    /* access modifiers changed from: protected */
    public void hideModels(EpoxyModel<?>... models2) {
        hideModels((Iterable<EpoxyModel<?>>) Arrays.asList(models2));
    }

    /* access modifiers changed from: protected */
    public void hideAllAfterModel(EpoxyModel<?> model) {
        hideModels((Iterable<EpoxyModel<?>>) getAllModelsAfter(model));
    }

    /* access modifiers changed from: protected */
    public List<EpoxyModel<?>> getAllModelsAfter(EpoxyModel<?> model) {
        int index = getModelPosition(model);
        if (index != -1) {
            return this.models.subList(index + 1, this.models.size());
        }
        throw new IllegalStateException("Model is not added: " + model);
    }

    private void pauseModelListNotifications() {
        ((ModelList) this.models).pauseNotifications();
    }

    private void resumeModelListNotifications() {
        ((ModelList) this.models).resumeNotifications();
    }
}
