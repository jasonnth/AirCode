package com.airbnb.android.core.viewcomponents.models;

import android.support.p002v7.widget.RecyclerView.LayoutManager;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class DeprecatedCarouselEpoxyModel_<A extends EpoxyAdapter> extends DeprecatedCarouselEpoxyModel<A> implements GeneratedModel<Carousel> {
    private OnModelBoundListener<DeprecatedCarouselEpoxyModel_<A>, Carousel> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<DeprecatedCarouselEpoxyModel_<A>, Carousel> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, Carousel object, int position) {
    }

    public void handlePostBind(Carousel object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public DeprecatedCarouselEpoxyModel_<A> onBind(OnModelBoundListener<DeprecatedCarouselEpoxyModel_<A>, Carousel> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(Carousel object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public DeprecatedCarouselEpoxyModel_<A> onUnbind(OnModelUnboundListener<DeprecatedCarouselEpoxyModel_<A>, Carousel> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public DeprecatedCarouselEpoxyModel_<A> models(List<? extends EpoxyModel<?>> models) {
        onMutation();
        this.models = models;
        return this;
    }

    public List<? extends EpoxyModel<?>> models() {
        return this.models;
    }

    public DeprecatedCarouselEpoxyModel_<A> hasFixedSize(boolean hasFixedSize) {
        onMutation();
        this.hasFixedSize = hasFixedSize;
        return this;
    }

    public boolean hasFixedSize() {
        return this.hasFixedSize;
    }

    public DeprecatedCarouselEpoxyModel_<A> startingPosition(int startingPosition) {
        onMutation();
        this.startingPosition = startingPosition;
        super.startingPosition(startingPosition);
        return this;
    }

    public int startingPosition() {
        return this.startingPosition;
    }

    public DeprecatedCarouselEpoxyModel_<A> adapter(A adapter) {
        onMutation();
        this.adapter = adapter;
        super.adapter(adapter);
        return this;
    }

    public A adapter() {
        return this.adapter;
    }

    public DeprecatedCarouselEpoxyModel_<A> viewPool(RecycledViewPool viewPool) {
        onMutation();
        this.viewPool = viewPool;
        super.viewPool(viewPool);
        return this;
    }

    public RecycledViewPool viewPool() {
        return this.viewPool;
    }

    public DeprecatedCarouselEpoxyModel_<A> layoutManager(LayoutManager layoutManager) {
        onMutation();
        this.layoutManager = layoutManager;
        super.layoutManager(layoutManager);
        return this;
    }

    public LayoutManager layoutManager() {
        return this.layoutManager;
    }

    public DeprecatedCarouselEpoxyModel_<A> onScrollListener(OnScrollListener onScrollListener) {
        onMutation();
        this.onScrollListener = onScrollListener;
        super.onScrollListener(onScrollListener);
        return this;
    }

    public OnScrollListener onScrollListener() {
        return this.onScrollListener;
    }

    public DeprecatedCarouselEpoxyModel_<A> numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    /* renamed from: id */
    public DeprecatedCarouselEpoxyModel_<A> m4504id(long id) {
        super.m4502id(id);
        return this;
    }

    public DeprecatedCarouselEpoxyModel_<A> forMicroCards(boolean forMicroCards) {
        super.forMicroCards(forMicroCards);
        return this;
    }

    public DeprecatedCarouselEpoxyModel_<A> noBottomPadding(boolean noBottomPadding) {
        super.noBottomPadding(noBottomPadding);
        return this;
    }

    public DeprecatedCarouselEpoxyModel_<A> forCheckInCards(boolean forCheckInCards) {
        super.forCheckInCards(forCheckInCards);
        return this;
    }

    public DeprecatedCarouselEpoxyModel_<A> hide() {
        super.hide();
        return this;
    }

    public DeprecatedCarouselEpoxyModel_<A> showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public DeprecatedCarouselEpoxyModel_<A> numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public DeprecatedCarouselEpoxyModel_<A> m4515id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public DeprecatedCarouselEpoxyModel_<A> m4511id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public DeprecatedCarouselEpoxyModel_<A> m4512id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public DeprecatedCarouselEpoxyModel_<A> m4514id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public DeprecatedCarouselEpoxyModel_<A> m4513id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public DeprecatedCarouselEpoxyModel_<A> layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public DeprecatedCarouselEpoxyModel_<A> spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public DeprecatedCarouselEpoxyModel_<A> show() {
        super.show();
        return this;
    }

    public DeprecatedCarouselEpoxyModel_<A> show(boolean show) {
        super.show(show);
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_carousel;
    }

    public DeprecatedCarouselEpoxyModel_<A> reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.models = null;
        this.hasFixedSize = false;
        this.startingPosition = 0;
        this.adapter = null;
        this.viewPool = null;
        this.layoutManager = null;
        this.onScrollListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (o == this) {
            return true;
        }
        if (!(o instanceof DeprecatedCarouselEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        DeprecatedCarouselEpoxyModel_ that = (DeprecatedCarouselEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.models != null) {
            if (!this.models.equals(that.models)) {
                return false;
            }
        } else if (that.models != null) {
            return false;
        }
        if (this.hasFixedSize != that.hasFixedSize || this.startingPosition != that.startingPosition) {
            return false;
        }
        if (this.adapter == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.adapter == null)) {
            return false;
        }
        if (this.viewPool == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.viewPool == null)) {
            return false;
        }
        if (this.layoutManager == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.layoutManager == null)) {
            return false;
        }
        if (this.onScrollListener == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5 != (that.onScrollListener == null)) {
            return false;
        }
        if (this.showDivider != null) {
            if (!this.showDivider.equals(that.showDivider)) {
                return false;
            }
        } else if (that.showDivider != null) {
            return false;
        }
        if (this.numCarouselItemsShown != null) {
            if (!this.numCarouselItemsShown.equals(that.numCarouselItemsShown)) {
                return false;
            }
        } else if (that.numCarouselItemsShown != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = 1;
        int i9 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i10 = (hashCode + i) * 31;
        if (this.models != null) {
            i2 = this.models.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (i10 + i2) * 31;
        if (this.hasFixedSize) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i12 = (((i11 + i3) * 31) + this.startingPosition) * 31;
        if (this.adapter != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.viewPool != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
        if (this.layoutManager != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.onScrollListener == null) {
            i8 = 0;
        }
        int i16 = (i15 + i8) * 31;
        if (this.showDivider != null) {
            i7 = this.showDivider.hashCode();
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.numCarouselItemsShown != null) {
            i9 = this.numCarouselItemsShown.hashCode();
        }
        return i17 + i9;
    }

    public String toString() {
        return "DeprecatedCarouselEpoxyModel_{models=" + this.models + ", hasFixedSize=" + this.hasFixedSize + ", startingPosition=" + this.startingPosition + ", adapter=" + this.adapter + ", viewPool=" + this.viewPool + ", layoutManager=" + this.layoutManager + ", onScrollListener=" + this.onScrollListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}