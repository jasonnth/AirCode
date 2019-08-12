package com.airbnb.android.cityregistration.fragments;

import android.os.Bundle;
import com.airbnb.android.cityregistration.fragments.CityRegistrationInputGroupFragment;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class CityRegistrationInputGroupFragment$$Icepick<T extends CityRegistrationInputGroupFragment> extends CityRegistrationBaseFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7674H = new Helper("com.airbnb.android.cityregistration.fragments.CityRegistrationInputGroupFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listingAddress = (AirAddress) f7674H.getParcelable(state, "listingAddress");
            target.nextButtonClicked = f7674H.getBoolean(state, "nextButtonClicked");
            target.saveButtonClicked = f7674H.getBoolean(state, "saveButtonClicked");
            target.currentQuestionBeingUpdated = (ListingRegistrationQuestion) f7674H.getParcelable(state, "currentQuestionBeingUpdated");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7674H.putParcelable(state, "listingAddress", target.listingAddress);
        f7674H.putBoolean(state, "nextButtonClicked", target.nextButtonClicked);
        f7674H.putBoolean(state, "saveButtonClicked", target.saveButtonClicked);
        f7674H.putParcelable(state, "currentQuestionBeingUpdated", target.currentQuestionBeingUpdated);
    }
}
