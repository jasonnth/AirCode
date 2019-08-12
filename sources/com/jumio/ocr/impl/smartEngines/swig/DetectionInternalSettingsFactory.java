package com.jumio.ocr.impl.smartEngines.swig;

public class DetectionInternalSettingsFactory {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected DetectionInternalSettingsFactory(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(DetectionInternalSettingsFactory obj) {
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
                jniInterfaceJNI.delete_DetectionInternalSettingsFactory(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public static DetectionInternalSettings createFromFileSystem(String configPath) throws Exception {
        long cPtr = jniInterfaceJNI.DetectionInternalSettingsFactory_createFromFileSystem(configPath);
        if (cPtr == 0) {
            return null;
        }
        return new DetectionInternalSettings(cPtr, false);
    }

    public DetectionInternalSettingsFactory() {
        this(jniInterfaceJNI.new_DetectionInternalSettingsFactory(), true);
    }
}
