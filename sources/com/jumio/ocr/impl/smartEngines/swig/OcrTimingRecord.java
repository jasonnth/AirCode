package com.jumio.ocr.impl.smartEngines.swig;

public class OcrTimingRecord {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrTimingRecord(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrTimingRecord obj) {
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
                jniInterfaceJNI.delete_OcrTimingRecord(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrTimingRecord() {
        this(jniInterfaceJNI.new_OcrTimingRecord__SWIG_0(), true);
    }

    public OcrTimingRecord(double totalTime, long callCount, String name, String comment) {
        this(jniInterfaceJNI.new_OcrTimingRecord__SWIG_1(totalTime, callCount, name, comment), true);
    }

    public double getTotalTime() {
        return jniInterfaceJNI.OcrTimingRecord_getTotalTime(this.swigCPtr, this);
    }

    public long getCallCount() {
        return jniInterfaceJNI.OcrTimingRecord_getCallCount(this.swigCPtr, this);
    }

    public String getName() {
        return jniInterfaceJNI.OcrTimingRecord_getName(this.swigCPtr, this);
    }

    public String getComment() {
        return jniInterfaceJNI.OcrTimingRecord_getComment(this.swigCPtr, this);
    }
}
