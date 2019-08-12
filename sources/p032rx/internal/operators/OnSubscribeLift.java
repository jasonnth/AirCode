package p032rx.internal.operators;

import p032rx.Observable.OnSubscribe;
import p032rx.Observable.Operator;
import p032rx.Subscriber;
import p032rx.exceptions.Exceptions;
import p032rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.OnSubscribeLift */
public final class OnSubscribeLift<T, R> implements OnSubscribe<R> {
    final Operator<? extends R, ? super T> operator;
    final OnSubscribe<T> parent;

    public OnSubscribeLift(OnSubscribe<T> parent2, Operator<? extends R, ? super T> operator2) {
        this.parent = parent2;
        this.operator = operator2;
    }

    public void call(Subscriber<? super R> o) {
        Subscriber<? super T> st;
        try {
            st = (Subscriber) RxJavaHooks.onObservableLift(this.operator).call(o);
            st.onStart();
            this.parent.call(st);
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            o.onError(e);
        }
    }
}
