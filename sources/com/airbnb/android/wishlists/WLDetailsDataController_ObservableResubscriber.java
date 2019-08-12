package com.airbnb.android.wishlists;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class WLDetailsDataController_ObservableResubscriber extends BaseObservableResubscriber {
    public WLDetailsDataController_ObservableResubscriber(WLDetailsDataController target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingsRequestListener, "WLDetailsDataController_listingsRequestListener");
        group.resubscribeAll(target.listingsRequestListener);
        setTag((AutoTaggableObserver) target.placesRequestListener, "WLDetailsDataController_placesRequestListener");
        group.resubscribeAll(target.placesRequestListener);
        setTag((AutoTaggableObserver) target.storyArticleRequestListener, "WLDetailsDataController_storyArticleRequestListener");
        group.resubscribeAll(target.storyArticleRequestListener);
        setTag((AutoTaggableObserver) target.placeActivitiesRequestListener, "WLDetailsDataController_placeActivitiesRequestListener");
        group.resubscribeAll(target.placeActivitiesRequestListener);
        setTag((AutoTaggableObserver) target.tripsRequestListener, "WLDetailsDataController_tripsRequestListener");
        group.resubscribeAll(target.tripsRequestListener);
        setTag((AutoTaggableObserver) target.votingRequestListener, "WLDetailsDataController_votingRequestListener");
        group.resubscribeAll(target.votingRequestListener);
    }
}
