package com.airbnb.epoxy;

import android.os.Bundle;
import android.os.Handler;
import android.support.p002v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.p002v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public abstract class EpoxyController {
    private static final Timer NO_OP_TIMER = new NoOpTimer();
    private final EpoxyControllerAdapter adapter = new EpoxyControllerAdapter(this);
    private final Runnable buildModelsRunnable = new Runnable() {
        public void run() {
            EpoxyController.this.dispatchModelBuild();
        }
    };
    private EpoxyDiffLogger debugObserver;
    private boolean filterDuplicates;
    private final Handler handler = new Handler();
    private boolean hasBuiltModelsEver;
    private final ControllerHelper helper = ControllerHelperLookup.getHelperForController(this);
    private final List<Interceptor> interceptors = new ArrayList();
    private List<ModelInterceptorCallback> modelInterceptorCallbacks;
    private ControllerModelList modelsBeingBuilt;
    private int recyclerViewAttachCount = 0;
    private EpoxyModel<?> stagedModel;
    private Timer timer = NO_OP_TIMER;

    public interface Interceptor {
        void intercept(List<EpoxyModel<?>> list);
    }

    interface ModelInterceptorCallback {
        void onInterceptorsFinished(EpoxyController epoxyController);

        void onInterceptorsStarted(EpoxyController epoxyController);
    }

    /* access modifiers changed from: protected */
    public abstract void buildModels();

    public void requestModelBuild() {
        if (isBuildingModels()) {
            throw new IllegalEpoxyUsage("Cannot call `requestModelBuild` from inside `buildModels`");
        } else if (this.hasBuiltModelsEver) {
            requestDelayedModelBuild(0);
        } else {
            cancelPendingModelBuild();
            dispatchModelBuild();
        }
    }

    public void requestDelayedModelBuild(int delayMs) {
        if (isBuildingModels()) {
            throw new IllegalEpoxyUsage("Cannot call `requestDelayedModelBuild` from inside `buildModels`");
        }
        cancelPendingModelBuild();
        this.handler.postDelayed(this.buildModelsRunnable, (long) delayMs);
    }

    public void cancelPendingModelBuild() {
        this.handler.removeCallbacks(this.buildModelsRunnable);
    }

    /* access modifiers changed from: private */
    public void dispatchModelBuild() {
        this.helper.resetAutoModels();
        this.modelsBeingBuilt = new ControllerModelList(getExpectedModelCount());
        this.timer.start();
        buildModels();
        addCurrentlyStagedModelIfExists();
        this.timer.stop("Models built");
        runInterceptors();
        filterDuplicatesIfNeeded(this.modelsBeingBuilt);
        this.modelsBeingBuilt.freeze();
        this.timer.start();
        this.adapter.setModels(this.modelsBeingBuilt);
        this.timer.stop("Models diffed");
        this.modelsBeingBuilt = null;
        this.hasBuiltModelsEver = true;
    }

    private int getExpectedModelCount() {
        int currentModelCount = this.adapter.getItemCount();
        if (currentModelCount != 0) {
            return currentModelCount;
        }
        return 25;
    }

    /* access modifiers changed from: 0000 */
    public int getFirstIndexOfModelInBuildingList(EpoxyModel<?> model) {
        int size = this.modelsBeingBuilt.size();
        for (int i = 0; i < size; i++) {
            if (this.modelsBeingBuilt.get(i) == model) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public boolean isModelAddedMultipleTimes(EpoxyModel<?> model) {
        int modelCount = 0;
        int size = this.modelsBeingBuilt.size();
        for (int i = 0; i < size; i++) {
            if (this.modelsBeingBuilt.get(i) == model) {
                modelCount++;
            }
        }
        if (modelCount > 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void addAfterInterceptorCallback(ModelInterceptorCallback callback) {
        if (!isBuildingModels()) {
            throw new IllegalEpoxyUsage("Can only call when building models");
        }
        if (this.modelInterceptorCallbacks == null) {
            this.modelInterceptorCallbacks = new ArrayList();
        }
        this.modelInterceptorCallbacks.add(callback);
    }

    private void runInterceptors() {
        if (!this.interceptors.isEmpty()) {
            if (this.modelInterceptorCallbacks != null) {
                for (ModelInterceptorCallback callback : this.modelInterceptorCallbacks) {
                    callback.onInterceptorsStarted(this);
                }
            }
            this.timer.start();
            for (Interceptor interceptor : this.interceptors) {
                interceptor.intercept(this.modelsBeingBuilt);
            }
            this.timer.stop("Interceptors executed");
            if (this.modelInterceptorCallbacks != null) {
                for (ModelInterceptorCallback callback2 : this.modelInterceptorCallbacks) {
                    callback2.onInterceptorsFinished(this);
                }
                this.modelInterceptorCallbacks = null;
            }
        }
    }

    public void addInterceptor(Interceptor interceptor) {
        this.interceptors.add(interceptor);
    }

    public void removeInterceptor(Interceptor interceptor) {
        this.interceptors.remove(interceptor);
    }

    /* access modifiers changed from: protected */
    public int getModelCountBuiltSoFar() {
        if (isBuildingModels()) {
            return this.modelsBeingBuilt.size();
        }
        throw new IllegalEpoxyUsage("Can only all this when inside the `buildModels` method");
    }

    /* access modifiers changed from: protected */
    public void add(EpoxyModel<?> model) {
        model.addTo(this);
    }

    /* access modifiers changed from: protected */
    public void add(EpoxyModel<?>... modelsToAdd) {
        this.modelsBeingBuilt.ensureCapacity(this.modelsBeingBuilt.size() + modelsToAdd.length);
        for (EpoxyModel<?> model : modelsToAdd) {
            model.addTo(this);
        }
    }

    /* access modifiers changed from: protected */
    public void add(List<? extends EpoxyModel<?>> modelsToAdd) {
        this.modelsBeingBuilt.ensureCapacity(this.modelsBeingBuilt.size() + modelsToAdd.size());
        for (EpoxyModel<?> model : modelsToAdd) {
            model.addTo(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public void addInternal(EpoxyModel<?> modelToAdd) {
        if (!isBuildingModels()) {
            throw new IllegalEpoxyUsage("You can only add models inside the `buildModels` methods, and you cannot call `buildModels` directly. Call `requestModelBuild` instead");
        } else if (modelToAdd.hasDefaultId()) {
            throw new IllegalEpoxyUsage("You must set an id on a model before adding it. Use the @AutoModel annotation if you want an id to be automatically generated for you.");
        } else if (!modelToAdd.isShown()) {
            throw new IllegalEpoxyUsage("You cannot hide a model in an EpoxyController. Use `addIf` to conditionally add a model instead.");
        } else {
            clearModelFromStaging(modelToAdd);
            modelToAdd.controllerToStageTo = null;
            this.modelsBeingBuilt.add(modelToAdd);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setStagedModel(EpoxyModel<?> model) {
        if (model != this.stagedModel) {
            addCurrentlyStagedModelIfExists();
        }
        this.stagedModel = model;
    }

    /* access modifiers changed from: 0000 */
    public void addCurrentlyStagedModelIfExists() {
        if (this.stagedModel != null) {
            this.stagedModel.addTo(this);
        }
        this.stagedModel = null;
    }

    /* access modifiers changed from: 0000 */
    public void clearModelFromStaging(EpoxyModel<?> model) {
        if (this.stagedModel != model) {
            addCurrentlyStagedModelIfExists();
        }
        this.stagedModel = null;
    }

    /* access modifiers changed from: 0000 */
    public boolean isBuildingModels() {
        return this.modelsBeingBuilt != null;
    }

    private void filterDuplicatesIfNeeded(List<EpoxyModel<?>> models) {
        if (this.filterDuplicates) {
            this.timer.start();
            Set<Long> modelIds = new HashSet<>(models.size());
            ListIterator<EpoxyModel<?>> modelIterator = models.listIterator();
            while (modelIterator.hasNext()) {
                EpoxyModel<?> model = (EpoxyModel) modelIterator.next();
                if (!modelIds.add(Long.valueOf(model.mo11715id()))) {
                    int indexOfDuplicate = modelIterator.previousIndex();
                    modelIterator.remove();
                    int indexOfOriginal = findPositionOfDuplicate(models, model);
                    EpoxyModel<?> originalModel = (EpoxyModel) models.get(indexOfOriginal);
                    if (indexOfDuplicate <= indexOfOriginal) {
                        indexOfOriginal++;
                    }
                    onExceptionSwallowed(new IllegalEpoxyUsage("Two models have the same ID. ID's must be unique!\nOriginal has position " + indexOfOriginal + ":\n" + originalModel + "\nDuplicate has position " + indexOfDuplicate + ":\n" + model));
                }
            }
            this.timer.stop("Duplicates filtered");
        }
    }

    private int findPositionOfDuplicate(List<EpoxyModel<?>> models, EpoxyModel<?> duplicateModel) {
        int size = models.size();
        for (int i = 0; i < size; i++) {
            if (((EpoxyModel) models.get(i)).mo11715id() == duplicateModel.mo11715id()) {
                return i;
            }
        }
        throw new IllegalArgumentException("No duplicates in list");
    }

    public void setFilterDuplicates(boolean filterDuplicates2) {
        this.filterDuplicates = filterDuplicates2;
    }

    public void setDebugLoggingEnabled(boolean enabled) {
        if (isBuildingModels()) {
            throw new IllegalEpoxyUsage("Debug logging should be enabled before models are built");
        } else if (enabled) {
            this.timer = new DebugTimer(getClass().getSimpleName());
            this.debugObserver = new EpoxyDiffLogger(getClass().getSimpleName());
            this.adapter.registerAdapterDataObserver(this.debugObserver);
        } else {
            this.timer = NO_OP_TIMER;
            if (this.debugObserver != null) {
                this.adapter.unregisterAdapterDataObserver(this.debugObserver);
            }
        }
    }

    public EpoxyControllerAdapter getAdapter() {
        return this.adapter;
    }

    public void onSaveInstanceState(Bundle outState) {
        this.adapter.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle inState) {
        this.adapter.onRestoreInstanceState(inState);
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.adapter.getSpanSizeLookup();
    }

    public void setSpanCount(int spanCount) {
        this.adapter.setSpanCount(spanCount);
    }

    public int getSpanCount() {
        return this.adapter.getSpanCount();
    }

    public boolean isMultiSpan() {
        return this.adapter.isMultiSpan();
    }

    /* access modifiers changed from: protected */
    public void onExceptionSwallowed(RuntimeException exception) {
    }

    /* access modifiers changed from: 0000 */
    public void onAttachedToRecyclerViewInternal(RecyclerView recyclerView) {
        this.recyclerViewAttachCount++;
        if (this.recyclerViewAttachCount > 1) {
            onExceptionSwallowed(new IllegalStateException("Epoxy does not support attaching an adapter to more than one RecyclerView because saved state will not work properly. If you did not intend to attach your adapter to multiple RecyclerViews you may be leaking a reference to a previous RecyclerView. Make sure to remove the adapter from any previous RecyclerViews (eg if the adapter is reused in a Fragment across multiple onCreateView/onDestroyView cycles). See https://github.com/airbnb/epoxy/wiki/Avoiding-Memory-Leaks for more information."));
        }
        onAttachedToRecyclerView(recyclerView);
    }

    /* access modifiers changed from: 0000 */
    public void onDetachedFromRecyclerViewInternal(RecyclerView recyclerView) {
        this.recyclerViewAttachCount--;
        onDetachedFromRecyclerView(recyclerView);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> epoxyModel, int position, EpoxyModel<?> epoxyModel2) {
    }

    /* access modifiers changed from: protected */
    public void onModelUnbound(EpoxyViewHolder holder, EpoxyModel<?> epoxyModel) {
    }

    /* access modifiers changed from: protected */
    public void onViewAttachedToWindow(EpoxyViewHolder holder, EpoxyModel<?> epoxyModel) {
    }

    /* access modifiers changed from: protected */
    public void onViewDetachedFromWindow(EpoxyViewHolder holder, EpoxyModel<?> epoxyModel) {
    }
}
