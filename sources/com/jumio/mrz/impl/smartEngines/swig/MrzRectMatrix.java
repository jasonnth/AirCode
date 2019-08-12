package com.jumio.mrz.impl.smartEngines.swig;

public class MrzRectMatrix {

    /* renamed from: a */
    private long f3225a;
    protected boolean swigCMemOwn;

    protected MrzRectMatrix(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3225a = j;
    }

    protected static long getCPtr(MrzRectMatrix mrzRectMatrix) {
        if (mrzRectMatrix == null) {
            return 0;
        }
        return mrzRectMatrix.f3225a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3225a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzRectMatrix(this.f3225a);
            }
            this.f3225a = 0;
        }
    }

    public MrzRectMatrix() {
        this(mrzjniInterfaceJNI.new_MrzRectMatrix__SWIG_0(), true);
    }

    public MrzRectMatrix(long j) {
        this(mrzjniInterfaceJNI.new_MrzRectMatrix__SWIG_1(j), true);
    }

    public long size() {
        return mrzjniInterfaceJNI.MrzRectMatrix_size(this.f3225a, this);
    }

    public long capacity() {
        return mrzjniInterfaceJNI.MrzRectMatrix_capacity(this.f3225a, this);
    }

    public void reserve(long j) {
        mrzjniInterfaceJNI.MrzRectMatrix_reserve(this.f3225a, this, j);
    }

    public boolean isEmpty() {
        return mrzjniInterfaceJNI.MrzRectMatrix_isEmpty(this.f3225a, this);
    }

    public void clear() {
        mrzjniInterfaceJNI.MrzRectMatrix_clear(this.f3225a, this);
    }

    public void add(MrzRectVector mrzRectVector) {
        mrzjniInterfaceJNI.MrzRectMatrix_add(this.f3225a, this, MrzRectVector.getCPtr(mrzRectVector), mrzRectVector);
    }

    public MrzRectVector get(int i) {
        return new MrzRectVector(mrzjniInterfaceJNI.MrzRectMatrix_get(this.f3225a, this, i), false);
    }

    public void set(int i, MrzRectVector mrzRectVector) {
        mrzjniInterfaceJNI.MrzRectMatrix_set(this.f3225a, this, i, MrzRectVector.getCPtr(mrzRectVector), mrzRectVector);
    }
}
