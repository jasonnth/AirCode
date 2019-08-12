package com.airbnb.rxgroups;

public class BaseObservableResubscriber {
    /* access modifiers changed from: protected */
    public void setTag(AutoResubscribingObserver target, String tag) {
        target.setTag(tag);
    }

    /* access modifiers changed from: protected */
    public void setTag(AutoTaggableObserver target, String tag) {
        target.setTag(tag);
    }
}
