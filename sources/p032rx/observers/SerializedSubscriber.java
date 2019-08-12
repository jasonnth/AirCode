package p032rx.observers;

import p032rx.Observer;
import p032rx.Subscriber;

/* renamed from: rx.observers.SerializedSubscriber */
public class SerializedSubscriber<T> extends Subscriber<T> {

    /* renamed from: s */
    private final Observer<T> f7259s;

    public SerializedSubscriber(Subscriber<? super T> s) {
        this(s, true);
    }

    public SerializedSubscriber(Subscriber<? super T> s, boolean shareSubscriptions) {
        super(s, shareSubscriptions);
        this.f7259s = new SerializedObserver(s);
    }

    public void onCompleted() {
        this.f7259s.onCompleted();
    }

    public void onError(Throwable e) {
        this.f7259s.onError(e);
    }

    public void onNext(T t) {
        this.f7259s.onNext(t);
    }
}
