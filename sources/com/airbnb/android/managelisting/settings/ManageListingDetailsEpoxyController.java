package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.view.View;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.HomeCollectionApplication;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.EventScheduleInterstitialEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ImpactDisplayCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InterstitialEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowWithLabelEpoxyModel_;
import com.airbnb.android.listing.utils.CollectionsTextUtils;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.epoxy.EpoxyController;
import com.google.common.collect.FluentIterable;

public class ManageListingDetailsEpoxyController extends ManageListingEpoxyController {
    private static final int SUBTITLE_MAX_LINE_COUNT = 2;
    StandardRowEpoxyModel_ amenitiesRow;
    StandardRowWithLabelEpoxyModel_ checkInGuideRow;
    private final Context context;
    StandardRowEpoxyModel_ descriptionSettingsRow;
    StandardRowEpoxyModel_ directionsRow;
    StandardRowEpoxyModel_ extraServicesRow;
    private final String firstName;
    SectionHeaderEpoxyModel_ guestResourcesTitleRow;
    SectionHeaderEpoxyModel_ header;
    StandardRowEpoxyModel_ houseManualRow;
    ImpactDisplayCardEpoxyModel_ imageRow;
    private boolean loading;
    EpoxyControllerLoadingModel_ loadingRow;
    StandardRowEpoxyModel_ locationRow;
    StandardRowEpoxyModel_ roomsAndGuestsRow;
    InterstitialEpoxyModel_ selectBanner;
    EventScheduleInterstitialEpoxyModel_ selectScheduleBanner;
    StandardRowEpoxyModel_ selectSummaryRow;
    StandardRowEpoxyModel_ selfCheckInRow;
    SharedPrefsHelper sharedPrefsHelper;
    StandardRowWithLabelEpoxyModel_ titleRow;
    StandardRowEpoxyModel_ wifiRow;

    ManageListingDetailsEpoxyController(Context context2, ManageListingDataController controller, String firstName2) {
        super(controller);
        ((ManageListingGraph) CoreApplication.instance(context2).component()).inject(this);
        this.context = context2;
        this.firstName = firstName2;
        controller.addListener(this);
    }

    public void dataLoading(boolean loading2) {
        this.loading = loading2;
        requestModelBuild();
    }

    public void dataUpdated() {
        this.loading = false;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        if (this.loading) {
            this.loadingRow.addTo(this);
            return;
        }
        Listing listing = this.controller.getListing();
        if (listing != null) {
            addCollectionsBannerIfNeeded();
            this.header.titleRes(C7368R.string.manage_listing_details_settings_page_title).descriptionRes(C7368R.string.manage_listing_details_settings_page_subtitle).buttonTextRes(C7368R.string.manage_listing_booking_preview_button).buttonOnClickListener(ManageListingDetailsEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
            Photo coverPhoto = (Photo) FluentIterable.from((Iterable<E>) listing.getSortedPhotos()).first().orNull();
            this.imageRow.imageUrl(coverPhoto != null ? coverPhoto.getLargeUrl() : null).title(this.context.getResources().getQuantityString(C7368R.plurals.photos, listing.getPhotos().size(), new Object[]{Integer.valueOf(listing.getPhotos().size())})).clickListener(ManageListingDetailsEpoxyController$$Lambda$2.lambdaFactory$(this)).addTo(this);
            addTitleRow();
            if (this.controller.shouldShowSelectML()) {
                this.selectSummaryRow.title(C7368R.string.manage_listing_description_settings_description_title).subtitle((CharSequence) this.controller.getSelectListing().getSummary()).actionText(ListingTextUtils.getActionTextForTextSetting(this.controller.getSelectListing().getSummary())).subTitleMaxLine(2).clickListener(ManageListingDetailsEpoxyController$$Lambda$3.lambdaFactory$(this));
            } else {
                this.descriptionSettingsRow.title(C7368R.string.manage_listing_details_item_description).clickListener(ManageListingDetailsEpoxyController$$Lambda$4.lambdaFactory$(this)).disclosure().addTo(this);
            }
            this.roomsAndGuestsRow.title(C7368R.string.manage_listing_details_item_rooms_guests).subtitle((CharSequence) getRoomsAndGuestsString(this.context, listing)).clickListener(ManageListingDetailsEpoxyController$$Lambda$5.lambdaFactory$(this)).disclosure().addTo(this);
            this.amenitiesRow.title(C7368R.string.manage_listing_details_item_amenities).clickListener(ManageListingDetailsEpoxyController$$Lambda$6.lambdaFactory$(this)).disclosure().addTo(this);
            this.extraServicesRow.title(C7368R.string.paid_amenities_host_manage_listing_entry_title_text).clickListener(ManageListingDetailsEpoxyController$$Lambda$7.lambdaFactory$(this)).disclosure().addIf(Trebuchet.launch(TrebuchetKeys.PAID_AMENITIES_HOST, false), (EpoxyController) this);
            this.locationRow.title(C7368R.string.manage_listing_details_item_location).clickListener(ManageListingDetailsEpoxyController$$Lambda$8.lambdaFactory$(this)).disclosure().addTo(this);
            this.guestResourcesTitleRow.titleRes(C7368R.string.manage_listing_details_item_guest_resources_title).descriptionRes(C7368R.string.manage_listing_details_item_guest_resources_subtitle).addTo(this);
            this.wifiRow.title(C7368R.string.manage_listing_details_item_wifi).clickListener(ManageListingDetailsEpoxyController$$Lambda$9.lambdaFactory$(this)).disclosure().addTo(this);
            if (FeatureToggles.showHostCheckinGuideTool()) {
                this.checkInGuideRow.title(C7368R.string.manage_listing_details_item_check_in_instructions).clickListener(ManageListingDetailsEpoxyController$$Lambda$10.lambdaFactory$(this)).label(ListingTextUtils.getCheckInGuideLabelRes(listing)).labelBackgroundRes(C7368R.C7369drawable.n2_label_background_small_babu).disclosure().addTo(this);
            } else {
                this.selfCheckInRow.title(ListingTextUtils.getSelfCheckinRowTitle(this.controller.getCheckInInformation())).clickListener(ManageListingDetailsEpoxyController$$Lambda$11.lambdaFactory$(this)).disclosure().addTo(this);
            }
            this.houseManualRow.title(C7368R.string.manage_listing_details_item_house_manual).clickListener(ManageListingDetailsEpoxyController$$Lambda$12.lambdaFactory$(this)).actionText(ListingTextUtils.getActionTextForTextSetting(listing.getHouseManual())).addTo(this);
            this.directionsRow.title(C7368R.string.manage_listing_details_item_direction).clickListener(ManageListingDetailsEpoxyController$$Lambda$13.lambdaFactory$(this)).actionText(ListingTextUtils.getActionTextForTextSetting(listing.getDirections())).addTo(this);
        }
    }

    private void addTitleRow() {
        Listing listing = this.controller.getListing();
        this.titleRow.title(C7368R.string.manage_listing_details_item_title).subTitleMaxLine(2);
        if (this.controller.shouldShowSelectML()) {
            this.titleRow.label(CollectionsTextUtils.getCollectionsStatusLabel(this.controller.getCollectionApplication())).subtitle((CharSequence) this.controller.getSelectListing().getName()).labelBackgroundRes(C7368R.C7369drawable.n2_label_background_small_hackberry);
        } else {
            this.titleRow.clickListener(ManageListingDetailsEpoxyController$$Lambda$14.lambdaFactory$(this)).subtitle((CharSequence) listing.getName()).actionText(ListingTextUtils.getActionTextForTextSetting(listing.getName()));
        }
        this.titleRow.addTo(this);
    }

    private void addCollectionsBannerIfNeeded() {
        HomeCollectionApplication application = this.controller.getCollectionApplication();
        if (application != null) {
            String titleText = this.firstName + ",";
            switch (application.getStatus()) {
                case 0:
                case 4:
                    addApplicationStatusBanner(titleText, application);
                    return;
                case 2:
                    getInpectionScheduledBanner(titleText, application);
                    return;
                case 5:
                    if (!FeatureToggles.enableSelectML()) {
                        int message = CollectionsTextUtils.getGoToWebBannerCaptionText(this.controller.getListing().getReadyForSelectStatusEnum());
                        if (message != 0) {
                            addGoToWebBanner(message);
                            return;
                        }
                        return;
                    } else if (this.controller.isInSelectLimboState()) {
                        addSelectSwitcherBanner();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private void addApplicationStatusBanner(String titleText, HomeCollectionApplication application) {
        this.selectBanner.text(titleText).captionRes(CollectionsTextUtils.getStatusCaptionText(application)).buttonTextRes(CollectionsTextUtils.getStatusButtonText(application)).buttonClickListener(ManageListingDetailsEpoxyController$$Lambda$15.lambdaFactory$(this)).jellyFishPallete(3).withPadding(false).addTo(this);
    }

    private void getInpectionScheduledBanner(String titleText, HomeCollectionApplication application) {
        this.selectScheduleBanner.headerText(titleText).titleTextRes(C7368R.string.collections_listing_scheduled_inspection_title).dateTimeText(CollectionsTextUtils.getInspectionBookingDateTimeText(this.context, application)).addressText(CollectionsTextUtils.getInspectionBookingAddressText(this.context, application)).addTo(this);
    }

    private void addGoToWebBanner(int message) {
        boolean z = true;
        InterstitialEpoxyModel_ withPadding = this.selectBanner.text(CollectionsTextUtils.getBeloAsString(this.context)).captionRes(message).buttonTextRes(C7368R.string.collections_listing_not_ready_action).buttonClickListener(ManageListingDetailsEpoxyController$$Lambda$16.lambdaFactory$(this)).jellyFishPallete(1).withPadding(false);
        if (this.sharedPrefsHelper.hasDismissedSelectNotReadyBanner(this.controller.getListingId())) {
            z = false;
        }
        withPadding.addIf(z, (EpoxyController) this);
    }

    static /* synthetic */ void lambda$addGoToWebBanner$15(ManageListingDetailsEpoxyController manageListingDetailsEpoxyController, View v) {
        manageListingDetailsEpoxyController.sharedPrefsHelper.dismisSelectNotReadyBanner(manageListingDetailsEpoxyController.controller.getListingId());
        manageListingDetailsEpoxyController.requestModelBuild();
    }

    private void addSelectSwitcherBanner() {
        boolean selectMode = this.controller.shouldShowSelectML();
        this.selectBanner.text(CollectionsTextUtils.getSwitcherBannerTitle(this.context, selectMode)).caption(CollectionsTextUtils.getSwitcherBannerMessage(this.context, selectMode)).buttonTextRes(CollectionsTextUtils.getSwitcherBannerActionText(this.context, selectMode)).buttonClickListener(ManageListingDetailsEpoxyController$$Lambda$17.lambdaFactory$(this, selectMode)).jellyFishPallete(selectMode ? 3 : 1).withPadding(false).addTo(this);
    }

    static /* synthetic */ void lambda$addSelectSwitcherBanner$16(ManageListingDetailsEpoxyController manageListingDetailsEpoxyController, boolean selectMode, View v) {
        manageListingDetailsEpoxyController.controller.setShouldShowSelect(!selectMode);
    }

    private static String getRoomsAndGuestsString(Context context2, Listing listing) {
        String roomType = listing.getRoomType(context2);
        int bedrooms = listing.getBedrooms();
        int personCapacity = listing.getPersonCapacity();
        if (bedrooms == 0) {
            return context2.getString(C7368R.string.manage_listing_details_item_studio_guests_subtitle, new Object[]{roomType, Integer.valueOf(personCapacity)});
        }
        return context2.getString(C7368R.string.manage_listing_details_item_rooms_guests_subtitle, new Object[]{roomType, Integer.valueOf(bedrooms), Integer.valueOf(personCapacity)});
    }
}
