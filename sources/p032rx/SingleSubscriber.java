package p032rx;

import p032rx.internal.util.SubscriptionList;

/* renamed from: rx.SingleSubscriber */
public abstract class SingleSubscriber<T> implements Subscription {

    /* renamed from: cs */
    private final SubscriptionList f7246cs = new SubscriptionList();

    public abstract void onError(Throwable th);

    public abstract void onSuccess(T t);

    public final void add(Subscription s) {
        this.f7246cs.add(s);
    }

    public final void unsubscribe() {
        this.f7246cs.unsubscribe();
    }

    public final boolean isUnsubscribed() {
        return this.f7246cs.isUnsubscribed();
    }
}
