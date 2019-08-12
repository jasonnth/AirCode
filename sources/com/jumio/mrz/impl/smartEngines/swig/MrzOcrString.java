package com.jumio.mrz.impl.smartEngines.swig;

public class MrzOcrString {

    /* renamed from: a */
    private long f3222a;
    protected boolean swigCMemOwn;

    protected MrzOcrString(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3222a = j;
    }

    protected static long getCPtr(MrzOcrString mrzOcrString) {
        if (mrzOcrString == null) {
            return 0;
        }
        return mrzOcrString.f3222a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3222a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzOcrString(this.f3222a);
            }
            this.f3222a = 0;
        }
    }

    public MrzOcrString() {
        this(mrzjniInterfaceJNI.new_MrzOcrString__SWIG_0(), true);
    }

    public MrzOcrString(MrzOcrCharVector mrzOcrCharVector) {
        this(mrzjniInterfaceJNI.new_MrzOcrString__SWIG_1(MrzOcrCharVector.getCPtr(mrzOcrCharVector), mrzOcrCharVector), true);
    }

    public MrzOcrCharVector getOcrChars() {
        return new MrzOcrCharVector(mrzjniInterfaceJNI.MrzOcrString_getOcrChars(this.f3222a, this), false);
    }
}
