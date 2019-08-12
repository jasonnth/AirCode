package com.jumio.mrz.impl.smartEngines.swig;

public class MrzOcrChar {

    /* renamed from: a */
    private long f3218a;
    protected boolean swigCMemOwn;

    protected MrzOcrChar(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3218a = j;
    }

    protected static long getCPtr(MrzOcrChar mrzOcrChar) {
        if (mrzOcrChar == null) {
            return 0;
        }
        return mrzOcrChar.f3218a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3218a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzOcrChar(this.f3218a);
            }
            this.f3218a = 0;
        }
    }

    public MrzOcrChar() {
        this(mrzjniInterfaceJNI.new_MrzOcrChar__SWIG_0(), true);
    }

    public MrzOcrChar(MrzOcrCharVariantVector mrzOcrCharVariantVector, boolean z, boolean z2, boolean z3) {
        this(mrzjniInterfaceJNI.new_MrzOcrChar__SWIG_1(MrzOcrCharVariantVector.getCPtr(mrzOcrCharVariantVector), mrzOcrCharVariantVector, z, z2, z3), true);
    }

    public MrzOcrCharVariantVector getOcrCharVariants() {
        return new MrzOcrCharVariantVector(mrzjniInterfaceJNI.MrzOcrChar_getOcrCharVariants(this.f3218a, this), false);
    }

    public boolean isHighlighted() {
        return mrzjniInterfaceJNI.MrzOcrChar_isHighlighted(this.f3218a, this);
    }

    public boolean isCorrected() {
        return mrzjniInterfaceJNI.MrzOcrChar_isCorrected(this.f3218a, this);
    }

    public boolean isFoundInDictionary() {
        return mrzjniInterfaceJNI.MrzOcrChar_isFoundInDictionary(this.f3218a, this);
    }

    public char GetChar() throws Exception {
        return mrzjniInterfaceJNI.MrzOcrChar_GetChar(this.f3218a, this);
    }
}
