package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.BedDetailsCard;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class BedDetailsCardEpoxyModel_ extends BedDetailsCardEpoxyModel implements GeneratedModel<BedDetailsCard> {
    private OnModelBoundListener<BedDetailsCardEpoxyModel_, BedDetailsCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<BedDetailsCardEpoxyModel_, BedDetailsCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, BedDetailsCard object, int position) {
    }

    public void handlePostBind(BedDetailsCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public BedDetailsCardEpoxyModel_ onBind(OnModelBoundListener<BedDetailsCardEpoxyModel_, BedDetailsCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(BedDetailsCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public BedDetailsCardEpoxyModel_ onUnbind(OnModelUnboundListener<BedDetailsCardEpoxyModel_, BedDetailsCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public BedDetailsCardEpoxyModel_ room(ListingRoom room) {
        onMutation();
        this.room = room;
        return this;
    }

    public ListingRoom room() {
        return this.room;
    }

    public BedDetailsCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public BedDetailsCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public BedDetailsCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public BedDetailsCardEpoxyModel_ m4363id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public BedDetailsCardEpoxyModel_ m4368id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public BedDetailsCardEpoxyModel_ m4364id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public BedDetailsCardEpoxyModel_ m4365id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public BedDetailsCardEpoxyModel_ m4367id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public BedDetailsCardEpoxyModel_ m4366id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public BedDetailsCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public BedDetailsCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public BedDetailsCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public BedDetailsCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public BedDetailsCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_bed_details_card;
    }

    public BedDetailsCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.room = null;
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
        if (!(o instanceof BedDetailsCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        BedDetailsCardEpoxyModel_ that = (BedDetailsCardEpoxyModel_) o;
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
        if (this.room != null) {
            if (!this.room.equals(that.room)) {
                return false;
            }
        } else if (that.room != null) {
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
        if (this.room != null) {
            i = this.room.hashCode();
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
        return "BedDetailsCardEpoxyModel_{room=" + this.room + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
