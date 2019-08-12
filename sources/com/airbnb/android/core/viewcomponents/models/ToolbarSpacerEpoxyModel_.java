package com.airbnb.android.core.viewcomponents.models;

import android.view.View;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ToolbarSpacerEpoxyModel_ extends ToolbarSpacerEpoxyModel implements GeneratedModel<View> {
    private OnModelBoundListener<ToolbarSpacerEpoxyModel_, View> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ToolbarSpacerEpoxyModel_, View> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, View object, int position) {
    }

    public void handlePostBind(View object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ToolbarSpacerEpoxyModel_ onBind(OnModelBoundListener<ToolbarSpacerEpoxyModel_, View> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(View object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ToolbarSpacerEpoxyModel_ onUnbind(OnModelUnboundListener<ToolbarSpacerEpoxyModel_, View> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ToolbarSpacerEpoxyModel_ backgroundRes(int backgroundRes) {
        onMutation();
        this.backgroundRes = backgroundRes;
        return this;
    }

    public int backgroundRes() {
        return this.backgroundRes;
    }

    public ToolbarSpacerEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ToolbarSpacerEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ToolbarSpacerEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ToolbarSpacerEpoxyModel_ m5710id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ToolbarSpacerEpoxyModel_ m5715id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ToolbarSpacerEpoxyModel_ m5711id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ToolbarSpacerEpoxyModel_ m5712id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ToolbarSpacerEpoxyModel_ m5714id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ToolbarSpacerEpoxyModel_ m5713id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ToolbarSpacerEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ToolbarSpacerEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ToolbarSpacerEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ToolbarSpacerEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ToolbarSpacerEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_model_toolbar_spacer;
    }

    public ToolbarSpacerEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.backgroundRes = 0;
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
        if (!(o instanceof ToolbarSpacerEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ToolbarSpacerEpoxyModel_ that = (ToolbarSpacerEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.backgroundRes != that.backgroundRes) {
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
        int i4 = (((hashCode + i2) * 31) + this.backgroundRes) * 31;
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
        return "ToolbarSpacerEpoxyModel_{backgroundRes=" + this.backgroundRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
