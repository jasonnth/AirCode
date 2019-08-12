package com.airbnb.android.cityregistration.fragments;

import android.os.Bundle;
import com.airbnb.android.cityregistration.fragments.CityRegistrationDocReviewFragment;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class CityRegistrationDocReviewFragment$$Icepick<T extends CityRegistrationDocReviewFragment> extends CityRegistrationBaseFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7672H = new Helper("com.airbnb.android.cityregistration.fragments.CityRegistrationDocReviewFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.question = (ListingRegistrationQuestion) f7672H.getParcelable(state, "question");
            target.filePath = f7672H.getString(state, "filePath");
            target.url = f7672H.getString(state, "url");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7672H.putParcelable(state, "question", target.question);
        f7672H.putString(state, "filePath", target.filePath);
        f7672H.putString(state, "url", target.url);
    }
}
