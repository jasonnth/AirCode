package com.airbnb.android.p011p3;

import android.os.Bundle;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import com.airbnb.android.p011p3.ReviewsController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.p3.ReviewsController$$Icepick */
public class ReviewsController$$Icepick<T extends ReviewsController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10649H = new Helper("com.airbnb.android.p3.ReviewsController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reviews = f10649H.getParcelableArrayList(state, VerificationsAdapter.VERIFICATION_REVIEWS);
            target.translationState = (HashMap) f10649H.getSerializable(state, "translationState");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10649H.putParcelableArrayList(state, VerificationsAdapter.VERIFICATION_REVIEWS, target.reviews);
        f10649H.putSerializable(state, "translationState", target.translationState);
    }
}
