package com.jumio.clientlib.impl.livenessAndTM;

public class LivenessDetectorCallback {

    /* renamed from: a */
    private long f3186a;
    protected boolean swigCMemOwn;

    protected LivenessDetectorCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3186a = j;
    }

    protected static long getCPtr(LivenessDetectorCallback livenessDetectorCallback) {
        if (livenessDetectorCallback == null) {
            return 0;
        }
        return livenessDetectorCallback.f3186a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3186a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_LivenessDetectorCallback(this.f3186a);
            }
            this.f3186a = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        jniLivenessAndTMJNI.LivenessDetectorCallback_change_ownership(this, this.f3186a, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        jniLivenessAndTMJNI.LivenessDetectorCallback_change_ownership(this, this.f3186a, true);
    }

    public LivenessDetectorCallback() {
        this(jniLivenessAndTMJNI.new_LivenessDetectorCallback(), true);
        jniLivenessAndTMJNI.LivenessDetectorCallback_director_connect(this, this.f3186a, this.swigCMemOwn, true);
    }

    public void onResult(LivenessDetectorResult livenessDetectorResult, int i) {
        if (getClass() == LivenessDetectorCallback.class) {
            jniLivenessAndTMJNI.LivenessDetectorCallback_onResult(this.f3186a, this, LivenessDetectorResult.getCPtr(livenessDetectorResult), livenessDetectorResult, i);
            return;
        }
        jniLivenessAndTMJNI.m1905x45617e71(this.f3186a, this, LivenessDetectorResult.getCPtr(livenessDetectorResult), livenessDetectorResult, i);
    }
}
