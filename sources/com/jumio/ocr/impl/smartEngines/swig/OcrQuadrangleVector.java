package com.jumio.ocr.impl.smartEngines.swig;

public class OcrQuadrangleVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrQuadrangleVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrQuadrangleVector obj) {
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
                jniInterfaceJNI.delete_OcrQuadrangleVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrQuadrangleVector() {
        this(jniInterfaceJNI.new_OcrQuadrangleVector__SWIG_0(), true);
    }

    public OcrQuadrangleVector(long n) {
        this(jniInterfaceJNI.new_OcrQuadrangleVector__SWIG_1(n), true);
    }

    public long size() {
        return jniInterfaceJNI.OcrQuadrangleVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return jniInterfaceJNI.OcrQuadrangleVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        jniInterfaceJNI.OcrQuadrangleVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return jniInterfaceJNI.OcrQuadrangleVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        jniInterfaceJNI.OcrQuadrangleVector_clear(this.swigCPtr, this);
    }

    public void add(OcrQuadrangle x) {
        jniInterfaceJNI.OcrQuadrangleVector_add(this.swigCPtr, this, OcrQuadrangle.getCPtr(x), x);
    }

    public OcrQuadrangle get(int i) {
        return new OcrQuadrangle(jniInterfaceJNI.OcrQuadrangleVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, OcrQuadrangle val) {
        jniInterfaceJNI.OcrQuadrangleVector_set(this.swigCPtr, this, i, OcrQuadrangle.getCPtr(val), val);
    }
}
