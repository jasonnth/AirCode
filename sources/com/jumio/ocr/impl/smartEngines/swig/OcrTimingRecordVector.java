package com.jumio.ocr.impl.smartEngines.swig;

public class OcrTimingRecordVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrTimingRecordVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrTimingRecordVector obj) {
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
                jniInterfaceJNI.delete_OcrTimingRecordVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrTimingRecordVector() {
        this(jniInterfaceJNI.new_OcrTimingRecordVector__SWIG_0(), true);
    }

    public OcrTimingRecordVector(long n) {
        this(jniInterfaceJNI.new_OcrTimingRecordVector__SWIG_1(n), true);
    }

    public long size() {
        return jniInterfaceJNI.OcrTimingRecordVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return jniInterfaceJNI.OcrTimingRecordVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        jniInterfaceJNI.OcrTimingRecordVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return jniInterfaceJNI.OcrTimingRecordVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        jniInterfaceJNI.OcrTimingRecordVector_clear(this.swigCPtr, this);
    }

    public void add(OcrTimingRecord x) {
        jniInterfaceJNI.OcrTimingRecordVector_add(this.swigCPtr, this, OcrTimingRecord.getCPtr(x), x);
    }

    public OcrTimingRecord get(int i) {
        return new OcrTimingRecord(jniInterfaceJNI.OcrTimingRecordVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, OcrTimingRecord val) {
        jniInterfaceJNI.OcrTimingRecordVector_set(this.swigCPtr, this, i, OcrTimingRecord.getCPtr(val), val);
    }
}