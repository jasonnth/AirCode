package p032rx.plugins;

import p032rx.Observable;
import p032rx.Observable.OnSubscribe;
import p032rx.Observable.Operator;
import p032rx.Subscription;

/* renamed from: rx.plugins.RxJavaObservableExecutionHook */
public abstract class RxJavaObservableExecutionHook {
    @Deprecated
    public <T> OnSubscribe<T> onCreate(OnSubscribe<T> f) {
        return f;
    }

    @Deprecated
    public <T> OnSubscribe<T> onSubscribeStart(Observable<? extends T> observable, OnSubscribe<T> onSubscribe) {
        return onSubscribe;
    }

    @Deprecated
    public <T> Subscription onSubscribeReturn(Subscription subscription) {
        return subscription;
    }

    @Deprecated
    public <T> Throwable onSubscribeError(Throwable e) {
        return e;
    }

    @Deprecated
    public <T, R> Operator<? extends R, ? super T> onLift(Operator<? extends R, ? super T> lift) {
        return lift;
    }
}
