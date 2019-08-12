package com.airbnb.epoxy;

import android.util.Log;

class DebugTimer implements Timer {
    private long startTime;
    private final String tag;

    DebugTimer(String tag2) {
        this.tag = tag2;
        reset();
    }

    private void reset() {
        this.startTime = -1;
    }

    public void start() {
        if (this.startTime != -1) {
            throw new IllegalStateException("Timer was already started");
        }
        this.startTime = System.nanoTime();
    }

    public void stop(String message) {
        if (this.startTime == -1) {
            throw new IllegalStateException("Timer was not started");
        }
        float durationMs = ((float) (System.nanoTime() - this.startTime)) / 1000000.0f;
        Log.d(this.tag, String.format(message + ": %.3fms", new Object[]{Float.valueOf(durationMs)}));
        reset();
    }
}
