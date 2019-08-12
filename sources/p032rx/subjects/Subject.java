package p032rx.subjects;

import p032rx.Observable;
import p032rx.Observable.OnSubscribe;
import p032rx.Observer;

/* renamed from: rx.subjects.Subject */
public abstract class Subject<T, R> extends Observable<R> implements Observer<T> {
    protected Subject(OnSubscribe<R> onSubscribe) {
        super(onSubscribe);
    }
}
