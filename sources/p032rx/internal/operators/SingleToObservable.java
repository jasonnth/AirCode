package p032rx.internal.operators;

import p032rx.Observable.OnSubscribe;
import p032rx.Single;
import p032rx.Subscriber;

/* renamed from: rx.internal.operators.SingleToObservable */
public final class SingleToObservable<T> implements OnSubscribe<T> {
    final Single.OnSubscribe<T> source;

    public SingleToObservable(Single.OnSubscribe<T> source2) {
        this.source = source2;
    }

    public void call(Subscriber<? super T> t) {
        WrapSubscriberIntoSingle<T> parent = new WrapSubscriberIntoSingle<>(t);
        t.add(parent);
        this.source.call(parent);
    }
}
