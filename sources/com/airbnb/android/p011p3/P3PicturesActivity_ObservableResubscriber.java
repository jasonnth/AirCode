package com.airbnb.android.p011p3;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.p3.P3PicturesActivity_ObservableResubscriber */
public class P3PicturesActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public P3PicturesActivity_ObservableResubscriber(P3PicturesActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingResponseRequestListener, "P3PicturesActivity_listingResponseRequestListener");
        group.resubscribeAll(target.listingResponseRequestListener);
    }
}
