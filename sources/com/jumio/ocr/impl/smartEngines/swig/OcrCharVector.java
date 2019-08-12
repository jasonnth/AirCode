package com.jumio.ocr.impl.smartEngines.swig;

public class OcrCharVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrCharVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrCharVector obj) {
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
                jniInterfaceJNI.delete_OcrCharVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrCharVector() {
        this(jniInterfaceJNI.new_OcrCharVector__SWIG_0(), true);
    }

    public OcrCharVector(long n) {
        this(jniInterfaceJNI.new_OcrCharVector__SWIG_1(n), true);
    }

    public long size() {
        return jniInterfaceJNI.OcrCharVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return jniInterfaceJNI.OcrCharVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        jniInterfaceJNI.OcrCharVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return jniInterfaceJNI.OcrCharVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        jniInterfaceJNI.OcrCharVector_clear(this.swigCPtr, this);
    }

    public void add(OcrChar x) {
        jniInterfaceJNI.OcrCharVector_add(this.swigCPtr, this, OcrChar.getCPtr(x), x);
    }

    public OcrChar get(int i) {
        return new OcrChar(jniInterfaceJNI.OcrCharVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, OcrChar val) {
        jniInterfaceJNI.OcrCharVector_set(this.swigCPtr, this, i, OcrChar.getCPtr(val), val);
    }
}
