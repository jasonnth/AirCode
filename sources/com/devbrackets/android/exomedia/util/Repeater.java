package com.devbrackets.android.exomedia.util;

import android.os.Handler;
import android.os.HandlerThread;

public class Repeater {
    /* access modifiers changed from: private */
    public Handler delayedHandler;
    private HandlerThread handlerThread;
    /* access modifiers changed from: private */
    public RepeatListener listener;
    /* access modifiers changed from: private */
    public PollRunnable pollRunnable;
    /* access modifiers changed from: private */
    public int repeatDelay;
    /* access modifiers changed from: private */
    public volatile boolean repeaterRunning;
    private boolean useHandlerThread;

    private class PollRunnable implements Runnable {
        private PollRunnable() {
        }

        public void run() {
            if (Repeater.this.listener != null) {
                Repeater.this.listener.onRepeat();
            }
            if (Repeater.this.repeaterRunning) {
                performPoll();
            }
        }

        public void performPoll() {
            Repeater.this.delayedHandler.postDelayed(Repeater.this.pollRunnable, (long) Repeater.this.repeatDelay);
        }
    }

    public interface RepeatListener {
        void onRepeat();
    }

    public Repeater() {
        this(true);
    }

    public Repeater(boolean processOnStartingThread) {
        this.repeaterRunning = false;
        this.repeatDelay = 33;
        this.useHandlerThread = false;
        this.pollRunnable = new PollRunnable();
        if (processOnStartingThread) {
            this.delayedHandler = new Handler();
        } else {
            this.useHandlerThread = true;
        }
    }

    public void setRepeaterDelay(int milliSeconds) {
        this.repeatDelay = milliSeconds;
    }

    public void start() {
        if (!this.repeaterRunning) {
            this.repeaterRunning = true;
            if (this.useHandlerThread) {
                this.handlerThread = new HandlerThread("ExoMedia_Repeater_HandlerThread");
                this.handlerThread.start();
                this.delayedHandler = new Handler(this.handlerThread.getLooper());
            }
            this.pollRunnable.performPoll();
        }
    }

    public void stop() {
        if (this.handlerThread != null) {
            this.handlerThread.quit();
        }
        this.repeaterRunning = false;
    }

    public void setRepeatListener(RepeatListener listener2) {
        this.listener = listener2;
    }
}
