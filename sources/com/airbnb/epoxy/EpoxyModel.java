package com.airbnb.epoxy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public abstract class EpoxyModel<T> {
    private static long idCounter = -1;
    boolean addedToAdapter;
    EpoxyController controllerToStageTo;
    /* access modifiers changed from: private */
    public boolean currentlyInInterceptors;
    private EpoxyController firstControllerAddedTo;
    private boolean hasDefaultId;
    /* access modifiers changed from: private */
    public int hashCodeWhenAdded;

    /* renamed from: id */
    private long f1365id;
    private int layout;
    private boolean shown;
    private SpanSizeOverrideCallback spanSizeOverride;

    public interface AddPredicate {
        boolean addIf();
    }

    public interface SpanSizeOverrideCallback {
        int getSpanSize(int i, int i2, int i3);
    }

    /* access modifiers changed from: protected */
    public abstract int getDefaultLayout();

    protected EpoxyModel(long id) {
        this.shown = true;
        mo11716id(id);
    }

    public EpoxyModel() {
        long j = idCounter;
        idCounter = j - 1;
        this(j);
        this.hasDefaultId = true;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasDefaultId() {
        return this.hasDefaultId;
    }

    /* access modifiers changed from: 0000 */
    public int getViewType() {
        return getLayout();
    }

    /* access modifiers changed from: 0000 */
    public View buildView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
    }

    public void bind(T t) {
    }

    public void bind(T view, List<Object> list) {
        bind(view);
    }

    public void bind(T view, EpoxyModel<?> epoxyModel) {
        bind(view);
    }

    public void unbind(T t) {
    }

    /* renamed from: id */
    public long mo11715id() {
        return this.f1365id;
    }

    /* renamed from: id */
    public EpoxyModel<T> mo11716id(long id) {
        if ((this.addedToAdapter || this.firstControllerAddedTo != null) && id != this.f1365id) {
            throw new IllegalEpoxyUsage("Cannot change a model's id after it has been added to the adapter.");
        }
        this.hasDefaultId = false;
        this.f1365id = id;
        return this;
    }

    /* renamed from: id */
    public EpoxyModel<T> mo11721id(Number... ids) {
        long result = 0;
        for (Number id : ids) {
            result = (31 * result) + hashLong64Bit((long) id.hashCode());
        }
        return mo11716id(result);
    }

    /* renamed from: id */
    public EpoxyModel<T> mo11717id(long id1, long id2) {
        return mo11716id((31 * hashLong64Bit(id1)) + hashLong64Bit(id2));
    }

    /* renamed from: id */
    public EpoxyModel<T> mo11718id(CharSequence key) {
        mo11716id(hashString64Bit(key));
        return this;
    }

    /* renamed from: id */
    public EpoxyModel<T> mo11720id(CharSequence key, CharSequence... otherKeys) {
        long result = hashString64Bit(key);
        for (CharSequence otherKey : otherKeys) {
            result = (31 * result) + hashString64Bit(otherKey);
        }
        return mo11716id(result);
    }

    /* renamed from: id */
    public EpoxyModel<T> mo11719id(CharSequence key, long id) {
        mo11716id((31 * hashString64Bit(key)) + hashLong64Bit(id));
        return this;
    }

    private static long hashLong64Bit(long value) {
        long value2 = value ^ (value << 21);
        long value3 = value2 ^ (value2 >>> 35);
        return value3 ^ (value3 << 4);
    }

    private static long hashString64Bit(CharSequence str) {
        long result = -3750763034362895579L;
        for (int i = 0; i < str.length(); i++) {
            result = (result ^ ((long) str.charAt(i))) * 1099511628211L;
        }
        return result;
    }

    public EpoxyModel<T> layout(int layoutRes) {
        onMutation();
        this.layout = layoutRes;
        return this;
    }

    public final int getLayout() {
        if (this.layout == 0) {
            return getDefaultLayout();
        }
        return this.layout;
    }

    public EpoxyModel<T> reset() {
        onMutation();
        this.layout = 0;
        this.shown = true;
        return this;
    }

    public void addTo(EpoxyController controller) {
        controller.addInternal(this);
    }

    public void addIf(boolean condition, EpoxyController controller) {
        if (condition) {
            addTo(controller);
        } else if (this.controllerToStageTo != null) {
            this.controllerToStageTo.clearModelFromStaging(this);
            this.controllerToStageTo = null;
        }
    }

    public void addIf(AddPredicate predicate, EpoxyController controller) {
        addIf(predicate.addIf(), controller);
    }

    /* access modifiers changed from: protected */
    public final void addWithDebugValidation(EpoxyController controller) {
        if (controller == null) {
            throw new IllegalArgumentException("Controller cannot be null");
        } else if (controller.isModelAddedMultipleTimes(this)) {
            throw new IllegalEpoxyUsage("This model was already added to the controller at position " + controller.getFirstIndexOfModelInBuildingList(this));
        } else if (this.firstControllerAddedTo == null) {
            this.firstControllerAddedTo = controller;
            this.hashCodeWhenAdded = hashCode();
            controller.addAfterInterceptorCallback(new ModelInterceptorCallback() {
                public void onInterceptorsStarted(EpoxyController controller) {
                    EpoxyModel.this.currentlyInInterceptors = true;
                }

                public void onInterceptorsFinished(EpoxyController controller) {
                    EpoxyModel.this.hashCodeWhenAdded = EpoxyModel.this.hashCode();
                    EpoxyModel.this.currentlyInInterceptors = false;
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isDebugValidationEnabled() {
        return this.firstControllerAddedTo != null;
    }

    /* access modifiers changed from: protected */
    public final void onMutation() {
        if (isDebugValidationEnabled() && !this.currentlyInInterceptors) {
            throw new ImmutableModelException(this, getPosition(this.firstControllerAddedTo, this));
        } else if (this.controllerToStageTo != null) {
            this.controllerToStageTo.setStagedModel(this);
        }
    }

    private static int getPosition(EpoxyController controller, EpoxyModel<?> model) {
        if (controller.isBuildingModels()) {
            return controller.getFirstIndexOfModelInBuildingList(model);
        }
        return controller.getAdapter().getModelPosition(model);
    }

    /* access modifiers changed from: protected */
    public final void validateStateHasNotChangedSinceAdded(String descriptionOfChange, int modelPosition) {
        if (isDebugValidationEnabled() && !this.currentlyInInterceptors && this.hashCodeWhenAdded != hashCode()) {
            throw new ImmutableModelException(this, descriptionOfChange, modelPosition);
        }
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (!(o instanceof EpoxyModel)) {
            return false;
        }
        EpoxyModel<?> that = (EpoxyModel) o;
        if (this.f1365id != that.f1365id || getViewType() != that.getViewType()) {
            return false;
        }
        if (this.shown != that.shown) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((((int) (this.f1365id ^ (this.f1365id >>> 32))) * 31) + getViewType()) * 31) + (this.shown ? 1 : 0);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return 1;
    }

    public EpoxyModel<T> spanSizeOverride(SpanSizeOverrideCallback spanSizeCallback) {
        this.spanSizeOverride = spanSizeCallback;
        return this;
    }

    /* access modifiers changed from: 0000 */
    public int getSpanSizeInternal(int totalSpanCount, int position, int itemCount) {
        if (this.spanSizeOverride != null) {
            return this.spanSizeOverride.getSpanSize(totalSpanCount, position, itemCount);
        }
        return getSpanSize(totalSpanCount, position, itemCount);
    }

    public EpoxyModel<T> show() {
        return show(true);
    }

    public EpoxyModel<T> show(boolean show) {
        onMutation();
        this.shown = show;
        return this;
    }

    public EpoxyModel<T> hide() {
        return show(false);
    }

    public boolean isShown() {
        return this.shown;
    }

    public boolean shouldSaveViewState() {
        return false;
    }

    public boolean onFailedToRecycleView(T t) {
        return false;
    }

    public void onViewAttachedToWindow(T t) {
    }

    public void onViewDetachedFromWindow(T t) {
    }

    public String toString() {
        return getClass().getSimpleName() + "{" + "id=" + this.f1365id + ", viewType=" + getViewType() + ", shown=" + this.shown + ", addedToAdapter=" + this.addedToAdapter + '}';
    }
}
