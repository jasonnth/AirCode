package com.airbnb.rxgroups;

import p032rx.Emitter;
import p032rx.Emitter.BackpressureMode;
import p032rx.Observable;
import p032rx.Observable.Transformer;
import p032rx.Observer;
import p032rx.functions.Action1;

class GroupResubscriptionTransformer<T> implements Transformer<T, T> {
    /* access modifiers changed from: private */
    public final ManagedObservable<T> managedObservable;

    GroupResubscriptionTransformer(ManagedObservable<T> managedObservable2) {
        this.managedObservable = managedObservable2;
    }

    public Observable<T> call(final Observable<T> observable) {
        return Observable.create(new Action1<Emitter<T>>() {
            public void call(final Emitter<T> emitter) {
                GroupResubscriptionTransformer.this.managedObservable.resubscribe(observable, new Observer<T>() {
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
