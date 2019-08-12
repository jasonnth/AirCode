package com.jumio.clientlib.impl.livenessAndTM;

public class StringVector {

    /* renamed from: a */
    private long f3194a;
    protected boolean swigCMemOwn;

    protected StringVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3194a = j;
    }

    protected static long getCPtr(StringVector stringVector) {
        if (stringVector == null) {
            return 0;
        }
        return stringVector.f3194a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3194a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_StringVector(this.f3194a);
            }
            this.f3194a = 0;
        }
    }

    public StringVector() {
        this(jniLivenessAndTMJNI.new_StringVector__SWIG_0(), true);
    }

    public StringVector(long j) {
        this(jniLivenessAndTMJNI.new_StringVector__SWIG_1(j), true);
    }

    public long size() {
        return jniLivenessAndTMJNI.StringVector_size(this.f3194a, this);
    }

    public long capacity() {
        return jniLivenessAndTMJNI.StringVector_capacity(this.f3194a, this);
    }

    public void reserve(long j) {
        jniLivenessAndTMJNI.StringVector_reserve(this.f3194a, this, j);
    }

    public boolean isEmpty() {
        return jniLivenessAndTMJNI.StringVector_isEmpty(this.f3194a, this);
    }

    public void clear() {
        jniLivenessAndTMJNI.StringVector_clear(this.f3194a, this);
    }

    public void add(String str) {
        jniLivenessAndTMJNI.StringVector_add(this.f3194a, this, str);
    }

    public String get(int i) {
        return jniLivenessAndTMJNI.StringVector_get(this.f3194a, this, i);
    }

    public void set(int i, String str) {
        jniLivenessAndTMJNI.StringVector_set(this.f3194a, this, i, str);
    }
}
