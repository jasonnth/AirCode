package com.airbnb.android.itinerary.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.data.models.FlightEntryPointItem;
import com.airbnb.android.itinerary.views.ItineraryItemView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class FlightEntryPointEpoxyModel_ extends FlightEntryPointEpoxyModel implements GeneratedModel<ItineraryItemView> {
    private OnModelBoundListener<FlightEntryPointEpoxyModel_, ItineraryItemView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<FlightEntryPointEpoxyModel_, ItineraryItemView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ItineraryItemView object, int position) {
        if (this.acceptClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.acceptClickListener).bind(holder, object);
        }
        if (this.dismissClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.dismissClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ItineraryItemView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public FlightEntryPointEpoxyModel_ onBind(OnModelBoundListener<FlightEntryPointEpoxyModel_, ItineraryItemView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ItineraryItemView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public FlightEntryPointEpoxyModel_ onUnbind(OnModelUnboundListener<FlightEntryPointEpoxyModel_, ItineraryItemView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public FlightEntryPointEpoxyModel_ flightEntryPointItem(FlightEntryPointItem flightEntryPointItem) {
        onMutation();
        this.flightEntryPointItem = flightEntryPointItem;
        return this;
    }

    public FlightEntryPointItem flightEntryPointItem() {
        return this.flightEntryPointItem;
    }

    public FlightEntryPointEpoxyModel_ acceptClickListener(OnModelClickListener<FlightEntryPointEpoxyModel_, ItineraryItemView> acceptClickListener) {
        onMutation();
        if (acceptClickListener == null) {
            this.acceptClickListener = null;
        } else {
            this.acceptClickListener = new WrappedEpoxyModelClickListener(this, acceptClickListener);
        }
        return this;
    }

    public FlightEntryPointEpoxyModel_ acceptClickListener(OnClickListener acceptClickListener) {
        onMutation();
        this.acceptClickListener = acceptClickListener;
        return this;
    }

    public OnClickListener acceptClickListener() {
        return this.acceptClickListener;
    }

    public FlightEntryPointEpoxyModel_ dismissClickListener(OnModelClickListener<FlightEntryPointEpoxyModel_, ItineraryItemView> dismissClickListener) {
        onMutation();
        if (dismissClickListener == null) {
            this.dismissClickListener = null;
        } else {
            this.dismissClickListener = new WrappedEpoxyModelClickListener(this, dismissClickListener);
        }
        return this;
    }

    public FlightEntryPointEpoxyModel_ dismissClickListener(OnClickListener dismissClickListener) {
        onMutation();
        this.dismissClickListener = dismissClickListener;
        return this;
    }

    public OnClickListener dismissClickListener() {
        return this.dismissClickListener;
    }

    public FlightEntryPointEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public FlightEntryPointEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public FlightEntryPointEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public FlightEntryPointEpoxyModel_ m4246id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public FlightEntryPointEpoxyModel_ m4251id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public FlightEntryPointEpoxyModel_ m4247id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public FlightEntryPointEpoxyModel_ m4248id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public FlightEntryPointEpoxyModel_ m4250id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public FlightEntryPointEpoxyModel_ m4249id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public FlightEntryPointEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public FlightEntryPointEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public FlightEntryPointEpoxyModel_ show() {
        super.show();
        return this;
    }

    public FlightEntryPointEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public FlightEntryPointEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5755R.layout.view_holder_itinerary_item;
    }

    public FlightEntryPointEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.flightEntryPointItem = null;
        this.acceptClickListener = null;
        this.dismissClickListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        if (o == this) {
            return true;
        }
        if (!(o instanceof FlightEntryPointEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        FlightEntryPointEpoxyModel_ that = (FlightEntryPointEpoxyModel_) o;
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
        if (this.flightEntryPointItem != null) {
            if (!this.flightEntryPointItem.equals(that.flightEntryPointItem)) {
                return false;
            }
        } else if (that.flightEntryPointItem != null) {
            return false;
        }
        if (this.acceptClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.acceptClickListener == null)) {
            return false;
        }
        if (this.dismissClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.dismissClickListener == null)) {
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
        int i5 = 1;
        int i6 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i7 = (hashCode + i) * 31;
        if (this.flightEntryPointItem != null) {
            i2 = this.flightEntryPointItem.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.acceptClickListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.dismissClickListener == null) {
            i5 = 0;
        }
        int i10 = (i9 + i5) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.numCarouselItemsShown != null) {
            i6 = this.numCarouselItemsShown.hashCode();
        }
        return i11 + i6;
    }

    public String toString() {
        return "FlightEntryPointEpoxyModel_{flightEntryPointItem=" + this.flightEntryPointItem + ", acceptClickListener=" + this.acceptClickListener + ", dismissClickListener=" + this.dismissClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
