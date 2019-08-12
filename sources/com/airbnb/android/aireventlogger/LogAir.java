package com.airbnb.android.aireventlogger;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LogAir {
    private static AirEventLogger instance;

    public static class Builder {
        private static final ThreadFactory defaultThreadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = new Thread(null, r, "aireventlogger", 0);
                t.setPriority(1);
                return t;
            }
        };
        private long alarmTimeStartMills = 120000;
        private long alarmWindowMillis = 120000;
        private final Context context;
        private final ThreadPoolExecutor defaultExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), defaultThreadFactory);
        private int eventsToFetch = 100;
        private Executor executor;
        private List<EventHandler> handlers = new ArrayList();
        private String userAgent;

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder addEventHandler(EventHandler handler) {
            this.handlers.add(handler);
            return this;
        }

        public Builder userAgent(String userAgent2) {
            this.userAgent = userAgent2;
            return this;
        }

        public Builder alarmStartMillis(long startMillis) {
            this.alarmTimeStartMills = startMillis;
            return this;
        }

        public Builder alarmWindowMillis(long windowMills) {
            this.alarmWindowMillis = windowMills;
            return this;
        }

        public Builder eventsToFetch(int count) {
            this.eventsToFetch = count;
            return this;
        }

        public Builder executor(Executor executor2) {
            this.executor = (Executor) Utils.throwIfNull(executor2, "executor == null");
            return this;
        }

        public AirEventLogger build() {
            if (this.handlers.isEmpty()) {
                throw new IllegalStateException("At least one EventHandler required");
            }
            if (this.executor == null) {
                this.executor = this.defaultExecutor;
            }
            return new AirEventLogger(this.context, this.alarmTimeStartMills, this.alarmWindowMillis, this.eventsToFetch, this.executor, Factory.create(this.userAgent), new EventHandlerRegistry((EventHandler[]) this.handlers.toArray(new EventHandler[this.handlers.size()])));
        }
    }

    public static void init(AirEventLogger logair) {
        instance = logair;
    }

    public static <T> void track(AirEvent<T> event) {
        track(event, false);
    }

    public static <T> void trackImmediately(AirEvent<T> event) {
        track(event, true);
    }

    public static <T> void track(AirEvent<T> event, boolean immediatelyFlush) {
        instance().track(event, immediatelyFlush);
    }

    static void stopService() {
        if (instance != null) {
            instance.stop();
        }
    }

    static void uploadAirEventsAsync() {
        instance.uploadEventsAsync();
    }

    private static AirEventLogger instance() {
        return (AirEventLogger) Utils.throwIfNull(instance, "Must call init() before track()");
    }
}
