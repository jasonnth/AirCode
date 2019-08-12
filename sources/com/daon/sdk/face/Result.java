package com.daon.sdk.face;

import android.os.Bundle;

public class Result {

    /* renamed from: a */
    private Bundle f3032a;

    Result(Bundle bundle) {
        this.f3032a = bundle;
    }

    public boolean isFaceFound() {
        boolean z;
        if (!new RecognitionResult(this.f3032a).getFacePosition().isEmpty()) {
            z = true;
        } else {
            z = false;
        }
        if (z || getQualityResult().mo28795a() || getLivenessResult().isTrackingFace()) {
            return true;
        }
        return false;
    }

    public RecognitionResult getRecognitionResult() {
        return new RecognitionResult(this.f3032a);
    }

    public LivenessResult getLivenessResult() {
        return new LivenessResult(this.f3032a);
    }

    public QualityResult getQualityResult() {
        QualityResult qualityResult = new QualityResult(this.f3032a);
        return qualityResult.mo28795a() ? qualityResult : new QualityResult(new Bundle());
    }

    public boolean isDeviceUpright() {
        float f = this.f3032a.getFloat("result.sensor.pitch", -60.0f);
        return f <= -60.0f && f > -90.0f;
    }

    public String toString() {
        return this.f3032a.toString();
    }
}
