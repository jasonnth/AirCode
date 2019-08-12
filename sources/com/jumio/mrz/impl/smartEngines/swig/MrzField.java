package com.jumio.mrz.impl.smartEngines.swig;

public class MrzField {

    /* renamed from: a */
    private long f3216a;
    protected boolean swigCMemOwn;

    protected MrzField(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3216a = j;
    }

    protected static long getCPtr(MrzField mrzField) {
        if (mrzField == null) {
            return 0;
        }
        return mrzField.f3216a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3216a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzField(this.f3216a);
            }
            this.f3216a = 0;
        }
    }

    public MrzField() {
        this(mrzjniInterfaceJNI.new_MrzField__SWIG_0(), true);
    }

    public MrzField(String str, boolean z, double d) {
        this(mrzjniInterfaceJNI.new_MrzField__SWIG_1(str, z, d), true);
    }

    public MrzField(String str, boolean z) {
        this(mrzjniInterfaceJNI.new_MrzField__SWIG_2(str, z), true);
    }

    public MrzField(String str) {
        this(mrzjniInterfaceJNI.new_MrzField__SWIG_3(str), true);
    }

    public MrzField(String str, boolean z, double d, MrzOcrString mrzOcrString, MrzOcrChar mrzOcrChar) {
        this(mrzjniInterfaceJNI.new_MrzField__SWIG_4(str, z, d, MrzOcrString.getCPtr(mrzOcrString), mrzOcrString, MrzOcrChar.getCPtr(mrzOcrChar), mrzOcrChar), true);
    }

    public String getAsString() {
        return mrzjniInterfaceJNI.MrzField_getAsString(this.f3216a, this);
    }

    public boolean isAccepted() {
        return mrzjniInterfaceJNI.MrzField_isAccepted(this.f3216a, this);
    }

    public double getConfidence() {
        return mrzjniInterfaceJNI.MrzField_getConfidence(this.f3216a, this);
    }

    public MrzOcrString getOcrString() {
        return new MrzOcrString(mrzjniInterfaceJNI.MrzField_getOcrString(this.f3216a, this), false);
    }

    public int calculateChecksum() throws Exception {
        return mrzjniInterfaceJNI.MrzField_calculateChecksum(this.f3216a, this);
    }

    public boolean hasChecksumOcrChar() {
        return mrzjniInterfaceJNI.MrzField_hasChecksumOcrChar(this.f3216a, this);
    }

    public MrzOcrChar getChecksumOcrChar() {
        return new MrzOcrChar(mrzjniInterfaceJNI.MrzField_getChecksumOcrChar(this.f3216a, this), false);
    }

    public boolean hasCorrectChecksum() {
        return mrzjniInterfaceJNI.MrzField_hasCorrectChecksum(this.f3216a, this);
    }
}
