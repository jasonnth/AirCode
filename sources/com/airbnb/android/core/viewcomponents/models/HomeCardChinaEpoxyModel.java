package com.airbnb.android.core.viewcomponents.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.utils.SearchUtil;
import com.airbnb.android.core.wishlists.WishListHeartController;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.p027n2.components.HomeCardChina;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.google.common.base.Objects;

public abstract class HomeCardChinaEpoxyModel extends AirEpoxyModel<HomeCardChina> {
    boolean allowBusinessTravelReady;
    boolean allowTotalPrice;
    OnClickListener clickListener;
    boolean fullyRefundable;
    Listing listing;
    PricingQuote pricingQuote;
    WishListableData wishListableData;

    @SuppressLint({"MissingSuperCall"})
    public void bind(HomeCardChina homeCard) {
        boolean chineseSpeakingHost;
        int i;
        int i2;
        int i3;
        boolean z;
        boolean hasWifi;
        int i4;
        int i5 = 0;
        Context context = homeCard.getContext();
        if (this.listing.getPreviewEncodedPng() != null) {
            homeCard.setListingUrlWithBlurredPreview(this.listing.getPictureUrl(), this.listing.getPreviewEncodedPng());
        } else {
            homeCard.setListingImageUrl(this.listing.getPictureUrl());
        }
        if (this.listing.getHostLanguages() == null || !this.listing.getHostLanguages().contains(AirbnbConstants.LANGUAGE_CODE_CHINESE)) {
            chineseSpeakingHost = false;
        } else {
            chineseSpeakingHost = true;
        }
        if (!this.listing.isBusinessTravelReady() || !this.allowBusinessTravelReady) {
            i = 0;
        } else {
            i = C0716R.string.home_card_china_tag_business_travel;
        }
        homeCard.setFirstTagText(i);
        if (chineseSpeakingHost) {
            i2 = C0716R.string.home_card_china_tag_chinese_speaker;
        } else {
            i2 = 0;
        }
        homeCard.setSecondTagText(i2);
        if (this.fullyRefundable) {
            i3 = C0716R.string.home_card_china_tag_fully_refundable;
        } else {
            i3 = 0;
        }
        homeCard.setThirdTagText(i3);
        String priceText = this.pricingQuote == null ? null : SearchUtil.getPriceTagText(this.pricingQuote, this.allowTotalPrice);
        String singlelinePriceRateTypeText = this.pricingQuote == null ? null : SearchUtil.getSinglelinePriceRateTypeText(context, this.pricingQuote, this.allowTotalPrice);
        if (TextUtils.isEmpty(singlelinePriceRateTypeText)) {
            priceText = priceText + context.getString(C0716R.string.home_card_china_rate_per_night);
        }
        homeCard.setPriceText(priceText, singlelinePriceRateTypeText);
        if (this.pricingQuote == null || !this.pricingQuote.isInstantBookable()) {
            z = false;
        } else {
            z = true;
        }
        homeCard.setInstantBookAvailable(z);
        homeCard.setRating(this.listing.getStarRating(), this.listing.getReviewsCount());
        if (this.listing.getReviewsCount() > 0) {
            homeCard.setReviewsText(this.listing.getNumReviewsText(homeCard.getContext()));
        } else if (this.listing.isIsNewListing() == null || !this.listing.isIsNewListing().booleanValue()) {
            homeCard.setReviewsText(null);
        } else {
            homeCard.setReviewsText(context.getString(C0716R.string.new_home));
        }
        homeCard.setListingTitleText(this.listing.getName());
        String detailsText = makeDetailsText(this.listing, context);
        if (this.listing.getAmenities() == null || !this.listing.getAmenities().contains(Amenity.WirelessInternet)) {
            hasWifi = false;
        } else {
            hasWifi = true;
        }
        if (hasWifi) {
            i4 = C0716R.C0717drawable.n2_ic_home_card_wifi;
        } else {
            i4 = 0;
        }
        homeCard.setListingDetails(detailsText, i4);
        String hostThumbnailUrl = this.listing.getHostThumbnailUrl();
        if (this.listing.isSuperHosted()) {
            i5 = C0716R.C0717drawable.sh_badge;
        }
        homeCard.setHostAvatar(hostThumbnailUrl, i5);
        homeCard.setOnClickListener(this.clickListener);
        if (this.wishListableData != null) {
            homeCard.setWishListHeartInterface(new WishListHeartController(context, this.wishListableData));
        } else {
            homeCard.clearWishListHeartInterface();
        }
        homeCard.setTransitionTypeId(this.listing == null ? 0 : this.listing.getId());
    }

    public void unbind(HomeCardChina homeCard) {
        homeCard.clearWishListHeartInterface();
        homeCard.clearImages();
        homeCard.clearTags();
        homeCard.setOnClickListener(null);
    }

    public HomeCardChinaEpoxyModel listing(Listing listing2) {
        this.listing = listing2;
        mo11716id(listing2.getId());
        return this;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
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

    private static String makeDetailsText(Listing listing2, Context context) {
        SpaceType spaceType = listing2.getSpaceType();
        StringBuilder sb = new StringBuilder();
        sb.append(spaceType != null ? context.getString(spaceType.titleId) + " · " : "");
        String bathRooms = listing2.getBathrooms() < 1.0f ? "0.5" : ((int) listing2.getBathrooms()) + "";
        if (listing2.getBedrooms() > 0) {
            sb.append(context.getString(C0716R.string.home_card_china_listing_details_format, new Object[]{Integer.valueOf(listing2.getBedrooms()), Integer.valueOf(listing2.getBedCount()), bathRooms}));
        } else {
            sb.append(context.getString(C0716R.string.home_card_china_listing_details_studio_format, new Object[]{Integer.valueOf(listing2.getBedCount()), bathRooms}));
        }
        return sb.toString();
    }
}
