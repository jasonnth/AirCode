package p032rx.internal.operators;

import java.util.NoSuchElementException;
import p032rx.Observable;
import p032rx.Single.OnSubscribe;
import p032rx.SingleSubscriber;
import p032rx.Subscriber;
import p032rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.SingleFromObservable */
public final class SingleFromObservable<T> implements OnSubscribe<T> {
    final Observable.OnSubscribe<T> source;

    /* renamed from: rx.internal.operators.SingleFromObservable$WrapSingleIntoSubscriber */
    static final class WrapSingleIntoSubscriber<T> extends Subscriber<T> {
        final SingleSubscriber<? super T> actual;
        int state;
        T value;

        WrapSingleIntoSubscriber(SingleSubscriber<? super T> actual2) {
            this.actual = actual2;
        }

        public void onNext(T t) {
            int s = this.state;
            if (s == 0) {
                this.state = 1;
                this.value = t;
            } else if (s == 1) {
                this.state = 2;
                this.actual.onError(new IndexOutOfBoundsException("The upstream produced more than one value"));
            }
        }

        public void onError(Throwable e) {
            if (this.state == 2) {
                RxJavaHooks.onError(e);
                return;
            }
            this.value = null;
            this.actual.onError(e);
        }

        public void onCompleted() {
            int s = this.state;
            if (s == 0) {
                this.actual.onError(new NoSuchElementException());
            } else if (s == 1) {
                this.state = 2;
                T v = this.value;
                this.value = null;
                this.actual.onSuccess(v);
            }
        }
    }

    public SingleFromObservable(Observable.OnSubscribe<T> source2) {
        this.source = source2;
    }

    public void call(SingleSubscriber<? super T> t) {
        WrapSingleIntoSubscriber<T> parent = new WrapSingleIntoSubscriber<>(t);
        t.add(parent);
        this.source.call(parent);
    }
}
