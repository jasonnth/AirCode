package com.airbnb.android.places.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.views.PlaceInfoView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.utils.MapOptions;

public class PlaceInfoEpoxyModel_ extends PlaceInfoEpoxyModel implements GeneratedModel<PlaceInfoView> {
    private OnModelBoundListener<PlaceInfoEpoxyModel_, PlaceInfoView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PlaceInfoEpoxyModel_, PlaceInfoView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, PlaceInfoView object, int position) {
        if (this.addressClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.addressClickListener).bind(holder, object);
        }
        if (this.websiteClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.websiteClickListener).bind(holder, object);
        }
        if (this.phoneClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.phoneClickListener).bind(holder, object);
        }
        if (this.hoursClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.hoursClickListener).bind(holder, object);
        }
        if (this.mapClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.mapClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(PlaceInfoView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PlaceInfoEpoxyModel_ onBind(OnModelBoundListener<PlaceInfoEpoxyModel_, PlaceInfoView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(PlaceInfoView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PlaceInfoEpoxyModel_ onUnbind(OnModelUnboundListener<PlaceInfoEpoxyModel_, PlaceInfoView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PlaceInfoEpoxyModel_ place(Place place) {
        onMutation();
        this.place = place;
        return this;
    }

    public Place place() {
        return this.place;
    }

    public PlaceInfoEpoxyModel_ mapOptions(MapOptions mapOptions) {
        onMutation();
        this.mapOptions = mapOptions;
        return this;
    }

    public MapOptions mapOptions() {
        return this.mapOptions;
    }

    public PlaceInfoEpoxyModel_ addressClickListener(OnModelClickListener<PlaceInfoEpoxyModel_, PlaceInfoView> addressClickListener) {
        onMutation();
        if (addressClickListener == null) {
            this.addressClickListener = null;
        } else {
            this.addressClickListener = new WrappedEpoxyModelClickListener(this, addressClickListener);
        }
        return this;
    }

    public PlaceInfoEpoxyModel_ addressClickListener(OnClickListener addressClickListener) {
        onMutation();
        this.addressClickListener = addressClickListener;
        return this;
    }

    public OnClickListener addressClickListener() {
        return this.addressClickListener;
    }

    public PlaceInfoEpoxyModel_ websiteClickListener(OnModelClickListener<PlaceInfoEpoxyModel_, PlaceInfoView> websiteClickListener) {
        onMutation();
        if (websiteClickListener == null) {
            this.websiteClickListener = null;
        } else {
            this.websiteClickListener = new WrappedEpoxyModelClickListener(this, websiteClickListener);
        }
        return this;
    }

    public PlaceInfoEpoxyModel_ websiteClickListener(OnClickListener websiteClickListener) {
        onMutation();
        this.websiteClickListener = websiteClickListener;
        return this;
    }

    public OnClickListener websiteClickListener() {
        return this.websiteClickListener;
    }

    public PlaceInfoEpoxyModel_ phoneClickListener(OnModelClickListener<PlaceInfoEpoxyModel_, PlaceInfoView> phoneClickListener) {
        onMutation();
        if (phoneClickListener == null) {
            this.phoneClickListener = null;
        } else {
            this.phoneClickListener = new WrappedEpoxyModelClickListener(this, phoneClickListener);
        }
        return this;
    }

    public PlaceInfoEpoxyModel_ phoneClickListener(OnClickListener phoneClickListener) {
        onMutation();
        this.phoneClickListener = phoneClickListener;
        return this;
    }

    public OnClickListener phoneClickListener() {
        return this.phoneClickListener;
    }

    public PlaceInfoEpoxyModel_ hoursClickListener(OnModelClickListener<PlaceInfoEpoxyModel_, PlaceInfoView> hoursClickListener) {
        onMutation();
        if (hoursClickListener == null) {
            this.hoursClickListener = null;
        } else {
            this.hoursClickListener = new WrappedEpoxyModelClickListener(this, hoursClickListener);
        }
        return this;
    }

    public PlaceInfoEpoxyModel_ hoursClickListener(OnClickListener hoursClickListener) {
        onMutation();
        this.hoursClickListener = hoursClickListener;
        return this;
    }

    public OnClickListener hoursClickListener() {
        return this.hoursClickListener;
    }

    public PlaceInfoEpoxyModel_ mapClickListener(OnModelClickListener<PlaceInfoEpoxyModel_, PlaceInfoView> mapClickListener) {
        onMutation();
        if (mapClickListener == null) {
            this.mapClickListener = null;
        } else {
            this.mapClickListener = new WrappedEpoxyModelClickListener(this, mapClickListener);
        }
        return this;
    }

    public PlaceInfoEpoxyModel_ mapClickListener(OnClickListener mapClickListener) {
        onMutation();
        this.mapClickListener = mapClickListener;
        return this;
    }

    public OnClickListener mapClickListener() {
        return this.mapClickListener;
    }

    public PlaceInfoEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public PlaceInfoEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public PlaceInfoEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public PlaceInfoEpoxyModel_ m6439id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PlaceInfoEpoxyModel_ m6444id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PlaceInfoEpoxyModel_ m6440id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PlaceInfoEpoxyModel_ m6441id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PlaceInfoEpoxyModel_ m6443id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PlaceInfoEpoxyModel_ m6442id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PlaceInfoEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PlaceInfoEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PlaceInfoEpoxyModel_ show() {
        super.show();
        return this;
    }

    public PlaceInfoEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PlaceInfoEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7627R.layout.view_holder_place_info;
    }

    public PlaceInfoEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.place = null;
        this.mapOptions = null;
        this.addressClickListener = null;
        this.websiteClickListener = null;
        this.phoneClickListener = null;
        this.hoursClickListener = null;
        this.mapClickListener = null;
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
        boolean z6;
        boolean z7;
        if (o == this) {
            return true;
        }
        if (!(o instanceof PlaceInfoEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        PlaceInfoEpoxyModel_ that = (PlaceInfoEpoxyModel_) o;
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
        if (this.place != null) {
            if (!this.place.equals(that.place)) {
                return false;
            }
        } else if (that.place != null) {
            return false;
        }
        if (this.mapOptions == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.mapOptions == null)) {
            return false;
        }
        if (this.addressClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.addressClickListener == null)) {
            return false;
        }
        if (this.websiteClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.websiteClickListener == null)) {
            return false;
        }
        if (this.phoneClickListener == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5 != (that.phoneClickListener == null)) {
            return false;
        }
        if (this.hoursClickListener == null) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (z6 != (that.hoursClickListener == null)) {
            return false;
        }
        if (this.mapClickListener == null) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (z7 != (that.mapClickListener == null)) {
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
        int i8;
        int i9 = 1;
        int i10 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i11 = (hashCode + i) * 31;
        if (this.place != null) {
            i2 = this.place.hashCode();
        } else {
            i2 = 0;
        }
        int i12 = (i11 + i2) * 31;
        if (this.mapOptions != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i13 = (i12 + i3) * 31;
        if (this.addressClickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i14 = (i13 + i4) * 31;
        if (this.websiteClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i15 = (i14 + i5) * 31;
        if (this.phoneClickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i16 = (i15 + i6) * 31;
        if (this.hoursClickListener != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.mapClickListener == null) {
            i9 = 0;
        }
        int i18 = (i17 + i9) * 31;
        if (this.showDivider != null) {
            i8 = this.showDivider.hashCode();
        } else {
            i8 = 0;
        }
        int i19 = (i18 + i8) * 31;
        if (this.numCarouselItemsShown != null) {
            i10 = this.numCarouselItemsShown.hashCode();
        }
        return i19 + i10;
    }

    public String toString() {
        return "PlaceInfoEpoxyModel_{place=" + this.place + ", mapOptions=" + this.mapOptions + ", addressClickListener=" + this.addressClickListener + ", websiteClickListener=" + this.websiteClickListener + ", phoneClickListener=" + this.phoneClickListener + ", hoursClickListener=" + this.hoursClickListener + ", mapClickListener=" + this.mapClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
