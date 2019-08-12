package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
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
import com.airbnb.p027n2.components.HomeCard;
import com.airbnb.p027n2.components.HomeCardStyle;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class HomeCardEpoxyModel_ extends HomeCardEpoxyModel implements GeneratedModel<HomeCard> {
    private OnModelBoundListener<HomeCardEpoxyModel_, HomeCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HomeCardEpoxyModel_, HomeCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, HomeCard object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(HomeCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HomeCardEpoxyModel_ onBind(OnModelBoundListener<HomeCardEpoxyModel_, HomeCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(HomeCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HomeCardEpoxyModel_ onUnbind(OnModelUnboundListener<HomeCardEpoxyModel_, HomeCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HomeCardEpoxyModel_ listing(Listing listing) {
        onMutation();
        this.listing = listing;
        super.listing(listing);
        return this;
    }

    public Listing listing() {
        return this.listing;
    }

    public HomeCardEpoxyModel_ pricingQuote(PricingQuote pricingQuote) {
        onMutation();
        this.pricingQuote = pricingQuote;
        return this;
    }

    public PricingQuote pricingQuote() {
        return this.pricingQuote;
    }

    public HomeCardEpoxyModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public HomeCardEpoxyModel_ isPrecisionBroaderThanCity(boolean isPrecisionBroaderThanCity) {
        onMutation();
        this.isPrecisionBroaderThanCity = isPrecisionBroaderThanCity;
        return this;
    }

    public boolean isPrecisionBroaderThanCity() {
        return this.isPrecisionBroaderThanCity;
    }

    public HomeCardEpoxyModel_ showLocation(boolean showLocation) {
        onMutation();
        this.showLocation = showLocation;
        return this;
    }

    public boolean showLocation() {
        return this.showLocation;
    }

    public HomeCardEpoxyModel_ showListingTitle(boolean showListingTitle) {
        onMutation();
        this.showListingTitle = showListingTitle;
        return this;
    }

    public boolean showListingTitle() {
        return this.showListingTitle;
    }

    public HomeCardEpoxyModel_ selectionHighlight(boolean selectionHighlight) {
        onMutation();
        this.selectionHighlight = selectionHighlight;
        return this;
    }

    public boolean selectionHighlight() {
        return this.selectionHighlight;
    }

    public HomeCardEpoxyModel_ invisible(boolean invisible) {
        onMutation();
        this.invisible = invisible;
        return this;
    }

    public boolean invisible() {
        return this.invisible;
    }

    public HomeCardEpoxyModel_ allowTotalPrice(boolean allowTotalPrice) {
        onMutation();
        this.allowTotalPrice = allowTotalPrice;
        return this;
    }

    public boolean allowTotalPrice() {
        return this.allowTotalPrice;
    }

    public HomeCardEpoxyModel_ allowBusinessTravelReady(boolean allowBusinessTravelReady) {
        onMutation();
        this.allowBusinessTravelReady = allowBusinessTravelReady;
        return this;
    }

    public boolean allowBusinessTravelReady() {
        return this.allowBusinessTravelReady;
    }

    public HomeCardEpoxyModel_ showDiscountAmount(boolean showDiscountAmount) {
        onMutation();
        this.showDiscountAmount = showDiscountAmount;
        return this;
    }

    public boolean showDiscountAmount() {
        return this.showDiscountAmount;
    }

    public HomeCardEpoxyModel_ clickListener(OnModelClickListener<HomeCardEpoxyModel_, HomeCard> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public HomeCardEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public HomeCardEpoxyModel_ wishListableData(WishListableData wishListableData) {
        onMutation();
        this.wishListableData = wishListableData;
        return this;
    }

    public WishListableData wishListableData() {
        return this.wishListableData;
    }

    public HomeCardEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public HomeCardEpoxyModel_ style(HomeCardStyle style) {
        onMutation();
        this.style = style;
        return this;
    }

    public HomeCardStyle style() {
        return this.style;
    }

    public HomeCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public HomeCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public HomeCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public HomeCardEpoxyModel_ m4726id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HomeCardEpoxyModel_ m4731id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HomeCardEpoxyModel_ m4727id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HomeCardEpoxyModel_ m4728id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HomeCardEpoxyModel_ m4730id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HomeCardEpoxyModel_ m4729id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HomeCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HomeCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HomeCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HomeCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HomeCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public HomeCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.listing = null;
        this.pricingQuote = null;
        this.loading = false;
        this.isPrecisionBroaderThanCity = false;
        this.showLocation = false;
        this.showListingTitle = false;
        this.selectionHighlight = false;
        this.invisible = false;
        this.allowTotalPrice = false;
        this.allowBusinessTravelReady = false;
        this.showDiscountAmount = false;
        this.clickListener = null;
        this.wishListableData = null;
        this.displayOptions = null;
        this.style = null;
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
        if (!(o instanceof HomeCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HomeCardEpoxyModel_ that = (HomeCardEpoxyModel_) o;
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
        if (z2 != z3 || this.loading != that.loading || this.isPrecisionBroaderThanCity != that.isPrecisionBroaderThanCity || this.showLocation != that.showLocation || this.showListingTitle != that.showListingTitle || this.selectionHighlight != that.selectionHighlight || this.invisible != that.invisible || this.allowTotalPrice != that.allowTotalPrice || this.allowBusinessTravelReady != that.allowBusinessTravelReady || this.showDiscountAmount != that.showDiscountAmount) {
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
        if (this.displayOptions != null) {
            if (!this.displayOptions.equals(that.displayOptions)) {
                return false;
            }
        } else if (that.displayOptions != null) {
            return false;
        }
        if (this.style != null) {
            if (!this.style.equals(that.style)) {
                return false;
            }
        } else if (that.style != null) {
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
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17 = 1;
        int i18 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i19 = (hashCode + i) * 31;
        if (this.listing != null) {
            i2 = this.listing.hashCode();
        } else {
            i2 = 0;
        }
        int i20 = (i19 + i2) * 31;
        if (this.pricingQuote != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i21 = (i20 + i3) * 31;
        if (this.loading) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i22 = (i21 + i4) * 31;
        if (this.isPrecisionBroaderThanCity) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i23 = (i22 + i5) * 31;
        if (this.showLocation) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i24 = (i23 + i6) * 31;
        if (this.showListingTitle) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i25 = (i24 + i7) * 31;
        if (this.selectionHighlight) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i26 = (i25 + i8) * 31;
        if (this.invisible) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i27 = (i26 + i9) * 31;
        if (this.allowTotalPrice) {
            i10 = 1;
        } else {
            i10 = 0;
        }
        int i28 = (i27 + i10) * 31;
        if (this.allowBusinessTravelReady) {
            i11 = 1;
        } else {
            i11 = 0;
        }
        int i29 = (i28 + i11) * 31;
        if (this.showDiscountAmount) {
            i12 = 1;
        } else {
            i12 = 0;
        }
        int i30 = (i29 + i12) * 31;
        if (this.clickListener != null) {
            i13 = 1;
        } else {
            i13 = 0;
        }
        int i31 = (i30 + i13) * 31;
        if (this.wishListableData == null) {
            i17 = 0;
        }
        int i32 = (i31 + i17) * 31;
        if (this.displayOptions != null) {
            i14 = this.displayOptions.hashCode();
        } else {
            i14 = 0;
        }
        int i33 = (i32 + i14) * 31;
        if (this.style != null) {
            i15 = this.style.hashCode();
        } else {
            i15 = 0;
        }
        int i34 = (i33 + i15) * 31;
        if (this.showDivider != null) {
            i16 = this.showDivider.hashCode();
        } else {
            i16 = 0;
        }
        int i35 = (i34 + i16) * 31;
        if (this.numCarouselItemsShown != null) {
            i18 = this.numCarouselItemsShown.hashCode();
        }
        return i35 + i18;
    }

    public String toString() {
        return "HomeCardEpoxyModel_{listing=" + this.listing + ", pricingQuote=" + this.pricingQuote + ", loading=" + this.loading + ", isPrecisionBroaderThanCity=" + this.isPrecisionBroaderThanCity + ", showLocation=" + this.showLocation + ", showListingTitle=" + this.showListingTitle + ", selectionHighlight=" + this.selectionHighlight + ", invisible=" + this.invisible + ", allowTotalPrice=" + this.allowTotalPrice + ", allowBusinessTravelReady=" + this.allowBusinessTravelReady + ", showDiscountAmount=" + this.showDiscountAmount + ", clickListener=" + this.clickListener + ", wishListableData=" + this.wishListableData + ", displayOptions=" + this.displayOptions + ", style=" + this.style + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
