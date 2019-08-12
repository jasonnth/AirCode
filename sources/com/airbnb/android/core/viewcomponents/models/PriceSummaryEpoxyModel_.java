package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.PriceSummary;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class PriceSummaryEpoxyModel_ extends PriceSummaryEpoxyModel implements GeneratedModel<PriceSummary> {
    private OnModelBoundListener<PriceSummaryEpoxyModel_, PriceSummary> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PriceSummaryEpoxyModel_, PriceSummary> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, PriceSummary object, int position) {
    }

    public void handlePostBind(PriceSummary object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PriceSummaryEpoxyModel_ onBind(OnModelBoundListener<PriceSummaryEpoxyModel_, PriceSummary> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(PriceSummary object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PriceSummaryEpoxyModel_ onUnbind(OnModelUnboundListener<PriceSummaryEpoxyModel_, PriceSummary> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PriceSummaryEpoxyModel_ currencyAmount(CurrencyAmount currencyAmount) {
        onMutation();
        this.currencyAmount = currencyAmount;
        return this;
    }

    public CurrencyAmount currencyAmount() {
        return this.currencyAmount;
    }

    public PriceSummaryEpoxyModel_ hideCaption(boolean hideCaption) {
        onMutation();
        this.hideCaption = hideCaption;
        return this;
    }

    public boolean hideCaption() {
        return this.hideCaption;
    }

    public PriceSummaryEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public PriceSummaryEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public PriceSummaryEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public PriceSummaryEpoxyModel_ m5326id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PriceSummaryEpoxyModel_ m5331id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PriceSummaryEpoxyModel_ m5327id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PriceSummaryEpoxyModel_ m5328id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PriceSummaryEpoxyModel_ m5330id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PriceSummaryEpoxyModel_ m5329id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PriceSummaryEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PriceSummaryEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PriceSummaryEpoxyModel_ show() {
        super.show();
        return this;
    }

    public PriceSummaryEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PriceSummaryEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_price_summary;
    }

    public PriceSummaryEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.currencyAmount = null;
        this.hideCaption = false;
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
        if (!(o instanceof PriceSummaryEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        PriceSummaryEpoxyModel_ that = (PriceSummaryEpoxyModel_) o;
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
        if (this.currencyAmount != null) {
            if (!this.currencyAmount.equals(that.currencyAmount)) {
                return false;
            }
        } else if (that.currencyAmount != null) {
            return false;
        }
        if (this.hideCaption != that.hideCaption) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i6 = (hashCode + i) * 31;
        if (this.currencyAmount != null) {
            i2 = this.currencyAmount.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (!this.hideCaption) {
            i4 = 0;
        }
        int i8 = (i7 + i4) * 31;
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
        return "PriceSummaryEpoxyModel_{currencyAmount=" + this.currencyAmount + ", hideCaption=" + this.hideCaption + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
