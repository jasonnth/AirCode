package com.jumio.analytics.agents;

import com.jumio.analytics.AnalyticsEvent;
import com.jumio.analytics.DispatchException;
import com.jumio.analytics.EventDispatcher;
import com.jumio.analytics.Filter;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.commons.log.Log;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class EventAgent {
    protected final String LOGTAG = JumioAnalytics.LOGTAG;
    private final ExecutorService mDispatchExecutor = Executors.newSingleThreadExecutor();
    protected EventDispatcher mEventDispatcher;
    protected Filter mFilter;
    private boolean mLocked = true;
    protected final Queue<AnalyticsEvent> mRequestQueue;
    protected final Object queueLock = new Object();

    private class DispatchRunner implements Callable<Void> {
        private Collection<AnalyticsEvent> mEvents;

        private DispatchRunner(Collection<AnalyticsEvent> events) {
            this.mEvents = events;
        }

        public Void call() {
            try {
                Log.m1924v(JumioAnalytics.LOGTAG, "EventDispatcher.dispatchEvents()");
                EventAgent.this.mEventDispatcher.dispatchEvents(this.mEvents);
            } catch (DispatchException e) {
                Log.m1930w(JumioAnalytics.LOGTAG, "Exception while event dispatch", (Throwable) e);
                synchronized (EventAgent.this.queueLock) {
                    EventAgent.this.mRequestQueue.addAll(this.mEvents);
                }
            }
            return null;
        }
    }

    public abstract void flush();

    public abstract void start();

    public EventAgent(Queue<AnalyticsEvent> queue) {
        this.mRequestQueue = queue;
        this.mFilter = null;
    }

    public final void enqueue(AnalyticsEvent event) {
        if (this.mFilter == null || !this.mFilter.contains(event.getEventType())) {
            synchronized (this.queueLock) {
                Log.m1909d(JumioAnalytics.LOGTAG, "enqueue " + event.toString());
                this.mRequestQueue.add(event);
                eventEnqueued();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void eventEnqueued() {
    }

    public final void setEventDispatcher(EventDispatcher eventDispatcher) {
        Log.m1924v(JumioAnalytics.LOGTAG, "set new EventDispatcher: " + eventDispatcher);
        this.mEventDispatcher = eventDispatcher;
    }

    public final int getQueueSize() {
        int size;
        synchronized (this.queueLock) {
            size = this.mRequestQueue.size();
        }
        return size;
    }

    public final boolean isLocked() {
        return this.mLocked;
    }

    public final void setLocked(boolean locked) {
        this.mLocked = locked;
    }

    /* access modifiers changed from: protected */
    public void dispatchAndClear() {
        if (this.mLocked) {
            Log.m1929w(JumioAnalytics.LOGTAG, "cannot dispatch - agent is locked!");
        } else if (this.mRequestQueue != null && this.mRequestQueue.size() > 0) {
            synchronized (this.queueLock) {
                if (this.mEventDispatcher != null) {
                    List<AnalyticsEvent> evt = Arrays.asList(this.mRequestQueue.toArray(new AnalyticsEvent[getQueueSize()]));
                    this.mRequestQueue.clear();
                    this.mDispatchExecutor.submit(new DispatchRunner(evt));
                } else {
                    Log.m1929w(JumioAnalytics.LOGTAG, "cannot dispatchAndClear - dispatcher is null!");
                }
            }
        }
    }

    public void addFinishTask(Runnable finishRunnable) {
        synchronized (this.queueLock) {
            if (!(this.mEventDispatcher == null || finishRunnable == null)) {
                this.mDispatchExecutor.submit(finishRunnable);
            }
        }
    }

    public void stop() {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.clear();
        }
    }

    public Filter getFilter() {
        return this.mFilter;
    }
}
