package com.airbnb.android.itinerary.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.data.models.TimelineTrip;
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

public class TimelineTripEpoxyModel_ extends TimelineTripEpoxyModel implements GeneratedModel<ItineraryItemView> {
    private OnModelBoundListener<TimelineTripEpoxyModel_, ItineraryItemView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<TimelineTripEpoxyModel_, ItineraryItemView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ItineraryItemView object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ItineraryItemView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public TimelineTripEpoxyModel_ onBind(OnModelBoundListener<TimelineTripEpoxyModel_, ItineraryItemView> listener) {
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

    public TimelineTripEpoxyModel_ onUnbind(OnModelUnboundListener<TimelineTripEpoxyModel_, ItineraryItemView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public TimelineTripEpoxyModel_ timelineTrip(TimelineTrip timelineTrip) {
        onMutation();
        this.timelineTrip = timelineTrip;
        return this;
    }

    public TimelineTrip timelineTrip() {
        return this.timelineTrip;
    }

    public TimelineTripEpoxyModel_ clickListener(OnModelClickListener<TimelineTripEpoxyModel_, ItineraryItemView> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public TimelineTripEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public TimelineTripEpoxyModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public TimelineTripEpoxyModel_ isBottomItem(boolean isBottomItem) {
        onMutation();
        this.isBottomItem = isBottomItem;
        return this;
    }

    public boolean isBottomItem() {
        return this.isBottomItem;
    }

    public TimelineTripEpoxyModel_ isUpcomingItem(boolean isUpcomingItem) {
        onMutation();
        this.isUpcomingItem = isUpcomingItem;
        return this;
    }

    public boolean isUpcomingItem() {
        return this.isUpcomingItem;
    }

    public TimelineTripEpoxyModel_ showHeaderPadding(boolean showHeaderPadding) {
        onMutation();
        this.showHeaderPadding = showHeaderPadding;
        return this;
    }

    public boolean showHeaderPadding() {
        return this.showHeaderPadding;
    }

    public TimelineTripEpoxyModel_ showExtraHeaderPadding(boolean showExtraHeaderPadding) {
        onMutation();
        this.showExtraHeaderPadding = showExtraHeaderPadding;
        return this;
    }

    public boolean showExtraHeaderPadding() {
        return this.showExtraHeaderPadding;
    }

    public TimelineTripEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public TimelineTripEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public TimelineTripEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public TimelineTripEpoxyModel_ m4270id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public TimelineTripEpoxyModel_ m4275id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public TimelineTripEpoxyModel_ m4271id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public TimelineTripEpoxyModel_ m4272id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public TimelineTripEpoxyModel_ m4274id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public TimelineTripEpoxyModel_ m4273id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public TimelineTripEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public TimelineTripEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public TimelineTripEpoxyModel_ show() {
        super.show();
        return this;
    }

    public TimelineTripEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public TimelineTripEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5755R.layout.view_holder_itinerary_item;
    }

    public TimelineTripEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.timelineTrip = null;
        this.clickListener = null;
        this.loading = false;
        this.isBottomItem = false;
        this.isUpcomingItem = false;
        this.showHeaderPadding = false;
        this.showExtraHeaderPadding = false;
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
        if (!(o instanceof TimelineTripEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        TimelineTripEpoxyModel_ that = (TimelineTripEpoxyModel_) o;
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
        if (this.timelineTrip != null) {
            if (!this.timelineTrip.equals(that.timelineTrip)) {
                return false;
            }
        } else if (that.timelineTrip != null) {
            return false;
        }
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (that.clickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z2 != z3 || this.loading != that.loading || this.isBottomItem != that.isBottomItem || this.isUpcomingItem != that.isUpcomingItem || this.showHeaderPadding != that.showHeaderPadding || this.showExtraHeaderPadding != that.showExtraHeaderPadding) {
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
        if (this.timelineTrip != null) {
            i2 = this.timelineTrip.hashCode();
        } else {
            i2 = 0;
        }
        int i12 = (i11 + i2) * 31;
        if (this.clickListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i13 = (i12 + i3) * 31;
        if (this.loading) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i14 = (i13 + i4) * 31;
        if (this.isBottomItem) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i15 = (i14 + i5) * 31;
        if (this.isUpcomingItem) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i16 = (i15 + i6) * 31;
        if (this.showHeaderPadding) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (!this.showExtraHeaderPadding) {
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
        return "TimelineTripEpoxyModel_{timelineTrip=" + this.timelineTrip + ", clickListener=" + this.clickListener + ", loading=" + this.loading + ", isBottomItem=" + this.isBottomItem + ", isUpcomingItem=" + this.isUpcomingItem + ", showHeaderPadding=" + this.showHeaderPadding + ", showExtraHeaderPadding=" + this.showExtraHeaderPadding + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
