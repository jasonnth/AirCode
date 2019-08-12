package p032rx.subscriptions;

import p032rx.Subscription;
import p032rx.internal.subscriptions.SequentialSubscription;

/* renamed from: rx.subscriptions.SerialSubscription */
public final class SerialSubscription implements Subscription {
    final SequentialSubscription state = new SequentialSubscription();

    public boolean isUnsubscribed() {
        return this.state.isUnsubscribed();
    }

    public void unsubscribe() {
        this.state.unsubscribe();
    }

    public void set(Subscription s) {
        if (s == null) {
            throw new IllegalArgumentException("Subscription can not be null");
        }
        this.state.update(s);
    }

    public Subscription get() {
        return this.state.current();
    }
}
