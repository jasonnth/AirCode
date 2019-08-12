package com.airbnb.android.places.viewmodels;

import com.airbnb.android.core.models.PlaceActivityAttribute;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.views.PlaceActivityDetailsView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class PlaceActivityDetailsEpoxyModel_ extends PlaceActivityDetailsEpoxyModel implements GeneratedModel<PlaceActivityDetailsView> {
    private OnModelBoundListener<PlaceActivityDetailsEpoxyModel_, PlaceActivityDetailsView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PlaceActivityDetailsEpoxyModel_, PlaceActivityDetailsView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, PlaceActivityDetailsView object, int position) {
    }

    public void handlePostBind(PlaceActivityDetailsView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PlaceActivityDetailsEpoxyModel_ onBind(OnModelBoundListener<PlaceActivityDetailsEpoxyModel_, PlaceActivityDetailsView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(PlaceActivityDetailsView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PlaceActivityDetailsEpoxyModel_ onUnbind(OnModelUnboundListener<PlaceActivityDetailsEpoxyModel_, PlaceActivityDetailsView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PlaceActivityDetailsEpoxyModel_ items(List<PlaceActivityAttribute> items) {
        onMutation();
        this.items = items;
        return this;
    }

    public List<PlaceActivityAttribute> items() {
        return this.items;
    }

    public PlaceActivityDetailsEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public PlaceActivityDetailsEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public PlaceActivityDetailsEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityDetailsEpoxyModel_ m6415id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityDetailsEpoxyModel_ m6420id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityDetailsEpoxyModel_ m6416id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityDetailsEpoxyModel_ m6417id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityDetailsEpoxyModel_ m6419id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityDetailsEpoxyModel_ m6418id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PlaceActivityDetailsEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PlaceActivityDetailsEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PlaceActivityDetailsEpoxyModel_ show() {
        super.show();
        return this;
    }

    public PlaceActivityDetailsEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PlaceActivityDetailsEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7627R.layout.view_holder_place_activity_details;
    }

    public PlaceActivityDetailsEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.items = null;
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
        if (!(o instanceof PlaceActivityDetailsEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        PlaceActivityDetailsEpoxyModel_ that = (PlaceActivityDetailsEpoxyModel_) o;
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
        if (this.items != null) {
            if (!this.items.equals(that.items)) {
                return false;
            }
        } else if (that.items != null) {
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
        if (this.items != null) {
            i = this.items.hashCode();
        } else {
            i = 0;
        }
        int i6 = (i5 + i) * 31;
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
        return "PlaceActivityDetailsEpoxyModel_{items=" + this.items + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
