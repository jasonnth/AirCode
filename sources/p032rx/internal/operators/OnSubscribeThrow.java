package p032rx.internal.operators;

import p032rx.Observable.OnSubscribe;
import p032rx.Subscriber;

/* renamed from: rx.internal.operators.OnSubscribeThrow */
public final class OnSubscribeThrow<T> implements OnSubscribe<T> {
    private final Throwable exception;

    public OnSubscribeThrow(Throwable exception2) {
        this.exception = exception2;
    }

    public void call(Subscriber<? super T> observer) {
        observer.onError(this.exception);
    }
}
