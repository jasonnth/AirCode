package com.jumio.mrz.impl.smartEngines.swig;

public class MrzOcrCharVariant {

    /* renamed from: a */
    private long f3219a;
    protected boolean swigCMemOwn;

    protected MrzOcrCharVariant(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3219a = j;
    }

    protected static long getCPtr(MrzOcrCharVariant mrzOcrCharVariant) {
        if (mrzOcrCharVariant == null) {
            return 0;
        }
        return mrzOcrCharVariant.f3219a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3219a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzOcrCharVariant(this.f3219a);
            }
            this.f3219a = 0;
        }
    }

    public MrzOcrCharVariant() {
        this(mrzjniInterfaceJNI.new_MrzOcrCharVariant__SWIG_0(), true);
    }

    public MrzOcrCharVariant(char c, double d) {
        this(mrzjniInterfaceJNI.new_MrzOcrCharVariant__SWIG_1(c, d), true);
    }

    public char getCharacter() {
        return mrzjniInterfaceJNI.MrzOcrCharVariant_getCharacter(this.f3219a, this);
    }

    public double getConfidence() {
        return mrzjniInterfaceJNI.MrzOcrCharVariant_getConfidence(this.f3219a, this);
    }
}
