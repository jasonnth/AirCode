package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.ListSpacer;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ListSpacerEpoxyModel_ extends ListSpacerEpoxyModel implements GeneratedModel<ListSpacer> {
    private OnModelBoundListener<ListSpacerEpoxyModel_, ListSpacer> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ListSpacerEpoxyModel_, ListSpacer> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ListSpacer object, int position) {
    }

    public void handlePostBind(ListSpacer object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ListSpacerEpoxyModel_ onBind(OnModelBoundListener<ListSpacerEpoxyModel_, ListSpacer> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ListSpacer object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ListSpacerEpoxyModel_ onUnbind(OnModelUnboundListener<ListSpacerEpoxyModel_, ListSpacer> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ListSpacerEpoxyModel_ spaceHeight(int spaceHeight) {
        onMutation();
        this.spaceHeight = spaceHeight;
        return this;
    }

    public int spaceHeight() {
        return this.spaceHeight;
    }

    public ListSpacerEpoxyModel_ spaceHeightRes(int spaceHeightRes) {
        onMutation();
        this.spaceHeightRes = spaceHeightRes;
        return this;
    }

    public int spaceHeightRes() {
        return this.spaceHeightRes;
    }

    public ListSpacerEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ListSpacerEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ListSpacerEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ListSpacerEpoxyModel_ m5050id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ListSpacerEpoxyModel_ m5055id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ListSpacerEpoxyModel_ m5051id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ListSpacerEpoxyModel_ m5052id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ListSpacerEpoxyModel_ m5054id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ListSpacerEpoxyModel_ m5053id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ListSpacerEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ListSpacerEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ListSpacerEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ListSpacerEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ListSpacerEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_list_spacer;
    }

    public ListSpacerEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.spaceHeight = 0;
        this.spaceHeightRes = 0;
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
        if (!(o instanceof ListSpacerEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ListSpacerEpoxyModel_ that = (ListSpacerEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.spaceHeight != that.spaceHeight || this.spaceHeightRes != that.spaceHeightRes) {
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
        int i4 = (((((hashCode + i2) * 31) + this.spaceHeight) * 31) + this.spaceHeightRes) * 31;
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
        return "ListSpacerEpoxyModel_{spaceHeight=" + this.spaceHeight + ", spaceHeightRes=" + this.spaceHeightRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
