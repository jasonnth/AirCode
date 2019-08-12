package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.PricingBreakdownRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class PricingRowEpoxyModel_ extends PricingRowEpoxyModel implements GeneratedModel<PricingBreakdownRow> {
    private OnModelBoundListener<PricingRowEpoxyModel_, PricingBreakdownRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PricingRowEpoxyModel_, PricingBreakdownRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, PricingBreakdownRow object, int position) {
        if (this.giftCreditClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.giftCreditClickListener).bind(holder, object);
        }
        if (this.couponCodeClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.couponCodeClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(PricingBreakdownRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PricingRowEpoxyModel_ onBind(OnModelBoundListener<PricingRowEpoxyModel_, PricingBreakdownRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(PricingBreakdownRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PricingRowEpoxyModel_ onUnbind(OnModelUnboundListener<PricingRowEpoxyModel_, PricingBreakdownRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PricingRowEpoxyModel_ price(Price price) {
        onMutation();
        this.price = price;
        return this;
    }

    public Price price() {
        return this.price;
    }

    public PricingRowEpoxyModel_ giftCreditTitle(String giftCreditTitle) {
        onMutation();
        this.giftCreditTitle = giftCreditTitle;
        return this;
    }

    public String giftCreditTitle() {
        return this.giftCreditTitle;
    }

    public PricingRowEpoxyModel_ couponCodeTitle(String couponCodeTitle) {
        onMutation();
        this.couponCodeTitle = couponCodeTitle;
        return this;
    }

    public String couponCodeTitle() {
        return this.couponCodeTitle;
    }

    public PricingRowEpoxyModel_ giftCreditClickListener(OnModelClickListener<PricingRowEpoxyModel_, PricingBreakdownRow> giftCreditClickListener) {
        onMutation();
        if (giftCreditClickListener == null) {
            this.giftCreditClickListener = null;
        } else {
            this.giftCreditClickListener = new WrappedEpoxyModelClickListener(this, giftCreditClickListener);
        }
        return this;
    }

    public PricingRowEpoxyModel_ giftCreditClickListener(OnClickListener giftCreditClickListener) {
        onMutation();
        this.giftCreditClickListener = giftCreditClickListener;
        return this;
    }

    public OnClickListener giftCreditClickListener() {
        return this.giftCreditClickListener;
    }

    public PricingRowEpoxyModel_ couponCodeClickListener(OnModelClickListener<PricingRowEpoxyModel_, PricingBreakdownRow> couponCodeClickListener) {
        onMutation();
        if (couponCodeClickListener == null) {
            this.couponCodeClickListener = null;
        } else {
            this.couponCodeClickListener = new WrappedEpoxyModelClickListener(this, couponCodeClickListener);
        }
        return this;
    }

    public PricingRowEpoxyModel_ couponCodeClickListener(OnClickListener couponCodeClickListener) {
        onMutation();
        this.couponCodeClickListener = couponCodeClickListener;
        return this;
    }

    public OnClickListener couponCodeClickListener() {
        return this.couponCodeClickListener;
    }

    public PricingRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public PricingRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public PricingRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public PricingRowEpoxyModel_ m6215id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PricingRowEpoxyModel_ m6220id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PricingRowEpoxyModel_ m6216id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PricingRowEpoxyModel_ m6217id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PricingRowEpoxyModel_ m6219id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PricingRowEpoxyModel_ m6218id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PricingRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PricingRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PricingRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public PricingRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PricingRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.view_holder_pricing_row;
    }

    public PricingRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.price = null;
        this.giftCreditTitle = null;
        this.couponCodeTitle = null;
        this.giftCreditClickListener = null;
        this.couponCodeClickListener = null;
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
        if (!(o instanceof PricingRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        PricingRowEpoxyModel_ that = (PricingRowEpoxyModel_) o;
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
        if (this.price != null) {
            if (!this.price.equals(that.price)) {
                return false;
            }
        } else if (that.price != null) {
            return false;
        }
        if (this.giftCreditTitle != null) {
            if (!this.giftCreditTitle.equals(that.giftCreditTitle)) {
                return false;
            }
        } else if (that.giftCreditTitle != null) {
            return false;
        }
        if (this.couponCodeTitle != null) {
            if (!this.couponCodeTitle.equals(that.couponCodeTitle)) {
                return false;
            }
        } else if (that.couponCodeTitle != null) {
            return false;
        }
        if (this.giftCreditClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.giftCreditClickListener == null)) {
            return false;
        }
        if (this.couponCodeClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.couponCodeClickListener == null)) {
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
        if (this.price != null) {
            i2 = this.price.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.giftCreditTitle != null) {
            i3 = this.giftCreditTitle.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.couponCodeTitle != null) {
            i4 = this.couponCodeTitle.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.giftCreditClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.couponCodeClickListener == null) {
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
        return "PricingRowEpoxyModel_{price=" + this.price + ", giftCreditTitle=" + this.giftCreditTitle + ", couponCodeTitle=" + this.couponCodeTitle + ", giftCreditClickListener=" + this.giftCreditClickListener + ", couponCodeClickListener=" + this.couponCodeClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
