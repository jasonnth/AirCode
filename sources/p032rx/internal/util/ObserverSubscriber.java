package p032rx.internal.util;

import p032rx.Observer;
import p032rx.Subscriber;

/* renamed from: rx.internal.util.ObserverSubscriber */
public final class ObserverSubscriber<T> extends Subscriber<T> {
    final Observer<? super T> observer;

    public ObserverSubscriber(Observer<? super T> observer2) {
        this.observer = observer2;
    }

    public void onNext(T t) {
        this.observer.onNext(t);
    }

    public void onError(Throwable e) {
        this.observer.onError(e);
    }

    public void onCompleted() {
        this.observer.onCompleted();
    }
}
