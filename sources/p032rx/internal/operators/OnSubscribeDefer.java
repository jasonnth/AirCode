package p032rx.internal.operators;

import p032rx.Observable;
import p032rx.Observable.OnSubscribe;
import p032rx.Observer;
import p032rx.Subscriber;
import p032rx.exceptions.Exceptions;
import p032rx.functions.Func0;
import p032rx.observers.Subscribers;

/* renamed from: rx.internal.operators.OnSubscribeDefer */
public final class OnSubscribeDefer<T> implements OnSubscribe<T> {
    final Func0<? extends Observable<? extends T>> observableFactory;

    public OnSubscribeDefer(Func0<? extends Observable<? extends T>> observableFactory2) {
        this.observableFactory = observableFactory2;
    }

    public void call(Subscriber<? super T> s) {
        try {
            ((Observable) this.observableFactory.call()).unsafeSubscribe(Subscribers.wrap(s));
        } catch (Throwable t) {
            Exceptions.throwOrReport(t, (Observer<?>) s);
        }
    }
}
