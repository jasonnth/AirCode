package com.airbnb.android.core.monitor;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.Strap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import p032rx.Observable;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.schedulers.Schedulers;

public class ExecutorMonitor {
    private static final long CHECK_INTERVAL_MS = 15000;

    private static class WatchDog {
        final AtomicBoolean executed = new AtomicBoolean(true);
        final Executor executor;
        final String tag;

        WatchDog(Executor executor2, String tag2) {
            this.executor = executor2;
            this.tag = tag2;
        }

        /* access modifiers changed from: 0000 */
        public void watch() {
            Observable.interval(ExecutorMonitor.CHECK_INTERVAL_MS, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).takeWhile(ExecutorMonitor$WatchDog$$Lambda$1.lambdaFactory$(this)).doOnCompleted(ExecutorMonitor$WatchDog$$Lambda$2.lambdaFactory$(this)).observeOn(Schedulers.from(this.executor)).subscribe(ExecutorMonitor$WatchDog$$Lambda$3.lambdaFactory$(this), ExecutorMonitor$WatchDog$$Lambda$4.lambdaFactory$());
        }

        /* access modifiers changed from: 0000 */
        public void report() {
            AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, "thread_pool_blocked").mo11639kv("tag", this.tag));
        }
    }

    private ExecutorMonitor() {
    }

    public static void watch(Executor executor, String tag) {
        new WatchDog(executor, tag).watch();
    }
}
