package com.jumio.mrz.impl.smartEngines.swig;

public class MrzOcrCharVector {

    /* renamed from: a */
    private long f3221a;
    protected boolean swigCMemOwn;

    protected MrzOcrCharVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3221a = j;
    }

    protected static long getCPtr(MrzOcrCharVector mrzOcrCharVector) {
        if (mrzOcrCharVector == null) {
            return 0;
        }
        return mrzOcrCharVector.f3221a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3221a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzOcrCharVector(this.f3221a);
            }
            this.f3221a = 0;
        }
    }

    public MrzOcrCharVector() {
        this(mrzjniInterfaceJNI.new_MrzOcrCharVector__SWIG_0(), true);
    }

    public MrzOcrCharVector(long j) {
        this(mrzjniInterfaceJNI.new_MrzOcrCharVector__SWIG_1(j), true);
    }

    public long size() {
        return mrzjniInterfaceJNI.MrzOcrCharVector_size(this.f3221a, this);
    }

    public long capacity() {
        return mrzjniInterfaceJNI.MrzOcrCharVector_capacity(this.f3221a, this);
    }

    public void reserve(long j) {
        mrzjniInterfaceJNI.MrzOcrCharVector_reserve(this.f3221a, this, j);
    }

    public boolean isEmpty() {
        return mrzjniInterfaceJNI.MrzOcrCharVector_isEmpty(this.f3221a, this);
    }

    public void clear() {
        mrzjniInterfaceJNI.MrzOcrCharVector_clear(this.f3221a, this);
    }

    public void add(MrzOcrChar mrzOcrChar) {
        mrzjniInterfaceJNI.MrzOcrCharVector_add(this.f3221a, this, MrzOcrChar.getCPtr(mrzOcrChar), mrzOcrChar);
    }

    public MrzOcrChar get(int i) {
        return new MrzOcrChar(mrzjniInterfaceJNI.MrzOcrCharVector_get(this.f3221a, this, i), false);
    }

    public void set(int i, MrzOcrChar mrzOcrChar) {
        mrzjniInterfaceJNI.MrzOcrCharVector_set(this.f3221a, this, i, MrzOcrChar.getCPtr(mrzOcrChar), mrzOcrChar);
    }
}
