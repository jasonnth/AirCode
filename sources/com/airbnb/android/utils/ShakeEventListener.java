package com.airbnb.android.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class ShakeEventListener implements SensorEventListener {
    private static final int FIVE_SECONDS = 5000;
    private static final int MIN_DIRECTION_CHANGES = 3;
    private static final int MIN_FORCE_TO_DETECT = 20;
    private static final int TIME_BETWEEN_DIRECTION_CHANGE_MAX = 250;
    private static final int TOTAL_SHAKE_TIME_MAX = 2000;
    private static final int TOTAL_SHAKE_TIME_MIN = 500;
    private int directionChangeCount = 0;
    private long firstDirectionChangeTime = 0;
    private long lastDirectionChangeTime;
    private long lastShakeTime;
    private float lastX = 0.0f;
    private float lastY = 0.0f;
    private float lastZ = 0.0f;
    private OnShakeListener shakeListener;

    public interface OnShakeListener {
        void onShake();
    }

    public void setOnShakeListener(OnShakeListener listener) {
        this.shakeListener = listener;
    }

    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if (Math.abs(((((x + y) + z) - this.lastX) - this.lastY) - this.lastZ) > 20.0f) {
            long now = System.currentTimeMillis();
            if (this.firstDirectionChangeTime == 0) {
                this.firstDirectionChangeTime = now;
                this.lastDirectionChangeTime = now;
            }
            if (now - this.lastDirectionChangeTime < 250) {
                this.lastDirectionChangeTime = now;
                this.directionChangeCount++;
                this.lastX = x;
                this.lastY = y;
                this.lastZ = z;
                if (this.directionChangeCount >= 3) {
                    long totalDuration = now - this.firstDirectionChangeTime;
                    long timeSinceLastShake = now - this.lastShakeTime;
                    if (totalDuration > 500 && totalDuration < 2000 && timeSinceLastShake > 5000) {
                        this.lastShakeTime = now;
                        if (this.shakeListener != null) {
                            this.shakeListener.onShake();
                        }
                        reset();
                        return;
                    }
                    return;
                }
                return;
            }
            reset();
        }
    }

    /* access modifiers changed from: 0000 */
    public void reset() {
        this.firstDirectionChangeTime = 0;
        this.directionChangeCount = 0;
        this.lastDirectionChangeTime = 0;
        this.lastX = 0.0f;
        this.lastY = 0.0f;
        this.lastZ = 0.0f;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
