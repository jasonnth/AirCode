package com.jumio.mrz.impl.smartEngines.swig;

public class MrzEngineInternalSettings {

    /* renamed from: a */
    private long f3213a;
    protected boolean swigCMemOwn;

    protected MrzEngineInternalSettings(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3213a = j;
    }

    protected static long getCPtr(MrzEngineInternalSettings mrzEngineInternalSettings) {
        if (mrzEngineInternalSettings == null) {
            return 0;
        }
        return mrzEngineInternalSettings.f3213a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3213a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzEngineInternalSettings(this.f3213a);
            }
            this.f3213a = 0;
        }
    }

    public static MrzEngineInternalSettings createFromFilesystem(String str) throws Exception {
        long MrzEngineInternalSettings_createFromFilesystem = mrzjniInterfaceJNI.MrzEngineInternalSettings_createFromFilesystem(str);
        if (MrzEngineInternalSettings_createFromFilesystem == 0) {
            return null;
        }
        return new MrzEngineInternalSettings(MrzEngineInternalSettings_createFromFilesystem, false);
    }
}
