package com.airbnb.android.places.viewmodels;

import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.views.PlaceActivityHeaderView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class PlaceActivityHeaderEpoxyModel_ extends PlaceActivityHeaderEpoxyModel implements GeneratedModel<PlaceActivityHeaderView> {
    private OnModelBoundListener<PlaceActivityHeaderEpoxyModel_, PlaceActivityHeaderView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PlaceActivityHeaderEpoxyModel_, PlaceActivityHeaderView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, PlaceActivityHeaderView object, int position) {
    }

    public void handlePostBind(PlaceActivityHeaderView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PlaceActivityHeaderEpoxyModel_ onBind(OnModelBoundListener<PlaceActivityHeaderEpoxyModel_, PlaceActivityHeaderView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(PlaceActivityHeaderView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PlaceActivityHeaderEpoxyModel_ onUnbind(OnModelUnboundListener<PlaceActivityHeaderEpoxyModel_, PlaceActivityHeaderView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PlaceActivityHeaderEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public PlaceActivityHeaderEpoxyModel_ boldSubtitle(String boldSubtitle) {
        onMutation();
        this.boldSubtitle = boldSubtitle;
        return this;
    }

    public String boldSubtitle() {
        return this.boldSubtitle;
    }

    public PlaceActivityHeaderEpoxyModel_ helpText(String helpText) {
        onMutation();
        this.helpText = helpText;
        return this;
    }

    public String helpText() {
        return this.helpText;
    }

    public PlaceActivityHeaderEpoxyModel_ actionKicker(String actionKicker) {
        onMutation();
        this.actionKicker = actionKicker;
        return this;
    }

    public String actionKicker() {
        return this.actionKicker;
    }

    public PlaceActivityHeaderEpoxyModel_ actionKickerColor(int actionKickerColor) {
        onMutation();
        this.actionKickerColor = actionKickerColor;
        return this;
    }

    public int actionKickerColor() {
        return this.actionKickerColor;
    }

    public PlaceActivityHeaderEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public PlaceActivityHeaderEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public PlaceActivityHeaderEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityHeaderEpoxyModel_ m6427id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityHeaderEpoxyModel_ m6432id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityHeaderEpoxyModel_ m6428id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityHeaderEpoxyModel_ m6429id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityHeaderEpoxyModel_ m6431id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PlaceActivityHeaderEpoxyModel_ m6430id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PlaceActivityHeaderEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PlaceActivityHeaderEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PlaceActivityHeaderEpoxyModel_ show() {
        super.show();
        return this;
    }

    public PlaceActivityHeaderEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PlaceActivityHeaderEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7627R.layout.view_holder_place_activity_header;
    }

    public PlaceActivityHeaderEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.boldSubtitle = null;
        this.helpText = null;
        this.actionKicker = null;
        this.actionKickerColor = 0;
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
        if (!(o instanceof PlaceActivityHeaderEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        PlaceActivityHeaderEpoxyModel_ that = (PlaceActivityHeaderEpoxyModel_) o;
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
        if (this.boldSubtitle != null) {
            if (!this.boldSubtitle.equals(that.boldSubtitle)) {
                return false;
            }
        } else if (that.boldSubtitle != null) {
            return false;
        }
        if (this.helpText != null) {
            if (!this.helpText.equals(that.helpText)) {
                return false;
            }
        } else if (that.helpText != null) {
            return false;
        }
        if (this.actionKicker != null) {
            if (!this.actionKicker.equals(that.actionKicker)) {
                return false;
            }
        } else if (that.actionKicker != null) {
            return false;
        }
        if (this.actionKickerColor != that.actionKickerColor) {
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
        int i6 = 1;
        int i7 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i6 = 0;
        }
        int i8 = (hashCode + i6) * 31;
        if (this.title != null) {
            i = this.title.hashCode();
        } else {
            i = 0;
        }
        int i9 = (i8 + i) * 31;
        if (this.boldSubtitle != null) {
            i2 = this.boldSubtitle.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.helpText != null) {
            i3 = this.helpText.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.actionKicker != null) {
            i4 = this.actionKicker.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (((i11 + i4) * 31) + this.actionKickerColor) * 31;
        if (this.showDivider != null) {
            i5 = this.showDivider.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.numCarouselItemsShown != null) {
            i7 = this.numCarouselItemsShown.hashCode();
        }
        return i13 + i7;
    }

    public String toString() {
        return "PlaceActivityHeaderEpoxyModel_{title=" + this.title + ", boldSubtitle=" + this.boldSubtitle + ", helpText=" + this.helpText + ", actionKicker=" + this.actionKicker + ", actionKickerColor=" + this.actionKickerColor + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
