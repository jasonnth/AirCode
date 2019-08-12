package com.jumio.clientlib.impl.livenessAndTM;

public class FramesVector {

    /* renamed from: a */
    private long f3175a;
    protected boolean swigCMemOwn;

    protected FramesVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3175a = j;
    }

    protected static long getCPtr(FramesVector framesVector) {
        if (framesVector == null) {
            return 0;
        }
        return framesVector.f3175a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3175a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_FramesVector(this.f3175a);
            }
            this.f3175a = 0;
        }
    }

    public FramesVector() {
        this(jniLivenessAndTMJNI.new_FramesVector__SWIG_0(), true);
    }

    public FramesVector(long j) {
        this(jniLivenessAndTMJNI.new_FramesVector__SWIG_1(j), true);
    }

    public long size() {
        return jniLivenessAndTMJNI.FramesVector_size(this.f3175a, this);
    }

    public long capacity() {
        return jniLivenessAndTMJNI.FramesVector_capacity(this.f3175a, this);
    }

    public void reserve(long j) {
        jniLivenessAndTMJNI.FramesVector_reserve(this.f3175a, this, j);
    }

    public boolean isEmpty() {
        return jniLivenessAndTMJNI.FramesVector_isEmpty(this.f3175a, this);
    }

    public void clear() {
        jniLivenessAndTMJNI.FramesVector_clear(this.f3175a, this);
    }

    public void add(LibImage libImage) {
        jniLivenessAndTMJNI.FramesVector_add(this.f3175a, this, LibImage.getCPtr(libImage), libImage);
    }

    public LibImage get(int i) {
        long FramesVector_get = jniLivenessAndTMJNI.FramesVector_get(this.f3175a, this, i);
        if (FramesVector_get == 0) {
            return null;
        }
        return new LibImage(FramesVector_get, true);
    }

    public void set(int i, LibImage libImage) {
        jniLivenessAndTMJNI.FramesVector_set(this.f3175a, this, i, LibImage.getCPtr(libImage), libImage);
    }
}
