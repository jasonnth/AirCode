package p032rx;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import p032rx.Scheduler.Worker;
import p032rx.exceptions.CompositeException;
import p032rx.exceptions.Exceptions;
import p032rx.functions.Action0;
import p032rx.functions.Action1;
import p032rx.functions.Actions;
import p032rx.functions.Func1;
import p032rx.internal.util.SubscriptionList;
import p032rx.plugins.RxJavaHooks;
import p032rx.schedulers.Schedulers;
import p032rx.subscriptions.BooleanSubscription;
import p032rx.subscriptions.MultipleAssignmentSubscription;
import p032rx.subscriptions.Subscriptions;

/* renamed from: rx.Completable */
public class Completable {
    static final Completable COMPLETE = new Completable(new OnSubscribe() {
        public void call(CompletableSubscriber s) {
            s.onSubscribe(Subscriptions.unsubscribed());
            s.onCompleted();
        }
    }, false);
    static final Completable NEVER = new Completable(new OnSubscribe() {
        public void call(CompletableSubscriber s) {
            s.onSubscribe(Subscriptions.unsubscribed());
        }
    }, false);
    private final OnSubscribe onSubscribe;

    /* renamed from: rx.Completable$OnSubscribe */
    public interface OnSubscribe extends Action1<CompletableSubscriber> {
    }

    /* renamed from: rx.Completable$Operator */
    public interface Operator extends Func1<CompletableSubscriber, CompletableSubscriber> {
    }

    public static Completable create(OnSubscribe onSubscribe2) {
        requireNonNull(onSubscribe2);
        try {
            return new Completable(onSubscribe2);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Throwable ex2) {
            RxJavaHooks.onError(ex2);
            throw toNpe(ex2);
        }
    }

    public static Completable fromAction(final Action0 action) {
        requireNonNull(action);
        return create(new OnSubscribe() {
            public void call(CompletableSubscriber s) {
                BooleanSubscription bs = new BooleanSubscription();
                s.onSubscribe(bs);
                try {
                    action.call();
                    if (!bs.isUnsubscribed()) {
                        s.onCompleted();
                    }
                } catch (Throwable e) {
                    if (!bs.isUnsubscribed()) {
                        s.onError(e);
                    }
                }
            }
        });
    }

    static <T> T requireNonNull(T o) {
        if (o != null) {
            return o;
        }
        throw new NullPointerException();
    }

    public static Completable timer(long delay, TimeUnit unit) {
        return timer(delay, unit, Schedulers.computation());
    }

    public static Completable timer(final long delay, final TimeUnit unit, final Scheduler scheduler) {
        requireNonNull(unit);
        requireNonNull(scheduler);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                MultipleAssignmentSubscription mad = new MultipleAssignmentSubscription();
                s.onSubscribe(mad);
                if (!mad.isUnsubscribed()) {
                    final Worker w = scheduler.createWorker();
                    mad.set(w);
                    w.schedule(new Action0() {
                        public void call() {
                            try {
                                s.onCompleted();
                            } finally {
                                w.unsubscribe();
                            }
                        }
                    }, delay, unit);
                }
            }
        });
    }

    static NullPointerException toNpe(Throwable ex) {
        NullPointerException npe = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        npe.initCause(ex);
        return npe;
    }

    protected Completable(OnSubscribe onSubscribe2) {
        this.onSubscribe = RxJavaHooks.onCreate(onSubscribe2);
    }

    protected Completable(OnSubscribe onSubscribe2, boolean useHook) {
        if (useHook) {
            onSubscribe2 = RxJavaHooks.onCreate(onSubscribe2);
        }
        this.onSubscribe = onSubscribe2;
    }

    public final Completable doOnCompleted(Action0 onCompleted) {
        return doOnLifecycle(Actions.empty(), Actions.empty(), onCompleted, Actions.empty(), Actions.empty());
    }

    /* access modifiers changed from: protected */
    public final Completable doOnLifecycle(Action1<? super Subscription> onSubscribe2, Action1<? super Throwable> onError, Action0 onComplete, Action0 onAfterTerminate, Action0 onUnsubscribe) {
        requireNonNull(onSubscribe2);
        requireNonNull(onError);
        requireNonNull(onComplete);
        requireNonNull(onAfterTerminate);
        requireNonNull(onUnsubscribe);
        final Action0 action0 = onComplete;
        final Action0 action02 = onAfterTerminate;
        final Action1<? super Throwable> action1 = onError;
        final Action1<? super Subscription> action12 = onSubscribe2;
        final Action0 action03 = onUnsubscribe;
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    public void onCompleted() {
                        try {
                            action0.call();
                            s.onCompleted();
                            try {
                                action02.call();
                            } catch (Throwable e) {
                                RxJavaHooks.onError(e);
                            }
                        } catch (Throwable e2) {
                            s.onError(e2);
                        }
                    }

                    public void onError(Throwable e) {
                        try {
                            action1.call(e);
                        } catch (Throwable ex) {
                            e = new CompositeException((Collection<? extends Throwable>) Arrays.asList(new Throwable[]{e, ex}));
                        }
                        s.onError(e);
                        try {
                            action02.call();
                        } catch (Throwable ex2) {
                            RxJavaHooks.onError(ex2);
                        }
                    }

                    public void onSubscribe(final Subscription d) {
                        try {
                            action12.call(d);
                            s.onSubscribe(Subscriptions.create(new Action0() {
                                public void call() {
                                    try {
                                        action03.call();
                                    } catch (Throwable e) {
                                        RxJavaHooks.onError(e);
                                    }
                                    d.unsubscribe();
                                }
                            }));
                        } catch (Throwable ex) {
                            d.unsubscribe();
                            s.onSubscribe(Subscriptions.unsubscribed());
                            s.onError(ex);
                        }
                    }
                });
            }
        });
    }

    public final Completable observeOn(final Scheduler scheduler) {
        requireNonNull(scheduler);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                final SubscriptionList ad = new SubscriptionList();
                final Worker w = scheduler.createWorker();
                ad.add(w);
                s.onSubscribe(ad);
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    public void onCompleted() {
                        w.schedule(new Action0() {
                            public void call() {
                                try {
                                    s.onCompleted();
                                } finally {
                                    ad.unsubscribe();
                                }
                            }
                        });
                    }

                    public void onError(final Throwable e) {
                        w.schedule(new Action0() {
                            public void call() {
                                try {
                                    s.onError(e);
                                } finally {
                                    ad.unsubscribe();
                                }
                            }
                        });
                    }

                    public void onSubscribe(Subscription d) {
                        ad.add(d);
                    }
                });
            }
        });
    }

    public final Subscription subscribe() {
        final MultipleAssignmentSubscription mad = new MultipleAssignmentSubscription();
        unsafeSubscribe(new CompletableSubscriber() {
            public void onCompleted() {
                mad.unsubscribe();
            }

            public void onError(Throwable e) {
                RxJavaHooks.onError(e);
                mad.unsubscribe();
                Completable.deliverUncaughtException(e);
            }

            public void onSubscribe(Subscription d) {
                mad.set(d);
            }
        });
        return mad;
    }

    public final Subscription subscribe(final Action0 onComplete) {
        requireNonNull(onComplete);
        final MultipleAssignmentSubscription mad = new MultipleAssignmentSubscription();
        unsafeSubscribe(new CompletableSubscriber() {
            boolean done;

            public void onCompleted() {
                if (!this.done) {
                    this.done = true;
                    try {
                        onComplete.call();
                    } catch (Throwable e) {
                        RxJavaHooks.onError(e);
                        Completable.deliverUncaughtException(e);
                    } finally {
                        mad.unsubscribe();
                    }
                }
            }

            public void onError(Throwable e) {
                RxJavaHooks.onError(e);
                mad.unsubscribe();
                Completable.deliverUncaughtException(e);
            }

            public void onSubscribe(Subscription d) {
                mad.set(d);
            }
        });
        return mad;
    }

    static void deliverUncaughtException(Throwable e) {
        Thread thread = Thread.currentThread();
        thread.getUncaughtExceptionHandler().uncaughtException(thread, e);
    }

    public final void unsafeSubscribe(CompletableSubscriber s) {
        requireNonNull(s);
        try {
            RxJavaHooks.onCompletableStart(this, this.onSubscribe).call(s);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Throwable ex2) {
            Exceptions.throwIfFatal(ex2);
            Throwable ex3 = RxJavaHooks.onCompletableError(ex2);
            RxJavaHooks.onError(ex3);
            throw toNpe(ex3);
        }
    }

    public final Completable subscribeOn(final Scheduler scheduler) {
        requireNonNull(scheduler);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                final Worker w = scheduler.createWorker();
                w.schedule(new Action0() {
                    public void call() {
                        try {
                            Completable.this.unsafeSubscribe(s);
                        } finally {
                            w.unsubscribe();
                        }
                    }
                });
            }
        });
    }
}
