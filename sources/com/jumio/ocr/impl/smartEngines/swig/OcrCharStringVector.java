package com.jumio.ocr.impl.smartEngines.swig;

public class OcrCharStringVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrCharStringVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrCharStringVector obj) {
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
                jniInterfaceJNI.delete_OcrCharStringVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrCharStringVector() {
        this(jniInterfaceJNI.new_OcrCharStringVector__SWIG_0(), true);
    }

    public OcrCharStringVector(long n) {
        this(jniInterfaceJNI.new_OcrCharStringVector__SWIG_1(n), true);
    }

    public long size() {
        return jniInterfaceJNI.OcrCharStringVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return jniInterfaceJNI.OcrCharStringVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        jniInterfaceJNI.OcrCharStringVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return jniInterfaceJNI.OcrCharStringVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        jniInterfaceJNI.OcrCharStringVector_clear(this.swigCPtr, this);
    }

    public void add(OcrCharVariants x) {
        jniInterfaceJNI.OcrCharStringVector_add(this.swigCPtr, this, OcrCharVariants.getCPtr(x), x);
    }

    public OcrCharVariants get(int i) {
        return new OcrCharVariants(jniInterfaceJNI.OcrCharStringVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, OcrCharVariants val) {
        jniInterfaceJNI.OcrCharStringVector_set(this.swigCPtr, this, i, OcrCharVariants.getCPtr(val), val);
    }
}
