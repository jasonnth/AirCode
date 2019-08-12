package com.daon.sdk.face;

import android.os.Bundle;

public class LivenessResult {

    /* renamed from: a */
    private Bundle f3029a;

    LivenessResult(Bundle bundle) {
        this.f3029a = bundle;
    }

    public int getType() {
        return this.f3029a.getInt("result.liveness.type", 0);
    }

    public int getTrackerStatus() {
        return this.f3029a.getInt("result.liveness.tracker", 0);
    }

    public boolean isBlink() {
        return getType() == 3;
    }

    public float getScore() {
        return this.f3029a.getFloat("result.liveness.score");
    }

    public boolean isTrackingFace() {
        return getTrackerStatus() == 1;
    }
}
