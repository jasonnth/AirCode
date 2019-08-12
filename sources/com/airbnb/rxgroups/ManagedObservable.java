package com.airbnb.rxgroups;

import p032rx.Observable;
import p032rx.Observer;
import p032rx.functions.Action0;

class ManagedObservable<T> implements RequestSubscription {
    private boolean locked = true;
    private Observable<T> observable;
    private final String observableTag;
    private Observer<? super T> observer;
    private final String observerTag;
    private final SubscriptionProxy<T> proxy;

    ManagedObservable(String observerTag2, String observableTag2, Observable<T> observable2, Observer<? super T> observer2, Action0 onTerminate) {
        this.observableTag = observableTag2;
        this.observerTag = observerTag2;
        this.observer = observer2;
        this.proxy = SubscriptionProxy.create(observable2, onTerminate);
        this.observable = this.proxy.observable();
    }

    public boolean isCancelled() {
        return this.proxy.isCancelled();
    }

    public void cancel() {
        this.proxy.cancel();
        this.observer = null;
    }

    /* access modifiers changed from: 0000 */
    public void lock() {
        this.locked = true;
        this.proxy.unsubscribe();
    }

    public void unsubscribe() {
        this.proxy.unsubscribe();
        this.observer = null;
    }

    public boolean isUnsubscribed() {
        return this.proxy.isUnsubscribed();
    }

    /* access modifiers changed from: 0000 */
    public void unlock() {
        this.locked = false;
        if (this.observer != null) {
            this.proxy.subscribe(this.observable, this.observer);
        }
    }

    /* access modifiers changed from: 0000 */
    public Observable<T> observable() {
        return this.proxy.observable();
    }

    /* access modifiers changed from: 0000 */
    public void resubscribe(Observable<T> observable2, Observer<? super T> observer2) {
        this.observable = observable2;
        this.observer = (Observer) Preconditions.checkNotNull(observer2);
        if (!this.locked) {
            this.proxy.subscribe(observable2, observer2);
        }
    }

    public String toString() {
        return "ManagedObservable{observableTag='" + this.observableTag + '\'' + ", observerTag='" + this.observerTag + '\'' + ", locked=" + this.locked + '}';
    }
}
