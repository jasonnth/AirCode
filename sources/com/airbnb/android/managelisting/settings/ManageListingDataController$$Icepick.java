package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.CheckInGuide;
import com.airbnb.android.core.models.HomeCollectionApplication;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.airbnb.android.core.models.ListingLongTermDiscountValues;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.models.SelectListing;
import com.airbnb.android.core.models.SelectRoomDescription;
import com.airbnb.android.managelisting.settings.ManageListingDataController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingDataController$$Icepick<T extends ManageListingDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10178H = new Helper("com.airbnb.android.managelisting.settings.ManageListingDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.loading = f10178H.getBoolean(state, "loading");
            target.initialDataLoaded = f10178H.getBoolean(state, "initialDataLoaded");
            target.listing = (Listing) f10178H.getParcelable(state, "listing");
            target.calendarRule = (CalendarRule) f10178H.getParcelable(state, "calendarRule");
            target.averagePrices = (ListingLongTermDiscountValues) f10178H.getParcelable(state, "averagePrices");
            target.listingHasChanged = f10178H.getBoolean(state, "listingHasChanged");
            target.checkInTimeOptions = (ListingCheckInTimeOptions) f10178H.getParcelable(state, "checkInTimeOptions");
            target.ibPromoDismissed = f10178H.getBoolean(state, "ibPromoDismissed");
            target.listingRooms = f10178H.getParcelableArrayList(state, "listingRooms");
            target.checkInGuide = (CheckInGuide) f10178H.getParcelable(state, "checkInGuide");
            target.collectionApplication = (HomeCollectionApplication) f10178H.getParcelable(state, "collectionApplication");
            target.checkInInformation = f10178H.getParcelableArrayList(state, "checkInInformation");
            target.showMarketplaceOverride = f10178H.getBoolean(state, "showMarketplaceOverride");
            target.selectListing = (SelectListing) f10178H.getParcelable(state, "selectListing");
            target.selectListingAmenities = f10178H.getIntegerArrayList(state, "selectListingAmenities");
            target.selectRoomDescriptions = (SelectRoomDescription) f10178H.getParcelable(state, "selectRoomDescriptions");
            target.listingRegistrationProcess = (ListingRegistrationProcess) f10178H.getParcelable(state, "listingRegistrationProcess");
            target.nestedListingsById = (HashMap) f10178H.getSerializable(state, "nestedListingsById");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10178H.putBoolean(state, "loading", target.loading);
        f10178H.putBoolean(state, "initialDataLoaded", target.initialDataLoaded);
        f10178H.putParcelable(state, "listing", target.listing);
        f10178H.putParcelable(state, "calendarRule", target.calendarRule);
        f10178H.putParcelable(state, "averagePrices", target.averagePrices);
        f10178H.putBoolean(state, "listingHasChanged", target.listingHasChanged);
        f10178H.putParcelable(state, "checkInTimeOptions", target.checkInTimeOptions);
        f10178H.putBoolean(state, "ibPromoDismissed", target.ibPromoDismissed);
        f10178H.putParcelableArrayList(state, "listingRooms", target.listingRooms);
        f10178H.putParcelable(state, "checkInGuide", target.checkInGuide);
        f10178H.putParcelable(state, "collectionApplication", target.collectionApplication);
        f10178H.putParcelableArrayList(state, "checkInInformation", target.checkInInformation);
        f10178H.putBoolean(state, "showMarketplaceOverride", target.showMarketplaceOverride);
        f10178H.putParcelable(state, "selectListing", target.selectListing);
        f10178H.putIntegerArrayList(state, "selectListingAmenities", target.selectListingAmenities);
        f10178H.putParcelable(state, "selectRoomDescriptions", target.selectRoomDescriptions);
        f10178H.putParcelable(state, "listingRegistrationProcess", target.listingRegistrationProcess);
        f10178H.putSerializable(state, "nestedListingsById", target.nestedListingsById);
    }
}
