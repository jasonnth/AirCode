package com.airbnb.android.p011p3;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.ListingTrayItem;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.models.CancellationRefundBanner;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.core.models.UrgencyMessage;
import com.airbnb.android.core.models.UrgencyMessageData;
import com.airbnb.android.core.models.UserFlag;
import com.airbnb.android.core.p009p3.P3State;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.ListingUtils;
import com.airbnb.android.core.utils.PricingDisclaimerModelUtils;
import com.airbnb.android.core.utils.SearchPricingUtil;
import com.airbnb.android.core.viewcomponents.models.BedDetailsTrayEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.HomeAmenitiesEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.HomeHighlightsEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.HomeMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListingDescriptionEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListingDetailsSummaryEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListingsTrayEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.P3ReviewsRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.UrgencyEpoxyModel_;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.p027n2.primitives.AirmojiEnum;
import com.airbnb.p027n2.utils.MapOptions;
import com.airbnb.p027n2.utils.MapOptions.CircleOptions;
import java.util.Collection;
import java.util.List;

/* renamed from: com.airbnb.android.p3.P3EpoxyController */
public class P3EpoxyController extends BaseP3EpoxyController {
    private static final int MAP_CIRCLE_RADIUS_METERS = 800;
    private static final int MAP_ZOOM_LEVEL = 14;
    StandardRowEpoxyModel_ additionalPricesModel;
    StandardRowEpoxyModel_ availabilityCalendarModel;
    BedDetailsTrayEpoxyModel_ bedDetailsModel;
    StandardRowEpoxyModel_ businessDetailsModel;
    StandardRowEpoxyModel_ cancellationModel;
    StandardRowEpoxyModel_ checkInTimeModel;
    StandardRowEpoxyModel_ checkOutTimeModel;
    StandardRowEpoxyModel_ contactHostModel;
    StandardRowEpoxyModel_ houseRulesModel;
    P3ReviewsRowEpoxyModel_ largeReviewRowModel;
    HomeAmenitiesEpoxyModel_ listingAmenitiesModel;
    ListingDescriptionEpoxyModel_ listingDescriptionModel;
    ListingDetailsSummaryEpoxyModel_ listingDetailsSummaryModel;
    HomeHighlightsEpoxyModel_ listingHighlightsModel;
    P3MapInterstitialModel_ mapInterstitialModel;
    HomeMarqueeEpoxyModel_ marqueeModel;
    MicroRowEpoxyModel_ minNightsAndPetsModel;
    StandardRowEpoxyModel_ refundPolicyModel;
    EpoxyControllerLoadingModel_ reportListingLoader;
    StandardRowEpoxyModel_ reportListingModel;
    ListingsTrayEpoxyModel_ similarListingsModel;
    StandardRowEpoxyModel_ superHostModel;
    UrgencyEpoxyModel_ urgencyModel;
    StandardRowEpoxyModel_ viewGuidebookModel;

    public P3EpoxyController(Context context, ListingDetailsController controller, BusinessTravelAccountManager businessTravelAccountManager, Bundle savedInstanceState) {
        super(context, controller, businessTravelAccountManager, savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        if (this.controller.getState() != null && this.controller.getState().listing() != null) {
            P3State state = this.controller.getState();
            Listing listing = state.listing();
            PricingQuote pricingQuote = state.pricingQuote();
            boolean hasLoadedFullListing = !TextUtils.isEmpty(listing.getSummary()) || !TextUtils.isEmpty(listing.getSpace());
            HomeMarqueeEpoxyModel_ homeMarqueeEpoxyModel_ = this.marqueeModel;
            ListingDetailsController listingDetailsController = this.controller;
            listingDetailsController.getClass();
            homeMarqueeEpoxyModel_.imageCarouselClickListener(P3EpoxyController$$Lambda$1.lambdaFactory$(listingDetailsController)).reviewsClickListener(P3EpoxyController$$Lambda$2.lambdaFactory$(this)).snapToPositionListener(P3EpoxyController$$Lambda$3.lambdaFactory$(this)).listing(listing).title(state.showTranslatedSections() ? state.translatedSectionedListingDescription().getName() : listing.getName());
            this.listingDetailsSummaryModel.hostImageClickListener(P3EpoxyController$$Lambda$4.lambdaFactory$(this)).listing(listing).businessReady(listing.isBusinessTravelReady() && this.businessTravelAccountManager.isVerifiedBusinessTraveler() && !Experiments.showBusinessTravelInP3Summary());
            UrgencyMessageData urgencyData = this.controller.getUrgencyMessage();
            if (urgencyData != null) {
                UrgencyMessage message = urgencyData.getMessage();
                this.urgencyModel.clickListener(urgencyData.hasContextualMessage() ? P3EpoxyController$$Lambda$5.lambdaFactory$(this, message) : null).title(message.getHeadline()).subtitle(message.getBody()).type(urgencyData.getType()).onBind(P3EpoxyController$$Lambda$6.lambdaFactory$(this, urgencyData));
            }
            if (SearchPricingUtil.isTotalPricingEnabled()) {
                PricingDisclaimerModelUtils.buildPricingDisclaimerModel(pricingQuote != null && pricingQuote.hasDates(), true).addTo(this);
            } else if (SearchPricingUtil.isIncludingServiceFeeRequired()) {
                PricingDisclaimerModelUtils.buildServiceFeeDisclaimerModel(pricingQuote != null && pricingQuote.hasDates()).addTo(this);
            }
            this.listingHighlightsModel.listing(listing).addTo(this);
            this.listingDescriptionModel.readMoreClickListener(P3EpoxyController$$Lambda$7.lambdaFactory$(this)).translateClickListener(P3EpoxyController$$Lambda$8.lambdaFactory$(this)).isTranslatable(ListingUtils.needsTranslation(listing)).summary(listing.getSummary()).summaryHighlight(getSummaryHighlight(listing)).space(listing.getSpace()).descriptionLocale(listing.getDescriptionLocale());
            if (hasLoadedFullListing) {
                this.listingDescriptionModel.translatedText(state.showTranslatedSections() ? state.translatedSectionedListingDescription().getDescription() : null);
            }
            this.bedDetailsModel.titleRes(C7532R.string.bed_details_section_header).rooms(listing.getSortedRooms()).addIf(!listing.getSortedRooms().isEmpty(), (EpoxyController) this);
            this.minNightsAndPetsModel.text(getMinNightsAndPetsRowText(listing)).addIf(listing.getMinNights() > 0, (EpoxyController) this);
            this.listingAmenitiesModel.titleRes(C7532R.string.amenities).clickListener(P3EpoxyController$$Lambda$9.lambdaFactory$(this)).amenities(listing.getAmenities()).addIf(!listing.getAmenities().isEmpty(), (EpoxyController) this);
            this.mapInterstitialModel.clickListener(P3EpoxyController$$Lambda$10.lambdaFactory$(this)).mapOptions(MapOptions.builder(AppLaunchUtils.isUserInChina()).center(listing.getLatLng()).circle(CircleOptions.create(listing.getLatLng(), 800)).zoom(14).build()).title(listing.getPublicAddress()).subtitle(listing.getNeighborhood()).addIf(hasLoadedFullListing, (EpoxyController) this);
            String checkIn = listing.getLocalizedCheckInTimeWindow();
            this.checkInTimeModel.title(C7532R.string.check_in_time).infoText((CharSequence) checkIn).addIf(!TextUtils.isEmpty(checkIn), (EpoxyController) this);
            String checkOut = listing.getLocalizedCheckOutTime();
            this.checkOutTimeModel.title(C7532R.string.check_out_time).infoText((CharSequence) checkOut).addIf(!TextUtils.isEmpty(checkOut), (EpoxyController) this);
            if (hasLoadedFullListing) {
                setUpAndAddReviewRow(this.largeReviewRowModel);
            }
            this.viewGuidebookModel.title(C7532R.string.guidebook_view_guidebook).actionText(C7532R.string.read).clickListener(P3EpoxyController$$Lambda$11.lambdaFactory$(this)).addIf(this.controller.hasGuidebook(), (EpoxyController) this);
            if (listing.getPrimaryHost() != null) {
                this.superHostModel.title((CharSequence) this.context.getString(C7532R.string.user_is_a_superhost, new Object[]{listing.getPrimaryHost().getName()})).rowDrawableRes(C7532R.C7533drawable.sh_badge).subtitle(C7532R.string.superhost_guest_view_description).clickListener(P3EpoxyController$$Lambda$12.lambdaFactory$(this)).addIf(listing.getPrimaryHost().isSuperhost(), (EpoxyController) this);
            }
            this.houseRulesModel.title(C7532R.string.house_rules).actionText(C7532R.string.read).clickListener(P3EpoxyController$$Lambda$13.lambdaFactory$(this)).addIf(listing.hasHouseRules(), (EpoxyController) this);
            this.cancellationModel.title(C7532R.string.p3_cancellation_policy_text).clickListener(P3EpoxyController$$Lambda$14.lambdaFactory$(this)).actionText((CharSequence) getCancellationPolicy(state)).addIf(!TextUtils.isEmpty(state.cancellationPolicyLabel()), (EpoxyController) this);
            CancellationRefundBanner cancellationRefundBanner = pricingQuote != null ? pricingQuote.getP3CancellationRefundBanner() : null;
            if (cancellationRefundBanner != null && cancellationRefundBanner.isShowBanner()) {
                this.refundPolicyModel.title((CharSequence) cancellationRefundBanner.getPlainTitle()).subtitle((CharSequence) "\n" + cancellationRefundBanner.getPlainBody()).subTitleMaxLine(Integer.MAX_VALUE).fullWidthSubtitle(true);
            }
            this.additionalPricesModel.title(C7532R.string.additional_prices).clickListener(P3EpoxyController$$Lambda$15.lambdaFactory$(this)).actionText(C7532R.string.see);
            this.availabilityCalendarModel.title(C7532R.string.availability).actionText(C7532R.string.check).clickListener(P3EpoxyController$$Lambda$16.lambdaFactory$(this));
            this.contactHostModel.title(C7532R.string.contact_host).actionText(C7532R.string.message).clickListener(P3EpoxyController$$Lambda$17.lambdaFactory$(this)).addIf(!ChinaUtils.enableExploreBookButtonOptimization(this.context), (EpoxyController) this);
            if (state.isFetchingReportedListingStatus()) {
                add((EpoxyModel<?>) this.reportListingLoader);
            } else {
                UserFlag flag = listing.getUserFlag();
                if (flag == null || flag.isRedacted()) {
                    this.reportListingModel.actionText(C7532R.string.report_listing_row_action_text).clickListener(P3EpoxyController$$Lambda$18.lambdaFactory$(this));
                }
                this.reportListingModel.title(C7532R.string.report_listing_row_text);
            }
            this.businessDetailsModel.title(C7532R.string.business_details_title).clickListener(P3EpoxyController$$Lambda$19.lambdaFactory$(this)).addIf(listing.getCommercialHostInfo() != null, (EpoxyController) this);
            addSimilarListings(state);
        }
    }

    static /* synthetic */ void lambda$buildModels$1(P3EpoxyController p3EpoxyController, int position, boolean swipedLeft, boolean autoScroll) {
        if (!autoScroll) {
            p3EpoxyController.controller.getAnalytics().trackCarouselSwipe(swipedLeft ? BaseAnalytics.SWIPE_LEFT : BaseAnalytics.SWIPE_RIGHT);
        }
    }

    private String getSummaryHighlight(Listing listing) {
        if (!this.businessTravelAccountManager.isVerifiedBusinessTraveler() || !listing.isBusinessTravelReady() || !Experiments.showBusinessTravelInP3Summary()) {
            return null;
        }
        return AirmojiEnum.AIRMOJI_CORE_BUSINESS_TRAVEL_READY.character + " " + this.context.getString(C7532R.string.home_is_business_travel_ready);
    }

    private void addSimilarListings(P3State state) {
        boolean hasPricingQuoteLoaded;
        boolean hasSimilarListings;
        boolean showUpsell;
        int i = 0;
        List<SimilarListing> similarListings = state.similarListings();
        if (state.pricingQuote() != null) {
            hasPricingQuoteLoaded = true;
        } else {
            hasPricingQuoteLoaded = false;
        }
        if (!ListUtils.isEmpty((Collection<?>) similarListings)) {
            hasSimilarListings = true;
        } else {
            hasSimilarListings = false;
        }
        if (hasSimilarListings && hasPricingQuoteLoaded) {
            if (!InstantBookUpsellUtils.isReservationRequestToBook(state) || !InstantBookUpsellUtils.areAllSimilarListingsInstantBookable(similarListings)) {
                showUpsell = false;
            } else {
                showUpsell = true;
            }
            ListingsTrayEpoxyModel_ titleRes = this.similarListingsModel.titleRes(showUpsell ? C7532R.string.similar_listings_ib_upsell : C7532R.string.similar_homes);
            if (showUpsell) {
                i = C7532R.string.similar_listings_rtb_label;
            }
            titleRes.subtitleRes(i).items(ListingTrayItem.fromSimilarListings(similarListings, C2813WishlistSource.HomeDetail, true, state.searchSessionId(), state.guestDetails(), state.checkInDate(), state.checkOutDate())).snapToPositionListener(P3EpoxyController$$Lambda$20.lambdaFactory$(this)).carouselItemClickListener(P3EpoxyController$$Lambda$21.lambdaFactory$(this)).onBind(P3EpoxyController$$Lambda$22.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$addSimilarListings$18(P3EpoxyController p3EpoxyController, int position, boolean userSwipedLeft, boolean autoScroll) {
        p3EpoxyController.controller.getAnalytics().trackSimilarListingsSwipe(userSwipedLeft ? BaseAnalytics.SWIPE_LEFT : BaseAnalytics.SWIPE_RIGHT);
    }

    static /* synthetic */ void lambda$addSimilarListings$19(P3EpoxyController p3EpoxyController, View view, ListingTrayItem item) {
        p3EpoxyController.controller.launchP3ForSimilarListing(view, item.listing, item.price);
        p3EpoxyController.controller.getAnalytics().trackSimilarListingsClick(item.listing.getId());
    }

    private static String getCancellationPolicy(P3State state) {
        String str = null;
        PricingQuote pricingQuote = state.pricingQuote();
        String policyNameFromPricingQuote = pricingQuote != null ? pricingQuote.getLocalizedCancellationPolicyName() : null;
        if (!TextUtils.isEmpty(policyNameFromPricingQuote)) {
            return policyNameFromPricingQuote;
        }
        Listing listing = state.listing();
        if (listing != null) {
            str = listing.getCancellationPolicy();
        }
        return str;
    }

    private String getMinNightsAndPetsRowText(Listing listing) {
        Resources resources = this.context.getResources();
        StringBuilder builder = new StringBuilder(resources.getQuantityString(C7532R.plurals.x_nights_min, listing.getMinNights(), new Object[]{Integer.valueOf(listing.getMinNights())}));
        if (listing.hasPets()) {
            builder.append(resources.getString(C7532R.string.bullet_with_space)).append(resources.getString(C7532R.string.amenity_has_pets));
        }
        return builder.toString();
    }

    /* access modifiers changed from: protected */
    public String getTagForModelId(long id) {
        if (id == this.marqueeModel.mo11715id() || id == this.listingHighlightsModel.mo11715id() || id == this.listingDetailsSummaryModel.mo11715id()) {
            return "listing_highlights";
        }
        if (id == this.listingDescriptionModel.mo11715id()) {
            return "listing_description";
        }
        if (id == PricingDisclaimerModelUtils.getModelId()) {
            return "pricing_disclaimer";
        }
        if (id == this.urgencyModel.mo11715id()) {
            return "urgency_message";
        }
        if (id == this.minNightsAndPetsModel.mo11715id()) {
            return "min_nights_requirement";
        }
        if (id == this.listingAmenitiesModel.mo11715id()) {
            return "amenities";
        }
        if (id == this.mapInterstitialModel.mo11715id()) {
            return P3Arguments.FROM_MAP;
        }
        if (id == this.checkInTimeModel.mo11715id() || id == this.checkOutTimeModel.mo11715id()) {
            return "check_in_out_time";
        }
        if (id == this.largeReviewRowModel.mo11715id()) {
            return VerificationsAdapter.VERIFICATION_REVIEWS;
        }
        if (id == this.viewGuidebookModel.mo11715id()) {
            return "guidebook";
        }
        if (id == this.superHostModel.mo11715id()) {
            return "superhost";
        }
        if (id == this.houseRulesModel.mo11715id()) {
            return ListingRequestConstants.JSON_HOUSE_RULES_KEY;
        }
        if (id == this.cancellationModel.mo11715id()) {
            return ListingRequestConstants.JSON_CANCELLATION_KEY;
        }
        if (id == this.refundPolicyModel.mo11715id()) {
            return "fully_refundable";
        }
        if (id == this.additionalPricesModel.mo11715id()) {
            return "additional_prices";
        }
        if (id == this.availabilityCalendarModel.mo11715id()) {
            return "availability_calendar";
        }
        if (id == this.contactHostModel.mo11715id()) {
            return "contact_host";
        }
        if (id == this.businessDetailsModel.mo11715id()) {
            return "business_details";
        }
        if (id == this.reportListingModel.mo11715id() || id == this.reportListingLoader.mo11715id()) {
            return "report_listing";
        }
        if (id == this.similarListingsModel.mo11715id()) {
            return P3Arguments.FROM_SIMILAR_LISTINGS;
        }
        if (id == this.bedDetailsModel.mo11715id()) {
            return "bed_details";
        }
        return null;
    }
}
