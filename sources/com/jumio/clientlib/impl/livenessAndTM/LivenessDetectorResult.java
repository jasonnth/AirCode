package com.jumio.clientlib.impl.livenessAndTM;

public class LivenessDetectorResult {

    /* renamed from: a */
    private long f3187a;
    protected boolean swigCMemOwn;

    protected LivenessDetectorResult(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3187a = j;
    }

    protected static long getCPtr(LivenessDetectorResult livenessDetectorResult) {
        if (livenessDetectorResult == null) {
            return 0;
        }
        return livenessDetectorResult.f3187a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3187a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_LivenessDetectorResult(this.f3187a);
            }
            this.f3187a = 0;
        }
    }

    public LivenessDetectorResult(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, LandmarkVector landmarkVector, FaceRect faceRect) {
        this(jniLivenessAndTMJNI.new_LivenessDetectorResult__SWIG_0(z, z2, z3, z4, z5, LandmarkVector.getCPtr(landmarkVector), landmarkVector, FaceRect.getCPtr(faceRect), faceRect), true);
    }

    public LivenessDetectorResult(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, LandmarkVector landmarkVector) {
        this(jniLivenessAndTMJNI.new_LivenessDetectorResult__SWIG_1(z, z2, z3, z4, z5, LandmarkVector.getCPtr(landmarkVector), landmarkVector), true);
    }

    public LivenessDetectorResult(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this(jniLivenessAndTMJNI.new_LivenessDetectorResult__SWIG_2(z, z2, z3, z4, z5), true);
    }

    public LivenessDetectorResult(boolean z, boolean z2, boolean z3, boolean z4) {
        this(jniLivenessAndTMJNI.new_LivenessDetectorResult__SWIG_3(z, z2, z3, z4), true);
    }

    public LivenessDetectorResult(boolean z, boolean z2, boolean z3) {
        this(jniLivenessAndTMJNI.new_LivenessDetectorResult__SWIG_4(z, z2, z3), true);
    }

    public LivenessDetectorResult(boolean z, boolean z2) {
        this(jniLivenessAndTMJNI.new_LivenessDetectorResult__SWIG_5(z, z2), true);
    }

    public LivenessDetectorResult(boolean z) {
        this(jniLivenessAndTMJNI.new_LivenessDetectorResult__SWIG_6(z), true);
    }

    public LivenessDetectorResult() {
        this(jniLivenessAndTMJNI.new_LivenessDetectorResult__SWIG_7(), true);
    }

    public boolean isRightEyeBlinking() {
        return jniLivenessAndTMJNI.LivenessDetectorResult_isRightEyeBlinking(this.f3187a, this);
    }

    public boolean isLeftEyeBlinking() {
        return jniLivenessAndTMJNI.LivenessDetectorResult_isLeftEyeBlinking(this.f3187a, this);
    }

    public boolean isSmiling() {
        return jniLivenessAndTMJNI.LivenessDetectorResult_isSmiling(this.f3187a, this);
    }

    public boolean isTracking() {
        return jniLivenessAndTMJNI.LivenessDetectorResult_isTracking(this.f3187a, this);
    }

    public boolean isTooLargeMovement() {
        return jniLivenessAndTMJNI.LivenessDetectorResult_isTooLargeMovement(this.f3187a, this);
    }

    public LandmarkVector getLandmarkPoints() {
        return new LandmarkVector(jniLivenessAndTMJNI.LivenessDetectorResult_getLandmarkPoints(this.f3187a, this), false);
    }

    public FaceRect getFaceRect() {
        return new FaceRect(jniLivenessAndTMJNI.LivenessDetectorResult_getFaceRect(this.f3187a, this), false);
    }
}
