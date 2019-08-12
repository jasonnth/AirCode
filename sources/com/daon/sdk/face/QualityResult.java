package com.daon.sdk.face;

import android.os.Bundle;

public class QualityResult {

    /* renamed from: a */
    private Bundle f3030a;

    QualityResult(Bundle bundle) {
        this.f3030a = bundle;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final boolean mo28795a() {
        return getFaceFoundConfidence() > 0.0f;
    }

    public float getFaceFoundConfidence() {
        return this.f3030a.getFloat("face.found.confidence");
    }

    public float getFaceFrontalConfidence() {
        return this.f3030a.getFloat("face.frontal.confidence");
    }

    public float getEyesFoundConfidence() {
        return this.f3030a.getFloat("eyes.found.confidence");
    }

    public float getEyesOpenConfidence() {
        return this.f3030a.getFloat("eyes.open.confidence");
    }

    public float getUniformLightingConfidence() {
        return this.f3030a.getFloat("lighting.uniform.confidence");
    }

    public float getGlobalScore() {
        return this.f3030a.getFloat("global.score");
    }

    public int getPoseAngle() {
        return this.f3030a.getInt("pose.angle");
    }

    public int getExposure() {
        return this.f3030a.getInt("exposure");
    }

    public int getEyeDistance() {
        return this.f3030a.getInt("eyes.distance");
    }

    public int getGrayscaleDensity() {
        return this.f3030a.getInt("grayscale.density");
    }

    public int getSharpness() {
        return this.f3030a.getInt("sharpness");
    }
}
