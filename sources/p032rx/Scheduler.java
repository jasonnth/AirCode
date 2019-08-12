package p032rx;

import java.util.concurrent.TimeUnit;
import p032rx.functions.Action0;
import p032rx.internal.schedulers.SchedulePeriodicHelper;

/* renamed from: rx.Scheduler */
public abstract class Scheduler {

    /* renamed from: rx.Scheduler$Worker */
    public static abstract class Worker implements Subscription {
        public abstract Subscription schedule(Action0 action0);

        public abstract Subscription schedule(Action0 action0, long j, TimeUnit timeUnit);

        public Subscription schedulePeriodically(Action0 action, long initialDelay, long period, TimeUnit unit) {
            return SchedulePeriodicHelper.schedulePeriodically(this, action, initialDelay, period, unit, null);
        }

        public long now() {
            return System.currentTimeMillis();
        }
    }

    public abstract Worker createWorker();

    public long now() {
        return System.currentTimeMillis();
    }
}
