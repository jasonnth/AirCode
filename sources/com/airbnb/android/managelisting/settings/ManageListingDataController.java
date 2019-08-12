package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.enums.ReadyForSelectStatus;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.BookingSettings;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.CheckInGuide;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.core.models.HomeCollectionApplication;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.airbnb.android.core.models.ListingExpectation;
import com.airbnb.android.core.models.ListingLongTermDiscountValues;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.models.SelectListing;
import com.airbnb.android.core.models.SelectRoomDescription;
import com.airbnb.android.core.requests.photos.PhotoUploadTarget;
import com.airbnb.android.core.responses.PhotoUploadResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.listing.utils.ListingPhotosUtil;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.photouploadmanager.PhotoUploadListener;
import com.airbnb.android.photouploadmanager.PhotoUploadListenerUtil;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import p032rx.functions.Action1;

public class ManageListingDataController {
    AirbnbAccountManager accountManager;
    public final ManageListingActionExecutor actionExecutor;
    @State
    ListingLongTermDiscountValues averagePrices;
    @State
    CalendarRule calendarRule;
    @State
    CheckInGuide checkInGuide;
    @State
    ArrayList<CheckInInformation> checkInInformation;
    @State
    ListingCheckInTimeOptions checkInTimeOptions;
    @State
    HomeCollectionApplication collectionApplication;
    @State
    boolean ibPromoDismissed;
    @State
    boolean initialDataLoaded;
    @State
    Listing listing;
    @State
    boolean listingHasChanged;
    private final long listingId;
    @State
    ListingRegistrationProcess listingRegistrationProcess;
    @State
    ArrayList<ListingRoom> listingRooms;
    @State
    boolean loading;
    @State
    HashMap<Long, NestedListing> nestedListingsById;
    private final PhotoUploadListener photoUploadListener = PhotoUploadListenerUtil.createSuccessListener(ManageListingDataController$$Lambda$1.lambdaFactory$(this));
    @State
    SelectListing selectListing;
    @State
    ArrayList<Integer> selectListingAmenities;
    @State
    SelectRoomDescription selectRoomDescriptions;
    @State
    boolean showMarketplaceOverride;
    private final List<UpdateListener> updateListeners = Lists.newArrayList();
    PhotoUploadManager uploadManager;

    interface UpdateListener {
        void dataLoading(boolean z);

        void dataUpdated();
    }

    static /* synthetic */ void lambda$new$0(ManageListingDataController manageListingDataController, PhotoUploadResponse response) {
        Listing listing2 = manageListingDataController.getListing();
        ListingPhotosUtil.addPhoto(listing2, response.listingPhoto.toPhoto());
        manageListingDataController.setListing(listing2);
    }

    ManageListingDataController(Context context, long listingId2, ManageListingActionExecutor actionExecutor2, Bundle savedInstanceState) {
        this.actionExecutor = actionExecutor2;
        this.listingId = listingId2;
        ((ManageListingGraph) CoreApplication.instance(context).component()).inject(this);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        if (this.initialDataLoaded) {
            setData(this.listing, this.calendarRule, this.averagePrices, this.listingRegistrationProcess, this.checkInTimeOptions, this.nestedListingsById, this.listingRooms, this.collectionApplication, this.checkInInformation, this.selectListing, this.selectListingAmenities, this.selectRoomDescriptions);
        }
        setLoading(this.loading);
        this.uploadManager.addListenerForPhotoUploadTarget(listingId2, PhotoUploadTarget.ListingPhoto, this.photoUploadListener);
    }

    /* access modifiers changed from: 0000 */
    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    /* access modifiers changed from: 0000 */
    public void destroy() {
        this.uploadManager.removeListenerForPhotoUploadTarget(this.listingId, PhotoUploadTarget.ListingPhoto, this.photoUploadListener);
    }

    public long getListingId() {
        return this.listingId;
    }

    public Listing getListing() {
        return this.listing;
    }

    public CalendarRule getCalendarRule() {
        return this.calendarRule;
    }

    public ListingLongTermDiscountValues getAveragePrices() {
        return this.averagePrices;
    }

    public ListingRegistrationProcess getListingRegistrationProcess() {
        return this.listingRegistrationProcess;
    }

    public HashMap<Long, NestedListing> getNestedListingsById() {
        return this.nestedListingsById;
    }

    public List<ListingRoom> getListingRooms() {
        return this.listingRooms;
    }

    public CheckInGuide getCheckInGuide() {
        return this.checkInGuide;
    }

    public CheckInStep getCheckInStepById(long stepId) {
        int index = getIndexOfCheckInStepById(stepId);
        if (index < 0) {
            return null;
        }
        return (CheckInStep) this.checkInGuide.getSteps().get(index);
    }

    private int getIndexOfCheckInStepById(long stepId) {
        if (stepId == -1 || this.checkInGuide == null || this.checkInGuide.getSteps() == null) {
            return -1;
        }
        for (int index = 0; index < this.checkInGuide.getSteps().size(); index++) {
            if (((CheckInStep) this.checkInGuide.getSteps().get(index)).getId() == stepId) {
                return index;
            }
        }
        return -1;
    }

    public List<CheckInInformation> getEnabledCheckInMethods() {
        return FluentIterable.from((Iterable<E>) ListUtils.ensureNotNull(this.checkInInformation)).filter(ManageListingDataController$$Lambda$2.lambdaFactory$()).toList();
    }

    public HomeCollectionApplication getCollectionApplication() {
        return this.collectionApplication;
    }

    public boolean isInSelectLimboState() {
        return this.listing.getReadyForSelectStatusEnum() == ReadyForSelectStatus.PostReadyForSelect;
    }

    public boolean shouldShowSelectML() {
        if (!FeatureToggles.enableSelectML()) {
            return false;
        }
        if (this.listing.getReadyForSelectStatusEnum() == ReadyForSelectStatus.Select || (isInSelectLimboState() && !this.showMarketplaceOverride)) {
            return true;
        }
        return false;
    }

    public void setShouldShowSelect(boolean shouldShowSelect) {
        this.showMarketplaceOverride = !shouldShowSelect;
        updateListeners();
    }

    public SelectListing getSelectListing() {
        return this.selectListing;
    }

    public List<Integer> getSelectListingAmenityIds() {
        return this.selectListingAmenities;
    }

    public SelectRoomDescription getSelectRoomDescriptions() {
        return this.selectRoomDescriptions;
    }

    public ListingRoom getRoomByNumber(int roomNumber) {
        return (ListingRoom) FluentIterable.from((Iterable<E>) this.listingRooms).firstMatch(ManageListingDataController$$Lambda$3.lambdaFactory$(roomNumber)).orNull();
    }

    static /* synthetic */ boolean lambda$getRoomByNumber$2(int roomNumber, ListingRoom room) {
        return roomNumber == room.getRoomNumber();
    }

    public ListingCheckInTimeOptions getCheckInTimeOptions() {
        return this.checkInTimeOptions;
    }

    public List<CheckInInformation> getCheckInInformation() {
        return this.checkInInformation;
    }

    /* access modifiers changed from: 0000 */
    public void addListener(UpdateListener updateListener) {
        this.updateListeners.add(updateListener);
        updateListener.dataLoading(this.loading);
        if (this.initialDataLoaded) {
            updateListener.dataUpdated();
        }
    }

    /* access modifiers changed from: 0000 */
    public void removeListener(UpdateListener updateListener) {
        this.updateListeners.remove(updateListener);
    }

    /* access modifiers changed from: 0000 */
    public void setLoading(boolean loading2) {
        this.loading = loading2;
        notifyListeners(ManageListingDataController$$Lambda$4.lambdaFactory$(loading2));
    }

    public void setListing(Listing listing2) {
        Listing oldListing = this.listing;
        this.listing = listing2;
        this.listing.setBookingCustomQuestions(oldListing.getBookingCustomQuestions());
        this.listing.setBookingStandardQuestions(oldListing.getBookingStandardQuestions());
        updateListeners();
    }

    public void setCalendarRule(CalendarRule calendarRule2) {
        this.calendarRule = calendarRule2;
        updateListeners();
    }

    public void setNestedListings(HashMap<Long, NestedListing> nestedListingsById2) {
        this.nestedListingsById = nestedListingsById2;
        updateListeners();
    }

    public void setListingRooms(List<ListingRoom> listingRooms2) {
        this.listingRooms = new ArrayList<>(listingRooms2);
        updateListeners();
    }

    public void setCheckInGuide(CheckInGuide guide) {
        this.checkInGuide = guide;
        updateListeners();
    }

    public void setSelectListing(SelectListing selectListing2) {
        this.selectListing = selectListing2;
        updateListeners();
    }

    public void setBookingSettings(BookingSettings bookingSettings) {
        this.listing.setBookingCustomQuestions(bookingSettings.getBookingCustomQuestions());
        this.listing.setBookingStandardQuestions(bookingSettings.getBookingStandardQuestions());
        updateListeners();
    }

    public void removeStep(CheckInStep step) {
        int index = getIndexOfCheckInStepById(step.getId());
        if (index != -1) {
            this.checkInGuide.getSteps().remove(index);
            updateListeners();
        }
    }

    public void updateStep(CheckInStep step) {
        int index = getIndexOfCheckInStepById(step.getId());
        if (index < 0) {
            this.checkInGuide.getSteps().add(step);
        } else {
            this.checkInGuide.getSteps().set(index, step);
        }
        updateListeners();
    }

    public void setListingRegistrationProcess(ListingRegistrationProcess listingRegistrationProcess2) {
        this.listingRegistrationProcess = listingRegistrationProcess2;
        updateListeners();
    }

    public void setListingExpectations(List<ListingExpectation> expectations) {
        this.listing.setListingExpectations(expectations);
        updateListeners();
    }

    public void setCheckInInformation(List<CheckInInformation> checkInInformation2) {
        this.checkInInformation = new ArrayList<>(checkInInformation2);
        updateListeners();
    }

    public void setData(Listing listing2, CalendarRule calendarRule2) {
        this.listing = listing2;
        this.calendarRule = calendarRule2;
        updateListeners();
    }

    public void setData(Listing listing2, CalendarRule calendarRule2, ListingLongTermDiscountValues averagePrices2, ListingRegistrationProcess listingRegistrationProcess2, ListingCheckInTimeOptions checkInTimeOptions2, HashMap<Long, NestedListing> nestedListingsById2, List<ListingRoom> listingRooms2, HomeCollectionApplication collectionApplication2, List<CheckInInformation> checkInInformation2, SelectListing selectListing2, List<Integer> selectListingAmenities2, SelectRoomDescription selectRoomDescriptions2) {
        this.listing = listing2;
        this.calendarRule = calendarRule2;
        this.averagePrices = averagePrices2;
        this.listingRegistrationProcess = listingRegistrationProcess2;
        this.checkInTimeOptions = checkInTimeOptions2;
        this.listingRooms = ListUtils.newArrayList(listingRooms2);
        this.collectionApplication = collectionApplication2;
        this.nestedListingsById = nestedListingsById2;
        this.checkInInformation = ListUtils.newArrayList(checkInInformation2);
        this.selectListing = selectListing2;
        this.selectListingAmenities = ListUtils.newArrayList(selectListingAmenities2);
        this.selectRoomDescriptions = selectRoomDescriptions2;
        this.loading = false;
        updateListeners();
    }

    /* access modifiers changed from: 0000 */
    public void updateListeners() {
        if (this.initialDataLoaded) {
            setListingHasChanged();
        } else {
            this.initialDataLoaded = true;
        }
        Check.notNull(this.listing);
        Check.notNull(this.calendarRule);
        Check.notNull(this.averagePrices);
        Check.notNull(this.checkInInformation);
        notifyListeners(ManageListingDataController$$Lambda$5.lambdaFactory$());
    }

    /* access modifiers changed from: 0000 */
    public void setListingHasChanged() {
        this.listingHasChanged = true;
    }

    /* access modifiers changed from: 0000 */
    public boolean getListingHasChanged() {
        return this.listingHasChanged;
    }

    private void notifyListeners(Action1<UpdateListener> listenerAction) {
        for (UpdateListener updateListener : this.updateListeners) {
            listenerAction.call(updateListener);
        }
    }
}
