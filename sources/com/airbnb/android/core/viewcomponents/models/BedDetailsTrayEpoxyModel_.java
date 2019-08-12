package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.views.BedDetailsTray;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class BedDetailsTrayEpoxyModel_ extends BedDetailsTrayEpoxyModel implements GeneratedModel<BedDetailsTray> {
    private OnModelBoundListener<BedDetailsTrayEpoxyModel_, BedDetailsTray> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<BedDetailsTrayEpoxyModel_, BedDetailsTray> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, BedDetailsTray object, int position) {
    }

    public void handlePostBind(BedDetailsTray object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public BedDetailsTrayEpoxyModel_ onBind(OnModelBoundListener<BedDetailsTrayEpoxyModel_, BedDetailsTray> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(BedDetailsTray object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public BedDetailsTrayEpoxyModel_ onUnbind(OnModelUnboundListener<BedDetailsTrayEpoxyModel_, BedDetailsTray> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public BedDetailsTrayEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public BedDetailsTrayEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public BedDetailsTrayEpoxyModel_ rooms(List<ListingRoom> rooms) {
        onMutation();
        this.rooms = rooms;
        return this;
    }

    public List<ListingRoom> rooms() {
        return this.rooms;
    }

    public BedDetailsTrayEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public BedDetailsTrayEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public BedDetailsTrayEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public BedDetailsTrayEpoxyModel_ m4375id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public BedDetailsTrayEpoxyModel_ m4380id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public BedDetailsTrayEpoxyModel_ m4376id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public BedDetailsTrayEpoxyModel_ m4377id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public BedDetailsTrayEpoxyModel_ m4379id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public BedDetailsTrayEpoxyModel_ m4378id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public BedDetailsTrayEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public BedDetailsTrayEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public BedDetailsTrayEpoxyModel_ show() {
        super.show();
        return this;
    }

    public BedDetailsTrayEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public BedDetailsTrayEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_bed_details_tray;
    }

    public BedDetailsTrayEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.rooms = null;
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
        if (!(o instanceof BedDetailsTrayEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        BedDetailsTrayEpoxyModel_ that = (BedDetailsTrayEpoxyModel_) o;
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
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.titleRes != that.titleRes) {
            return false;
        }
        if (this.rooms != null) {
            if (!this.rooms.equals(that.rooms)) {
                return false;
            }
        } else if (that.rooms != null) {
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i4 = 0;
        }
        int i6 = (hashCode + i4) * 31;
        if (this.title != null) {
            i = this.title.hashCode();
        } else {
            i = 0;
        }
        int i7 = (((i6 + i) * 31) + this.titleRes) * 31;
        if (this.rooms != null) {
            i2 = this.rooms.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.showDivider != null) {
            i3 = this.showDivider.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.numCarouselItemsShown != null) {
            i5 = this.numCarouselItemsShown.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "BedDetailsTrayEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", rooms=" + this.rooms + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
