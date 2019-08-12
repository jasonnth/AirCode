package com.airbnb.android.managelisting.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.p000v4.util.Pair;
import android.view.Menu;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline.Event;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.enums.ListingRegistrationStatus;
import com.airbnb.android.core.enums.ListingStatus;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.CityRegistrationIntents;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.intents.ListYourSpaceIntents;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.intents.NestedListingsIntents;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.PaidAmenityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.BookingSettings;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequencyVersion;
import com.airbnb.android.core.models.HomeCollectionApplication;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.airbnb.android.core.models.ListingExpectation;
import com.airbnb.android.core.models.ListingLongTermDiscountValues;
import com.airbnb.android.core.models.ListingRegistration;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;
import com.airbnb.android.core.models.SelectListing;
import com.airbnb.android.core.models.SelectRoomDescription;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.BookingSettingsRequest;
import com.airbnb.android.core.requests.CalendarRulesRequest;
import com.airbnb.android.core.requests.CheckInInformationRequest;
import com.airbnb.android.core.requests.CheckInTermsRequest;
import com.airbnb.android.core.requests.GetSelectListingRequest;
import com.airbnb.android.core.requests.GetSelectRoomDescriptionsRequest;
import com.airbnb.android.core.requests.HomesCollectionsApplicationsRequest;
import com.airbnb.android.core.requests.ListingRegistrationProcessesRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.requests.ListingRoomsRequest;
import com.airbnb.android.core.requests.LongTermDiscountsConversionRequest;
import com.airbnb.android.core.requests.NestedListingsRequest;
import com.airbnb.android.core.requests.UserRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.BookingSettingsResponse;
import com.airbnb.android.core.responses.CalendarRulesResponse;
import com.airbnb.android.core.responses.HomesCollectionsApplicationsResponse;
import com.airbnb.android.core.responses.ListingCheckInInformationResponse;
import com.airbnb.android.core.responses.ListingCheckInOptionsResponse;
import com.airbnb.android.core.responses.ListingRegistrationProcessesResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.ListingRoomsResponse;
import com.airbnb.android.core.responses.LongTermDiscountsConversionResponse;
import com.airbnb.android.core.responses.NestedListingsResponse;
import com.airbnb.android.core.responses.SelectListingResponse;
import com.airbnb.android.core.responses.SelectRoomDescriptionsResponse;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.AirAddressUtil;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NavigationUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.listing.AmenityGroup;
import com.airbnb.android.listing.fragments.HouseRulesLegalInfoFragment;
import com.airbnb.android.listing.fragments.ListingHostingFrequencyInfoFragment;
import com.airbnb.android.listing.fragments.ListingSmartPricingTipFragment;
import com.airbnb.android.listing.fragments.ListingSmartPricingTipFragment.ListingSmartPriceTipListener;
import com.airbnb.android.listing.fragments.TipFragment;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.listing.utils.TextSetting;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.managelisting.analytics.CheckInJitneyLogger;
import com.airbnb.android.managelisting.settings.ManageListingSettingsFragment.ManageListingPage;
import com.airbnb.android.managelisting.settings.photos.ManagePhotosFragment;
import com.airbnb.android.managelisting.settings.photos.ManageSelectPhotosFragment;
import com.airbnb.android.managelisting.settings.photos.PhotoFragment;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.RefreshLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DlsManageListingActivity extends AirActivity implements ListingSmartPriceTipListener {
    private static final int ACTIVITY_REQUEST_CODE_CITY_REGISTRATION = 102;
    private static final int ACTIVITY_REQUEST_CODE_COHOST_MANAGEMENT = 101;
    private static final int ACTIVITY_REQUEST_CODE_EXPECTATIONS = 103;
    private static final int ACTIVITY_REQUEST_CODE_GENERIC = 100;
    private static final int ACTIVITY_REQUEST_CODE_NESTED_LISTINGS = 104;
    private static final String SETTINGS_TAB_FRAGMENT_TAG = "ml_settings_tabs";
    private final ManageListingActionExecutor actionExecutor = new ManageListingActionExecutor() {
        public void previewListing() {
            DlsManageListingActivity.this.startActivity(P3ActivityIntents.withListing(DlsManageListingActivity.this, DlsManageListingActivity.this.controller.getListing(), P3Arguments.FROM_MANAGE_LISTING_OR_LYS));
        }

        public void photos() {
            if (DlsManageListingActivity.this.controller.shouldShowSelectML()) {
                showFragment(ManageSelectPhotosFragment.create());
            } else {
                showFragment(ManagePhotosFragment.create());
            }
        }

        public void photo(long photoId) {
            showModal(PhotoFragment.create(photoId));
        }

        public void textSetting(TextSetting setting) {
            showModal(ManageListingTextSettingFragment.forListingField(setting));
        }

        public void collectionsLanding() {
            DlsManageListingActivity.this.startActivity(WebViewIntentBuilder.newBuilder(DlsManageListingActivity.this, DlsManageListingActivity.this.getString(C7368R.string.homes_collections_application_earlyaccess_url) + DlsManageListingActivity.this.controller.getListingId()).authenticate().toIntent());
        }

        public void description() {
            showFragment(ManageListingDescriptionSettingsFragment.create());
        }

        public void roomsAndGuests() {
            showFragment(ManageListingRoomsGuestsFragment.create());
        }

        public void bedDetails() {
            showFragment(ManageListingBedDetailsFragment.create());
        }

        public void singleRoomBedDetails(int roomNumber) {
            showModal(ManageListingRoomBedDetailsFragment.forRoom(roomNumber));
        }

        public void amenities() {
            if (FeatureToggles.shouldShowFamilyAmenitiesML() || FeatureToggles.shouldShowLocationAmenitiesML()) {
                showFragment(ManageListingAmenityListFragment.create());
            } else {
                showFragment(ManageListingAmenitiesFragment.createForAllAmenities());
            }
        }

        public void amenityGroupDetail(int titleRes, AmenityGroup amenityGroup) {
            showModal(ManageListingAmenitiesFragment.create(titleRes, amenityGroup, true));
        }

        public void location() {
            showFragment(ManageListingLocationFragment.create());
        }

        public void address() {
            showFragment(ManageListingEditAddressFragment.create());
        }

        public void exactLocation() {
            showFragment(ManageListingExactLocationFragment.create());
        }

        public void wifi() {
            showFragment(ManageListingWirelessInfoFragment.create());
        }

        public void smartCheckInInformation() {
            if (!FeatureToggles.showHostCheckinGuideTool()) {
                selfCheckin();
            } else if (DlsManageListingActivity.this.controller.getEnabledCheckInMethods().size() == 1) {
                checkInGuide();
            } else {
                checkInMethod();
            }
        }

        public void selfCheckin() {
            showFragment(ManageListingSelfCheckinMethodsFragment.create());
        }

        public void checkInMethod() {
            if (!tryPopToFragment(ManageListingAllCheckinMethodsFragment.class)) {
                showFragment(ManageListingAllCheckinMethodsFragment.create());
            }
        }

        public void checkInGuide() {
            if (!tryPopToFragment(ManageListingCheckInGuideFragment.class)) {
                showFragment(ManageListingCheckInGuideFragment.create());
            }
        }

        public void reorderCheckInSteps() {
            showFragment(ManageListingReorderCheckInStepsFragment.create());
        }

        public void publishedGuideConfirmation() {
            showFragment(ManageListingPublishGuideConfirmationSheetFragment.create());
        }

        public void checkInStep(int stepNum, long stepId) {
            showModal(CheckinNoteTextSettingFragment.forStep(stepNum, stepId));
        }

        public void editCheckinType(CheckInInformation checkinType) {
            showModal(ManageListingCheckinTypeTextSettingFragment.forCheckinInformation(checkinType, true));
        }

        public void editCodeForCheckinType(CheckInInformation checkinType) {
            showModal(ManageListingCheckinTypeTextSettingFragment.forCheckinInformation(checkinType, false));
        }

        public void instantBook() {
            showFragment(ManageListingInstantBookSettingsFragment.create());
        }

        public void instantBookTip() {
            showModal(ManageListingInstantBookTipFragment.create());
        }

        public void guestRequirements() {
            showFragment(ManageListingGuestRequirementsFragment.create());
        }

        public void additionalGuestRequirements() {
            showModal(ManageListingAdditionalGuestRequirementsFragment.create());
        }

        public void houseRules() {
            showFragment(ManageListingHouseRulesSettingsFragment.create());
        }

        public void guestExpectations() {
            DlsManageListingActivity.this.startActivityForResult(ReactNativeIntents.intentForHouseRulesAndExpectations(DlsManageListingActivity.this, DlsManageListingActivity.this.controller.getListing().getId(), DlsManageListingActivity.this.controller.getListing().getListingExpectations()), 103, ActivityOptionsCompat.makeSceneTransitionAnimation(DlsManageListingActivity.this, new Pair[0]).toBundle());
        }

        public void cancellationPolicy() {
            showFragment(ManageListingCancellationPolicyFragment.create());
        }

        public void nightlyPrice() {
            showFragment(ManageListingNightlyPriceSettingsFragment.create());
        }

        public void smartPricingTip(boolean fromSmartPricing) {
            showModal(ListingSmartPricingTipFragment.create(fromSmartPricing, false));
        }

        public void hostingFrequencyInfo(DesiredHostingFrequencyVersion version) {
            showModal(ListingHostingFrequencyInfoFragment.newInstance(version, false));
        }

        public void extraCharges() {
            showFragment(ManageListingFeesFragment.create());
        }

        public void currency() {
            showFragment(ManageListingCurrencyFragment.newInstance());
        }

        public void longTermDiscounts() {
            showFragment(ManageListingDiscountsSettingsFragment.create());
        }

        public void aboutLengthOfStayDiscounts() {
            showModal(TipFragment.builder(DlsManageListingActivity.this, NavigationTag.ManageListingAboutLengthOfStayDiscounts).addTitleRes(C7368R.string.manage_listing_about_length_of_stay_discount_title).addTextRes(C7368R.string.manage_listing_about_length_of_stay_discount_info).build());
        }

        public void aboutLastMinuteDiscounts() {
            showModal(TipFragment.builder(DlsManageListingActivity.this, NavigationTag.ManageListingAboutLengthOfStayDiscounts).addTitleRes(C7368R.string.manage_listings_about_last_minute_discount_title).addTextRes(C7368R.string.manage_listings_about_last_minute_discount_info).build());
        }

        public void aboutEarlyBirdDiscounts() {
            showModal(TipFragment.builder(DlsManageListingActivity.this, NavigationTag.ManageListingAboutLengthOfStayDiscounts).addTitleRes(C7368R.string.manage_listings_about_early_bird_discount_title).addTextRes(C7368R.string.manage_listings_about_early_bird_discount_info).build());
        }

        public void lastMinuteDiscounts() {
            showFragment(ManageListingLastMinuteDiscountFragment.create());
        }

        public void earlyBirdDiscounts() {
            showFragment(ManageListingEarlyBirdDiscountFragment.create());
        }

        public void priceTipsDisclaimer() {
            showPricingDisclaimerModal();
        }

        public void discountsExample() {
            showModal(DiscountsExampleFragment.newInstance());
        }

        public void calendarSettings() {
            showFragment(ManageListingCalendarSettingsFragment.create());
        }

        public void availabilityRules() {
            showFragment(ManageListingAvailabilitySettingsFragment.create());
        }

        public void calendarTip() {
            showModal(ManageListingCalendarTipFragment.create());
        }

        public void tripLength() {
            showFragment(ManageListingTripLengthFragment.create());
        }

        public void tripLengthSeasonalRequirement(SeasonalMinNightsCalendarSetting setting) {
            showFragment(ManageListingSeasonalCalendarSettingsFragment.create(setting));
        }

        public void checkInOut() {
            showFragment(ManageListingCheckInOutFragment.create());
        }

        public void nestedListings() {
            DlsManageListingActivity.this.startActivityForResult(NestedListingsIntents.intentWithNestedListings(DlsManageListingActivity.this, new ArrayList<>(DlsManageListingActivity.this.controller.getNestedListingsById().values())), 104);
        }

        public void localLaws() {
            showFragment(ManageListingLocalLawsFragment.create());
        }

        public void listingStatus() {
            showFragment(ManageListingStatusSettingFragment.create());
        }

        public void snoozeListing(int sourcePage) {
            switch (sourcePage) {
                case 0:
                    showFragment(ManageListingSnoozeSettingFragment.create(sourcePage));
                    return;
                case 1:
                case 2:
                    showFragmentWithinModal(ManageListingSnoozeSettingFragment.create(sourcePage));
                    return;
                default:
                    return;
            }
        }

        public void unlistReasons() {
            showModal(ManageListingUnlistingReasonsFragment.create());
        }

        public void unlistReason(int reason) {
            switch (reason) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    showFragmentWithinModal(ManageListingUnlistingReasonSheetFragment.forReason(reason));
                    return;
                case 6:
                    showFragmentWithinModal(ManageListingUnlistOtherReasonFragment.create());
                    return;
                default:
                    throw new RuntimeException("Unexpected reason value: " + Integer.toString(reason));
            }
        }

        public void listingDeleted() {
            DlsManageListingActivity.this.controller.setListingHasChanged();
            DlsManageListingActivity.this.finish();
        }

        public void extraService() {
            Listing listing = DlsManageListingActivity.this.controller.getListing();
            if (listing.hasPaidAmenities()) {
                DlsManageListingActivity.this.startActivityForResult(PaidAmenityIntents.hostAmenitiesIntent(DlsManageListingActivity.this, listing.getId()), PaidAmenityIntents.ACTIVITY_HOST_AMENITY_LIST);
            } else {
                DlsManageListingActivity.this.startActivityForResult(PaidAmenityIntents.createAmenitiesWithListingIdIntent(DlsManageListingActivity.this, listing.getId()), 100);
            }
        }

        public void invalidateData() {
            DlsManageListingActivity.this.fetchData();
        }

        public void licenseOrRegistrationNumber() {
            showFragment(ManageListingLicenseOrRegistrationNumberFragment.create());
        }

        public void cityRegistration() {
            if (Trebuchet.launch(TrebuchetKeys.LISTING_REGISTRATION_ANDROID_MODULE)) {
                Intent intent = CityRegistrationIntents.intent(DlsManageListingActivity.this, DlsManageListingActivity.this.controller.getListing(), DlsManageListingActivity.this.controller.getListingRegistrationProcess(), Boolean.valueOf(false), null);
                if (intent != null) {
                    DlsManageListingActivity.this.startActivityForResult(intent, 102);
                    return;
                }
            }
            ListingRegistrationProcess listingRegistrationProcess = DlsManageListingActivity.this.controller.getListingRegistrationProcess();
            if (listingRegistrationProcess != null && listingRegistrationProcess.isRegulatoryBodySupported()) {
                ListingRegistration listingRegistration = listingRegistrationProcess.getListingRegistration();
                if (listingRegistration == null || listingRegistration.getStatus() == ListingRegistrationStatus.Unregistered) {
                    showFragment(CityRegistrationOverviewFragment.create());
                } else if (listingRegistration.getStatus() == ListingRegistrationStatus.Exempted) {
                    showFragment(CityRegistrationReviewExemptedFragment.create());
                } else if (listingRegistration.getStatus().hasBeenSubmitted()) {
                    showFragment(CityRegistrationReviewFragment.create());
                }
            }
        }

        public void cityRegistrationExemption() {
            showFragment(CityRegistrationExemptionFragment.create());
        }

        public void cityRegistrationInputGroup(int groupIndex) {
            ListingRegistrationProcess process = DlsManageListingActivity.this.controller.getListingRegistrationProcess();
            if (process == null) {
                cityRegistrationApplication();
                return;
            }
            ListingRegistrationProcessInputGroup inputGroup = process.getInputGroupFromIndex(groupIndex);
            if (inputGroup != null) {
                showFragment(CityRegistrationInputGroupFragment.create(inputGroup, AirAddressUtil.fromListing(DlsManageListingActivity.this.controller.getListing()), groupIndex + 1));
            } else {
                cityRegistrationApplication();
            }
        }

        public void cityRegistrationApplication() {
            showFragment(CityRegistrationApplicationFragment.create());
        }

        public void cityRegistrationNextSteps() {
            showFragment(CityRegistrationNextStepsFragment.create());
        }

        public void cohosting() {
            DlsManageListingActivity.this.cohostingManagementJitneyLogger.logCohostsTabInManageListingSettingClickedForDlsML(DlsManageListingActivity.this.controller.getListingId());
            DlsManageListingActivity.this.startActivityForResult(CohostingIntents.intentForCohostManagementWithListing(DlsManageListingActivity.this, DlsManageListingActivity.this.controller.getListing()), 101);
        }

        public void popToHome() {
            popToFragment(DlsManageListingActivity.SETTINGS_TAB_FRAGMENT_TAG);
        }

        public void popToFragment(Class<? extends Fragment> clazz) {
            popToFragment(clazz.getCanonicalName());
        }

        public void preBookingQuestions() {
            showFragment(new ManageListingPreBookingQuestionsFragment());
        }

        public void preBookingMessagePreview() {
            showModal(new ManageListingPreBookingPreviewFragment());
        }

        public void preBookingAddQuestions() {
            showFragment(new ManageListingPreBookingAddQuestionsFragment());
        }

        public void instantBookUpsell() {
            if (InstantBookingAllowedCategory.fromKey(DlsManageListingActivity.this.controller.getListing().getInstantBookingAllowedCategory()) == InstantBookingAllowedCategory.Off) {
                showFragment(new ManageListingInstantBookUpsellFragment());
            } else {
                popToHome();
            }
        }

        public void houseRulesLegalInfo() {
            showModal(HouseRulesLegalInfoFragment.newInstance());
        }

        private boolean tryPopToFragment(Class<? extends Fragment> clazz) {
            return tryPopToFragment(clazz.getCanonicalName());
        }

        private void showPricingDisclaimerModal() {
            showModal(TipFragment.builder(DlsManageListingActivity.this, NavigationTag.ManageListingPricingDisclaimer).addTitleRes(C7368R.string.manage_listing_pricing_disclaimer_title).addText(ListingTextUtils.createPricingDisclaimer(DlsManageListingActivity.this, !PricingFeatureToggles.showSmartPricing(DlsManageListingActivity.this.controller.getListing()))).build());
        }

        private void showModal(Fragment fragment) {
            DlsManageListingActivity.this.showModal(fragment, C7368R.C7370id.content_container, C7368R.C7370id.modal_container, true, fragment.getClass().getCanonicalName());
        }

        private void showFragment(Fragment fragment) {
            showFragmentInContainer(fragment, C7368R.C7370id.content_container);
        }

        private void showFragmentWithinModal(Fragment fragment) {
            showFragmentInContainer(fragment, C7368R.C7370id.modal_container);
        }

        private void showFragmentInContainer(Fragment fragment, int fragmentContainerId) {
            DlsManageListingActivity.this.showFragment(fragment, fragmentContainerId, FragmentTransitionType.SlideInFromSide, true, fragment.getClass().getCanonicalName());
        }

        private void popToFragment(String tag) {
            NavigationUtils.popBackStackToFragment(DlsManageListingActivity.this.getSupportFragmentManager(), tag);
        }

        private boolean tryPopToFragment(String tag) {
            return NavigationUtils.tryPopBackStackToFragment(DlsManageListingActivity.this.getSupportFragmentManager(), tag);
        }
    };
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(DlsManageListingActivity$$Lambda$1.lambdaFactory$(this)).onError(DlsManageListingActivity$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    CheckInJitneyLogger checkInJitneyLogger;
    CohostingManagementJitneyLogger cohostingManagementJitneyLogger;
    /* access modifiers changed from: private */
    public ManageListingDataController controller;
    @BindView
    RefreshLoader fullLoader;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        PerformanceLoggerTimeline.start(Event.HOST_LISTING);
        this.controller = new ManageListingDataController(this, getIntent().getLongExtra("listing_id", -1), this.actionExecutor, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(C7368R.layout.activity_manage_listing);
        ButterKnife.bind((Activity) this);
        ((ManageListingGraph) CoreApplication.instance(this).component()).inject(this);
        if (savedInstanceState == null) {
            fetchData();
            if (showOnlyFullLoadingForSettingDeepLink()) {
                this.fullLoader.setVisibility(0);
            } else {
                showFragment(ManageListingSettingsTabFragment.create(), C7368R.C7370id.content_container, FragmentTransitionType.None, true, SETTINGS_TAB_FRAGMENT_TAG);
            }
        }
        if (hasSettingDeepLinkEarlyReturn()) {
            getSupportFragmentManager().addOnBackStackChangedListener(new OnBackStackChangedListener() {
                public void onBackStackChanged() {
                    if (DlsManageListingActivity.this.getSupportFragmentManager().getBackStackEntryCount() == 0) {
                        DlsManageListingActivity.this.finish();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    /* access modifiers changed from: private */
    public void responseFinished(AirBatchResponse batchResponse) {
        Listing listing = ((ListingResponse) batchResponse.singleResponse(ListingResponse.class)).listing;
        BookingSettings bookingSettings = ((BookingSettingsResponse) batchResponse.singleResponse(BookingSettingsResponse.class)).bookingSettings;
        CalendarRule calendarRule = ((CalendarRulesResponse) batchResponse.singleResponse(CalendarRulesResponse.class)).calendarRule;
        ListingLongTermDiscountValues averagePrices = ((LongTermDiscountsConversionResponse) batchResponse.singleResponse(LongTermDiscountsConversionResponse.class)).values;
        ListingCheckInTimeOptions checkInTimeOptions = ((ListingCheckInOptionsResponse) batchResponse.singleResponse(ListingCheckInOptionsResponse.class)).checkInTimeOptions;
        List<ListingRoom> listingRooms = ((ListingRoomsResponse) batchResponse.singleResponse(ListingRoomsResponse.class)).listingRooms;
        HomeCollectionApplication homeApplication = ((HomesCollectionsApplicationsResponse) batchResponse.singleResponse(HomesCollectionsApplicationsResponse.class)).getSingleResponse();
        List<CheckInInformation> checkInInformations = ((ListingCheckInInformationResponse) batchResponse.singleResponseOrNull(ListingCheckInInformationResponse.class)).checkInInformation;
        SelectListingResponse selectListingResponse = (SelectListingResponse) batchResponse.singleResponseOrNull(SelectListingResponse.class);
        SelectListing selectListing = selectListingResponse.selectListing;
        List<Integer> selectListingAmenities = selectListingResponse.metadata.getSelectAmenitiesIds();
        SelectRoomDescription roomsDescriptions = (SelectRoomDescription) ((SelectRoomDescriptionsResponse) batchResponse.singleResponse(SelectRoomDescriptionsResponse.class)).selectRoomDescriptions.get(0);
        Check.notNull(listing);
        Check.notNull(bookingSettings);
        Check.notNull(calendarRule);
        Check.notNull(averagePrices);
        Check.notNull(checkInTimeOptions);
        Check.notNull(listingRooms);
        Check.notNull(checkInInformations);
        Check.notNull(selectListing);
        Check.notNull(roomsDescriptions);
        listing.setListingExpectations(bookingSettings.getListingExpectations());
        listing.setBookingCustomQuestions(bookingSettings.getBookingCustomQuestions());
        listing.setBookingStandardQuestions(bookingSettings.getBookingStandardQuestions());
        ListingRegistrationProcess listingRegistrationProcess = null;
        if (FeatureToggles.isListingRegistrationEnabled()) {
            ArrayList<ListingRegistrationProcess> arrayList = ((ListingRegistrationProcessesResponse) batchResponse.singleResponse(ListingRegistrationProcessesResponse.class)).listingRegistrationProcesses;
            Check.notNull(arrayList);
            if (!arrayList.isEmpty()) {
                listingRegistrationProcess = (ListingRegistrationProcess) arrayList.get(0);
            }
        }
        boolean isEligibleForNestedListings = false;
        if (FeatureToggles.showNestedListings()) {
            isEligibleForNestedListings = ((UserResponse) batchResponse.singleResponse(UserResponse.class)).user.isEligibleForNestedListings();
        }
        HashMap<Long, NestedListing> nestedListingsById = null;
        if (FeatureToggles.isNestedListingEnabled(isEligibleForNestedListings)) {
            nestedListingsById = ((NestedListingsResponse) batchResponse.singleResponse(NestedListingsResponse.class)).getNestedListingsById();
            Check.notNull(nestedListingsById);
        }
        this.controller.setData(listing, calendarRule, averagePrices, listingRegistrationProcess, checkInTimeOptions, nestedListingsById, listingRooms, homeApplication, checkInInformations, selectListing, selectListingAmenities, roomsDescriptions);
        if (listing.getStatus() == ListingStatus.InProgress) {
            startActivity(ListYourSpaceIntents.intentForInProgressListing(this, listing.getId(), "ManageListingPicker", "ListingRow"));
            finish();
        }
        maybeShowDeepLinkSetting();
        PerformanceLoggerTimeline.logTimeToInteraction(Event.HOST_LISTING);
    }

    private boolean hasSettingDeepLinkEarlyReturn() {
        return getIntent().getExtras().getBoolean(ManageListingIntents.INTENT_EXTRA_SETTING_DEEP_LINK_EARLY_RETURN, false);
    }

    private boolean showOnlyFullLoadingForSettingDeepLink() {
        return getIntent().getExtras().getBoolean(ManageListingIntents.INTENT_EXTRA_SETTING_DEEP_LINK_SHOW_FULL_LOADING_ONLY, false);
    }

    private void maybeShowDeepLinkSetting() {
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey(ManageListingIntents.INTENT_EXTRA_SETTING_DEEP_LINK)) {
            this.fullLoader.setVisibility(8);
            SettingDeepLink setting = (SettingDeepLink) extras.getSerializable(ManageListingIntents.INTENT_EXTRA_SETTING_DEEP_LINK);
            extras.remove(ManageListingIntents.INTENT_EXTRA_SETTING_DEEP_LINK);
            switch (setting) {
                case CalendarSettings:
                    this.actionExecutor.calendarSettings();
                    return;
                case AdvanceNotice:
                case BookingWindow:
                case TurnoverDays:
                    this.actionExecutor.availabilityRules();
                    return;
                case TripLength:
                    this.actionExecutor.tripLength();
                    return;
                case Photos:
                    this.actionExecutor.photos();
                    return;
                case Name:
                    this.actionExecutor.textSetting(TextSetting.Name);
                    return;
                case Description:
                    this.actionExecutor.description();
                    return;
                case Amenities:
                    this.actionExecutor.amenities();
                    return;
                case Location:
                    this.actionExecutor.location();
                    return;
                case Wifi:
                    this.actionExecutor.wifi();
                    return;
                case HouseManual:
                    this.actionExecutor.textSetting(TextSetting.HouseManual);
                    return;
                case Directions:
                    this.actionExecutor.textSetting(TextSetting.Directions);
                    return;
                case InstantBook:
                    this.actionExecutor.instantBook();
                    return;
                case NumberOfGuests:
                    this.actionExecutor.roomsAndGuests();
                    return;
                case HouseRules:
                    this.actionExecutor.houseRules();
                    return;
                case AdditionalHouseRules:
                    this.actionExecutor.textSetting(TextSetting.ManageListingAdditionalRules);
                    return;
                case CancellationPolicy:
                    this.actionExecutor.cancellationPolicy();
                    return;
                case CheckInWindow:
                    this.actionExecutor.checkInOut();
                    return;
                case ExtraCharges:
                    this.actionExecutor.extraCharges();
                    return;
                case LongTermPricing:
                    this.actionExecutor.longTermDiscounts();
                    return;
                case Currency:
                    this.actionExecutor.currency();
                    return;
                case Price:
                    ManageListingSettingsTabFragment fragment = (ManageListingSettingsTabFragment) getSupportFragmentManager().findFragmentByTag(SETTINGS_TAB_FRAGMENT_TAG);
                    if (fragment != null) {
                        fragment.showPage(ManageListingPage.BookingSettings);
                        return;
                    }
                    return;
                case CityRegistration:
                    this.actionExecutor.cityRegistration();
                    return;
                case NestedListings:
                    this.actionExecutor.nestedListings();
                    return;
                case CheckInInformation:
                    this.actionExecutor.smartCheckInInformation();
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void onError(NetworkException e) {
        this.controller.setLoading(false);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(findViewById(C7368R.C7370id.root_container), e, DlsManageListingActivity$$Lambda$3.lambdaFactory$(this));
    }

    public boolean onMenuOpened(int featureId, Menu menu) {
        if (getSupportFragmentManager().findFragmentById(C7368R.C7370id.content_container) instanceof ManageListingCheckInGuideFragment) {
            this.checkInJitneyLogger.logCheckinGuideMoreOptionsEvent(this.controller.getListingId());
        }
        return super.onMenuOpened(featureId, menu);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.controller.onSaveInstanceState(outState);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && data != null) {
            if (data.hasExtra("listing")) {
                this.controller.setListing((Listing) data.getParcelableExtra("listing"));
            }
            if (data.hasExtra(CityRegistrationIntents.INTENT_EXTRA_LISTING_REGISTRATION_PROCESS)) {
                this.controller.setListingRegistrationProcess((ListingRegistrationProcess) data.getParcelableExtra(CityRegistrationIntents.INTENT_EXTRA_LISTING_REGISTRATION_PROCESS));
            }
        } else if (resultCode == -1) {
            switch (requestCode) {
                case 100:
                    fetchData();
                    return;
                case 101:
                    this.controller.setListingHasChanged();
                    finish();
                    return;
                case 103:
                    List<ListingExpectation> newExpectations = ListingExpectation.getExpectationListFromRNResponse(data);
                    if (!ListUtils.isEmpty((Collection<?>) newExpectations)) {
                        this.controller.setListingExpectations(newExpectations);
                        break;
                    }
                    break;
                case 104:
                    this.controller.setNestedListings(NestedListing.listToHashById(data.getParcelableArrayListExtra(NestedListingsIntents.INTENT_NESTED_LISTINGS)));
                    return;
                case PaidAmenityIntents.ACTIVITY_HOST_AMENITY_LIST /*888*/:
                    break;
                default:
                    return;
            }
            if (data.getBooleanExtra(PaidAmenityIntents.EXTRA_HAS_ZERO_PAID_AMENITY, false)) {
                fetchData();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.controller.destroy();
        this.controller = null;
    }

    public void finish() {
        setResult(this.controller.getListingHasChanged() ? -1 : 0);
        super.finish();
    }

    public ManageListingDataController getManageListingDataController() {
        return this.controller;
    }

    /* access modifiers changed from: private */
    public void fetchData() {
        this.controller.setLoading(true);
        long listingId = getIntent().getLongExtra("listing_id", -1);
        ArrayList arrayList = new ArrayList();
        ListingRequest listingRequest = ListingRequest.forV1LegacyManageListing(listingId);
        listingRequest.skipCache();
        BookingSettingsRequest bookingSettingsRequest = new BookingSettingsRequest(listingId);
        bookingSettingsRequest.skipCache();
        CalendarRulesRequest calendarRulesRequest = CalendarRulesRequest.forListingId(listingId);
        calendarRulesRequest.skipCache();
        LongTermDiscountsConversionRequest averagePricesRequest = new LongTermDiscountsConversionRequest(listingId, 1.0d, 1.0d);
        averagePricesRequest.skipCache();
        CheckInTermsRequest checkInOutRequest = CheckInTermsRequest.forCheckInTerms(listingId);
        checkInOutRequest.skipCache();
        ListingRoomsRequest listingRoomsRequest = new ListingRoomsRequest(listingId);
        listingRoomsRequest.skipCache();
        HomesCollectionsApplicationsRequest collectionsApplicationsRequest = HomesCollectionsApplicationsRequest.forListingId(listingId, this.accountManager.getCurrentUserId());
        collectionsApplicationsRequest.skipCache();
        CheckInInformationRequest checkInInformationRequest = CheckInInformationRequest.forAllMethods(listingId);
        checkInInformationRequest.skipCache();
        GetSelectListingRequest selectListingRequest = GetSelectListingRequest.forListingId(listingId);
        selectListingRequest.skipCache();
        GetSelectRoomDescriptionsRequest roomDescriptionsRequest = new GetSelectRoomDescriptionsRequest();
        arrayList.add(listingRequest);
        arrayList.add(bookingSettingsRequest);
        arrayList.add(calendarRulesRequest);
        arrayList.add(averagePricesRequest);
        arrayList.add(checkInOutRequest);
        arrayList.add(listingRoomsRequest);
        arrayList.add(collectionsApplicationsRequest);
        arrayList.add(checkInInformationRequest);
        arrayList.add(selectListingRequest);
        arrayList.add(roomDescriptionsRequest);
        if (FeatureToggles.isListingRegistrationEnabled()) {
            arrayList.add((ListingRegistrationProcessesRequest) ListingRegistrationProcessesRequest.forML(listingId).skipCache());
        }
        if (FeatureToggles.showNestedListings()) {
            arrayList.add(UserRequest.newRequestForNestedListingsEligibility(this.accountManager.getCurrentUserId()));
            arrayList.add((NestedListingsRequest) NestedListingsRequest.forCurrentUser().skipCache());
        }
        new AirBatchRequest(arrayList, this.batchListener).execute(this.requestManager);
    }

    public void showNightlyPrice() {
        this.actionExecutor.nightlyPrice();
    }
}
