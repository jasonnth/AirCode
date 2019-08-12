package p032rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import p032rx.Subscription;
import p032rx.functions.Action0;

/* renamed from: rx.subscriptions.BooleanSubscription */
public final class BooleanSubscription implements Subscription {
    static final Action0 EMPTY_ACTION = new Action0() {
        public void call() {
        }
    };
    final AtomicReference<Action0> actionRef;

    public BooleanSubscription() {
        this.actionRef = new AtomicReference<>();
    }

    private BooleanSubscription(Action0 action) {
        this.actionRef = new AtomicReference<>(action);
    }

    public static BooleanSubscription create() {
        return new BooleanSubscription();
    }

    public static BooleanSubscription create(Action0 onUnsubscribe) {
        return new BooleanSubscription(onUnsubscribe);
    }

    public boolean isUnsubscribed() {
        return this.actionRef.get() == EMPTY_ACTION;
    }

    public void unsubscribe() {
        if (((Action0) this.actionRef.get()) != EMPTY_ACTION) {
            Action0 action = (Action0) this.actionRef.getAndSet(EMPTY_ACTION);
            if (action != null && action != EMPTY_ACTION) {
                action.call();
            }
        }
    }
}
