package com.devbrackets.android.exomedia.util;

import android.os.Handler;
import android.os.HandlerThread;

public class StopWatch {
    /* access modifiers changed from: private */
    public long currentTime;
    /* access modifiers changed from: private */
    public Handler delayedHandler;
    private HandlerThread handlerThread;
    /* access modifiers changed from: private */
    public volatile boolean isRunning;
    /* access modifiers changed from: private */
    public TickListener listener;
    /* access modifiers changed from: private */
    public long startTime;
    /* access modifiers changed from: private */
    public long storedTime;
    /* access modifiers changed from: private */
    public int tickDelay;
    /* access modifiers changed from: private */
    public TickRunnable tickRunnable;
    private boolean useHandlerThread;

    public interface TickListener {
        void onStopWatchTick(long j);
    }

    private class TickRunnable implements Runnable {
        private TickRunnable() {
        }

        public void run() {
            StopWatch.this.currentTime = System.currentTimeMillis() - StopWatch.this.startTime;
            if (StopWatch.this.isRunning) {
                performTick();
            }
            if (StopWatch.this.listener != null) {
                StopWatch.this.listener.onStopWatchTick(StopWatch.this.currentTime + StopWatch.this.storedTime);
            }
        }

        public void performTick() {
            StopWatch.this.delayedHandler.postDelayed(StopWatch.this.tickRunnable, (long) StopWatch.this.tickDelay);
        }
    }

    public StopWatch() {
        this(true);
    }

    public StopWatch(boolean processOnStartingThread) {
        this.isRunning = false;
        this.tickDelay = 33;
        this.useHandlerThread = false;
        this.tickRunnable = new TickRunnable();
        this.startTime = 0;
        this.currentTime = 0;
        this.storedTime = 0;
        if (processOnStartingThread) {
            this.delayedHandler = new Handler();
        } else {
            this.useHandlerThread = true;
        }
    }

    public void start() {
        if (!isRunning()) {
            this.isRunning = true;
            this.startTime = System.currentTimeMillis();
            if (this.useHandlerThread) {
                this.handlerThread = new HandlerThread("ExoMedia_StopWatch_HandlerThread");
                this.handlerThread.start();
                this.delayedHandler = new Handler(this.handlerThread.getLooper());
            }
            this.tickRunnable.performTick();
        }
    }

    public void stop() {
        if (isRunning()) {
            this.delayedHandler.removeCallbacksAndMessages(null);
            if (this.handlerThread != null) {
                this.handlerThread.quit();
            }
            this.isRunning = false;
            this.currentTime = 0;
            this.storedTime += System.currentTimeMillis() - this.startTime;
        }
    }

    public void reset() {
        this.currentTime = 0;
        this.storedTime = 0;
        this.startTime = System.currentTimeMillis();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public int getTimeInt() {
        long time = this.currentTime + this.storedTime;
        if (time < 2147483647L) {
            return (int) time;
        }
        return Integer.MAX_VALUE;
    }
}
