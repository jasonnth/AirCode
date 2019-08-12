package com.airbnb.rxgroups;

import p032rx.Observable;
import p032rx.Observer;
import p032rx.Subscription;
import p032rx.functions.Action0;
import p032rx.subjects.ReplaySubject;
import p032rx.subscriptions.CompositeSubscription;

final class SubscriptionProxy<T> {
    private final Observable<T> proxy;
    private Subscription subscription;
    private final CompositeSubscription subscriptionList = new CompositeSubscription(this.upstreamSubscription);
    private final Subscription upstreamSubscription;

    private SubscriptionProxy(Observable<T> upstreamObservable, Action0 onTerminate) {
        ReplaySubject<T> replaySubject = ReplaySubject.create();
        this.upstreamSubscription = upstreamObservable.subscribe((Observer<? super T>) replaySubject);
        this.proxy = replaySubject.doOnTerminate(onTerminate);
    }

    static <T> SubscriptionProxy<T> create(Observable<T> observable, Action0 onTerminate) {
        return new SubscriptionProxy<>(observable, onTerminate);
    }

    /* access modifiers changed from: 0000 */
    public Subscription subscribe(Observable<T> observable, Observer<? super T> observer) {
        unsubscribe();
        this.subscription = observable.subscribe(observer);
        this.subscriptionList.add(this.subscription);
        return this.subscription;
    }

    /* access modifiers changed from: 0000 */
    public void cancel() {
        this.subscriptionList.unsubscribe();
    }

    /* access modifiers changed from: 0000 */
    public void unsubscribe() {
        if (this.subscription != null) {
            this.subscriptionList.remove(this.subscription);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isUnsubscribed() {
        return this.subscription != null && this.subscription.isUnsubscribed();
    }

    /* access modifiers changed from: 0000 */
    public boolean isCancelled() {
        return isUnsubscribed() && this.upstreamSubscription.isUnsubscribed();
    }

    /* access modifiers changed from: 0000 */
    public Observable<T> observable() {
        return this.proxy;
    }
}
