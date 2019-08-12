package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.HomeCardChina;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class HomeCardChinaEpoxyModel_ extends HomeCardChinaEpoxyModel implements GeneratedModel<HomeCardChina> {
    private OnModelBoundListener<HomeCardChinaEpoxyModel_, HomeCardChina> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HomeCardChinaEpoxyModel_, HomeCardChina> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, HomeCardChina object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(HomeCardChina object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HomeCardChinaEpoxyModel_ onBind(OnModelBoundListener<HomeCardChinaEpoxyModel_, HomeCardChina> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(HomeCardChina object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HomeCardChinaEpoxyModel_ onUnbind(OnModelUnboundListener<HomeCardChinaEpoxyModel_, HomeCardChina> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HomeCardChinaEpoxyModel_ listing(Listing listing) {
        onMutation();
        this.listing = listing;
        super.listing(listing);
        return this;
    }

    public Listing listing() {
        return this.listing;
    }

    public HomeCardChinaEpoxyModel_ pricingQuote(PricingQuote pricingQuote) {
        onMutation();
        this.pricingQuote = pricingQuote;
        return this;
    }

    public PricingQuote pricingQuote() {
        return this.pricingQuote;
    }

    public HomeCardChinaEpoxyModel_ allowTotalPrice(boolean allowTotalPrice) {
        onMutation();
        this.allowTotalPrice = allowTotalPrice;
        return this;
    }

    public boolean allowTotalPrice() {
        return this.allowTotalPrice;
    }

    public HomeCardChinaEpoxyModel_ allowBusinessTravelReady(boolean allowBusinessTravelReady) {
        onMutation();
        this.allowBusinessTravelReady = allowBusinessTravelReady;
        return this;
    }

    public boolean allowBusinessTravelReady() {
        return this.allowBusinessTravelReady;
    }

    public HomeCardChinaEpoxyModel_ fullyRefundable(boolean fullyRefundable) {
        onMutation();
        this.fullyRefundable = fullyRefundable;
        return this;
    }

    public boolean fullyRefundable() {
        return this.fullyRefundable;
    }

    public HomeCardChinaEpoxyModel_ clickListener(OnModelClickListener<HomeCardChinaEpoxyModel_, HomeCardChina> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public HomeCardChinaEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public HomeCardChinaEpoxyModel_ wishListableData(WishListableData wishListableData) {
        onMutation();
        this.wishListableData = wishListableData;
        return this;
    }

    public WishListableData wishListableData() {
        return this.wishListableData;
    }

    public HomeCardChinaEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public HomeCardChinaEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public HomeCardChinaEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public HomeCardChinaEpoxyModel_ m4714id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HomeCardChinaEpoxyModel_ m4719id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HomeCardChinaEpoxyModel_ m4715id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HomeCardChinaEpoxyModel_ m4716id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HomeCardChinaEpoxyModel_ m4718id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HomeCardChinaEpoxyModel_ m4717id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HomeCardChinaEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HomeCardChinaEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HomeCardChinaEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HomeCardChinaEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HomeCardChinaEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_home_card_china;
    }

    public HomeCardChinaEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.listing = null;
        this.pricingQuote = null;
        this.allowTotalPrice = false;
        this.allowBusinessTravelReady = false;
        this.fullyRefundable = false;
        this.clickListener = null;
        this.wishListableData = null;
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
        if (!(o instanceof HomeCardChinaEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HomeCardChinaEpoxyModel_ that = (HomeCardChinaEpoxyModel_) o;
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
        if (this.listing != null) {
            if (!this.listing.equals(that.listing)) {
                return false;
            }
        } else if (that.listing != null) {
            return false;
        }
        if (this.pricingQuote == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (that.pricingQuote == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z2 != z3 || this.allowTotalPrice != that.allowTotalPrice || this.allowBusinessTravelReady != that.allowBusinessTravelReady || this.fullyRefundable != that.fullyRefundable) {
            return false;
        }
        if ((this.clickListener == null) != (that.clickListener == null)) {
            return false;
        }
        if (this.wishListableData == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.wishListableData == null)) {
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
        if (this.listing != null) {
            i2 = this.listing.hashCode();
        } else {
            i2 = 0;
        }
        int i12 = (i11 + i2) * 31;
        if (this.pricingQuote != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i13 = (i12 + i3) * 31;
        if (this.allowTotalPrice) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i14 = (i13 + i4) * 31;
        if (this.allowBusinessTravelReady) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i15 = (i14 + i5) * 31;
        if (this.fullyRefundable) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i16 = (i15 + i6) * 31;
        if (this.clickListener != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.wishListableData == null) {
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
        return "HomeCardChinaEpoxyModel_{listing=" + this.listing + ", pricingQuote=" + this.pricingQuote + ", allowTotalPrice=" + this.allowTotalPrice + ", allowBusinessTravelReady=" + this.allowBusinessTravelReady + ", fullyRefundable=" + this.fullyRefundable + ", clickListener=" + this.clickListener + ", wishListableData=" + this.wishListableData + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
