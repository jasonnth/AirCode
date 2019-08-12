package com.jumio.ocr.impl.smartEngines.swig;

public class OcrPoint {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrPoint(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrPoint obj) {
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
                jniInterfaceJNI.delete_OcrPoint(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrPoint(int x, int y) {
        this(jniInterfaceJNI.new_OcrPoint__SWIG_0(x, y), true);
    }

    public OcrPoint(int x) {
        this(jniInterfaceJNI.new_OcrPoint__SWIG_1(x), true);
    }

    public OcrPoint() {
        this(jniInterfaceJNI.new_OcrPoint__SWIG_2(), true);
    }

    public int getX() {
        return jniInterfaceJNI.OcrPoint_getX(this.swigCPtr, this);
    }

    public int getY() {
        return jniInterfaceJNI.OcrPoint_getY(this.swigCPtr, this);
    }
}
