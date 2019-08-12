package com.jumio.mrz.impl.smartEngines.swig;

public class StringVector {

    /* renamed from: a */
    private long f3229a;
    protected boolean swigCMemOwn;

    protected StringVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3229a = j;
    }

    protected static long getCPtr(StringVector stringVector) {
        if (stringVector == null) {
            return 0;
        }
        return stringVector.f3229a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3229a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_StringVector(this.f3229a);
            }
            this.f3229a = 0;
        }
    }

    public StringVector() {
        this(mrzjniInterfaceJNI.new_StringVector__SWIG_0(), true);
    }

    public StringVector(long j) {
        this(mrzjniInterfaceJNI.new_StringVector__SWIG_1(j), true);
    }

    public long size() {
        return mrzjniInterfaceJNI.StringVector_size(this.f3229a, this);
    }

    public long capacity() {
        return mrzjniInterfaceJNI.StringVector_capacity(this.f3229a, this);
    }

    public void reserve(long j) {
        mrzjniInterfaceJNI.StringVector_reserve(this.f3229a, this, j);
    }

    public boolean isEmpty() {
        return mrzjniInterfaceJNI.StringVector_isEmpty(this.f3229a, this);
    }

    public void clear() {
        mrzjniInterfaceJNI.StringVector_clear(this.f3229a, this);
    }

    public void add(String str) {
        mrzjniInterfaceJNI.StringVector_add(this.f3229a, this, str);
    }

    public String get(int i) {
        return mrzjniInterfaceJNI.StringVector_get(this.f3229a, this, i);
    }

    public void set(int i, String str) {
        mrzjniInterfaceJNI.StringVector_set(this.f3229a, this, i, str);
    }
}
