package com.airbnb.android.places.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.ResyController.ResyTimeSlotClickListener;
import com.airbnb.android.places.ResyState;
import com.airbnb.android.places.views.ResyRow;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ResyRowEpoxyModel_ extends ResyRowEpoxyModel implements GeneratedModel<ResyRow> {
    private OnModelBoundListener<ResyRowEpoxyModel_, ResyRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ResyRowEpoxyModel_, ResyRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ResyRow object, int position) {
        if (this.changeClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.changeClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ResyRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ResyRowEpoxyModel_ onBind(OnModelBoundListener<ResyRowEpoxyModel_, ResyRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ResyRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ResyRowEpoxyModel_ onUnbind(OnModelUnboundListener<ResyRowEpoxyModel_, ResyRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ResyRowEpoxyModel_ resyState(ResyState resyState) {
        onMutation();
        this.resyState = resyState;
        return this;
    }

    public ResyState resyState() {
        return this.resyState;
    }

    public ResyRowEpoxyModel_ changeClickListener(OnModelClickListener<ResyRowEpoxyModel_, ResyRow> changeClickListener) {
        onMutation();
        if (changeClickListener == null) {
            this.changeClickListener = null;
        } else {
            this.changeClickListener = new WrappedEpoxyModelClickListener(this, changeClickListener);
        }
        return this;
    }

    public ResyRowEpoxyModel_ changeClickListener(OnClickListener changeClickListener) {
        onMutation();
        this.changeClickListener = changeClickListener;
        return this;
    }

    public OnClickListener changeClickListener() {
        return this.changeClickListener;
    }

    public ResyRowEpoxyModel_ timeSlotClickListener(ResyTimeSlotClickListener timeSlotClickListener) {
        onMutation();
        this.timeSlotClickListener = timeSlotClickListener;
        return this;
    }

    public ResyTimeSlotClickListener timeSlotClickListener() {
        return this.timeSlotClickListener;
    }

    public ResyRowEpoxyModel_ slotsOnly(boolean slotsOnly) {
        onMutation();
        this.slotsOnly = slotsOnly;
        return this;
    }

    public boolean slotsOnly() {
        return this.slotsOnly;
    }

    public ResyRowEpoxyModel_ selectedTimeId(int selectedTimeId) {
        onMutation();
        this.selectedTimeId = selectedTimeId;
        return this;
    }

    public int selectedTimeId() {
        return this.selectedTimeId;
    }

    public ResyRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ResyRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ResyRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ResyRowEpoxyModel_ m6463id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ResyRowEpoxyModel_ m6468id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ResyRowEpoxyModel_ m6464id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ResyRowEpoxyModel_ m6465id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ResyRowEpoxyModel_ m6467id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ResyRowEpoxyModel_ m6466id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ResyRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ResyRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ResyRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ResyRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ResyRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7627R.layout.view_holder_resy_row;
    }

    public ResyRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.resyState = null;
        this.changeClickListener = null;
        this.timeSlotClickListener = null;
        this.slotsOnly = false;
        this.selectedTimeId = 0;
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
        if (!(o instanceof ResyRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ResyRowEpoxyModel_ that = (ResyRowEpoxyModel_) o;
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
        if (this.resyState != null) {
            if (!this.resyState.equals(that.resyState)) {
                return false;
            }
        } else if (that.resyState != null) {
            return false;
        }
        if (this.changeClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.changeClickListener == null)) {
            return false;
        }
        if (this.timeSlotClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.timeSlotClickListener == null) || this.slotsOnly != that.slotsOnly || this.selectedTimeId != that.selectedTimeId) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (hashCode + i) * 31;
        if (this.resyState != null) {
            i2 = this.resyState.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.changeClickListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.timeSlotClickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (!this.slotsOnly) {
            i6 = 0;
        }
        int i12 = (((i11 + i6) * 31) + this.selectedTimeId) * 31;
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
        return "ResyRowEpoxyModel_{resyState=" + this.resyState + ", changeClickListener=" + this.changeClickListener + ", timeSlotClickListener=" + this.timeSlotClickListener + ", slotsOnly=" + this.slotsOnly + ", selectedTimeId=" + this.selectedTimeId + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
