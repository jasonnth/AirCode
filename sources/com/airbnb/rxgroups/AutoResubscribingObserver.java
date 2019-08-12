package com.airbnb.rxgroups;

public abstract class AutoResubscribingObserver<T> implements TaggedObserver<T> {
    private String tag;

    public final String getTag() {
        return this.tag;
    }

    /* access modifiers changed from: 0000 */
    public void setTag(String tag2) {
        this.tag = tag2;
    }

    public void onCompleted() {
    }

    public void onError(Throwable e) {
    }

    public void onNext(T t) {
    }
}
