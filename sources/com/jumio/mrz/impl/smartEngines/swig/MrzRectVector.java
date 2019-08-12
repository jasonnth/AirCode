package com.jumio.mrz.impl.smartEngines.swig;

public class MrzRectVector {

    /* renamed from: a */
    private long f3226a;
    protected boolean swigCMemOwn;

    protected MrzRectVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3226a = j;
    }

    protected static long getCPtr(MrzRectVector mrzRectVector) {
        if (mrzRectVector == null) {
            return 0;
        }
        return mrzRectVector.f3226a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3226a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzRectVector(this.f3226a);
            }
            this.f3226a = 0;
        }
    }

    public MrzRectVector() {
        this(mrzjniInterfaceJNI.new_MrzRectVector__SWIG_0(), true);
    }

    public MrzRectVector(long j) {
        this(mrzjniInterfaceJNI.new_MrzRectVector__SWIG_1(j), true);
    }

    public long size() {
        return mrzjniInterfaceJNI.MrzRectVector_size(this.f3226a, this);
    }

    public long capacity() {
        return mrzjniInterfaceJNI.MrzRectVector_capacity(this.f3226a, this);
    }

    public void reserve(long j) {
        mrzjniInterfaceJNI.MrzRectVector_reserve(this.f3226a, this, j);
    }

    public boolean isEmpty() {
        return mrzjniInterfaceJNI.MrzRectVector_isEmpty(this.f3226a, this);
    }

    public void clear() {
        mrzjniInterfaceJNI.MrzRectVector_clear(this.f3226a, this);
    }

    public void add(MrzRect mrzRect) {
        mrzjniInterfaceJNI.MrzRectVector_add(this.f3226a, this, MrzRect.getCPtr(mrzRect), mrzRect);
    }

    public MrzRect get(int i) {
        return new MrzRect(mrzjniInterfaceJNI.MrzRectVector_get(this.f3226a, this, i), false);
    }

    public void set(int i, MrzRect mrzRect) {
        mrzjniInterfaceJNI.MrzRectVector_set(this.f3226a, this, i, MrzRect.getCPtr(mrzRect), mrzRect);
    }
}
