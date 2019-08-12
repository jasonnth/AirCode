package com.facebook.react.uimanager.events;

import android.util.LongSparseArray;
import android.view.Choreographer.FrameCallback;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ReactChoreographer;
import com.facebook.react.uimanager.ReactChoreographer.CallbackType;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

public class EventDispatcher implements LifecycleEventListener {
    /* access modifiers changed from: private */
    public static final Comparator<Event> EVENT_COMPARATOR = new Comparator<Event>() {
        public int compare(Event lhs, Event rhs) {
            if (lhs == null && rhs == null) {
                return 0;
            }
            if (lhs == null) {
                return -1;
            }
            if (rhs == null) {
                return 1;
            }
            long diff = lhs.getTimestampMs() - rhs.getTimestampMs();
            if (diff == 0) {
                return 0;
            }
            if (diff < 0) {
                return -1;
            }
            return 1;
        }
    };
    /* access modifiers changed from: private */
    public final ScheduleDispatchFrameCallback mCurrentFrameCallback;
    /* access modifiers changed from: private */
    public final DispatchEventsRunnable mDispatchEventsRunnable = new DispatchEventsRunnable();
    /* access modifiers changed from: private */
    public final LongSparseArray<Integer> mEventCookieToLastEventIdx = new LongSparseArray<>();
    private final Map<String, Short> mEventNameToEventId = MapBuilder.newHashMap();
    private final ArrayList<Event> mEventStaging = new ArrayList<>();
    private final Object mEventsStagingLock = new Object();
    /* access modifiers changed from: private */
    public Event[] mEventsToDispatch = new Event[16];
    /* access modifiers changed from: private */
    public final Object mEventsToDispatchLock = new Object();
    /* access modifiers changed from: private */
    public int mEventsToDispatchSize = 0;
    /* access modifiers changed from: private */
    public volatile boolean mHasDispatchScheduled = false;
    /* access modifiers changed from: private */
    public volatile int mHasDispatchScheduledCount = 0;
    private final ArrayList<EventDispatcherListener> mListeners = new ArrayList<>();
    private short mNextEventTypeId = 0;
    /* access modifiers changed from: private */
    public volatile RCTEventEmitter mRCTEventEmitter;
    /* access modifiers changed from: private */
    public final ReactApplicationContext mReactContext;

    private class DispatchEventsRunnable implements Runnable {
        private DispatchEventsRunnable() {
        }

        public void run() {
            Systrace.beginSection(0, "DispatchEventsRunnable");
            try {
                Systrace.endAsyncFlow(0, "ScheduleDispatchFrameCallback", EventDispatcher.this.mHasDispatchScheduledCount);
                EventDispatcher.this.mHasDispatchScheduled = false;
                EventDispatcher.this.mHasDispatchScheduledCount = EventDispatcher.this.mHasDispatchScheduledCount + 1;
                Assertions.assertNotNull(EventDispatcher.this.mRCTEventEmitter);
                synchronized (EventDispatcher.this.mEventsToDispatchLock) {
                    if (EventDispatcher.this.mEventsToDispatchSize > 1) {
                        Arrays.sort(EventDispatcher.this.mEventsToDispatch, 0, EventDispatcher.this.mEventsToDispatchSize, EventDispatcher.EVENT_COMPARATOR);
                    }
                    for (int eventIdx = 0; eventIdx < EventDispatcher.this.mEventsToDispatchSize; eventIdx++) {
                        Event event = EventDispatcher.this.mEventsToDispatch[eventIdx];
                        if (event != null) {
                            Systrace.endAsyncFlow(0, event.getEventName(), event.getUniqueID());
                            event.dispatch(EventDispatcher.this.mRCTEventEmitter);
                            event.dispose();
                        }
                    }
                    EventDispatcher.this.clearEventsToDispatch();
                    EventDispatcher.this.mEventCookieToLastEventIdx.clear();
                }
            } finally {
                Systrace.endSection(0);
            }
        }
    }

    private class ScheduleDispatchFrameCallback implements FrameCallback {
        private volatile boolean mIsPosted;
        private boolean mShouldStop;

        private ScheduleDispatchFrameCallback() {
            this.mIsPosted = false;
            this.mShouldStop = false;
        }

        public void doFrame(long frameTimeNanos) {
            UiThreadUtil.assertOnUiThread();
            if (this.mShouldStop) {
                this.mIsPosted = false;
            } else {
                post();
            }
            Systrace.beginSection(0, "ScheduleDispatchFrameCallback");
            try {
                EventDispatcher.this.moveStagedEventsToDispatchQueue();
                if (EventDispatcher.this.mEventsToDispatchSize > 0 && !EventDispatcher.this.mHasDispatchScheduled) {
                    EventDispatcher.this.mHasDispatchScheduled = true;
                    Systrace.startAsyncFlow(0, "ScheduleDispatchFrameCallback", EventDispatcher.this.mHasDispatchScheduledCount);
                    EventDispatcher.this.mReactContext.runOnJSQueueThread(EventDispatcher.this.mDispatchEventsRunnable);
                }
            } finally {
                Systrace.endSection(0);
            }
        }

        public void stop() {
            this.mShouldStop = true;
        }

        public void maybePost() {
            if (!this.mIsPosted) {
                this.mIsPosted = true;
                post();
            }
        }

        private void post() {
            ReactChoreographer.getInstance().postFrameCallback(CallbackType.TIMERS_EVENTS, EventDispatcher.this.mCurrentFrameCallback);
        }

        public void maybePostFromNonUI() {
            if (!this.mIsPosted) {
                if (EventDispatcher.this.mReactContext.isOnUiQueueThread()) {
                    maybePost();
                } else {
                    EventDispatcher.this.mReactContext.runOnUiQueueThread(new Runnable() {
                        public void run() {
                            ScheduleDispatchFrameCallback.this.maybePost();
                        }
                    });
                }
            }
        }
    }

    public EventDispatcher(ReactApplicationContext reactContext) {
        this.mReactContext = reactContext;
        this.mReactContext.addLifecycleEventListener(this);
        this.mCurrentFrameCallback = new ScheduleDispatchFrameCallback();
    }

    public void dispatchEvent(Event event) {
        Assertions.assertCondition(event.isInitialized(), "Dispatched event hasn't been initialized");
        Iterator it = this.mListeners.iterator();
        while (it.hasNext()) {
            ((EventDispatcherListener) it.next()).onEventDispatch(event);
        }
        synchronized (this.mEventsStagingLock) {
            this.mEventStaging.add(event);
            Systrace.startAsyncFlow(0, event.getEventName(), event.getUniqueID());
        }
        if (this.mRCTEventEmitter != null) {
            this.mCurrentFrameCallback.maybePostFromNonUI();
        }
    }

    public void addListener(EventDispatcherListener listener) {
        this.mListeners.add(listener);
    }

    public void removeListener(EventDispatcherListener listener) {
        this.mListeners.remove(listener);
    }

    public void onHostResume() {
        UiThreadUtil.assertOnUiThread();
        if (this.mRCTEventEmitter == null) {
            this.mRCTEventEmitter = (RCTEventEmitter) this.mReactContext.getJSModule(RCTEventEmitter.class);
        }
        this.mCurrentFrameCallback.maybePost();
    }

    public void onHostPause() {
        stopFrameCallback();
    }

    public void onHostDestroy() {
        stopFrameCallback();
    }

    public void onCatalystInstanceDestroyed() {
        stopFrameCallback();
    }

    private void stopFrameCallback() {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentFrameCallback.stop();
    }

    /* access modifiers changed from: private */
    public void moveStagedEventsToDispatchQueue() {
        synchronized (this.mEventsStagingLock) {
            synchronized (this.mEventsToDispatchLock) {
                for (int i = 0; i < this.mEventStaging.size(); i++) {
                    Event event = (Event) this.mEventStaging.get(i);
                    if (!event.canCoalesce()) {
                        addEventToEventsToDispatch(event);
                    } else {
                        long eventCookie = getEventCookie(event.getViewTag(), event.getEventName(), event.getCoalescingKey());
                        Event eventToAdd = null;
                        Event eventToDispose = null;
                        Integer lastEventIdx = (Integer) this.mEventCookieToLastEventIdx.get(eventCookie);
                        if (lastEventIdx == null) {
                            eventToAdd = event;
                            this.mEventCookieToLastEventIdx.put(eventCookie, Integer.valueOf(this.mEventsToDispatchSize));
                        } else {
                            Event lastEvent = this.mEventsToDispatch[lastEventIdx.intValue()];
                            Event coalescedEvent = event.coalesce(lastEvent);
                            if (coalescedEvent != lastEvent) {
                                eventToAdd = coalescedEvent;
                                this.mEventCookieToLastEventIdx.put(eventCookie, Integer.valueOf(this.mEventsToDispatchSize));
                                eventToDispose = lastEvent;
                                this.mEventsToDispatch[lastEventIdx.intValue()] = null;
                            } else {
                                eventToDispose = event;
                            }
                        }
                        if (eventToAdd != null) {
                            addEventToEventsToDispatch(eventToAdd);
                        }
                        if (eventToDispose != null) {
                            eventToDispose.dispose();
                        }
                    }
                }
            }
            this.mEventStaging.clear();
        }
    }

    private long getEventCookie(int viewTag, String eventName, short coalescingKey) {
        short eventTypeId;
        Short eventIdObj = (Short) this.mEventNameToEventId.get(eventName);
        if (eventIdObj != null) {
            eventTypeId = eventIdObj.shortValue();
        } else {
            eventTypeId = this.mNextEventTypeId;
            this.mNextEventTypeId = (short) (eventTypeId + 1);
            this.mEventNameToEventId.put(eventName, Short.valueOf(eventTypeId));
        }
        return getEventCookie(viewTag, eventTypeId, coalescingKey);
    }

    private static long getEventCookie(int viewTag, short eventTypeId, short coalescingKey) {
        return ((long) viewTag) | ((((long) eventTypeId) & 65535) << 32) | ((((long) coalescingKey) & 65535) << 48);
    }

    private void addEventToEventsToDispatch(Event event) {
        if (this.mEventsToDispatchSize == this.mEventsToDispatch.length) {
            this.mEventsToDispatch = (Event[]) Arrays.copyOf(this.mEventsToDispatch, this.mEventsToDispatch.length * 2);
        }
        Event[] eventArr = this.mEventsToDispatch;
        int i = this.mEventsToDispatchSize;
        this.mEventsToDispatchSize = i + 1;
        eventArr[i] = event;
    }

    /* access modifiers changed from: private */
    public void clearEventsToDispatch() {
        Arrays.fill(this.mEventsToDispatch, 0, this.mEventsToDispatchSize, null);
        this.mEventsToDispatchSize = 0;
    }
}
