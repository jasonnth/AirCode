package com.jumio.ocr.impl.smartEngines.swig;

public class DetectionEngine {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected DetectionEngine(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(DetectionEngine obj) {
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
                jniInterfaceJNI.delete_DetectionEngine(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public DetectionEngine(DetectionSettings settings, DetectionInternalSettings internalSettings) {
        this(jniInterfaceJNI.new_DetectionEngine(DetectionSettings.getCPtr(settings), settings, DetectionInternalSettings.getCPtr(internalSettings), internalSettings), true);
    }

    public void resetSession() {
        jniInterfaceJNI.DetectionEngine_resetSession(this.swigCPtr, this);
    }

    public DetectionResult processRawImage(byte[] a_data, int width, int height, int stride, int timeStamp) {
        return new DetectionResult(jniInterfaceJNI.DetectionEngine_processRawImage(this.swigCPtr, this, a_data, width, height, stride, timeStamp), true);
    }

    public DetectionResult processRawYUVImage(byte[] a_data, int width, int height, int stride, int timeStamp) {
        return new DetectionResult(jniInterfaceJNI.DetectionEngine_processRawYUVImage(this.swigCPtr, this, a_data, width, height, stride, timeStamp), true);
    }
}
