package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.ExpandableCollectionRow;
import com.airbnb.p027n2.components.ExpandableCollectionRow.RowItem;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class ExpandableCollectionRowEpoxyModel_ extends ExpandableCollectionRowEpoxyModel implements GeneratedModel<ExpandableCollectionRow> {
    private OnModelBoundListener<ExpandableCollectionRowEpoxyModel_, ExpandableCollectionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ExpandableCollectionRowEpoxyModel_, ExpandableCollectionRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ExpandableCollectionRow object, int position) {
    }

    public void handlePostBind(ExpandableCollectionRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ExpandableCollectionRowEpoxyModel_ onBind(OnModelBoundListener<ExpandableCollectionRowEpoxyModel_, ExpandableCollectionRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ExpandableCollectionRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ExpandableCollectionRowEpoxyModel_ onUnbind(OnModelUnboundListener<ExpandableCollectionRowEpoxyModel_, ExpandableCollectionRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ExpandableCollectionRowEpoxyModel_ rowItems(List<RowItem> rowItems) {
        onMutation();
        this.rowItems = rowItems;
        return this;
    }

    public List<RowItem> rowItems() {
        return this.rowItems;
    }

    public ExpandableCollectionRowEpoxyModel_ expandTextRes(int expandTextRes) {
        onMutation();
        this.expandTextRes = expandTextRes;
        return this;
    }

    public int expandTextRes() {
        return this.expandTextRes;
    }

    public ExpandableCollectionRowEpoxyModel_ bottomTextRes(int bottomTextRes) {
        onMutation();
        this.bottomTextRes = bottomTextRes;
        return this;
    }

    public int bottomTextRes() {
        return this.bottomTextRes;
    }

    public ExpandableCollectionRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ExpandableCollectionRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ExpandableCollectionRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ExpandableCollectionRowEpoxyModel_ m6107id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ExpandableCollectionRowEpoxyModel_ m6112id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ExpandableCollectionRowEpoxyModel_ m6108id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ExpandableCollectionRowEpoxyModel_ m6109id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ExpandableCollectionRowEpoxyModel_ m6111id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ExpandableCollectionRowEpoxyModel_ m6110id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ExpandableCollectionRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ExpandableCollectionRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ExpandableCollectionRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ExpandableCollectionRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ExpandableCollectionRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.n2_view_holder_expandable_collection_row;
    }

    public ExpandableCollectionRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.rowItems = null;
        this.expandTextRes = 0;
        this.bottomTextRes = 0;
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
        if (!(o instanceof ExpandableCollectionRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ExpandableCollectionRowEpoxyModel_ that = (ExpandableCollectionRowEpoxyModel_) o;
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
        if (this.rowItems != null) {
            if (!this.rowItems.equals(that.rowItems)) {
                return false;
            }
        } else if (that.rowItems != null) {
            return false;
        }
        if (this.expandTextRes != that.expandTextRes || this.bottomTextRes != that.bottomTextRes) {
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
        int i3 = 1;
        int i4 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i3 = 0;
        }
        int i5 = (hashCode + i3) * 31;
        if (this.rowItems != null) {
            i = this.rowItems.hashCode();
        } else {
            i = 0;
        }
        int i6 = (((((i5 + i) * 31) + this.expandTextRes) * 31) + this.bottomTextRes) * 31;
        if (this.showDivider != null) {
            i2 = this.showDivider.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.numCarouselItemsShown != null) {
            i4 = this.numCarouselItemsShown.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "ExpandableCollectionRowEpoxyModel_{rowItems=" + this.rowItems + ", expandTextRes=" + this.expandTextRes + ", bottomTextRes=" + this.bottomTextRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
