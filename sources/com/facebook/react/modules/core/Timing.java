package com.facebook.react.modules.core;

import android.util.SparseArray;
import android.view.Choreographer.FrameCallback;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.OnExecutorUnregisteredListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.SystemClock;
import com.facebook.react.devsupport.DevSupportManager;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ReactChoreographer;
import com.facebook.react.uimanager.ReactChoreographer.CallbackType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@ReactModule(name = "RCTTiming", supportsWebWorkers = true)
public final class Timing extends ReactContextBaseJavaModule implements LifecycleEventListener, OnExecutorUnregisteredListener, HeadlessJsTaskEventListener {
    private static final float FRAME_DURATION_MS = 16.666666f;
    private static final float IDLE_CALLBACK_FRAME_DEADLINE_MS = 1.0f;
    /* access modifiers changed from: private */
    public final AtomicBoolean isPaused = new AtomicBoolean(true);
    /* access modifiers changed from: private */
    public final AtomicBoolean isRunningTasks = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public IdleCallbackRunnable mCurrentIdleCallbackRunnable;
    private final DevSupportManager mDevSupportManager;
    private boolean mFrameCallbackPosted = false;
    private boolean mFrameIdleCallbackPosted = false;
    /* access modifiers changed from: private */
    public final List<ExecutorToken> mIdleCallbackContextsToCall;
    /* access modifiers changed from: private */
    public final Object mIdleCallbackGuard = new Object();
    private final IdleFrameCallback mIdleFrameCallback = new IdleFrameCallback();
    /* access modifiers changed from: private */
    public ReactChoreographer mReactChoreographer;
    /* access modifiers changed from: private */
    public final Set<ExecutorToken> mSendIdleEventsExecutorTokens;
    private final TimerFrameCallback mTimerFrameCallback = new TimerFrameCallback();
    /* access modifiers changed from: private */
    public final Object mTimerGuard = new Object();
    /* access modifiers changed from: private */
    public final Map<ExecutorToken, SparseArray<Timer>> mTimerIdsToTimers;
    /* access modifiers changed from: private */
    public final PriorityQueue<Timer> mTimers;

    private class IdleCallbackRunnable implements Runnable {
        private volatile boolean mCancelled = false;
        private final long mFrameStartTime;

        public IdleCallbackRunnable(long frameStartTime) {
            this.mFrameStartTime = frameStartTime;
        }

        public void run() {
            if (!this.mCancelled) {
                long frameTimeElapsed = SystemClock.uptimeMillis() - (this.mFrameStartTime / 1000000);
                long absoluteFrameStartTime = SystemClock.currentTimeMillis() - frameTimeElapsed;
                if (Timing.FRAME_DURATION_MS - ((float) frameTimeElapsed) >= 1.0f) {
                    Timing.this.mIdleCallbackContextsToCall.clear();
                    synchronized (Timing.this.mIdleCallbackGuard) {
                        Timing.this.mIdleCallbackContextsToCall.addAll(Timing.this.mSendIdleEventsExecutorTokens);
                    }
                    for (ExecutorToken context : Timing.this.mIdleCallbackContextsToCall) {
                        ((JSTimersExecution) Timing.this.getReactApplicationContext().getJSModule(context, JSTimersExecution.class)).callIdleCallbacks((double) absoluteFrameStartTime);
                    }
                    Timing.this.mCurrentIdleCallbackRunnable = null;
                }
            }
        }

        public void cancel() {
            this.mCancelled = true;
        }
    }

    private class IdleFrameCallback implements FrameCallback {
        private IdleFrameCallback() {
        }

        public void doFrame(long frameTimeNanos) {
            if (!Timing.this.isPaused.get() || Timing.this.isRunningTasks.get()) {
                if (Timing.this.mCurrentIdleCallbackRunnable != null) {
                    Timing.this.mCurrentIdleCallbackRunnable.cancel();
                }
                Timing.this.mCurrentIdleCallbackRunnable = new IdleCallbackRunnable(frameTimeNanos);
                Timing.this.getReactApplicationContext().runOnJSQueueThread(Timing.this.mCurrentIdleCallbackRunnable);
                ((ReactChoreographer) Assertions.assertNotNull(Timing.this.mReactChoreographer)).postFrameCallback(CallbackType.IDLE_EVENT, this);
            }
        }
    }

    private static class Timer {
        /* access modifiers changed from: private */
        public final int mCallbackID;
        /* access modifiers changed from: private */
        public final ExecutorToken mExecutorToken;
        /* access modifiers changed from: private */
        public final int mInterval;
        /* access modifiers changed from: private */
        public final boolean mRepeat;
        /* access modifiers changed from: private */
        public long mTargetTime;

        private Timer(ExecutorToken executorToken, int callbackID, long initialTargetTime, int duration, boolean repeat) {
            this.mExecutorToken = executorToken;
            this.mCallbackID = callbackID;
            this.mTargetTime = initialTargetTime;
            this.mInterval = duration;
            this.mRepeat = repeat;
        }
    }

    private class TimerFrameCallback implements FrameCallback {
        private final HashMap<ExecutorToken, WritableArray> mTimersToCall;

        private TimerFrameCallback() {
            this.mTimersToCall = new HashMap<>();
        }

        public void doFrame(long frameTimeNanos) {
            if (!Timing.this.isPaused.get() || Timing.this.isRunningTasks.get()) {
                long frameTimeMillis = frameTimeNanos / 1000000;
                synchronized (Timing.this.mTimerGuard) {
                    while (!Timing.this.mTimers.isEmpty() && ((Timer) Timing.this.mTimers.peek()).mTargetTime < frameTimeMillis) {
                        Timer timer = (Timer) Timing.this.mTimers.poll();
                        WritableArray timersForContext = (WritableArray) this.mTimersToCall.get(timer.mExecutorToken);
                        if (timersForContext == null) {
                            timersForContext = Arguments.createArray();
                            this.mTimersToCall.put(timer.mExecutorToken, timersForContext);
                        }
                        timersForContext.pushInt(timer.mCallbackID);
                        if (timer.mRepeat) {
                            timer.mTargetTime = ((long) timer.mInterval) + frameTimeMillis;
                            Timing.this.mTimers.add(timer);
                        } else {
                            SparseArray<Timer> timers = (SparseArray) Timing.this.mTimerIdsToTimers.get(timer.mExecutorToken);
                            if (timers != null) {
                                timers.remove(timer.mCallbackID);
                                if (timers.size() == 0) {
                                    Timing.this.mTimerIdsToTimers.remove(timer.mExecutorToken);
                                }
                            }
                        }
                    }
                }
                for (Entry<ExecutorToken, WritableArray> entry : this.mTimersToCall.entrySet()) {
                    ((JSTimersExecution) Timing.this.getReactApplicationContext().getJSModule((ExecutorToken) entry.getKey(), JSTimersExecution.class)).callTimers((WritableArray) entry.getValue());
                }
                this.mTimersToCall.clear();
                ((ReactChoreographer) Assertions.assertNotNull(Timing.this.mReactChoreographer)).postFrameCallback(CallbackType.TIMERS_EVENTS, this);
            }
        }
    }

    public Timing(ReactApplicationContext reactContext, DevSupportManager devSupportManager) {
        super(reactContext);
        this.mDevSupportManager = devSupportManager;
        this.mTimers = new PriorityQueue<>(11, new Comparator<Timer>() {
            public int compare(Timer lhs, Timer rhs) {
                long diff = lhs.mTargetTime - rhs.mTargetTime;
                if (diff == 0) {
                    return 0;
                }
                if (diff < 0) {
                    return -1;
                }
                return 1;
            }
        });
        this.mTimerIdsToTimers = new HashMap();
        this.mSendIdleEventsExecutorTokens = new HashSet();
        this.mIdleCallbackContextsToCall = new ArrayList();
    }

    public void initialize() {
        this.mReactChoreographer = ReactChoreographer.getInstance();
        getReactApplicationContext().addLifecycleEventListener(this);
        HeadlessJsTaskContext.getInstance(getReactApplicationContext()).addTaskEventListener(this);
    }

    public void onHostPause() {
        this.isPaused.set(true);
        clearChoreographerCallback();
        maybeClearChoreographerIdleCallback();
    }

    public void onHostDestroy() {
        clearChoreographerCallback();
        maybeClearChoreographerIdleCallback();
    }

    public void onHostResume() {
        this.isPaused.set(false);
        setChoreographerCallback();
        maybeSetChoreographerIdleCallback();
    }

    public void onHeadlessJsTaskStart(int taskId) {
        if (!this.isRunningTasks.getAndSet(true)) {
            setChoreographerCallback();
            maybeSetChoreographerIdleCallback();
        }
    }

    public void onHeadlessJsTaskFinish(int taskId) {
        if (!HeadlessJsTaskContext.getInstance(getReactApplicationContext()).hasActiveTasks()) {
            this.isRunningTasks.set(false);
            clearChoreographerCallback();
            maybeClearChoreographerIdleCallback();
        }
    }

    public void onCatalystInstanceDestroy() {
        clearChoreographerCallback();
        clearChoreographerIdleCallback();
        HeadlessJsTaskContext.getInstance(getReactApplicationContext()).removeTaskEventListener(this);
    }

    private void maybeSetChoreographerIdleCallback() {
        synchronized (this.mIdleCallbackGuard) {
            if (this.mSendIdleEventsExecutorTokens.size() > 0) {
                setChoreographerIdleCallback();
            }
        }
    }

    private void maybeClearChoreographerIdleCallback() {
        if (this.isPaused.get() && !this.isRunningTasks.get()) {
            clearChoreographerCallback();
        }
    }

    private void setChoreographerCallback() {
        if (!this.mFrameCallbackPosted) {
            ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).postFrameCallback(CallbackType.TIMERS_EVENTS, this.mTimerFrameCallback);
            this.mFrameCallbackPosted = true;
        }
    }

    private void clearChoreographerCallback() {
        HeadlessJsTaskContext headlessJsTaskContext = HeadlessJsTaskContext.getInstance(getReactApplicationContext());
        if (this.mFrameCallbackPosted && this.isPaused.get() && !headlessJsTaskContext.hasActiveTasks()) {
            ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).removeFrameCallback(CallbackType.TIMERS_EVENTS, this.mTimerFrameCallback);
            this.mFrameCallbackPosted = false;
        }
    }

    /* access modifiers changed from: private */
    public void setChoreographerIdleCallback() {
        if (!this.mFrameIdleCallbackPosted) {
            ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).postFrameCallback(CallbackType.IDLE_EVENT, this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = true;
        }
    }

    /* access modifiers changed from: private */
    public void clearChoreographerIdleCallback() {
        if (this.mFrameIdleCallbackPosted) {
            ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).removeFrameCallback(CallbackType.IDLE_EVENT, this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = false;
        }
    }

    public String getName() {
        return "RCTTiming";
    }

    public boolean supportsWebWorkers() {
        return true;
    }

    public void onExecutorDestroyed(ExecutorToken executorToken) {
        synchronized (this.mTimerGuard) {
            SparseArray<Timer> timersForContext = (SparseArray) this.mTimerIdsToTimers.remove(executorToken);
            if (timersForContext != null) {
                for (int i = 0; i < timersForContext.size(); i++) {
                    this.mTimers.remove((Timer) timersForContext.get(timersForContext.keyAt(i)));
                }
                synchronized (this.mIdleCallbackGuard) {
                    this.mSendIdleEventsExecutorTokens.remove(executorToken);
                }
            }
        }
    }

    @ReactMethod
    public void createTimer(ExecutorToken executorToken, int callbackID, int duration, double jsSchedulingTime, boolean repeat) {
        long deviceTime = SystemClock.currentTimeMillis();
        long remoteTime = (long) jsSchedulingTime;
        if (this.mDevSupportManager.getDevSupportEnabled() && Math.abs(remoteTime - deviceTime) > 60000) {
            ((JSTimersExecution) getReactApplicationContext().getJSModule(executorToken, JSTimersExecution.class)).emitTimeDriftWarning("Debugger and device times have drifted by more than 60s. Please correct this by running adb shell \"date `date +%m%d%H%M%Y.%S`\" on your debugger machine.");
        }
        long adjustedDuration = Math.max(0, (remoteTime - deviceTime) + ((long) duration));
        if (duration != 0 || repeat) {
            Timer timer = new Timer(executorToken, callbackID, (SystemClock.nanoTime() / 1000000) + adjustedDuration, duration, repeat);
            synchronized (this.mTimerGuard) {
                this.mTimers.add(timer);
                SparseArray<Timer> timersForContext = (SparseArray) this.mTimerIdsToTimers.get(executorToken);
                if (timersForContext == null) {
                    timersForContext = new SparseArray<>();
                    this.mTimerIdsToTimers.put(executorToken, timersForContext);
                }
                timersForContext.put(callbackID, timer);
            }
            return;
        }
        WritableArray timerToCall = Arguments.createArray();
        timerToCall.pushInt(callbackID);
        ((JSTimersExecution) getReactApplicationContext().getJSModule(executorToken, JSTimersExecution.class)).callTimers(timerToCall);
    }

    @ReactMethod
    public void deleteTimer(ExecutorToken executorToken, int timerId) {
        synchronized (this.mTimerGuard) {
            SparseArray<Timer> timersForContext = (SparseArray) this.mTimerIdsToTimers.get(executorToken);
            if (timersForContext != null) {
                Timer timer = (Timer) timersForContext.get(timerId);
                if (timer != null) {
                    timersForContext.remove(timerId);
                    if (timersForContext.size() == 0) {
                        this.mTimerIdsToTimers.remove(executorToken);
                    }
                    this.mTimers.remove(timer);
                }
            }
        }
    }

    @ReactMethod
    public void setSendIdleEvents(ExecutorToken executorToken, boolean sendIdleEvents) {
        synchronized (this.mIdleCallbackGuard) {
            if (sendIdleEvents) {
                this.mSendIdleEventsExecutorTokens.add(executorToken);
            } else {
                this.mSendIdleEventsExecutorTokens.remove(executorToken);
            }
        }
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                synchronized (Timing.this.mIdleCallbackGuard) {
                    if (Timing.this.mSendIdleEventsExecutorTokens.size() > 0) {
                        Timing.this.setChoreographerIdleCallback();
                    } else {
                        Timing.this.clearChoreographerIdleCallback();
                    }
                }
            }
        });
    }
}
