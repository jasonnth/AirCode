package com.airbnb.android.itinerary.controllers;

import android.os.Bundle;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestExecutor;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.TimelineMetadata;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.itinerary.data.ItineraryTableOpenHelper;
import com.airbnb.android.itinerary.data.models.BaseItineraryItem;
import com.airbnb.android.itinerary.data.models.BaseReservationObject;
import com.airbnb.android.itinerary.data.models.FlightEntryPointItem;
import com.airbnb.android.itinerary.data.models.FreeTimeItem;
import com.airbnb.android.itinerary.data.models.TimelineTrip;
import com.airbnb.android.itinerary.data.models.TripEvent;
import com.airbnb.android.itinerary.data.models.TripEventCardType;
import com.airbnb.android.itinerary.listeners.ItineraryDataChangedListener;
import com.airbnb.android.itinerary.requests.ExperienceReservationObjectRequest;
import com.airbnb.android.itinerary.requests.HomeReservationObjectRequest;
import com.airbnb.android.itinerary.requests.PendingTimelineRequest;
import com.airbnb.android.itinerary.requests.PlaceReservationObjectRequest;
import com.airbnb.android.itinerary.requests.SuggestionsRequest;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.itinerary.requests.TripRequest;
import com.airbnb.android.itinerary.responses.ExperienceReservationObjectResponse;
import com.airbnb.android.itinerary.responses.HomeReservationObjectResponse;
import com.airbnb.android.itinerary.responses.PlaceReservationObjectResponse;
import com.airbnb.android.itinerary.responses.SuggestionsResponse;
import com.airbnb.android.itinerary.responses.TimelineResponse;
import com.airbnb.android.itinerary.responses.TripResponse;
import com.airbnb.android.itinerary.utils.ItineraryUtils;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.ImmutableList;
import com.google.gson.jpush.Gson;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import p032rx.Observable;
import p032rx.Observer;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.schedulers.Schedulers;

public class ItineraryDataController {
    private static final String TAG = "ItineraryDataController";
    private static final Comparator<BaseItineraryItem> TIMELINE_TRIP_COMPARATOR = new Comparator<BaseItineraryItem>() {
        public int compare(BaseItineraryItem o1, BaseItineraryItem o2) {
            return ItineraryDataController.compareItineraryItems(o2, o1);
        }
    };
    private static final Comparator<BaseItineraryItem> TRIP_EVENT_COMPARATOR = new Comparator<BaseItineraryItem>() {
        public int compare(BaseItineraryItem o1, BaseItineraryItem o2) {
            return ItineraryDataController.compareItineraryItems(o1, o2);
        }
    };
    @State
    String currentConfirmationCode;
    private final Set<ItineraryDataChangedListener> dataChangedListenerSet;
    private final NonResubscribableRequestListener<ExperienceReservationObjectResponse> experienceReservationObjectListener;
    @State
    FreeTimeItem freeTimeItem;
    private final NonResubscribableRequestListener<HomeReservationObjectResponse> homeReservationObjectListener;
    private ItineraryTableOpenHelper itineraryTableOpenHelper;
    private final NonResubscribableRequestListener<HomeReservationObjectResponse> pendingHomeReservationObjectListener;
    TimelineMetadata pendingMetadata;
    final RequestListener<TimelineResponse> pendingTimelineListener;
    List<TimelineTrip> pendingTimelineTripList;
    private ItineraryPerformanceAnalytics performanceAnalytics;
    private final NonResubscribableRequestListener<PlaceReservationObjectResponse> placeReservationObjectListener;
    private RequestExecutor requestManager;
    @State
    String reservationObjectId;
    private SharedPrefsHelper sharedPrefsHelper;
    private ItinerarySnackbarListener snackbarListener;
    final RequestListener<SuggestionsResponse> suggestionsListener;
    final RequestListener<TimelineResponse> timelineListener;
    @State
    int timelineTripPaginationOffset;
    SortedSet<BaseItineraryItem> timelineTripsSet;
    SortedSet<BaseItineraryItem> tripEventsSet;
    final RequestListener<TripResponse> tripScheduleCardsListener;

    public interface ItinerarySnackbarListener {
        void showNetworkErrorSnackbar(NetworkException networkException);
    }

    public ItineraryDataController(ItineraryTableOpenHelper itineraryTableOpenHelper2, RequestExecutor requestExecutor, ItineraryPerformanceAnalytics performanceAnalytics2, SharedPrefsHelper sharedPrefsHelper2) {
        this.dataChangedListenerSet = new HashSet();
        this.timelineTripsSet = new TreeSet(TIMELINE_TRIP_COMPARATOR);
        this.tripEventsSet = new TreeSet(TRIP_EVENT_COMPARATOR);
        this.timelineListener = new C0699RL().onResponse(ItineraryDataController$$Lambda$1.lambdaFactory$(this)).onError(ItineraryDataController$$Lambda$4.lambdaFactory$(this)).build();
        this.pendingTimelineListener = new C0699RL().onResponse(ItineraryDataController$$Lambda$5.lambdaFactory$(this)).onError(ItineraryDataController$$Lambda$6.lambdaFactory$()).build();
        this.tripScheduleCardsListener = new C0699RL().onResponse(ItineraryDataController$$Lambda$7.lambdaFactory$()).onError(ItineraryDataController$$Lambda$8.lambdaFactory$(this)).build();
        this.suggestionsListener = new C0699RL().onResponse(ItineraryDataController$$Lambda$9.lambdaFactory$(this)).onError(ItineraryDataController$$Lambda$10.lambdaFactory$()).build();
        this.homeReservationObjectListener = new C0699RL().onResponse(ItineraryDataController$$Lambda$11.lambdaFactory$(this)).onError(ItineraryDataController$$Lambda$12.lambdaFactory$(this)).buildWithoutResubscription();
        this.pendingHomeReservationObjectListener = new C0699RL().onResponse(ItineraryDataController$$Lambda$13.lambdaFactory$(this)).onError(ItineraryDataController$$Lambda$14.lambdaFactory$(this)).buildWithoutResubscription();
        this.placeReservationObjectListener = new C0699RL().onResponse(ItineraryDataController$$Lambda$15.lambdaFactory$(this)).onError(ItineraryDataController$$Lambda$16.lambdaFactory$(this)).buildWithoutResubscription();
        this.experienceReservationObjectListener = new C0699RL().onResponse(ItineraryDataController$$Lambda$17.lambdaFactory$(this)).onError(ItineraryDataController$$Lambda$18.lambdaFactory$(this)).buildWithoutResubscription();
        this.requestManager = requestExecutor;
        this.itineraryTableOpenHelper = itineraryTableOpenHelper2;
        this.performanceAnalytics = performanceAnalytics2;
        this.sharedPrefsHelper = sharedPrefsHelper2;
    }

    public ItineraryDataController(ItineraryTableOpenHelper itineraryTableOpenHelper2, RequestManager requestManager2, ItineraryPerformanceAnalytics performanceAnalytics2, SharedPrefsHelper sharedPrefsHelper2, ItinerarySnackbarListener snackbarListener2) {
        this(itineraryTableOpenHelper2, requestManager2, performanceAnalytics2, sharedPrefsHelper2);
        requestManager2.subscribe(this);
        this.snackbarListener = snackbarListener2;
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void addDataChangedListener(ItineraryDataChangedListener listener) {
        Check.state(this.dataChangedListenerSet.add(listener), "listener was already added to set");
    }

    public void removeDataChangedListener(ItineraryDataChangedListener listener) {
        Check.state(this.dataChangedListenerSet.remove(listener), "listener did not exist in set");
    }

    public void fetchPendingTimelineTrips() {
        PendingTimelineRequest.newInstance(TimelineRequest.FORMAT_PENDING, true).withListener((Observer) this.pendingTimelineListener).doubleResponse().execute(this.requestManager);
    }

    public List<TimelineTrip> getPendingTimelineTripList() {
        return this.pendingTimelineTripList;
    }

    public TimelineMetadata getPendingMetadata() {
        return this.pendingMetadata;
    }

    public boolean isFetchingNextPageForTimelineTrips() {
        return this.timelineTripPaginationOffset != 0;
    }

    public void fetchTimelineTrips(boolean fetchFromNetwork) {
        Observable.fromCallable(ItineraryDataController$$Lambda$19.lambdaFactory$(this)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ItineraryDataController$$Lambda$20.lambdaFactory$(this));
        if (fetchFromNetwork) {
            fetchTimelineTripsFromNetwork(0);
        }
    }

    public void fetchTimelineTripsFromNetwork(int offset) {
        this.timelineTripPaginationOffset = offset;
        TimelineRequest.newInstance(TimelineRequest.FORMAT_BUNDLE, false, offset, 10).withListener((Observer) this.timelineListener).skipCache().execute(this.requestManager);
    }

    public int getTimelineTripCount() {
        return this.timelineTripsSet.size() - (FeatureToggles.showFlightEntryPoint(this.sharedPrefsHelper) ? 1 : 0);
    }

    public Collection<BaseItineraryItem> getTimelineTrips() {
        return this.timelineTripsSet;
    }

    public List<BaseItineraryItem> getUnbundledTimelineTrips(Collection<BaseItineraryItem> list) {
        List<BaseItineraryItem> unbundledList = new ArrayList<>();
        for (BaseItineraryItem item : list) {
            if (!(item instanceof TimelineTrip) || ((TimelineTrip) item).should_bundle().booleanValue()) {
                unbundledList.add(item);
            } else {
                unbundledList.addAll(getUnbundledTripEventList((TimelineTrip) item));
            }
        }
        return unbundledList;
    }

    public BaseItineraryItem getUpcomingItem(List<BaseItineraryItem> list, boolean isTimeline) {
        if (ListUtils.isEmpty((Collection<?>) list)) {
            return null;
        }
        BaseItineraryItem upcomingItem = null;
        for (BaseItineraryItem item : list) {
            if (ItineraryUtils.isDuringOrUpcoming(item, isTimeline) && (upcomingItem == null || item.getStartsAt().isBefore(upcomingItem.getStartsAt()))) {
                upcomingItem = item;
            }
        }
        return upcomingItem;
    }

    public void removeFlightEntryPoint(FlightEntryPointItem flightEntryPointItem) {
        this.sharedPrefsHelper.setShowFlightEntryPoint(false);
        this.timelineTripsSet.remove(flightEntryPointItem);
        for (ItineraryDataChangedListener dataChangedListener : this.dataChangedListenerSet) {
            dataChangedListener.onTimelineContentUpdated(true);
        }
    }

    private List<TripEvent> getUnbundledTripEventList(TimelineTrip timelineTrip) {
        List<TripEvent> tripEvents = this.itineraryTableOpenHelper.getTripEventsByTripConfirmationCode(timelineTrip.confirmation_code());
        if (!ItineraryUtils.isSingleHome(tripEvents)) {
            return tripEvents;
        }
        TripEvent tripEvent = this.itineraryTableOpenHelper.getTripEventByTripConfirmationCodeAndCardType(timelineTrip.confirmation_code(), TripEventCardType.Checkin);
        if (tripEvent != null) {
            return Collections.singletonList(tripEvent);
        }
        return Collections.emptyList();
    }

    private void notifyTimelineContentUpdated(List<BaseItineraryItem> itineraryCards) {
        if (!ListUtils.isEmpty((Collection<?>) itineraryCards)) {
            this.timelineTripsSet.clear();
            this.timelineTripsSet.addAll(itineraryCards);
            if (FeatureToggles.showFlightEntryPoint(this.sharedPrefsHelper)) {
                this.timelineTripsSet.add(FlightEntryPointItem.getDefaultFlightEntryPointItem());
            }
        }
        for (ItineraryDataChangedListener dataChangedListener : this.dataChangedListenerSet) {
            dataChangedListener.onTimelineContentUpdated(!ListUtils.isEmpty((Collection<?>) itineraryCards));
        }
    }

    /* access modifiers changed from: private */
    public void handleTimelineTripDatabaseResults(List<TimelineTrip> list) {
        if (!ListUtils.isEmpty((Collection<?>) list)) {
            notifyTimelineContentUpdated(ItineraryUtils.getBaseItineraryItemList(list));
        }
    }

    /* access modifiers changed from: private */
    public void triggerRefreshForTimelineTrips(int numUpdated) {
        if (numUpdated > 0) {
            fetchTimelineTrips(false);
        }
    }

    public void fetchTripEvents(String confirmationCode) {
        this.currentConfirmationCode = confirmationCode;
        Observable.fromCallable(ItineraryDataController$$Lambda$21.lambdaFactory$(this, confirmationCode)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ItineraryDataController$$Lambda$22.lambdaFactory$(this));
    }

    public void fetchTripEventsFromNetwork(String confirmationCode) {
        this.currentConfirmationCode = confirmationCode;
        TripRequest.newInstance(confirmationCode).withListener((Observer) this.tripScheduleCardsListener).doubleResponse().execute(this.requestManager);
    }

    public void fetchSuggestionsFromNetwork(String confirmationCode) {
        TimelineTrip timelineTrip = this.itineraryTableOpenHelper.getTimelineTripByConfirmationCode(confirmationCode);
        if (timelineTrip != null) {
            this.freeTimeItem = getFreeTimeItem(timelineTrip.trip_schedule_cards());
            if (this.freeTimeItem != null && confirmationCode.equals(this.freeTimeItem.confirmationCode())) {
                SuggestionsRequest.newInstance(this.freeTimeItem).withListener((Observer) this.suggestionsListener).doubleResponse().execute(this.requestManager);
            }
        }
    }

    public Collection<BaseItineraryItem> getTripEventsList() {
        return this.tripEventsSet;
    }

    private void notifyTripContentUpdated(List<BaseItineraryItem> itineraryCards) {
        this.tripEventsSet.clear();
        this.tripEventsSet.addAll(itineraryCards);
        if (this.freeTimeItem != null && this.currentConfirmationCode.equals(this.freeTimeItem.confirmationCode()) && !ListUtils.isEmpty((Collection<?>) this.freeTimeItem.suggestions())) {
            this.tripEventsSet.add(this.freeTimeItem);
        }
        for (ItineraryDataChangedListener dataChangedListener : this.dataChangedListenerSet) {
            dataChangedListener.onTripContentUpdated();
        }
    }

    private void notifyTripContentUpdatedWithSuggestions(FreeTimeItem freeTimeItem2) {
        this.tripEventsSet.add(freeTimeItem2);
        notifyTripContentUpdated(ImmutableList.copyOf((Collection<? extends E>) this.tripEventsSet));
    }

    /* access modifiers changed from: private */
    public void handleTripEventsDatabaseResults(List<TripEvent> list) {
        notifyTripContentUpdated(ItineraryUtils.getBaseItineraryItemList(list));
    }

    private int diffWithServer(List<String> serverConfirmationCodes) {
        int count = 0;
        for (String confirmationCode : this.itineraryTableOpenHelper.getAllTimelineTripConfirmationCodes()) {
            if (!serverConfirmationCodes.contains(confirmationCode)) {
                count += this.itineraryTableOpenHelper.deleteTimelineTrip(confirmationCode) ? 1 : 0;
            }
        }
        return count;
    }

    /* access modifiers changed from: private */
    public static int compareItineraryItems(BaseItineraryItem o1, BaseItineraryItem o2) {
        int startsAtCmp = o1.getStartsAt().compareTo(o2.getStartsAt());
        return startsAtCmp != 0 ? startsAtCmp : o1.getId().compareTo(o2.getId());
    }

    private FreeTimeItem getFreeTimeItem(List<TripEvent> tripEvents) {
        if (!ListUtils.isEmpty((Collection<?>) tripEvents)) {
            for (TripEvent tripEvent : tripEvents) {
                if (TripEventCardType.Freetime.equals(tripEvent.card_type())) {
                    return FreeTimeItem.builder().startsAt(tripEvent.starts_at()).endsAt(tripEvent.ends_at()).confirmationCode(tripEvent.schedule_confirmation_code()).build();
                }
            }
        }
        return null;
    }

    public void fetchPendingHomeReservation(String id) {
        this.reservationObjectId = id;
        HomeReservationObjectRequest.newInstance(id, true).withListener((Observer) this.pendingHomeReservationObjectListener).doubleResponse().execute(this.requestManager);
    }

    public void fetchHomeReservation(String id, boolean fetchFromNetwork) {
        this.reservationObjectId = id;
        Observable.fromCallable(ItineraryDataController$$Lambda$23.lambdaFactory$(this, id)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ItineraryDataController$$Lambda$24.lambdaFactory$(this));
        if (fetchFromNetwork) {
            fetchHomeReservationFromNetwork(id);
        }
    }

    public void fetchHomeReservationFromNetwork(String id) {
        this.reservationObjectId = id;
        HomeReservationObjectRequest.newInstance(id, false).withListener((Observer) this.homeReservationObjectListener).skipCache().execute(this.requestManager);
    }

    public void fetchPlaceReservation(String id, boolean fetchFromNetwork) {
        this.reservationObjectId = id;
        Observable.fromCallable(ItineraryDataController$$Lambda$25.lambdaFactory$(this, id)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ItineraryDataController$$Lambda$26.lambdaFactory$(this));
        if (fetchFromNetwork) {
            fetchPlaceReservationFromNetwork(id);
        }
    }

    public void fetchPlaceReservationFromNetwork(String id) {
        this.reservationObjectId = id;
        PlaceReservationObjectRequest.newInstance(id).withListener((Observer) this.placeReservationObjectListener).skipCache().execute(this.requestManager);
    }

    public void fetchExperienceReservation(String id, boolean fetchFromNetwork) {
        this.reservationObjectId = id;
        Observable.fromCallable(ItineraryDataController$$Lambda$27.lambdaFactory$(this, id)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ItineraryDataController$$Lambda$28.lambdaFactory$(this));
        if (fetchFromNetwork) {
            fetchExperienceReservationFromNetwork(id);
        }
    }

    public void fetchExperienceReservationFromNetwork(String id) {
        this.reservationObjectId = id;
        ExperienceReservationObjectRequest.newInstance(id).withListener((Observer) this.experienceReservationObjectListener).skipCache().execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void handleReservationObjectDatabaseResults(BaseReservationObject reservationObject) {
        if (reservationObject != null) {
            notifyReservationObjectContentUpdated(reservationObject, (String) null);
        }
    }

    /* access modifiers changed from: private */
    public void triggerRefreshForHomeReservationObject(int numUpdated) {
        if (numUpdated > 0) {
            fetchHomeReservation(this.reservationObjectId, false);
        }
    }

    /* access modifiers changed from: private */
    public void triggerRefreshForExperienceReservationObject(int numUpdated) {
        if (numUpdated > 0) {
            fetchExperienceReservation(this.reservationObjectId, false);
        }
    }

    /* access modifiers changed from: private */
    public void triggerRefreshForPlaceReservationObject(int numUpdated) {
        if (numUpdated > 0) {
            fetchPlaceReservation(this.reservationObjectId, false);
        }
    }

    /* access modifiers changed from: private */
    public void notifyReservationObjectContentUpdated(BaseReservationObject reservation, String errorMessage) {
        for (ItineraryDataChangedListener dataChangedListener : this.dataChangedListenerSet) {
            dataChangedListener.onReservationObjectContentUpdated(reservation != null ? reservation.getReservation() : null, errorMessage);
        }
    }

    /* access modifiers changed from: private */
    public void notifyReservationObjectContentUpdated(Object reservation, String errorMessage) {
        for (ItineraryDataChangedListener dataChangedListener : this.dataChangedListenerSet) {
            dataChangedListener.onReservationObjectContentUpdated(new Gson().toJson(reservation), errorMessage);
        }
    }

    public String getReservationObjectId() {
        return this.reservationObjectId;
    }

    static /* synthetic */ void lambda$new$7(ItineraryDataController itineraryDataController, TimelineResponse response) {
        if (itineraryDataController.timelineTripPaginationOffset == 0) {
            Observable.fromCallable(ItineraryDataController$$Lambda$35.lambdaFactory$(itineraryDataController, response)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ItineraryDataController$$Lambda$36.lambdaFactory$(itineraryDataController));
        }
        itineraryDataController.timelineTripPaginationOffset = 0;
        if (ListUtils.isEmpty((Collection<?>) response.timelineTrips)) {
            itineraryDataController.notifyTimelineContentUpdated(Collections.emptyList());
        } else {
            Observable.fromCallable(ItineraryDataController$$Lambda$37.lambdaFactory$(itineraryDataController, response)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ItineraryDataController$$Lambda$38.lambdaFactory$(itineraryDataController));
        }
    }

    static /* synthetic */ void lambda$new$8(ItineraryDataController itineraryDataController, AirRequestNetworkException e) {
        itineraryDataController.timelineTripPaginationOffset = 0;
        itineraryDataController.notifyTimelineContentUpdated(Collections.emptyList());
        itineraryDataController.performanceAnalytics.trackTimelineLoadFailed();
        if (itineraryDataController.snackbarListener == null) {
            return;
        }
        if (itineraryDataController.timelineTripPaginationOffset != 0 || ListUtils.isEmpty((Collection<?>) itineraryDataController.timelineTripsSet)) {
            itineraryDataController.snackbarListener.showNetworkErrorSnackbar(e);
        }
    }

    static /* synthetic */ void lambda$new$9(ItineraryDataController itineraryDataController, TimelineResponse response) {
        for (ItineraryDataChangedListener dataChangedListener : itineraryDataController.dataChangedListenerSet) {
            itineraryDataController.pendingTimelineTripList = response.timelineTrips;
            itineraryDataController.pendingMetadata = response.metadata;
            dataChangedListener.onPendingContentUpdated();
        }
    }

    static /* synthetic */ void lambda$new$10(AirRequestNetworkException e) {
    }

    static /* synthetic */ void lambda$new$11(TripResponse response) {
    }

    static /* synthetic */ void lambda$new$13(ItineraryDataController itineraryDataController, SuggestionsResponse response) {
        if (!ListUtils.isEmpty((Collection<?>) response.suggestions) && itineraryDataController.freeTimeItem != null) {
            itineraryDataController.freeTimeItem = itineraryDataController.freeTimeItem.toBuilder().suggestions(response.suggestions).build();
            itineraryDataController.notifyTripContentUpdatedWithSuggestions(itineraryDataController.freeTimeItem);
        }
    }

    static /* synthetic */ void lambda$new$14(AirRequestNetworkException e) {
    }
}
