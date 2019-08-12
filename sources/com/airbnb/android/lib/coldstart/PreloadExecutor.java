package com.airbnb.android.lib.coldstart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p032rx.Observable;

public final class PreloadExecutor {
    private static final long TASK_DELAY_MS = 2000;
    private final DelayedTask delayedTask;
    private final List<Preloader> preloaderList;

    public static class Builder {
        /* access modifiers changed from: private */
        public DelayedTask delayedTask;
        /* access modifiers changed from: private */
        public final List<Preloader> preloaderList = new ArrayList();

        public Builder addPreloaders(Preloader... preloaders) {
            this.preloaderList.add(new SerialPreloader(Arrays.asList(preloaders)));
            return this;
        }

        public Builder delayedTask(DelayedTask delayedTask2) {
            this.delayedTask = delayedTask2;
            return this;
        }

        public PreloadExecutor build() {
            return new PreloadExecutor(this);
        }
    }

    public interface DelayedTask {
        void onPreloadComplete();
    }

    private static class SerialPreloader implements Preloader {
        private final List<Preloader> preloaders;

        SerialPreloader(List<Preloader> preloaders2) {
            this.preloaders = preloaders2;
        }

        public void preload() {
            for (Preloader preloader : this.preloaders) {
                preloader.preload();
            }
        }
    }

    private PreloadExecutor(Builder builder) {
        this.preloaderList = builder.preloaderList;
        this.delayedTask = builder.delayedTask;
    }

    public void execute() {
        Observable.from((Iterable<? extends T>) this.preloaderList).map(PreloadExecutor$$Lambda$1.lambdaFactory$()).toList().flatMap(PreloadExecutor$$Lambda$2.lambdaFactory$()).takeLast(1).delay(TASK_DELAY_MS, TimeUnit.MILLISECONDS).subscribe(PreloadExecutor$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$execute$2(PreloadExecutor preloadExecutor, Boolean aBoolean) {
        if (preloadExecutor.delayedTask != null) {
            preloadExecutor.delayedTask.onPreloadComplete();
        }
    }
}
