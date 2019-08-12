package p032rx.internal.schedulers;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p032rx.Subscription;
import p032rx.exceptions.OnErrorNotImplementedException;
import p032rx.functions.Action0;
import p032rx.internal.util.SubscriptionList;
import p032rx.plugins.RxJavaHooks;
import p032rx.subscriptions.CompositeSubscription;

/* renamed from: rx.internal.schedulers.ScheduledAction */
public final class ScheduledAction extends AtomicReference<Thread> implements Runnable, Subscription {
    final Action0 action;
    final SubscriptionList cancel;

    /* renamed from: rx.internal.schedulers.ScheduledAction$FutureCompleter */
    final class FutureCompleter implements Subscription {

        /* renamed from: f */
        private final Future<?> f7255f;

        FutureCompleter(Future<?> f) {
            this.f7255f = f;
        }

        public void unsubscribe() {
            if (ScheduledAction.this.get() != Thread.currentThread()) {
                this.f7255f.cancel(true);
            } else {
                this.f7255f.cancel(false);
            }
        }

        public boolean isUnsubscribed() {
            return this.f7255f.isCancelled();
        }
    }

    /* renamed from: rx.internal.schedulers.ScheduledAction$Remover */
    static final class Remover extends AtomicBoolean implements Subscription {
        final CompositeSubscription parent;

        /* renamed from: s */
        final ScheduledAction f7256s;

        public Remover(ScheduledAction s, CompositeSubscription parent2) {
            this.f7256s = s;
            this.parent = parent2;
        }

        public boolean isUnsubscribed() {
            return this.f7256s.isUnsubscribed();
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.f7256s);
            }
        }
    }

    /* renamed from: rx.internal.schedulers.ScheduledAction$Remover2 */
    static final class Remover2 extends AtomicBoolean implements Subscription {
        final SubscriptionList parent;

        /* renamed from: s */
        final ScheduledAction f7257s;

        public Remover2(ScheduledAction s, SubscriptionList parent2) {
            this.f7257s = s;
            this.parent = parent2;
        }

        public boolean isUnsubscribed() {
            return this.f7257s.isUnsubscribed();
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.f7257s);
            }
        }
    }

    public ScheduledAction(Action0 action2) {
        this.action = action2;
        this.cancel = new SubscriptionList();
    }

    public ScheduledAction(Action0 action2, CompositeSubscription parent) {
        this.action = action2;
        this.cancel = new SubscriptionList((Subscription) new Remover(this, parent));
    }

    public ScheduledAction(Action0 action2, SubscriptionList parent) {
        this.action = action2;
        this.cancel = new SubscriptionList((Subscription) new Remover2(this, parent));
    }

    public void run() {
        try {
            lazySet(Thread.currentThread());
            this.action.call();
        } catch (OnErrorNotImplementedException e) {
            signalError(new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", e));
        } catch (Throwable e2) {
            signalError(new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", e2));
        } finally {
            unsubscribe();
        }
    }

    /* access modifiers changed from: 0000 */
    public void signalError(Throwable ie) {
        RxJavaHooks.onError(ie);
        Thread thread = Thread.currentThread();
        thread.getUncaughtExceptionHandler().uncaughtException(thread, ie);
    }

    public boolean isUnsubscribed() {
        return this.cancel.isUnsubscribed();
    }

    public void unsubscribe() {
        if (!this.cancel.isUnsubscribed()) {
            this.cancel.unsubscribe();
        }
    }

    public void add(Subscription s) {
        this.cancel.add(s);
    }

    public void add(Future<?> f) {
        this.cancel.add(new FutureCompleter(f));
    }

    public void addParent(CompositeSubscription parent) {
        this.cancel.add(new Remover(this, parent));
    }
}
