package com.airbnb.android.lib.fragments.reviews;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.reviews.ReservationReviewsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReservationReviewsFragment$$Icepick<T extends ReservationReviewsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9583H = new Helper("com.airbnb.android.lib.fragments.reviews.ReservationReviewsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.allReviews = f9583H.getParcelableArrayList(state, "allReviews");
            target.showLoading = f9583H.getBoolean(state, "showLoading");
            target.translationState = (HashMap) f9583H.getSerializable(state, "translationState");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9583H.putParcelableArrayList(state, "allReviews", target.allReviews);
        f9583H.putBoolean(state, "showLoading", target.showLoading);
        f9583H.putSerializable(state, "translationState", target.translationState);
    }
}
