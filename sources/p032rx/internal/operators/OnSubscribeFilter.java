package p032rx.internal.operators;

import p032rx.Observable;
import p032rx.Observable.OnSubscribe;
import p032rx.Producer;
import p032rx.Subscriber;
import p032rx.exceptions.Exceptions;
import p032rx.exceptions.OnErrorThrowable;
import p032rx.functions.Func1;
import p032rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.OnSubscribeFilter */
public final class OnSubscribeFilter<T> implements OnSubscribe<T> {
    final Func1<? super T, Boolean> predicate;
    final Observable<T> source;

    /* renamed from: rx.internal.operators.OnSubscribeFilter$FilterSubscriber */
    static final class FilterSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        boolean done;
        final Func1<? super T, Boolean> predicate;

        public FilterSubscriber(Subscriber<? super T> actual2, Func1<? super T, Boolean> predicate2) {
            this.actual = actual2;
            this.predicate = predicate2;
            request(0);
        }

        public void onNext(T t) {
            try {
                if (((Boolean) this.predicate.call(t)).booleanValue()) {
                    this.actual.onNext(t);
                } else {
                    request(1);
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(ex, t));
            }
        }

        public void onError(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
                return;
            }
            this.done = true;
            this.actual.onError(e);
        }

        public void onCompleted() {
            if (!this.done) {
                this.actual.onCompleted();
            }
        }

        public void setProducer(Producer p) {
            super.setProducer(p);
            this.actual.setProducer(p);
        }
    }

    public OnSubscribeFilter(Observable<T> source2, Func1<? super T, Boolean> predicate2) {
        this.source = source2;
        this.predicate = predicate2;
    }

    public void call(Subscriber<? super T> child) {
        FilterSubscriber<T> parent = new FilterSubscriber<>(child, this.predicate);
        child.add(parent);
        this.source.unsafeSubscribe(parent);
    }
}
