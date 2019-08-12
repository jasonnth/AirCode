package com.airbnb.rxgroups;

import p032rx.Emitter;
import p032rx.Emitter.BackpressureMode;
import p032rx.Observable;
import p032rx.Observable.Transformer;
import p032rx.Observer;
import p032rx.functions.Action1;

class GroupSubscriptionTransformer<T> implements Transformer<T, T> {
    /* access modifiers changed from: private */
    public final ObservableGroup group;
    /* access modifiers changed from: private */
    public final String observableTag;
    /* access modifiers changed from: private */
    public final String observerTag;

    GroupSubscriptionTransformer(ObservableGroup group2, String observerTag2, String observableTag2) {
        this.group = group2;
        this.observableTag = observableTag2;
        this.observerTag = observerTag2;
    }

    public Observable<T> call(final Observable<T> observable) {
        return Observable.create(new Action1<Emitter<T>>() {
            public void call(final Emitter<T> emitter) {
                GroupSubscriptionTransformer.this.group.add(GroupSubscriptionTransformer.this.observerTag, GroupSubscriptionTransformer.this.observableTag, observable, new Observer<T>() {
                    public void onCompleted() {
                        emitter.onCompleted();
                    }

                    public void onError(Throwable e) {
                        emitter.onError(e);
                    }

                    public void onNext(T t) {
                        emitter.onNext(t);
                    }
                });
            }
        }, BackpressureMode.BUFFER);
    }
}
