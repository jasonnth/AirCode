package com.jumio.mrz.impl.smartEngines.swig;

public class MrzOcrStringVector {

    /* renamed from: a */
    private long f3223a;
    protected boolean swigCMemOwn;

    protected MrzOcrStringVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3223a = j;
    }

    protected static long getCPtr(MrzOcrStringVector mrzOcrStringVector) {
        if (mrzOcrStringVector == null) {
            return 0;
        }
        return mrzOcrStringVector.f3223a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3223a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzOcrStringVector(this.f3223a);
            }
            this.f3223a = 0;
        }
    }

    public MrzOcrStringVector() {
        this(mrzjniInterfaceJNI.new_MrzOcrStringVector__SWIG_0(), true);
    }

    public MrzOcrStringVector(long j) {
        this(mrzjniInterfaceJNI.new_MrzOcrStringVector__SWIG_1(j), true);
    }

    public long size() {
        return mrzjniInterfaceJNI.MrzOcrStringVector_size(this.f3223a, this);
    }

    public long capacity() {
        return mrzjniInterfaceJNI.MrzOcrStringVector_capacity(this.f3223a, this);
    }

    public void reserve(long j) {
        mrzjniInterfaceJNI.MrzOcrStringVector_reserve(this.f3223a, this, j);
    }

    public boolean isEmpty() {
        return mrzjniInterfaceJNI.MrzOcrStringVector_isEmpty(this.f3223a, this);
    }

    public void clear() {
        mrzjniInterfaceJNI.MrzOcrStringVector_clear(this.f3223a, this);
    }

    public void add(MrzOcrString mrzOcrString) {
        mrzjniInterfaceJNI.MrzOcrStringVector_add(this.f3223a, this, MrzOcrString.getCPtr(mrzOcrString), mrzOcrString);
    }

    public MrzOcrString get(int i) {
        return new MrzOcrString(mrzjniInterfaceJNI.MrzOcrStringVector_get(this.f3223a, this, i), false);
    }

    public void set(int i, MrzOcrString mrzOcrString) {
        mrzjniInterfaceJNI.MrzOcrStringVector_set(this.f3223a, this, i, MrzOcrString.getCPtr(mrzOcrString), mrzOcrString);
    }
}
