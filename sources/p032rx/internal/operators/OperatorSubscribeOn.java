package p032rx.internal.operators;

import p032rx.Observable;
import p032rx.Observable.OnSubscribe;
import p032rx.Producer;
import p032rx.Scheduler;
import p032rx.Scheduler.Worker;
import p032rx.Subscriber;
import p032rx.functions.Action0;

/* renamed from: rx.internal.operators.OperatorSubscribeOn */
public final class OperatorSubscribeOn<T> implements OnSubscribe<T> {
    final boolean requestOn;
    final Scheduler scheduler;
    final Observable<T> source;

    /* renamed from: rx.internal.operators.OperatorSubscribeOn$SubscribeOnSubscriber */
    static final class SubscribeOnSubscriber<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super T> actual;
        final boolean requestOn;
        Observable<T> source;

        /* renamed from: t */
        Thread f7252t;
        final Worker worker;

        SubscribeOnSubscriber(Subscriber<? super T> actual2, boolean requestOn2, Worker worker2, Observable<T> source2) {
            this.actual = actual2;
            this.requestOn = requestOn2;
            this.worker = worker2;
            this.source = source2;
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable e) {
            try {
                this.actual.onError(e);
            } finally {
                this.worker.unsubscribe();
            }
        }

        public void onCompleted() {
            try {
                this.actual.onCompleted();
            } finally {
                this.worker.unsubscribe();
            }
        }

        public void call() {
            Observable<T> src = this.source;
            this.source = null;
            this.f7252t = Thread.currentThread();
            src.unsafeSubscribe(this);
        }

        public void setProducer(final Producer p) {
            this.actual.setProducer(new Producer() {
                public void request(final long n) {
                    if (SubscribeOnSubscriber.this.f7252t == Thread.currentThread() || !SubscribeOnSubscriber.this.requestOn) {
                        p.request(n);
                    } else {
                        SubscribeOnSubscriber.this.worker.schedule(new Action0() {
                            public void call() {
                                p.request(n);
                            }
                        });
                    }
                }
            });
        }
    }

    public OperatorSubscribeOn(Observable<T> source2, Scheduler scheduler2, boolean requestOn2) {
        this.scheduler = scheduler2;
        this.source = source2;
        this.requestOn = requestOn2;
    }

    public void call(Subscriber<? super T> subscriber) {
        Worker inner = this.scheduler.createWorker();
        SubscribeOnSubscriber<T> parent = new SubscribeOnSubscriber<>(subscriber, this.requestOn, inner, this.source);
        subscriber.add(parent);
        subscriber.add(inner);
        inner.schedule(parent);
    }
}
