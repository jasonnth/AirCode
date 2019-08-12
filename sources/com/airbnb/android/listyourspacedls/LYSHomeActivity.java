package com.airbnb.android.listyourspacedls;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.ListYourSpacePricingMode;
import com.airbnb.android.core.enums.ListingRegistrationStatus;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.IdentityVerificationUtil;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.intents.CityRegistrationIntents;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingExpectation;
import com.airbnb.android.core.models.ListingRegistration;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.utils.AirAddressUtil;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NavigationUtils;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.listing.LYSCollection;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.RoomsAndGuestsAdapter.Mode;
import com.airbnb.android.listing.fragments.HouseRulesLegalInfoFragment;
import com.airbnb.android.listing.fragments.TipFragment;
import com.airbnb.android.listing.utils.TextSetting;
import com.airbnb.android.listyourspacedls.fragments.LYSAddressFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSAmenitiesFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSAvailabilityFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSBaseFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSBasePriceFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSBedDetailsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCalendarFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCharacterCountMarqueeFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCharacterCountMarqueeFragment.Setting;
import com.airbnb.android.listyourspacedls.fragments.LYSCityRegistrationApplicationFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCityRegistrationExemptionFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCityRegistrationInputGroupFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCityRegistrationNextStepsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCityRegistrationOverviewFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCityRegistrationReviewExemptedFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCityRegistrationReviewFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCurrencyFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSDiscountsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSExactLocationFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSGuestAdditionalRequirementsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSGuestBookFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSGuestRequirementsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSHostingFrequencyFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSHouseRulesFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSLandingFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSLocalLawsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSNewHostDiscountFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoDetailFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoManagerFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoStartFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPublishFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSRTBChecklistFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSRentHistoryFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSReviewSettingsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSRoomBedDetailsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSRoomsAndGuestsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSSelectPricingTypeFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSSmartPricingFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSSpaceTypeFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSTextSettingFragment;
import com.airbnb.android.listyourspacedls.utils.LYSBatchRequestUtil;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.SheetProgressBar;
import com.airbnb.p027n2.utils.ObjectAnimatorFactory;
import icepick.State;
import java.util.Collection;
import java.util.List;

public class LYSHomeActivity extends AirActivity {
    private static final String LANDING_TAG = "landing";
    private static final int REQUEST_CODE_CITY_REGISTRATION = 9004;
    private static final int REQUEST_CODE_EXPECTATIONS = 9003;
    private static final int REQUEST_CODE_IDENTITY = 9005;
    private static final int REQUEST_CODE_VERIFICATIONS = 9002;
    private final LYSActionExecutor actionExecutor = new LYSActionExecutor() {
        public void returnToLanding() {
            NavigationUtils.popBackStackToFragment(LYSHomeActivity.this.getSupportFragmentManager(), LYSHomeActivity.LANDING_TAG);
        }

        public void showStep(LYSStep step) {
            LYSHomeActivity.this.setProgress(step.lysCollection);
            switch (C72502.$SwitchMap$com$airbnb$android$listing$LYSStep[step.ordinal()]) {
                case 1:
                    LYSHomeActivity.this.showFragment(LYSSpaceTypeFragment.newInstance());
                    return;
                case 2:
                    LYSHomeActivity.this.showFragment(LYSRoomsAndGuestsFragment.newInstance(Mode.NonBathrooms));
                    return;
                case 3:
                    LYSHomeActivity.this.showFragment(LYSBedDetailsFragment.newInstance());
                    return;
                case 4:
                    LYSHomeActivity.this.showFragment(LYSRoomsAndGuestsFragment.newInstance(Mode.BathroomsOnly));
                    return;
                case 5:
                    LYSHomeActivity.this.showFragment(LYSAddressFragment.newInstance());
                    return;
                case 6:
                    LYSHomeActivity.this.showFragment(LYSExactLocationFragment.newInstance());
                    return;
                case 7:
                    LYSHomeActivity.this.showFragment(LYSAmenitiesFragment.newInstance(LYSAmenitiesFragment.Mode.ListYourSpace));
                    return;
                case 8:
                    LYSHomeActivity.this.showFragment(LYSAmenitiesFragment.newInstance(LYSAmenitiesFragment.Mode.SpacesOnly));
                    return;
                case 9:
                    LYSHomeActivity.this.showFragment(LYSPhotoStartFragment.newInstance());
                    return;
                case 10:
                    LYSHomeActivity.this.showFragment(LYSPhotoManagerFragment.newInstance());
                    return;
                case 11:
                    LYSHomeActivity.this.showFragment(LYSCharacterCountMarqueeFragment.newInstance(Setting.Description));
                    return;
                case 12:
                    LYSHomeActivity.this.showFragment(LYSCharacterCountMarqueeFragment.newInstance(Setting.Title));
                    return;
                case 13:
                    LYSHomeActivity.this.showVerificationActivity();
                    return;
                case 14:
                    LYSHomeActivity.this.showIdentityActivity();
                    return;
                case 15:
                    LYSHomeActivity.this.showFragment(LYSGuestRequirementsFragment.newInstance(false));
                    return;
                case 16:
                    LYSHomeActivity.this.showFragment(LYSHouseRulesFragment.newInstance(false));
                    return;
                case 17:
                    LYSHomeActivity.this.showFragment(LYSGuestBookFragment.newInstance());
                    return;
                case 18:
                    LYSHomeActivity.this.showFragment(LYSRentHistoryFragment.newInstance());
                    return;
                case 19:
                    LYSHomeActivity.this.showFragment(LYSHostingFrequencyFragment.newInstance());
                    return;
                case 20:
                    LYSHomeActivity.this.showFragment(LYSAvailabilityFragment.newInstance(false));
                    return;
                case 21:
                    LYSHomeActivity.this.showFragment(LYSCalendarFragment.newInstance());
                    return;
                case 22:
                    LYSHomeActivity.this.showFragment(LYSSelectPricingTypeFragment.newInstance());
                    return;
                case 23:
                    if (LYSHomeActivity.this.shouldShowSmartPricing(LYSHomeActivity.this.controller.getListing())) {
                        LYSHomeActivity.this.showFragment(LYSSmartPricingFragment.newInstance(false));
                        return;
                    } else {
                        LYSHomeActivity.this.showFragment(LYSBasePriceFragment.newInstance(false));
                        return;
                    }
                case 24:
                    LYSHomeActivity.this.showFragment(LYSNewHostDiscountFragment.newInstance());
                    return;
                case 25:
                    LYSHomeActivity.this.showFragment(LYSDiscountsFragment.newInstance(false));
                    return;
                case 26:
                    LYSHomeActivity.this.showFragment(LYSReviewSettingsFragment.newInstance());
                    return;
                case 27:
                    LYSHomeActivity.this.showFragment(LYSLocalLawsFragment.newInstance());
                    return;
                case 28:
                    LYSHomeActivity.this.showCityRegistrationFragment();
                    return;
                default:
                    throw new UnhandledStateException(step);
            }
        }

        public void showLoadingOverlay(boolean show) {
            loadingOverlayAnimation(show, LYSHomeActivity.this.loadingOverlay);
        }

        public void showOpaqueLoader(boolean show) {
            loadingOverlayAnimation(show, LYSHomeActivity.this.opaqueLoader);
        }

        private void loadingOverlayAnimation(boolean show, View loadingOverlay) {
            int targetVisibility = show ? 0 : 8;
            if (loadingOverlay.getVisibility() != targetVisibility) {
                ObjectAnimatorFactory.fade(loadingOverlay, show).onStartStep(LYSHomeActivity$1$$Lambda$1.lambdaFactory$(loadingOverlay)).onEndStep(LYSHomeActivity$1$$Lambda$2.lambdaFactory$(loadingOverlay, targetVisibility)).setDuration(150).buildAndStart();
            }
        }

        public void photo(long photoId) {
            LYSHomeActivity.this.showModal(LYSPhotoDetailFragment.create(photoId));
        }

        public void showPublishStep() {
            LYSHomeActivity.this.showModal(LYSPublishFragment.newInstance());
        }

        public void showGuestAdditionalRequirementsModal() {
            LYSHomeActivity.this.showModal(LYSGuestAdditionalRequirementsFragment.newInstance());
        }

        public void showRequestToBookChecklistModal() {
            LYSHomeActivity.this.showModal(LYSRTBChecklistFragment.newInstance());
        }

        public void showRoomBedDetails(int roomNumber) {
            LYSHomeActivity.this.showModal(LYSRoomBedDetailsFragment.newInstance(roomNumber));
        }

        public void showGuestExpectations() {
            LYSHomeActivity.this.showGuestExpectationsActivity();
        }

        public void showCityRegistrationExemption() {
            LYSHomeActivity.this.showFragment(LYSCityRegistrationExemptionFragment.newInstance());
        }

        public void showCityRegistrationInputGroup(int groupIndex) {
            ListingRegistrationProcess process = LYSHomeActivity.this.controller.getListingRegistrationProcess();
            ListingRegistrationProcessInputGroup inputGroup = process != null ? process.getInputGroupFromIndex(groupIndex) : null;
            if (inputGroup != null) {
                LYSHomeActivity.this.showFragment(LYSCityRegistrationInputGroupFragment.newInstance(inputGroup, AirAddressUtil.fromListing(LYSHomeActivity.this.controller.getListing()), groupIndex + 1));
            } else {
                LYSHomeActivity.this.showFragment(LYSCityRegistrationApplicationFragment.newInstance());
            }
        }

        public void showCityRegistrationNextSteps() {
            LYSHomeActivity.this.showFragment(LYSCityRegistrationNextStepsFragment.newInstance());
        }

        public void showTipModal(int titleRes, int textRes, NavigationTag navigationTag) {
            LYSHomeActivity.this.showModal(TipFragment.builder(LYSHomeActivity.this, navigationTag).addTitleRes(titleRes).addTextRes(textRes).build());
        }

        public void showTipModal(int titleRes, CharSequence text, NavigationTag navigationTag) {
            LYSHomeActivity.this.showModal(TipFragment.builder(LYSHomeActivity.this, navigationTag).addTitleRes(titleRes).addText(text).build());
        }

        public void showCurrencyModal(String currentCurrency) {
            LYSHomeActivity.this.showModal(LYSCurrencyFragment.newInstance(currentCurrency));
        }

        public void showHouseRulesModal() {
            LYSHomeActivity.this.showModal(LYSHouseRulesFragment.newInstance(true));
        }

        public void showSetPriceModal() {
            if (LYSHomeActivity.this.shouldShowSmartPricing(LYSHomeActivity.this.controller.getListing())) {
                LYSHomeActivity.this.showModal(LYSSmartPricingFragment.newInstance(true));
            } else {
                LYSHomeActivity.this.showModal(LYSBasePriceFragment.newInstance(true));
            }
        }

        public void showGuestRequirementsModal() {
            LYSHomeActivity.this.showModal(LYSGuestRequirementsFragment.newInstance(true));
        }

        public void showDiscountsModal() {
            LYSHomeActivity.this.showModal(LYSDiscountsFragment.newInstance(true));
        }

        public void showAvailabilityModal() {
            LYSHomeActivity.this.showModal(LYSAvailabilityFragment.newInstance(true));
        }

        public void showHouseRulesLegalInfo() {
            LYSHomeActivity.this.showModal(HouseRulesLegalInfoFragment.newInstance());
        }

        public void popFragment() {
            LYSHomeActivity.this.getSupportFragmentManager().popBackStack();
        }

        public void showTextSettingModal(TextSetting textSetting) {
            LYSHomeActivity.this.showModal(LYSTextSettingFragment.create(textSetting));
        }
    };
    public final NonResubscribableRequestListener<AirBatchResponse> batchRequestListener = new C0699RL().onResponse(LYSHomeActivity$$Lambda$1.lambdaFactory$(this)).onError(LYSHomeActivity$$Lambda$2.lambdaFactory$(this)).onComplete(LYSHomeActivity$$Lambda$3.lambdaFactory$(this)).buildWithoutResubscription();
    /* access modifiers changed from: private */
    public LYSDataController controller;
    IdentityJitneyLogger identityJitneyLogger;
    @BindView
    View loadingOverlay;
    @BindView
    ViewGroup modalContainer;
    @BindView
    View opaqueLoader;
    @State
    float progress = 0.0f;
    @BindView
    SheetProgressBar progressBar;
    private boolean returnedFromIdentity;
    private boolean returnedFromVerification;

    /* renamed from: com.airbnb.android.listyourspacedls.LYSHomeActivity$2 */
    static /* synthetic */ class C72502 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$android$listing$LYSStep = new int[LYSStep.values().length];

        static {
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.SpaceType.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.RoomsAndGuests.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.BedDetails.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.Bathrooms.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.Address.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.ExactLocation.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.Amenities.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.Spaces.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.Photos.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.PhotoManager.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.DescriptionStep.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.TitleStep.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.VerificationSteps.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.Identity.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.GuestRequirementsStep.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.HouseRules.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.HowGuestsBookStep.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.RentHistoryStep.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.HostingFrequencyStep.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.AvailabilityStep.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.CalendarStep.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.SelectPricingType.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.SetPrice.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.NewHostDiscount.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.Discounts.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.ReviewSettings.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.LocalLaws.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                $SwitchMap$com$airbnb$android$listing$LYSStep[LYSStep.CityRegistration.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean shouldShowSmartPricing(Listing listing) {
        return listing.getListYourSpacePricingMode() == ListYourSpacePricingMode.Smart && PricingFeatureToggles.showSmartPricing(listing);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(this).component()).inject(this);
        this.controller = new LYSDataController(this.actionExecutor, getApplicationContext(), savedInstanceState, getIntent().getStringExtra(ManageListingIntents.INTENT_EXTRA_SESSION_ID));
        super.onCreate(savedInstanceState);
        setContentView(C7251R.layout.lys_dls_entry_point);
        FragmentTransitionType type = FragmentTransitionType.SlideFromBottom;
        overridePendingTransition(type.enter, type.exit);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            long listingId = getIntent().getLongExtra("listing_id", -1);
            if (listingId == -1) {
                this.controller.createDefaultListing();
                setupLandingFragment();
            } else {
                this.actionExecutor.showLoadingOverlay(true);
                executeBatchRequest(listingId);
                this.controller.addUploadManagerListenerForListing(listingId);
            }
        }
        getSupportFragmentManager().addOnBackStackChangedListener(LYSHomeActivity$$Lambda$4.lambdaFactory$(this));
        updateProgressVisibility();
        this.progressBar.setProgress(this.progress);
    }

    public void finish() {
        super.finish();
        FragmentTransitionType type = FragmentTransitionType.SlideFromBottom;
        overridePendingTransition(type.popEnter, type.popExit);
    }

    /* access modifiers changed from: private */
    public void updateProgressVisibility() {
        boolean emptyOrLanding;
        boolean showingModal;
        int i = 0;
        Fragment fragment = getSupportFragmentManager().findFragmentById(C7251R.C7253id.fragment_container);
        if (fragment == null || (fragment instanceof LYSLandingFragment)) {
            emptyOrLanding = true;
        } else {
            emptyOrLanding = false;
        }
        if (this.modalContainer.getChildCount() > 0) {
            showingModal = true;
        } else {
            showingModal = false;
        }
        SheetProgressBar sheetProgressBar = this.progressBar;
        if (emptyOrLanding || showingModal) {
            i = 8;
        }
        sheetProgressBar.setVisibility(i);
    }

    /* access modifiers changed from: private */
    public void executeBatchRequest(long listingId) {
        LYSBatchRequestUtil.getListingBatchRequest(listingId, this.batchRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.controller.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: private */
    public void setProgress(LYSCollection collection) {
        this.progress = this.controller.getStepProgressForCollection(collection);
        this.progressBar.setProgress(this.progress, true);
    }

    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        if (childFragment instanceof LYSBaseFragment) {
            ((LYSBaseFragment) childFragment).setController(this.controller);
        } else if (childFragment instanceof LYSLandingFragment) {
            ((LYSLandingFragment) childFragment).setController(this.controller);
        } else if (childFragment instanceof LYSPublishFragment) {
            ((LYSPublishFragment) childFragment).setController(this.controller);
        }
    }

    /* access modifiers changed from: private */
    public void showFragment(Fragment fragment) {
        NavigationUtils.showFragment(getSupportFragmentManager(), this, fragment, C7251R.C7253id.fragment_container, FragmentTransitionType.SlideInFromSide, true);
    }

    /* access modifiers changed from: private */
    public void showModal(Fragment fragment) {
        NavigationUtils.showModal(getSupportFragmentManager(), this, fragment, C7251R.C7253id.fragment_container, C7251R.C7253id.modal_container, true);
    }

    /* access modifiers changed from: private */
    public void showGuestExpectationsActivity() {
        startActivityForResult(ReactNativeIntents.intentForHouseRulesAndExpectations(this, this.controller.getListing().getId(), this.controller.getListing().getListingExpectations()), REQUEST_CODE_EXPECTATIONS, ActivityOptionsCompat.makeSceneTransitionAnimation(this, new Pair[0]).toBundle());
    }

    /* access modifiers changed from: private */
    public void showVerificationActivity() {
        String[] verifications = LYSStep.getIncompleteVerificationSteps(this.controller.getAccountVerifications());
        startActivityForResult(IdentityVerificationUtil.verify(this, VerificationFlow.ListYourSpaceDLS, null, null, this.controller.getListing().getId(), verifications), REQUEST_CODE_VERIFICATIONS);
    }

    /* access modifiers changed from: private */
    public void showIdentityActivity() {
        startActivityForResult(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(this.identityJitneyLogger, this, AccountVerificationStartFragmentArguments.builder().verificationFlow(VerificationFlow.ListYourSpaceIdentity).listingId(this.controller.getListing().getId()).incompleteVerifications(LYSStep.getIncompleteIdentitySteps(this.controller.getAccountVerifications())).build()), REQUEST_CODE_IDENTITY);
    }

    /* access modifiers changed from: private */
    public void showCityRegistrationFragment() {
        if (Trebuchet.launch(TrebuchetKeys.LISTING_REGISTRATION_ANDROID_MODULE)) {
            Intent intent = CityRegistrationIntents.intent(this, this.controller.getListing(), this.controller.getListingRegistrationProcess(), Boolean.valueOf(true), Float.valueOf(this.progress));
            if (intent != null) {
                startActivityForResult(intent, REQUEST_CODE_CITY_REGISTRATION);
                return;
            }
        }
        ListingRegistration listingRegistration = ((ListingRegistrationProcess) Check.notNull(this.controller.getListingRegistrationProcess())).getListingRegistration();
        if (listingRegistration == null || listingRegistration.getStatus() == ListingRegistrationStatus.Unregistered) {
            showFragment(LYSCityRegistrationOverviewFragment.newInstance());
        } else if (listingRegistration.getStatus() == ListingRegistrationStatus.Exempted) {
            showFragment(LYSCityRegistrationReviewExemptedFragment.newInstance());
        } else if (listingRegistration.getStatus().hasBeenSubmitted()) {
            showFragment(LYSCityRegistrationReviewFragment.newInstance());
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_VERIFICATIONS && resultCode == -1) {
            this.controller.setAccountVerificationsCompleteOnClient();
            this.returnedFromVerification = true;
        } else if (requestCode == REQUEST_CODE_IDENTITY && resultCode == -1) {
            this.controller.setIdentityCompleteOnClient();
            this.returnedFromIdentity = true;
        } else if (requestCode == REQUEST_CODE_EXPECTATIONS) {
            List<ListingExpectation> newExpectations = ListingExpectation.getExpectationListFromRNResponse(data);
            if (!ListUtils.isEmpty((Collection<?>) newExpectations)) {
                Listing listing = this.controller.getListing();
                listing.setListingExpectations(newExpectations);
                this.controller.setListing(listing);
            }
        } else if (requestCode == REQUEST_CODE_CITY_REGISTRATION) {
            if (data.hasExtra("listing")) {
                this.controller.setListing((Listing) data.getParcelableExtra("listing"));
            }
            if (data.hasExtra(CityRegistrationIntents.INTENT_EXTRA_LISTING_REGISTRATION_PROCESS)) {
                this.controller.setListingRegistrationProcess((ListingRegistrationProcess) data.getParcelableExtra(CityRegistrationIntents.INTENT_EXTRA_LISTING_REGISTRATION_PROCESS));
            }
            if (resultCode == -1) {
                this.controller.nextStep(LYSStep.CityRegistration);
            } else if (resultCode == 100) {
                this.controller.returnToLanding();
            } else {
                if (resultCode == 0) {
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.returnedFromVerification) {
            this.controller.nextStep(LYSStep.VerificationSteps);
            this.returnedFromVerification = false;
        } else if (this.returnedFromIdentity) {
            this.controller.nextStep(LYSStep.Identity);
            this.returnedFromIdentity = false;
        }
        if (this.controller.listing != null && this.controller.listing.getId() != -1) {
            this.controller.addUploadManagerListenerForListing(this.controller.listing.getId());
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.controller.destroy();
        this.controller = null;
        getSupportFragmentManager().removeOnBackStackChangedListener(LYSHomeActivity$$Lambda$5.lambdaFactory$(this));
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    private void setupLandingFragment() {
        showFragment(LYSLandingFragment.newInstance(), C7251R.C7253id.fragment_container, FragmentTransitionType.None, true, LANDING_TAG);
        updateProgressVisibility();
    }

    static /* synthetic */ void lambda$new$0(LYSHomeActivity lYSHomeActivity, AirBatchResponse response) {
        lYSHomeActivity.controller = LYSBatchRequestUtil.setListingBatchResponse(lYSHomeActivity.controller, response);
        lYSHomeActivity.setupLandingFragment();
    }
}
