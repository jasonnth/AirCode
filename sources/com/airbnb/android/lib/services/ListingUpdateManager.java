package com.airbnb.android.lib.services;

import android.os.Bundle;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.events.ListingEvent.ListingUpdateFailedEvent;
import com.airbnb.android.core.events.ListingEvent.ListingUpdatedEvent;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.CreateListingRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ParcelStrap;
import com.squareup.otto.Bus;
import icepick.State;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import p032rx.Observer;

public class ListingUpdateManager {
    static final String APARTMENT = "apt";
    static final String BATHROOMS = "bathrooms";
    static final String BEDROOMS = "bedrooms";
    static final String BEDS = "beds";
    static final String CITY = "city";
    static final String COUNTRY = "country";
    static final String COUNTRY_CODE = "country_code";
    static final String IS_LOCATION_USER_DEFINED = "user_defined_location";
    static final String LAT = "lat";
    public static final String LISTING_ID = "listing_id";
    public static final String LISTING_TITLE = "name";
    static final String LNG = "lng";
    static final String MAX_GUESTS = "person_capacity";
    static final String PRICE = "listing_price";
    static final String PROPERTY_TYPE_ID = "property_type_id";
    public static final String SOURCE = "source";
    static final String SPACE_TYPE_ID = "room_type_category";
    static final String STATE = "state";
    static final String STRAP = "strap";
    static final String STREET = "street";
    static final String ZIPCODE = "zipcode";
    private final Bus bus;
    @State
    ParcelStrap fieldsInFlight = ParcelStrap.make();
    @State
    ParcelStrap fieldsToUpdate = ParcelStrap.make();
    @State
    Listing listing;
    final RequestListener<SimpleListingResponse> listingRequestListener = new C0699RL().onResponse(ListingUpdateManager$$Lambda$1.lambdaFactory$(this)).onError(ListingUpdateManager$$Lambda$2.lambdaFactory$(this)).onComplete(ListingUpdateManager$$Lambda$3.lambdaFactory$(this)).build();
    private final RequestManager requestManager;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ListingKey {
    }

    public ListingUpdateManager(Bus bus2, RequestManager requestManager2, Bundle savedInstanceState) {
        this.bus = bus2;
        this.requestManager = requestManager2;
        onRestoreInstanceState(savedInstanceState);
    }

    static /* synthetic */ void lambda$new$2(ListingUpdateManager listingUpdateManager, Boolean successful) {
        if (successful.booleanValue()) {
            listingUpdateManager.executeUpdate();
        }
    }

    private void executeUpdate() {
        if (!requestIsCurrentlyInFlight()) {
            this.fieldsInFlight.mix(this.fieldsToUpdate);
            this.fieldsToUpdate.clear();
            if (this.fieldsInFlight.isEmpty()) {
                return;
            }
            if (this.listing != null) {
                UpdateListingRequest.forFields(this.listing.getId(), this.fieldsInFlight.internal()).withListener((Observer) this.listingRequestListener).execute(this.requestManager);
            } else {
                CreateListingRequest.newInstance(this.fieldsInFlight.internal(), this.listingRequestListener).execute(this.requestManager);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateFail() {
        this.fieldsToUpdate.mix(this.fieldsInFlight, false);
        this.fieldsInFlight.clear();
        this.bus.post(new ListingUpdateFailedEvent(this.listing));
    }

    /* access modifiers changed from: private */
    public void onUpdateSuccess(Listing listing2) {
        this.listing = listing2;
        this.fieldsInFlight.clear();
        this.bus.post(new ListingUpdatedEvent(listing2));
    }

    public void setField(String field, String value) {
        this.fieldsToUpdate.mo9946kv(field, value);
        executeUpdate();
    }

    private boolean requestIsCurrentlyInFlight() {
        return !this.fieldsInFlight.isEmpty();
    }

    public boolean hasListing() {
        return this.listing != null;
    }

    public Listing getListing() {
        return (Listing) Check.notNull(this.listing);
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
    }
}
