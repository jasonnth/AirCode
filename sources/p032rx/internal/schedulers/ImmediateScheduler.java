package p032rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import p032rx.Scheduler;
import p032rx.Scheduler.Worker;
import p032rx.Subscription;
import p032rx.functions.Action0;
import p032rx.subscriptions.BooleanSubscription;
import p032rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.schedulers.ImmediateScheduler */
public final class ImmediateScheduler extends Scheduler {
    public static final ImmediateScheduler INSTANCE = new ImmediateScheduler();

    /* renamed from: rx.internal.schedulers.ImmediateScheduler$InnerImmediateScheduler */
    final class InnerImmediateScheduler extends Worker implements Subscription {
        final BooleanSubscription innerSubscription = new BooleanSubscription();

        InnerImmediateScheduler() {
        }

        public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
            return schedule(new SleepingAction(action, this, ImmediateScheduler.this.now() + unit.toMillis(delayTime)));
        }

        public Subscription schedule(Action0 action) {
            action.call();
            return Subscriptions.unsubscribed();
        }

        public void unsubscribe() {
            this.innerSubscription.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.innerSubscription.isUnsubscribed();
        }
    }

    private ImmediateScheduler() {
    }

    public Worker createWorker() {
        return new InnerImmediateScheduler();
    }
}
