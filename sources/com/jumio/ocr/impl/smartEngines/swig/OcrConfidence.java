package com.jumio.ocr.impl.smartEngines.swig;

public class OcrConfidence {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrConfidence(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrConfidence obj) {
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
                jniInterfaceJNI.delete_OcrConfidence(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrConfidence(double minimum, double average) {
        this(jniInterfaceJNI.new_OcrConfidence(minimum, average), true);
    }

    public double getMinimum() {
        return jniInterfaceJNI.OcrConfidence_getMinimum(this.swigCPtr, this);
    }

    public double getAverage() {
        return jniInterfaceJNI.OcrConfidence_getAverage(this.swigCPtr, this);
    }
}
