package com.airbnb.android.listyourspacedls;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.BedType;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.airbnb.android.core.models.ListingLongTermDiscountValues;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.requests.photos.PhotoUploadTarget;
import com.airbnb.android.core.responses.PhotoUploadResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.listing.LYSCollection;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.constants.LYSStepIdRead;
import com.airbnb.android.listing.utils.ListingPhotosUtil;
import com.airbnb.android.listing.utils.TextSetting;
import com.airbnb.android.listyourspacedls.constants.LYSConstants;
import com.airbnb.android.listyourspacedls.utils.LYSLastSeenStepUtil;
import com.airbnb.android.photouploadmanager.PhotoUploadListener;
import com.airbnb.android.photouploadmanager.PhotoUploadListenerUtil;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.functions.Action1;

public class LYSDataController {
    @State
    boolean accountVerificationCompletedOnClient;
    @State
    ArrayList<AccountVerification> accountVerifications;
    private final LYSActionExecutor actionExecutor;
    @State
    ListingLongTermDiscountValues averagePrices;
    @State
    ArrayList<ListingRoom> bedDetails = new ArrayList<>();
    @State
    CalendarRule calendarRule;
    @State
    ListingCheckInTimeOptions checkInTimeOptions;
    @State
    String currencyCode;
    @State
    GuestControls guestControls;
    @State
    boolean identityCompletedOnClient;
    LYSJitneyLogger jitneyLogger;
    @State
    Listing listing;
    @State
    ListingRegistrationProcess listingRegistrationProcess;
    @State
    boolean loading;
    @State
    LYSStep maxStepReached;
    @State
    Boolean newHostPromoEnabled;
    private final PhotoUploadListener photoUploadListener = PhotoUploadListenerUtil.createSuccessListener(LYSDataController$$Lambda$1.lambdaFactory$(this));
    @State
    DynamicPricingControl pricingSettings;
    @State
    String sessionId;
    @State
    boolean shouldReloadCalendar;
    private final List<UpdateListener> updateListeners = Lists.newArrayList();
    PhotoUploadManager uploadManager;

    public interface UpdateListener {
        void dataLoading(boolean z);

        void dataUpdated();
    }

    static /* synthetic */ void lambda$new$0(LYSDataController lYSDataController, PhotoUploadResponse response) {
        Listing listing2 = lYSDataController.getListing();
        ListingPhotosUtil.addPhoto(listing2, response.listingPhoto.toPhoto());
        lYSDataController.setListing(listing2);
    }

    public LYSDataController(LYSActionExecutor actionExecutor2, Context context, Bundle savedInstanceState, String sessionId2) {
        this.actionExecutor = actionExecutor2;
        this.sessionId = sessionId2;
        ((ListYourSpaceDLSGraph) CoreApplication.instance(context).component()).inject(this);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void addUploadManagerListenerForListing(long listingId) {
        this.uploadManager.addListenerForPhotoUploadTarget(listingId, PhotoUploadTarget.ListingPhoto, this.photoUploadListener);
    }

    public void addListener(UpdateListener updateListener) {
        this.updateListeners.add(updateListener);
        updateListener.dataLoading(this.loading);
        if (this.listing != null) {
            updateListener.dataUpdated();
        }
    }

    public void removeListener(UpdateListener updateListener) {
        this.updateListeners.remove(updateListener);
    }

    public void setLoading(boolean loading2) {
        this.loading = loading2;
        notifyListeners(LYSDataController$$Lambda$2.lambdaFactory$(loading2));
    }

    public void setListing(Listing listing2) {
        this.listing = listing2;
        updateListeners();
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setAccountVerifications(List<AccountVerification> accountVerificationList) {
        this.accountVerifications = new ArrayList<>(accountVerificationList);
    }

    public void setAccountVerificationsCompleteOnClient() {
        this.accountVerificationCompletedOnClient = true;
    }

    public boolean isAccountVerificationCompletedOnClient() {
        return this.accountVerificationCompletedOnClient;
    }

    public void setIdentityCompleteOnClient() {
        this.identityCompletedOnClient = true;
    }

    public boolean isIdentityCompletedOnClient() {
        return this.identityCompletedOnClient;
    }

    public List<AccountVerification> getAccountVerifications() {
        return this.accountVerifications;
    }

    public void setDynamicPricingControls(DynamicPricingControl pricingSettings2) {
        this.pricingSettings = pricingSettings2;
        notifyListeners(LYSDataController$$Lambda$3.lambdaFactory$());
    }

    public void setGuestControls(GuestControls guestControls2) {
        this.guestControls = guestControls2;
        notifyListeners(LYSDataController$$Lambda$4.lambdaFactory$());
    }

    public void setCurrencyCode(String currencyCode2) {
        this.currencyCode = currencyCode2;
        notifyListeners(LYSDataController$$Lambda$5.lambdaFactory$());
    }

    public void setInstantBookAllowedCategory(InstantBookingAllowedCategory ibCategory) {
        this.listing.setInstantBookingAllowedCategory(ibCategory.serverKey);
        notifyListeners(LYSDataController$$Lambda$6.lambdaFactory$());
    }

    public void setAveragePrices(ListingLongTermDiscountValues averagePrices2) {
        this.averagePrices = averagePrices2;
    }

    public boolean isInstantBook() {
        String categoryString = getListing().getInstantBookingAllowedCategory();
        return TextUtils.isEmpty(categoryString) || InstantBookingAllowedCategory.fromKey(categoryString).isInstantBookEnabled();
    }

    public ListingCheckInTimeOptions getCheckInTimeOptions() {
        return this.checkInTimeOptions;
    }

    public void setCheckInTimeOptions(ListingCheckInTimeOptions checkInTimeOptions2) {
        this.checkInTimeOptions = checkInTimeOptions2;
    }

    public void setListingRegistrationProcess(ListingRegistrationProcess listingRegistrationProcess2) {
        this.listingRegistrationProcess = listingRegistrationProcess2;
    }

    public ListingRegistrationProcess getListingRegistrationProcess() {
        return this.listingRegistrationProcess;
    }

    public void setCalendarRule(CalendarRule calendarRule2) {
        this.calendarRule = calendarRule2;
        notifyListeners(LYSDataController$$Lambda$7.lambdaFactory$());
    }

    public void setBedDetails(List<ListingRoom> bedDetails2) {
        this.bedDetails = new ArrayList<>(bedDetails2);
        notifyListeners(LYSDataController$$Lambda$8.lambdaFactory$());
    }

    public void setBedsForRoom(int roomNumber, List<BedType> beds) {
        getOrCreateRoomByNumber(roomNumber).setBedTypes(beds);
        notifyListeners(LYSDataController$$Lambda$9.lambdaFactory$());
    }

    public void setNewHostPromoEnabled(Boolean isEnabled) {
        this.newHostPromoEnabled = isEnabled;
    }

    public Boolean isNewHostPromoEnabled() {
        return this.newHostPromoEnabled;
    }

    public void setShouldReloadCalendar(boolean shouldReloadCalendar2) {
        this.shouldReloadCalendar = shouldReloadCalendar2;
    }

    public void showTipModal(int titleRes, int textRes, NavigationTag navigationTag) {
        this.actionExecutor.showTipModal(titleRes, textRes, navigationTag);
    }

    public void showTipModal(int titleRes, CharSequence text, NavigationTag navigationTag) {
        this.actionExecutor.showTipModal(titleRes, text, navigationTag);
    }

    public void showCurrencyModal(String currentCurrency) {
        this.actionExecutor.showCurrencyModal(currentCurrency);
    }

    public void showRequestToBookChecklistModal() {
        this.actionExecutor.showRequestToBookChecklistModal();
    }

    public void showRoomBedDetails(int roomNumber) {
        this.actionExecutor.showRoomBedDetails(roomNumber);
    }

    public void showHouseRulesModal() {
        this.actionExecutor.showHouseRulesModal();
    }

    public void showAvailabilityModal() {
        this.actionExecutor.showAvailabilityModal();
    }

    public void showSetPriceModal() {
        this.actionExecutor.showSetPriceModal();
    }

    public void showDiscountsModal() {
        this.actionExecutor.showDiscountsModal();
    }

    public void showGuestRequirementsModal() {
        this.actionExecutor.showGuestRequirementsModal();
    }

    public void showCityRegistrationExemption() {
        this.actionExecutor.showCityRegistrationExemption();
    }

    public void showCityRegistrationInputGroup(int inputGroupIndex) {
        this.actionExecutor.showCityRegistrationInputGroup(inputGroupIndex);
    }

    public void showCityRegistrationNextSteps() {
        this.actionExecutor.showCityRegistrationNextSteps();
    }

    public void showLoadingOverlay(boolean show) {
        this.actionExecutor.showLoadingOverlay(show);
    }

    public void showOpaqueLoader(boolean show) {
        this.actionExecutor.showOpaqueLoader(show);
    }

    public void popFragment() {
        this.actionExecutor.popFragment();
    }

    public Listing getListing() {
        return this.listing;
    }

    public DynamicPricingControl getDynamicPricingControls() {
        return this.pricingSettings;
    }

    public CalendarRule getCalendarRule() {
        return this.calendarRule;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public GuestControls getGuestControls() {
        return this.guestControls;
    }

    public List<ListingRoom> getBedDetails() {
        return ListUtils.ensureNotNull(this.bedDetails);
    }

    public ListingRoom getRoomByNumber(int roomNumber) {
        return (ListingRoom) FluentIterable.from((Iterable<E>) getBedDetails()).firstMatch(LYSDataController$$Lambda$10.lambdaFactory$(roomNumber)).orNull();
    }

    static /* synthetic */ boolean lambda$getRoomByNumber$2(int roomNumber, ListingRoom room) {
        return room != null && roomNumber == room.getRoomNumber();
    }

    private ListingRoom getOrCreateRoomByNumber(int roomNumber) {
        ListingRoom result = getRoomByNumber(roomNumber);
        if (result != null) {
            return result;
        }
        ListingRoom result2 = new ListingRoom();
        result2.setRoomNumber(roomNumber);
        result2.setBedTypes(new ArrayList());
        this.bedDetails.add(result2);
        return result2;
    }

    public boolean shouldReloadCalendar() {
        return this.shouldReloadCalendar;
    }

    public ListingLongTermDiscountValues getAveragePrices() {
        return this.averagePrices;
    }

    public boolean isListingCreated() {
        return this.listing.getId() > 0;
    }

    public boolean isReadyToPublish() {
        return LYSStep.areAllStepsComplete(getMaxReachedStep(), getListing(), getAccountVerifications(), isAccountVerificationCompletedOnClient(), isIdentityCompletedOnClient(), getListingRegistrationProcess());
    }

    public void setMaxStepReachedFromInProgressListing(String lastFinishedStepId) {
        LYSStep maxCompletedStep = LYSStepIdRead.stepIdToLYSStepSuperset(lastFinishedStepId);
        if (LYSStep.canMoveToNextStep(maxCompletedStep, maxCompletedStep, this.listing, this.accountVerifications, this.accountVerificationCompletedOnClient, this.identityCompletedOnClient, this.listingRegistrationProcess)) {
            maxCompletedStep = LYSStep.getNextStep(maxCompletedStep, maxCompletedStep, this.listing, this.accountVerifications, this.accountVerificationCompletedOnClient, this.identityCompletedOnClient, this.listingRegistrationProcess, isInstantBook());
        }
        this.maxStepReached = LYSStep.getFirstIncompleteAndNonSkippableStep(maxCompletedStep, maxCompletedStep, this.listing, this.accountVerifications, this.accountVerificationCompletedOnClient, this.identityCompletedOnClient, this.listingRegistrationProcess, isInstantBook());
    }

    public void destroy() {
        if (this.listing != null && isListingCreated()) {
            this.uploadManager.removeListenerForPhotoUploadTarget(this.listing.getId(), PhotoUploadTarget.ListingPhoto, this.photoUploadListener);
        }
    }

    public void nextStep(LYSStep currentStep) {
        if (currentStep.shouldUpdateLastFinishedStepId) {
            logUpdateLastFinishedStepId(this.listing.getId(), currentStep);
        }
        LYSStep nextStep = LYSStep.getNextStep(currentStep, getMaxReachedStep(), this.listing, this.accountVerifications, this.accountVerificationCompletedOnClient, this.identityCompletedOnClient, this.listingRegistrationProcess, isInstantBook());
        if (nextStep.lysCollection != currentStep.lysCollection) {
            updateMaxStepReached(nextStep);
            this.actionExecutor.returnToLanding();
            return;
        }
        executeStep(nextStep);
    }

    public void logUpdateLastFinishedStepId(long listingId, LYSStep currentStep) {
        if (LYSLastSeenStepUtil.shouldUpdateLastFinishedStepId(currentStep.stepId, getListing().getListYourSpaceLastFinishedStepId())) {
            this.jitneyLogger.logUpdateLastFinishedStepIdEvent(Long.valueOf(listingId), currentStep.stepId);
        }
    }

    public void firstStep(LYSCollection collection) {
        executeStep(LYSStep.getFirstNonskippableStepFromCurrent(LYSStep.getFirstStepForCollection(collection), getMaxReachedStep(), this.listing, this.accountVerifications, this.accountVerificationCompletedOnClient, this.identityCompletedOnClient, this.listingRegistrationProcess, isInstantBook()));
    }

    private void executeStep(LYSStep step) {
        updateMaxStepReached(step);
        this.actionExecutor.showStep(step);
    }

    private void updateMaxStepReached(LYSStep step) {
        if (this.maxStepReached == null || step.isAfter(this.maxStepReached)) {
            this.maxStepReached = step;
        }
    }

    public void returnToLanding() {
        this.actionExecutor.returnToLanding();
    }

    public void showPublishFragment() {
        this.actionExecutor.showPublishStep();
    }

    public void showGuestExpectationsFragment() {
        this.actionExecutor.showGuestExpectations();
    }

    public float getStepProgressForCollection(LYSCollection collection) {
        LYSStep maxReachedStep = getMaxReachedStep();
        if (maxReachedStep.lysCollection.isAfter(collection)) {
            return 1.0f;
        }
        if (maxReachedStep.lysCollection != collection) {
            return 0.0f;
        }
        List<LYSStep> steps = LYSStep.getStepsForCollection(collection).toList();
        int position = steps.indexOf(maxReachedStep);
        if (position == 0) {
            return 0.05f;
        }
        return ((float) position) / ((float) steps.size());
    }

    /* access modifiers changed from: 0000 */
    public void updateListeners() {
        Check.notNull(this.listing);
        notifyListeners(LYSDataController$$Lambda$11.lambdaFactory$());
    }

    public void showPhotoDetail(long photoId) {
        this.actionExecutor.photo(photoId);
    }

    public void showGuestAdditionalRequirements() {
        this.actionExecutor.showGuestAdditionalRequirementsModal();
    }

    public void showTextSettingModal(TextSetting textSetting) {
        this.actionExecutor.showTextSettingModal(textSetting);
    }

    public void showHouseRulesLegalInfoModal() {
        this.actionExecutor.showHouseRulesLegalInfo();
    }

    public void createDefaultListing() {
        Listing listing2 = new Listing();
        listing2.setBedCount(1);
        listing2.setPersonCapacity(1);
        listing2.setBathrooms(1.0f);
        listing2.setRoomTypeKey(LYSConstants.DEFAULT_INITIAL_SPACE_TYPE.serverDescKey);
        listing2.setPropertyTypeId(LYSConstants.DEFAULT_INITIAL_PROPERTY_TYPE.serverDescKey);
        listing2.setPhotos(ImmutableList.m1284of());
        setCurrencyCode(CurrencyUtils.getLocalDefaultCurrency().getCurrencyCode());
        setListing(listing2);
    }

    private void notifyListeners(Action1<UpdateListener> listenerAction) {
        for (UpdateListener updateListener : this.updateListeners) {
            listenerAction.call(updateListener);
        }
    }

    public LYSStep getMaxReachedStep() {
        return this.maxStepReached == null ? LYSStep.getFirstStepForCollection(LYSCollection.first()) : this.maxStepReached;
    }
}
