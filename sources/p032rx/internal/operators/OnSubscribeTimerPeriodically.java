package p032rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p032rx.Observable.OnSubscribe;
import p032rx.Observer;
import p032rx.Scheduler;
import p032rx.Scheduler.Worker;
import p032rx.Subscriber;
import p032rx.exceptions.Exceptions;
import p032rx.functions.Action0;

/* renamed from: rx.internal.operators.OnSubscribeTimerPeriodically */
public final class OnSubscribeTimerPeriodically implements OnSubscribe<Long> {
    final long initialDelay;
    final long period;
    final Scheduler scheduler;
    final TimeUnit unit;

    public OnSubscribeTimerPeriodically(long initialDelay2, long period2, TimeUnit unit2, Scheduler scheduler2) {
        this.initialDelay = initialDelay2;
        this.period = period2;
        this.unit = unit2;
        this.scheduler = scheduler2;
    }

    public void call(final Subscriber<? super Long> child) {
        final Worker worker = this.scheduler.createWorker();
        child.add(worker);
        worker.schedulePeriodically(new Action0() {
            long counter;

            public void call() {
                try {
                    Subscriber subscriber = child;
                    long j = this.counter;
                    this.counter = 1 + j;
                    subscriber.onNext(Long.valueOf(j));
                } catch (Throwable th) {
                    Exceptions.throwOrReport(e, (Observer<?>) child);
                    throw th;
                }
            }
        }, this.initialDelay, this.period, this.unit);
    }
}
