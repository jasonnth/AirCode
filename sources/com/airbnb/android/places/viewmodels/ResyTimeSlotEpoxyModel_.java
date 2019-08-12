package com.airbnb.android.places.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.views.ResyTimeSlotView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ResyTimeSlotEpoxyModel_ extends ResyTimeSlotEpoxyModel implements GeneratedModel<ResyTimeSlotView> {
    private OnModelBoundListener<ResyTimeSlotEpoxyModel_, ResyTimeSlotView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ResyTimeSlotEpoxyModel_, ResyTimeSlotView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ResyTimeSlotView object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ResyTimeSlotView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ResyTimeSlotEpoxyModel_ onBind(OnModelBoundListener<ResyTimeSlotEpoxyModel_, ResyTimeSlotView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ResyTimeSlotView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ResyTimeSlotEpoxyModel_ onUnbind(OnModelUnboundListener<ResyTimeSlotEpoxyModel_, ResyTimeSlotView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ResyTimeSlotEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public ResyTimeSlotEpoxyModel_ subtitle(String subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public String subtitle() {
        return this.subtitle;
    }

    public ResyTimeSlotEpoxyModel_ isLast(boolean isLast) {
        onMutation();
        this.isLast = isLast;
        return this;
    }

    public boolean isLast() {
        return this.isLast;
    }

    public ResyTimeSlotEpoxyModel_ isSelected(boolean isSelected) {
        onMutation();
        this.isSelected = isSelected;
        return this;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public ResyTimeSlotEpoxyModel_ clickListener(OnModelClickListener<ResyTimeSlotEpoxyModel_, ResyTimeSlotView> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public ResyTimeSlotEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public ResyTimeSlotEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ResyTimeSlotEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ResyTimeSlotEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ResyTimeSlotEpoxyModel_ m6475id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ResyTimeSlotEpoxyModel_ m6480id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ResyTimeSlotEpoxyModel_ m6476id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ResyTimeSlotEpoxyModel_ m6477id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ResyTimeSlotEpoxyModel_ m6479id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ResyTimeSlotEpoxyModel_ m6478id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ResyTimeSlotEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ResyTimeSlotEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ResyTimeSlotEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ResyTimeSlotEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ResyTimeSlotEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7627R.layout.view_holder_resy_time_slot;
    }

    public ResyTimeSlotEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.subtitle = null;
        this.isLast = false;
        this.isSelected = false;
        this.clickListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResyTimeSlotEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ResyTimeSlotEpoxyModel_ that = (ResyTimeSlotEpoxyModel_) o;
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
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.isLast != that.isLast || this.isSelected != that.isSelected) {
            return false;
        }
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.clickListener == null)) {
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
        int i7 = 1;
        int i8 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i9 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.isLast) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.isSelected) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.clickListener == null) {
            i7 = 0;
        }
        int i14 = (i13 + i7) * 31;
        if (this.showDivider != null) {
            i6 = this.showDivider.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.numCarouselItemsShown != null) {
            i8 = this.numCarouselItemsShown.hashCode();
        }
        return i15 + i8;
    }

    public String toString() {
        return "ResyTimeSlotEpoxyModel_{title=" + this.title + ", subtitle=" + this.subtitle + ", isLast=" + this.isLast + ", isSelected=" + this.isSelected + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
