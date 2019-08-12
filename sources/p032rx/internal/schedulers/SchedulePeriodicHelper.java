package p032rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import p032rx.Scheduler.Worker;
import p032rx.Subscription;
import p032rx.functions.Action0;
import p032rx.internal.subscriptions.SequentialSubscription;

/* renamed from: rx.internal.schedulers.SchedulePeriodicHelper */
public final class SchedulePeriodicHelper {
    public static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    /* renamed from: rx.internal.schedulers.SchedulePeriodicHelper$NowNanoSupplier */
    public interface NowNanoSupplier {
        long nowNanos();
    }

    public static Subscription schedulePeriodically(Worker worker, Action0 action, long initialDelay, long period, TimeUnit unit, NowNanoSupplier nowNanoSupplier) {
        final long periodInNanos = unit.toNanos(period);
        final long firstNowNanos = nowNanoSupplier != null ? nowNanoSupplier.nowNanos() : TimeUnit.MILLISECONDS.toNanos(worker.now());
        final long firstStartInNanos = firstNowNanos + unit.toNanos(initialDelay);
        SequentialSubscription first = new SequentialSubscription();
        final SequentialSubscription mas = new SequentialSubscription(first);
        final Action0 action0 = action;
        final NowNanoSupplier nowNanoSupplier2 = nowNanoSupplier;
        final Worker worker2 = worker;
        first.replace(worker.schedule(new Action0() {
            long count;
            long lastNowNanos = firstNowNanos;
            long startInNanos = firstStartInNanos;

            public void call() {
                long nowNanos;
                long nextTick;
                action0.call();
                if (!mas.isUnsubscribed()) {
                    if (nowNanoSupplier2 != null) {
                        nowNanos = nowNanoSupplier2.nowNanos();
                    } else {
                        nowNanos = TimeUnit.MILLISECONDS.toNanos(worker2.now());
                    }
                    if (SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS + nowNanos < this.lastNowNanos || nowNanos >= this.lastNowNanos + periodInNanos + SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS) {
                        nextTick = nowNanos + periodInNanos;
                        long j = periodInNanos;
                        long j2 = this.count + 1;
                        this.count = j2;
                        this.startInNanos = nextTick - (j * j2);
                    } else {
                        long j3 = this.startInNanos;
                        long j4 = this.count + 1;
                        this.count = j4;
                        nextTick = j3 + (j4 * periodInNanos);
                    }
                    this.lastNowNanos = nowNanos;
                    mas.replace(worker2.schedule(this, nextTick - nowNanos, TimeUnit.NANOSECONDS));
                }
            }
        }, initialDelay, unit));
        return mas;
    }
}
