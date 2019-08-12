package com.airbnb.android.p011p3;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.p3.P3ReviewSearchFragment_ObservableResubscriber */
public class P3ReviewSearchFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public P3ReviewSearchFragment_ObservableResubscriber(P3ReviewSearchFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.keywordsRequestListener, "P3ReviewSearchFragment_keywordsRequestListener");
        group.resubscribeAll(target.keywordsRequestListener);
        setTag((AutoTaggableObserver) target.reviewSearchResultsRequestListener, "P3ReviewSearchFragment_reviewSearchResultsRequestListener");
        group.resubscribeAll(target.reviewSearchResultsRequestListener);
    }
}
