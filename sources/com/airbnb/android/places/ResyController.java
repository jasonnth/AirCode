package com.airbnb.android.places;

import android.content.Intent;
import android.os.Bundle;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.datepicker.DatesFragment;
import com.airbnb.android.core.intents.QuickPayActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.PlaceActivity;
import com.airbnb.android.core.models.Restaurant;
import com.airbnb.android.core.models.RestaurantAvailability;
import com.airbnb.android.core.payments.models.Bill;
import com.airbnb.android.core.payments.models.Bill.BillItem;
import com.airbnb.android.places.activities.ResyActivity;
import com.airbnb.android.places.requests.RestaurantAvailabilityRequest;
import com.airbnb.android.places.responses.RestaurantAvailabilityResponse;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class ResyController {
    private static final String RESY_DATE_PICKER_FRAGMENT_TAG = "fragment_resy_date_picker";
    private static final int RESY_QUICK_PAY_REQUEST_CODE = 1112;
    private final AirActivity activity;
    private final boolean isResyActivity;
    private ResyUpdateListener listener;
    LoggingContextFactory loggingContextFactory;
    private final PlaceJitneyLogger placeJitneyLogger;
    private final RequestManager requestManager;
    final RequestListener<RestaurantAvailabilityResponse> restaurantAvailabilityRequestListener = new C0699RL().onResponse(ResyController$$Lambda$1.lambdaFactory$(this)).onError(ResyController$$Lambda$4.lambdaFactory$(this)).build();
    @State
    public ResyState resyState;

    public interface ResyControllerProvider {
        ResyController getResyController();
    }

    public interface ResyTimeSlotClickListener {
        void onClick(RestaurantAvailability restaurantAvailability);
    }

    public interface ResyUpdateListener {
        void onContentUpdated(ResyState resyState);
    }

    public ResyController(AirActivity activity2, RequestManager requestManager2) {
        this.activity = activity2;
        this.requestManager = requestManager2;
        requestManager2.subscribe(this);
        this.isResyActivity = activity2 instanceof ResyActivity;
        ((PlaceGraph) CoreApplication.instance(activity2).component()).inject(this);
        this.placeJitneyLogger = new PlaceJitneyLogger(this.loggingContextFactory, this);
    }

    public void goToResyPage() {
        this.activity.startActivity(ResyActivity.newIntent(this.activity, this.resyState));
    }

    public void setSelectedTimeSlot(RestaurantAvailability timeSlot) {
        this.resyState = this.resyState.toBuilder().selectedTime(timeSlot).build();
        updateListenerWithResyState();
    }

    public void goToQuickPay() {
        this.activity.startActivityForResult(QuickPayActivityIntents.intentForResyReservation(this.activity, this.resyState.makeCartItem(this.activity, this.resyState.selectedTime()), null), RESY_QUICK_PAY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESY_QUICK_PAY_REQUEST_CODE && resultCode == -1) {
            this.activity.startActivity(ReactNativeIntents.intentForItineraryPlaceCard(this.activity, ((BillItem) ((Bill) data.getParcelableExtra(QuickPayActivityIntents.RESULT_EXTRA_BILL)).billItems().get(0)).productMetadata().placeReservationId(), "", ""));
            if (this.isResyActivity) {
                this.activity.finish();
            }
        }
    }

    public void goToDatePicker() {
        this.activity.showModal(DatesFragment.forResy(this.resyState.date(), NavigationTag.PlaceActivityPDP), C7627R.C7629id.content_container, C7627R.C7629id.modal_container, true, RESY_DATE_PICKER_FRAGMENT_TAG);
        if (this.isResyActivity) {
            this.placeJitneyLogger.resyPageClickChangeDates(this.resyState.activityId());
        }
    }

    public void updateGuests(int newGuests) {
        if (this.isResyActivity) {
            this.placeJitneyLogger.resyPageChangeGuestCount(this.resyState.activityId(), this.resyState.guests() < newGuests);
        }
        this.resyState = this.resyState.toBuilder().guests(newGuests).build();
        makeResyRequest();
    }

    public void updateDate(AirDate date) {
        this.resyState = this.resyState.toBuilder().date(date).build();
        makeResyRequest();
    }

    private void makeResyRequest() {
        RestaurantAvailabilityRequest.forActivity(this.resyState.activityId(), this.resyState.date(), this.resyState.guests()).withListener((Observer) this.restaurantAvailabilityRequestListener).execute(this.requestManager);
        this.resyState = this.resyState.toBuilder().isLoading(true).selectedTime(null).build();
        updateListenerWithResyState();
    }

    /* access modifiers changed from: private */
    public void onRestaurantAvailabilitySuccess(List<RestaurantAvailability> availabilities) {
        if (availabilities == null) {
            availabilities = new ArrayList<>();
        }
        this.resyState = this.resyState.toBuilder().timeSlots(availabilities).isLoading(false).build();
        updateListenerWithResyState();
        if (!this.isResyActivity) {
            this.placeJitneyLogger.activityPDPLoadResy();
        }
    }

    /* access modifiers changed from: private */
    public void onRestaurantAvailabilityError(NetworkException e) {
        this.resyState = this.resyState.toBuilder().timeSlots(new ArrayList()).isLoading(false).build();
        updateListenerWithResyState();
        if (this.isResyActivity) {
            BugsnagWrapper.notify(e);
        }
    }

    public void attachResySticky(ResyUpdateListener listener2) {
        this.listener = listener2;
        if (this.resyState.isLoading()) {
            makeResyRequest();
        } else {
            updateListenerWithResyState();
        }
    }

    public void unbindListener() {
        this.listener = null;
    }

    public void setPlaceDetails(Restaurant restaurant, PlaceActivity placeActivity) {
        this.resyState = this.resyState.toBuilder().coverImage(placeActivity.getFirstCoverPhoto()).placeName(restaurant.getName()).showResy(true).build();
    }

    public void setResyState(ResyState resyState2) {
        this.resyState = resyState2;
    }

    public ResyState getResyState() {
        return this.resyState;
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState, ResyState defaultState) {
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        if (this.resyState == null) {
            setResyState(defaultState);
        }
    }

    private void updateListenerWithResyState() {
        if (this.listener != null) {
            this.listener.onContentUpdated(this.resyState);
        }
    }
}
