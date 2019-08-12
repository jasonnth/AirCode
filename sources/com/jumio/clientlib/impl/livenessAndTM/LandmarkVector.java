package com.jumio.clientlib.impl.livenessAndTM;

public class LandmarkVector {

    /* renamed from: a */
    private long f3183a;
    protected boolean swigCMemOwn;

    protected LandmarkVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3183a = j;
    }

    protected static long getCPtr(LandmarkVector landmarkVector) {
        if (landmarkVector == null) {
            return 0;
        }
        return landmarkVector.f3183a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3183a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_LandmarkVector(this.f3183a);
            }
            this.f3183a = 0;
        }
    }

    public LandmarkVector() {
        this(jniLivenessAndTMJNI.new_LandmarkVector__SWIG_0(), true);
    }

    public LandmarkVector(long j) {
        this(jniLivenessAndTMJNI.new_LandmarkVector__SWIG_1(j), true);
    }

    public long size() {
        return jniLivenessAndTMJNI.LandmarkVector_size(this.f3183a, this);
    }

    public long capacity() {
        return jniLivenessAndTMJNI.LandmarkVector_capacity(this.f3183a, this);
    }

    public void reserve(long j) {
        jniLivenessAndTMJNI.LandmarkVector_reserve(this.f3183a, this, j);
    }

    public boolean isEmpty() {
        return jniLivenessAndTMJNI.LandmarkVector_isEmpty(this.f3183a, this);
    }

    public void clear() {
        jniLivenessAndTMJNI.LandmarkVector_clear(this.f3183a, this);
    }

    public void add(LandmarkPoint landmarkPoint) {
        jniLivenessAndTMJNI.LandmarkVector_add(this.f3183a, this, LandmarkPoint.getCPtr(landmarkPoint), landmarkPoint);
    }

    public LandmarkPoint get(int i) {
        return new LandmarkPoint(jniLivenessAndTMJNI.LandmarkVector_get(this.f3183a, this, i), false);
    }

    public void set(int i, LandmarkPoint landmarkPoint) {
        jniLivenessAndTMJNI.LandmarkVector_set(this.f3183a, this, i, LandmarkPoint.getCPtr(landmarkPoint), landmarkPoint);
    }
}
