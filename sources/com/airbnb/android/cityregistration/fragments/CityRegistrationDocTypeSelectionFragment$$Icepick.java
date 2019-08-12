package com.airbnb.android.cityregistration.fragments;

import android.os.Bundle;
import com.airbnb.android.cityregistration.fragments.CityRegistrationDocTypeSelectionFragment;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class CityRegistrationDocTypeSelectionFragment$$Icepick<T extends CityRegistrationDocTypeSelectionFragment> extends CityRegistrationBaseFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7673H = new Helper("com.airbnb.android.cityregistration.fragments.CityRegistrationDocTypeSelectionFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.question = (ListingRegistrationQuestion) f7673H.getParcelable(state, "question");
            target.selectedDocType = f7673H.getString(state, "selectedDocType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7673H.putParcelable(state, "question", target.question);
        f7673H.putString(state, "selectedDocType", target.selectedDocType);
    }
}
