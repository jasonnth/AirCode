package com.jumio.mrz.impl.smartEngines.swig;

public class MrzOcrCharVariantVector {

    /* renamed from: a */
    private long f3220a;
    protected boolean swigCMemOwn;

    protected MrzOcrCharVariantVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3220a = j;
    }

    protected static long getCPtr(MrzOcrCharVariantVector mrzOcrCharVariantVector) {
        if (mrzOcrCharVariantVector == null) {
            return 0;
        }
        return mrzOcrCharVariantVector.f3220a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3220a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzOcrCharVariantVector(this.f3220a);
            }
            this.f3220a = 0;
        }
    }

    public MrzOcrCharVariantVector() {
        this(mrzjniInterfaceJNI.new_MrzOcrCharVariantVector__SWIG_0(), true);
    }

    public MrzOcrCharVariantVector(long j) {
        this(mrzjniInterfaceJNI.new_MrzOcrCharVariantVector__SWIG_1(j), true);
    }

    public long size() {
        return mrzjniInterfaceJNI.MrzOcrCharVariantVector_size(this.f3220a, this);
    }

    public long capacity() {
        return mrzjniInterfaceJNI.MrzOcrCharVariantVector_capacity(this.f3220a, this);
    }

    public void reserve(long j) {
        mrzjniInterfaceJNI.MrzOcrCharVariantVector_reserve(this.f3220a, this, j);
    }

    public boolean isEmpty() {
        return mrzjniInterfaceJNI.MrzOcrCharVariantVector_isEmpty(this.f3220a, this);
    }

    public void clear() {
        mrzjniInterfaceJNI.MrzOcrCharVariantVector_clear(this.f3220a, this);
    }

    public void add(MrzOcrCharVariant mrzOcrCharVariant) {
        mrzjniInterfaceJNI.MrzOcrCharVariantVector_add(this.f3220a, this, MrzOcrCharVariant.getCPtr(mrzOcrCharVariant), mrzOcrCharVariant);
    }

    public MrzOcrCharVariant get(int i) {
        return new MrzOcrCharVariant(mrzjniInterfaceJNI.MrzOcrCharVariantVector_get(this.f3220a, this, i), false);
    }

    public void set(int i, MrzOcrCharVariant mrzOcrCharVariant) {
        mrzjniInterfaceJNI.MrzOcrCharVariantVector_set(this.f3220a, this, i, MrzOcrCharVariant.getCPtr(mrzOcrCharVariant), mrzOcrCharVariant);
    }
}
