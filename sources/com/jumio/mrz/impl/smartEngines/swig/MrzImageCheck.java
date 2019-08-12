package com.jumio.mrz.impl.smartEngines.swig;

public class MrzImageCheck {

    /* renamed from: a */
    private long f3217a;
    protected boolean swigCMemOwn;

    protected MrzImageCheck(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3217a = j;
    }

    protected static long getCPtr(MrzImageCheck mrzImageCheck) {
        if (mrzImageCheck == null) {
            return 0;
        }
        return mrzImageCheck.f3217a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3217a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzImageCheck(this.f3217a);
            }
            this.f3217a = 0;
        }
    }

    public static boolean isRefocusNeeded(byte[] bArr, int i, int i2, int i3, int i4, int i5) throws Exception {
        return mrzjniInterfaceJNI.MrzImageCheck_isRefocusNeeded(bArr, i, i2, i3, i4, i5);
    }

    public static int computeFocusConfidence(byte[] bArr, int i, int i2, int i3, int i4) throws Exception {
        return mrzjniInterfaceJNI.MrzImageCheck_computeFocusConfidence(bArr, i, i2, i3, i4);
    }

    public static boolean isFlashNeeded(byte[] bArr, int i, int i2, int i3, int i4, int i5) throws Exception {
        return mrzjniInterfaceJNI.MrzImageCheck_isFlashNeeded(bArr, i, i2, i3, i4, i5);
    }

    public static float computeFlashConfidence(byte[] bArr, int i, int i2, int i3, int i4) throws Exception {
        return mrzjniInterfaceJNI.MrzImageCheck_computeFlashConfidence(bArr, i, i2, i3, i4);
    }

    public MrzImageCheck() {
        this(mrzjniInterfaceJNI.new_MrzImageCheck(), true);
    }
}
