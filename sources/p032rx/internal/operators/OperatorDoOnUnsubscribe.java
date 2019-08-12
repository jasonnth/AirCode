package p032rx.internal.operators;

import p032rx.Observable.Operator;
import p032rx.Subscriber;
import p032rx.functions.Action0;
import p032rx.observers.Subscribers;
import p032rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.operators.OperatorDoOnUnsubscribe */
public class OperatorDoOnUnsubscribe<T> implements Operator<T, T> {
    private final Action0 unsubscribe;

    public OperatorDoOnUnsubscribe(Action0 unsubscribe2) {
        this.unsubscribe = unsubscribe2;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        child.add(Subscriptions.create(this.unsubscribe));
        return Subscribers.wrap(child);
    }
}
