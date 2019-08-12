package com.airbnb.android.cityregistration.activities;

import android.os.Bundle;
import com.airbnb.android.cityregistration.activities.CityRegistrationActivity;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CityRegistrationActivity$$Icepick<T extends CityRegistrationActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7668H = new Helper("com.airbnb.android.cityregistration.activities.CityRegistrationActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f7668H.getParcelable(state, "listing");
            target.listingRegistrationProcess = (ListingRegistrationProcess) f7668H.getParcelable(state, "listingRegistrationProcess");
            target.isLYS = f7668H.getBoolean(state, "isLYS");
            target.progress = f7668H.getBoxedFloat(state, "progress");
            target.currentInputGroupIndex = f7668H.getInt(state, "currentInputGroupIndex");
            target.answers = (HashMap) f7668H.getSerializable(state, "answers");
            target.numModalsLaunched = f7668H.getInt(state, "numModalsLaunched");
            target.standalonePage = f7668H.getBoolean(state, "standalonePage");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7668H.putParcelable(state, "listing", target.listing);
        f7668H.putParcelable(state, "listingRegistrationProcess", target.listingRegistrationProcess);
        f7668H.putBoolean(state, "isLYS", target.isLYS);
        f7668H.putBoxedFloat(state, "progress", target.progress);
        f7668H.putInt(state, "currentInputGroupIndex", target.currentInputGroupIndex);
        f7668H.putSerializable(state, "answers", target.answers);
        f7668H.putInt(state, "numModalsLaunched", target.numModalsLaunched);
        f7668H.putBoolean(state, "standalonePage", target.standalonePage);
    }
}
