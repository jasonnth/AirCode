package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class EpoxyControllerLoadingModel_ extends EpoxyControllerLoadingModel implements GeneratedModel<RefreshLoader> {
    private OnModelBoundListener<EpoxyControllerLoadingModel_, RefreshLoader> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<EpoxyControllerLoadingModel_, RefreshLoader> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, RefreshLoader object, int position) {
    }

    public void handlePostBind(RefreshLoader object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public EpoxyControllerLoadingModel_ onBind(OnModelBoundListener<EpoxyControllerLoadingModel_, RefreshLoader> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(RefreshLoader object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public EpoxyControllerLoadingModel_ onUnbind(OnModelUnboundListener<EpoxyControllerLoadingModel_, RefreshLoader> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public int seed() {
        return this.seed;
    }

    public EpoxyControllerLoadingModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public EpoxyControllerLoadingModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public EpoxyControllerLoadingModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public EpoxyControllerLoadingModel_ m4582id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public EpoxyControllerLoadingModel_ m4587id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public EpoxyControllerLoadingModel_ m4583id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public EpoxyControllerLoadingModel_ m4584id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public EpoxyControllerLoadingModel_ m4586id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public EpoxyControllerLoadingModel_ m4585id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public EpoxyControllerLoadingModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public EpoxyControllerLoadingModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public EpoxyControllerLoadingModel_ show() {
        super.show();
        return this;
    }

    public EpoxyControllerLoadingModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public EpoxyControllerLoadingModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_refresh_loader;
    }

    public EpoxyControllerLoadingModel_ withCarouselLayout() {
        layout(C0716R.layout.n2_view_holder_refresh_loader_carousel);
        return this;
    }

    public EpoxyControllerLoadingModel_ withInverseLayout() {
        layout(C0716R.layout.n2_view_holder_refresh_loader_inverse);
        return this;
    }

    public EpoxyControllerLoadingModel_ withRegularLayout() {
        layout(C0716R.layout.n2_view_holder_refresh_loader_regular);
        return this;
    }

    public EpoxyControllerLoadingModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof EpoxyControllerLoadingModel_) || !super.equals(o)) {
            return false;
        }
        EpoxyControllerLoadingModel_ that = (EpoxyControllerLoadingModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.seed != that.seed) {
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
        int i2 = 1;
        int i3 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i2 = 0;
        }
        int i4 = (((hashCode + i2) * 31) + this.seed) * 31;
        if (this.showDivider != null) {
            i = this.showDivider.hashCode();
        } else {
            i = 0;
        }
        int i5 = (i4 + i) * 31;
        if (this.numCarouselItemsShown != null) {
            i3 = this.numCarouselItemsShown.hashCode();
        }
        return i5 + i3;
    }

    public String toString() {
        return "EpoxyControllerLoadingModel_{seed=" + this.seed + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
