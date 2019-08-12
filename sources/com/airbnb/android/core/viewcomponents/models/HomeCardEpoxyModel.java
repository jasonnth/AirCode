package com.airbnb.android.core.viewcomponents.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PriceFactor;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.SearchUtil;
import com.airbnb.android.core.wishlists.WishListHeartController;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.HomeCard;
import com.airbnb.p027n2.components.HomeCardStyle;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.base.Objects;

public abstract class HomeCardEpoxyModel extends AirEpoxyModel<HomeCard> {
    boolean allowBusinessTravelReady;
    boolean allowTotalPrice;
    OnClickListener clickListener;
    DisplayOptions displayOptions;
    boolean invisible;
    boolean isPrecisionBroaderThanCity;
    Listing listing;
    boolean loading;
    PricingQuote pricingQuote;
    boolean selectionHighlight;
    boolean showDiscountAmount;
    boolean showListingTitle;
    boolean showLocation = true;
    HomeCardStyle style;
    WishListableData wishListableData;

    public int getDefaultLayout() {
        if (isCarousel()) {
            return C0716R.layout.view_holder_home_card_carousel;
        }
        if (isGrid()) {
            return C0716R.layout.view_holder_home_card_grid;
        }
        return C0716R.layout.n2_view_holder_home_card;
    }

    private boolean isGrid() {
        return this.displayOptions != null && this.displayOptions.isGrid();
    }

    private boolean isCarousel() {
        return this.displayOptions != null && this.displayOptions.isCarousel();
    }

    @SuppressLint({"MissingSuperCall"})
    public void bind(HomeCard homeCard) {
        doBind(homeCard, true);
    }

    public void doBind(HomeCard homeCard, boolean adjustLayoutParams) {
        HomeCardStyle styleToUse;
        int discountAmount;
        super.bind(homeCard);
        if (!this.loading) {
            Check.notNull(this.listing, "listing must be set");
        }
        if (this.displayOptions != null && adjustLayoutParams) {
            this.displayOptions.adjustLayoutParams(homeCard);
        }
        if (this.displayOptions == null || this.displayOptions.isList() || this.displayOptions.isGrid()) {
            styleToUse = HomeCardStyle.FULL;
        } else {
            styleToUse = HomeCardStyle.MINI;
        }
        homeCard.setStyle(styleToUse);
        Context context = homeCard.getContext();
        homeCard.setListingTitleText(null);
        homeCard.setDiscountText(null);
        if (this.loading) {
            homeCard.setSubtitle(null);
        } else {
            if (this.showListingTitle) {
                homeCard.setListingTitleText(this.listing.getName());
            }
            if (this.showDiscountAmount) {
                PriceFactor monthlyPriceFactor = this.pricingQuote.getMonthlyPriceFactor();
                PriceFactor weeklyPriceFactor = this.pricingQuote.getWeeklyPriceFactor();
                if (monthlyPriceFactor.hasDiscount()) {
                    discountAmount = monthlyPriceFactor.getDiscountPercentage();
                } else {
                    discountAmount = weeklyPriceFactor.getDiscountPercentage();
                }
                homeCard.setDiscountText(context.getString(C0716R.string.x_percent_off, new Object[]{Integer.valueOf(discountAmount)}));
            } else {
                homeCard.setDiscountText(null);
            }
            SpaceType spaceType = this.listing.getSpaceType();
            if (spaceType == null) {
                homeCard.setSubtitle(this.listing.getRoomType(context));
            } else if (styleToUse == HomeCardStyle.FULL && Experiments.usePropertyTypeAndSpaceTypeInP2HomeCardSubtitle()) {
                homeCard.setSubtitle(this.listing.getSpaceTypeDescription());
            } else if (!this.showLocation) {
                homeCard.setSubtitle(this.listing.getRoomType(context));
            } else if (!this.isPrecisionBroaderThanCity && !TextUtils.isEmpty(this.listing.getNeighborhood())) {
                homeCard.setSubtitle(context.getString(spaceType.inNeighborhoodId, new Object[]{this.listing.getNeighborhood()}));
            } else if (!TextUtils.isEmpty(this.listing.getLocalizedCity())) {
                homeCard.setSubtitle(context.getString(spaceType.inCityId, new Object[]{this.listing.getLocalizedCity()}));
            } else if (!TextUtils.isEmpty(this.listing.getCountry())) {
                homeCard.setSubtitle(context.getString(spaceType.inCountryId, new Object[]{this.listing.getCountry()}));
            }
        }
        if (this.loading) {
            homeCard.setListingImageUrl(null);
        } else if (this.listing.getPreviewEncodedPng() != null) {
            homeCard.setListingUrlWithBlurredPreview(this.listing.getPictureUrl(), this.listing.getPreviewEncodedPng());
        } else {
            homeCard.setListingImageUrl(this.listing.getPictureUrl());
        }
        if (this.loading) {
            homeCard.setRating(0.0f, 0);
            homeCard.setReviewsText(null);
        } else {
            homeCard.setRating(this.listing.getStarRating(), this.listing.getReviewsCount());
            if (this.listing.getReviewsCount() > 0) {
                homeCard.setReviewsText(this.listing.getNumReviewsText(homeCard.getContext()));
            } else if (this.listing.isIsNewListing() == null || !this.listing.isIsNewListing().booleanValue()) {
                homeCard.setReviewsText(null);
            } else {
                homeCard.setReviewsText(context.getString(C0716R.string.new_home));
            }
        }
        homeCard.setPriceText((this.loading || this.pricingQuote == null) ? null : SearchUtil.getPriceTagText(this.pricingQuote, this.allowTotalPrice), (this.loading || this.pricingQuote == null) ? null : SearchUtil.getSinglelinePriceRateTypeText(context, this.pricingQuote, this.allowTotalPrice));
        homeCard.setInstantBookAvailable(!this.loading && this.pricingQuote != null && this.pricingQuote.isInstantBookable());
        homeCard.setIsSuperhost(!this.loading && this.listing.isSuperHosted());
        homeCard.setIsBusinessTravelReady(!this.loading && this.allowBusinessTravelReady && this.listing.isBusinessTravelReady());
        homeCard.setOnClickListener(this.loading ? null : this.clickListener);
        homeCard.showSelectionHighlight(this.selectionHighlight);
        if (this.loading || this.wishListableData == null) {
            homeCard.clearWishListHeartInterface();
        } else {
            homeCard.setWishListHeartInterface(new WishListHeartController(context, this.wishListableData));
        }
        homeCard.setTransitionTypeId(this.listing == null ? 0 : this.listing.getId());
        ViewLibUtils.setInvisibleIf(homeCard, this.invisible);
    }

    public void unbind(HomeCard homeCard) {
        homeCard.clearWishListHeartInterface();
        homeCard.clearImages();
        homeCard.setOnClickListener(null);
    }

    public HomeCardEpoxyModel listing(Listing listing2) {
        this.listing = listing2;
        mo11716id(listing2.getId());
        return this;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (this.displayOptions != null) {
            return this.displayOptions.getSpanSize(totalSpanCount);
        }
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }

    public int getDividerViewType() {
        return 0;
    }

    public int hashCode() {
        if (this.pricingQuote == null) {
            return super.hashCode();
        }
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(super.hashCode());
        objArr[1] = Boolean.valueOf(this.pricingQuote.isInstantBookable());
        objArr[2] = this.pricingQuote.hasTotalPrice() ? this.pricingQuote.getTotalPrice() : this.pricingQuote.getRate();
        objArr[3] = this.pricingQuote.getRateType();
        return Objects.hashCode(objArr);
    }
}
