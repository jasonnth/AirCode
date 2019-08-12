package com.jumio.ocr.impl.smartEngines.swig;

public class OcrEngineInternalSettings {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrEngineInternalSettings(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrEngineInternalSettings obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniInterfaceJNI.delete_OcrEngineInternalSettings(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public static OcrEngineInternalSettings createFromFilesystem(String configPath) throws Exception {
        long cPtr = jniInterfaceJNI.OcrEngineInternalSettings_createFromFilesystem(configPath);
        if (cPtr == 0) {
            return null;
        }
        return new OcrEngineInternalSettings(cPtr, false);
    }
}
