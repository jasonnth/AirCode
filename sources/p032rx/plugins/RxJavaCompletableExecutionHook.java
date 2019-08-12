package p032rx.plugins;

import p032rx.Completable;
import p032rx.Completable.OnSubscribe;
import p032rx.Completable.Operator;

/* renamed from: rx.plugins.RxJavaCompletableExecutionHook */
public abstract class RxJavaCompletableExecutionHook {
    @Deprecated
    public OnSubscribe onCreate(OnSubscribe f) {
        return f;
    }

    @Deprecated
    public OnSubscribe onSubscribeStart(Completable completableInstance, OnSubscribe onSubscribe) {
        return onSubscribe;
    }

    @Deprecated
    public Throwable onSubscribeError(Throwable e) {
        return e;
    }

    @Deprecated
    public Operator onLift(Operator lift) {
        return lift;
    }
}
