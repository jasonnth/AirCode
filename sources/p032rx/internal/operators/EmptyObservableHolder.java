package p032rx.internal.operators;

import p032rx.Observable;
import p032rx.Observable.OnSubscribe;
import p032rx.Subscriber;

/* renamed from: rx.internal.operators.EmptyObservableHolder */
public enum EmptyObservableHolder implements OnSubscribe<Object> {
    INSTANCE;
    
    static final Observable<Object> EMPTY = null;

    static {
        EMPTY = Observable.unsafeCreate(INSTANCE);
    }

    public static <T> Observable<T> instance() {
        return EMPTY;
    }

    public void call(Subscriber<? super Object> child) {
        child.onCompleted();
    }
}
