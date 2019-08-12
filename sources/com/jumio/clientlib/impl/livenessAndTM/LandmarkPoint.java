package com.jumio.clientlib.impl.livenessAndTM;

public class LandmarkPoint {

    /* renamed from: a */
    private long f3182a;
    protected boolean swigCMemOwn;

    protected LandmarkPoint(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3182a = j;
    }

    protected static long getCPtr(LandmarkPoint landmarkPoint) {
        if (landmarkPoint == null) {
            return 0;
        }
        return landmarkPoint.f3182a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3182a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_LandmarkPoint(this.f3182a);
            }
            this.f3182a = 0;
        }
    }

    public LandmarkPoint(float f, float f2) {
        this(jniLivenessAndTMJNI.new_LandmarkPoint__SWIG_0(f, f2), true);
    }

    public LandmarkPoint(float f) {
        this(jniLivenessAndTMJNI.new_LandmarkPoint__SWIG_1(f), true);
    }

    public LandmarkPoint() {
        this(jniLivenessAndTMJNI.new_LandmarkPoint__SWIG_2(), true);
    }

    public float getX() {
        return jniLivenessAndTMJNI.LandmarkPoint_getX(this.f3182a, this);
    }

    public float getY() {
        return jniLivenessAndTMJNI.LandmarkPoint_getY(this.f3182a, this);
    }
}
