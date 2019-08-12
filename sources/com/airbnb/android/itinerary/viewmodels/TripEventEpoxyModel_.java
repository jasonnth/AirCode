package com.airbnb.android.itinerary.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.data.models.TripEvent;
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

public class TripEventEpoxyModel_ extends TripEventEpoxyModel implements GeneratedModel<ItineraryItemView> {
    private OnModelBoundListener<TripEventEpoxyModel_, ItineraryItemView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<TripEventEpoxyModel_, ItineraryItemView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ItineraryItemView object, int position) {
        if (this.cardClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.cardClickListener).bind(holder, object);
        }
        if (this.actionClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.actionClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ItineraryItemView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public TripEventEpoxyModel_ onBind(OnModelBoundListener<TripEventEpoxyModel_, ItineraryItemView> listener) {
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

    public TripEventEpoxyModel_ onUnbind(OnModelUnboundListener<TripEventEpoxyModel_, ItineraryItemView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public TripEventEpoxyModel_ tripEvent(TripEvent tripEvent) {
        onMutation();
        this.tripEvent = tripEvent;
        return this;
    }

    public TripEvent tripEvent() {
        return this.tripEvent;
    }

    public TripEventEpoxyModel_ cardClickListener(OnModelClickListener<TripEventEpoxyModel_, ItineraryItemView> cardClickListener) {
        onMutation();
        if (cardClickListener == null) {
            this.cardClickListener = null;
        } else {
            this.cardClickListener = new WrappedEpoxyModelClickListener(this, cardClickListener);
        }
        return this;
    }

    public TripEventEpoxyModel_ cardClickListener(OnClickListener cardClickListener) {
        onMutation();
        this.cardClickListener = cardClickListener;
        return this;
    }

    public OnClickListener cardClickListener() {
        return this.cardClickListener;
    }

    public TripEventEpoxyModel_ actionClickListener(OnModelClickListener<TripEventEpoxyModel_, ItineraryItemView> actionClickListener) {
        onMutation();
        if (actionClickListener == null) {
            this.actionClickListener = null;
        } else {
            this.actionClickListener = new WrappedEpoxyModelClickListener(this, actionClickListener);
        }
        return this;
    }

    public TripEventEpoxyModel_ actionClickListener(OnClickListener actionClickListener) {
        onMutation();
        this.actionClickListener = actionClickListener;
        return this;
    }

    public OnClickListener actionClickListener() {
        return this.actionClickListener;
    }

    public TripEventEpoxyModel_ isBottomItem(boolean isBottomItem) {
        onMutation();
        this.isBottomItem = isBottomItem;
        return this;
    }

    public boolean isBottomItem() {
        return this.isBottomItem;
    }

    public TripEventEpoxyModel_ isTimeline(boolean isTimeline) {
        onMutation();
        this.isTimeline = isTimeline;
        return this;
    }

    public boolean isTimeline() {
        return this.isTimeline;
    }

    public TripEventEpoxyModel_ isUpcomingItem(boolean isUpcomingItem) {
        onMutation();
        this.isUpcomingItem = isUpcomingItem;
        return this;
    }

    public boolean isUpcomingItem() {
        return this.isUpcomingItem;
    }

    public TripEventEpoxyModel_ showHeaderPadding(boolean showHeaderPadding) {
        onMutation();
        this.showHeaderPadding = showHeaderPadding;
        return this;
    }

    public boolean showHeaderPadding() {
        return this.showHeaderPadding;
    }

    public TripEventEpoxyModel_ showExtraHeaderPadding(boolean showExtraHeaderPadding) {
        onMutation();
        this.showExtraHeaderPadding = showExtraHeaderPadding;
        return this;
    }

    public boolean showExtraHeaderPadding() {
        return this.showExtraHeaderPadding;
    }

    public TripEventEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public TripEventEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public TripEventEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public TripEventEpoxyModel_ m4282id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public TripEventEpoxyModel_ m4287id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public TripEventEpoxyModel_ m4283id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public TripEventEpoxyModel_ m4284id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public TripEventEpoxyModel_ m4286id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public TripEventEpoxyModel_ m4285id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public TripEventEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public TripEventEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public TripEventEpoxyModel_ show() {
        super.show();
        return this;
    }

    public TripEventEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public TripEventEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5755R.layout.view_holder_itinerary_item;
    }

    public TripEventEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.tripEvent = null;
        this.cardClickListener = null;
        this.actionClickListener = null;
        this.isBottomItem = false;
        this.isTimeline = false;
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
        boolean z4;
        if (o == this) {
            return true;
        }
        if (!(o instanceof TripEventEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        TripEventEpoxyModel_ that = (TripEventEpoxyModel_) o;
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
        if (this.tripEvent != null) {
            if (!this.tripEvent.equals(that.tripEvent)) {
                return false;
            }
        } else if (that.tripEvent != null) {
            return false;
        }
        if (this.cardClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.cardClickListener == null)) {
            return false;
        }
        if (this.actionClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (that.actionClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z3 != z4 || this.isBottomItem != that.isBottomItem || this.isTimeline != that.isTimeline || this.isUpcomingItem != that.isUpcomingItem || this.showHeaderPadding != that.showHeaderPadding || this.showExtraHeaderPadding != that.showExtraHeaderPadding) {
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
        int i9;
        int i10 = 1;
        int i11 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i12 = (hashCode + i) * 31;
        if (this.tripEvent != null) {
            i2 = this.tripEvent.hashCode();
        } else {
            i2 = 0;
        }
        int i13 = (i12 + i2) * 31;
        if (this.cardClickListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i14 = (i13 + i3) * 31;
        if (this.actionClickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i15 = (i14 + i4) * 31;
        if (this.isBottomItem) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i16 = (i15 + i5) * 31;
        if (this.isTimeline) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i17 = (i16 + i6) * 31;
        if (this.isUpcomingItem) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i18 = (i17 + i7) * 31;
        if (this.showHeaderPadding) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i19 = (i18 + i8) * 31;
        if (!this.showExtraHeaderPadding) {
            i10 = 0;
        }
        int i20 = (i19 + i10) * 31;
        if (this.showDivider != null) {
            i9 = this.showDivider.hashCode();
        } else {
            i9 = 0;
        }
        int i21 = (i20 + i9) * 31;
        if (this.numCarouselItemsShown != null) {
            i11 = this.numCarouselItemsShown.hashCode();
        }
        return i21 + i11;
    }

    public String toString() {
        return "TripEventEpoxyModel_{tripEvent=" + this.tripEvent + ", cardClickListener=" + this.cardClickListener + ", actionClickListener=" + this.actionClickListener + ", isBottomItem=" + this.isBottomItem + ", isTimeline=" + this.isTimeline + ", isUpcomingItem=" + this.isUpcomingItem + ", showHeaderPadding=" + this.showHeaderPadding + ", showExtraHeaderPadding=" + this.showExtraHeaderPadding + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
