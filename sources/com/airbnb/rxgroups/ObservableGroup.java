package com.airbnb.rxgroups;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p032rx.Observable;
import p032rx.Observable.Transformer;
import p032rx.Observer;
import p032rx.functions.Action0;
import p032rx.functions.Action1;

public class ObservableGroup {
    private boolean destroyed;
    private final long groupId;
    private final Map<String, Map<String, ManagedObservable<?>>> groupMap = new ConcurrentHashMap();
    private boolean locked;

    ObservableGroup(long groupId2) {
        this.groupId = groupId2;
    }

    /* renamed from: id */
    public long mo26652id() {
        return this.groupId;
    }

    public void initializeAutoTaggingAndResubscription(Object target) {
        Preconditions.checkNotNull(target, "Target cannot be null");
        ResubscribeHelper.initializeAutoTaggingAndResubscription(target, this);
    }

    /* access modifiers changed from: 0000 */
    public <T> void add(String observerTag, final String observableTag, Observable<T> observable, Observer<? super T> observer) {
        checkNotDestroyed();
        final Map<String, ManagedObservable<?>> existingObservables = getObservablesForObserver(observerTag);
        if (((ManagedObservable) existingObservables.get(observableTag)) != null) {
            cancelAndRemove(observerTag, observableTag);
        }
        ManagedObservable<T> managedObservable = new ManagedObservable<>(observerTag, observableTag, observable, observer, new Action0() {
            public void call() {
                existingObservables.remove(observableTag);
            }
        });
        existingObservables.put(observableTag, managedObservable);
        if (!this.locked) {
            managedObservable.unlock();
        }
    }

    private Map<String, ManagedObservable<?>> getObservablesForObserver(Observer<?> observer) {
        return getObservablesForObserver(Utils.getObserverTag(observer));
    }

    private Map<String, ManagedObservable<?>> getObservablesForObserver(String observerTag) {
        Map<String, ManagedObservable<?>> map = (Map) this.groupMap.get(observerTag);
        if (map != null) {
            return map;
        }
        Map<String, ManagedObservable<?>> map2 = new HashMap<>();
        this.groupMap.put(observerTag, map2);
        return map2;
    }

    public <T> Transformer<? super T, T> transform(Observer<? super T> observer, String observableTag) {
        return new GroupSubscriptionTransformer(this, Utils.getObserverTag(observer), observableTag);
    }

    /* access modifiers changed from: 0000 */
    public void destroy() {
        this.destroyed = true;
        for (Map<String, ManagedObservable<?>> observableMap : this.groupMap.values()) {
            for (ManagedObservable<?> managedObservable : observableMap.values()) {
                managedObservable.cancel();
            }
            observableMap.clear();
        }
        this.groupMap.clear();
    }

    private void forAllObservables(Action1<ManagedObservable<?>> action) {
        for (Map<String, ManagedObservable<?>> observableMap : this.groupMap.values()) {
            for (ManagedObservable<?> managedObservable : observableMap.values()) {
                action.call(managedObservable);
            }
        }
    }

    public void lock() {
        this.locked = true;
        forAllObservables(new Action1<ManagedObservable<?>>() {
            public void call(ManagedObservable<?> managedObservable) {
                managedObservable.lock();
            }
        });
    }

    public void unlock() {
        this.locked = false;
        forAllObservables(new Action1<ManagedObservable<?>>() {
            public void call(ManagedObservable<?> managedObservable) {
                managedObservable.unlock();
            }
        });
    }

    public void unsubscribe() {
        forAllObservables(new Action1<ManagedObservable<?>>() {
            public void call(ManagedObservable<?> managedObservable) {
                managedObservable.unsubscribe();
            }
        });
    }

    public <T> Observable<T> observable(Observer<? super T> observer, String observableTag) {
        checkNotDestroyed();
        String observerTag = Utils.getObserverTag(observer);
        ManagedObservable<T> managedObservable = (ManagedObservable) getObservablesForObserver(observerTag).get(observableTag);
        if (managedObservable != null) {
            return managedObservable.observable().compose(new GroupResubscriptionTransformer(managedObservable));
        }
        throw new IllegalStateException("No observable exists for observer: " + observerTag + " and observable: " + observableTag);
    }

    public RequestSubscription subscription(Observer<?> observer, String observableTag) {
        return subscription(Utils.getObserverTag(observer), observableTag);
    }

    private RequestSubscription subscription(String observerTag, String observableTag) {
        return (RequestSubscription) getObservablesForObserver(observerTag).get(observableTag);
    }

    public <T> void resubscribeAll(TaggedObserver<? super T> observer) {
        for (String observableTag : getObservablesForObserver((Observer<?>) observer).keySet()) {
            observable(observer, observableTag).subscribe((Observer<? super T>) observer);
        }
    }

    public <T> void resubscribe(TaggedObserver<? super T> observer, String observableTag) {
        Observable<T> observable = observable(observer, observableTag);
        if (observable != null) {
            observable.subscribe((Observer<? super T>) observer);
        }
    }

    public void cancelAndRemove(Observer<?> observer, String observableTag) {
        cancelAndRemove(Utils.getObserverTag(observer), observableTag);
    }

    public void cancelAllObservablesForObserver(Observer<?> observer) {
        cancelAllObservablesForObserver(Utils.getObserverTag(observer));
    }

    private void cancelAllObservablesForObserver(String observerTag) {
        Map<String, ManagedObservable<?>> observables = getObservablesForObserver(observerTag);
        for (ManagedObservable<?> managedObservable : observables.values()) {
            managedObservable.cancel();
        }
        observables.clear();
    }

    private void cancelAndRemove(String observerTag, String observableTag) {
        Map<String, ManagedObservable<?>> observables = getObservablesForObserver(observerTag);
        ManagedObservable<?> managedObservable = (ManagedObservable) observables.get(observableTag);
        if (managedObservable != null) {
            managedObservable.cancel();
            observables.remove(observableTag);
        }
    }

    public boolean hasObservable(Observer<?> observer, String observableTag) {
        return subscription(observer, observableTag) != null;
    }

    public boolean hasObservables(Observer<?> observer) {
        return !getObservablesForObserver(observer).isEmpty();
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    private void checkNotDestroyed() {
        Preconditions.checkState(!this.destroyed, "Group is already destroyed! id=" + this.groupId);
    }

    public String toString() {
        return "ObservableGroup{groupMap=" + this.groupMap + ", groupId=" + this.groupId + ", locked=" + this.locked + ", destroyed=" + this.destroyed + '}';
    }

    /* access modifiers changed from: 0000 */
    public void removeNonResubscribableObservers() {
        for (String observerTag : this.groupMap.keySet()) {
            if (NonResubscribableTag.isNonResubscribableTag(observerTag)) {
                cancelAllObservablesForObserver(observerTag);
            }
        }
    }
}
