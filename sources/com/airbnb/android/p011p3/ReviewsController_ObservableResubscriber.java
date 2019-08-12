package com.airbnb.android.p011p3;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.p3.ReviewsController_ObservableResubscriber */
public class ReviewsController_ObservableResubscriber extends BaseObservableResubscriber {
    public ReviewsController_ObservableResubscriber(ReviewsController target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reviewsListener, "ReviewsController_reviewsListener");
        group.resubscribeAll(target.reviewsListener);
        setTag((AutoTaggableObserver) target.userFlagListener, "ReviewsController_userFlagListener");
        group.resubscribeAll(target.userFlagListener);
        setTag((AutoTaggableObserver) target.translationListener, "ReviewsController_translationListener");
        group.resubscribeAll(target.translationListener);
    }
}
