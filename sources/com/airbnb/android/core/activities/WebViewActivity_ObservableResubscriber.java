package com.airbnb.android.core.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class WebViewActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public WebViewActivity_ObservableResubscriber(WebViewActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.sessionRequestListener, "WebViewActivity_sessionRequestListener");
        group.resubscribeAll(target.sessionRequestListener);
    }
}
