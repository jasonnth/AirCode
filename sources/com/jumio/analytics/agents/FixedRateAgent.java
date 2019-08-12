package com.jumio.analytics.agents;

import com.jumio.analytics.EventDispatcher;
import com.jumio.analytics.Filter;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.commons.log.Log;
import com.miteksystems.misnap.params.SDKConstants;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class FixedRateAgent extends EventAgent implements Runnable {
    private ScheduledFuture<?> mEventChecker;
    private int mSendIntervalMsec;
    private final ScheduledExecutorService scheduler;

    public FixedRateAgent(EventDispatcher dispatcher, Filter disabledEvents) {
        super(new ConcurrentLinkedQueue());
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.mSendIntervalMsec = SDKConstants.CAM_INIT_CAMERA;
        this.mEventDispatcher = dispatcher;
        start();
        this.mFilter = disabledEvents;
    }

    public FixedRateAgent() {
        super(new ConcurrentLinkedQueue());
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.mSendIntervalMsec = SDKConstants.CAM_INIT_CAMERA;
        this.mEventDispatcher = null;
        start();
    }

    private void initTimedHandler() {
        Log.m1924v(JumioAnalytics.LOGTAG, "start with fixed rate at P=" + this.mSendIntervalMsec + " ms");
        if (this.mEventChecker != null) {
            Log.m1924v(JumioAnalytics.LOGTAG, "cancelling old event handler");
            this.mEventChecker.cancel(true);
        }
        this.mEventChecker = this.scheduler.scheduleWithFixedDelay(this, (long) this.mSendIntervalMsec, (long) this.mSendIntervalMsec, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        super.stop();
        if (this.mEventChecker != null && !this.mEventChecker.isCancelled()) {
            this.mEventChecker.cancel(true);
        }
    }

    public void start() {
        initTimedHandler();
    }

    public void flush() {
        Log.m1909d(JumioAnalytics.LOGTAG, "flush() queue");
        if (this.mEventChecker != null) {
            this.mEventChecker.cancel(true);
        }
        synchronized (this.queueLock) {
            if (this.mRequestQueue.isEmpty()) {
                Log.m1924v(JumioAnalytics.LOGTAG, " -- nothing to flush()");
            } else {
                dispatchAndClear();
            }
        }
    }

    public void run() {
        if (this.mRequestQueue.size() > 0) {
            Log.m1909d(JumioAnalytics.LOGTAG, "time trigger: dispatch " + this.mRequestQueue.size() + " events");
            dispatchAndClear();
            return;
        }
        Log.m1924v(JumioAnalytics.LOGTAG, "time trigger: NOOP (no events)");
    }

    public void setSendInterval(int intervalMsec) {
        this.mSendIntervalMsec = intervalMsec;
        Log.m1924v(JumioAnalytics.LOGTAG, "set new interval to " + intervalMsec);
        initTimedHandler();
    }
}
