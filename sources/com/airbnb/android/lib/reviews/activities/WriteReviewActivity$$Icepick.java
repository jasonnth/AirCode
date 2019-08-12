package com.airbnb.android.lib.reviews.activities;

import android.os.Bundle;
import com.airbnb.android.core.activities.SheetFlowActivity$$Icepick;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.lib.fragments.NPSFragment;
import com.airbnb.android.lib.reviews.activities.WriteReviewActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class WriteReviewActivity$$Icepick<T extends WriteReviewActivity> extends SheetFlowActivity$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9658H = new Helper("com.airbnb.android.lib.reviews.activities.WriteReviewActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.review = (Review) f9658H.getParcelable(state, NPSFragment.ARG_REVIEW);
            target.skippedPrivateFeedback = f9658H.getBoolean(state, "skippedPrivateFeedback");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9658H.putParcelable(state, NPSFragment.ARG_REVIEW, target.review);
        f9658H.putBoolean(state, "skippedPrivateFeedback", target.skippedPrivateFeedback);
    }
}
