package com.jumio.ocr.impl.smartEngines.swig;

public class OcrCharVariants {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrCharVariants(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrCharVariants obj) {
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
                jniInterfaceJNI.delete_OcrCharVariants(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrCharVariants() {
        this(jniInterfaceJNI.new_OcrCharVariants__SWIG_0(), true);
    }

    public OcrCharVariants(OcrQuadrangle quadrangle, int highlightingMask) {
        this(jniInterfaceJNI.new_OcrCharVariants__SWIG_1(OcrQuadrangle.getCPtr(quadrangle), quadrangle, highlightingMask), true);
    }

    public void add(OcrChar a_result) {
        jniInterfaceJNI.OcrCharVariants_add(this.swigCPtr, this, OcrChar.getCPtr(a_result), a_result);
    }

    public OcrCharVector get() {
        return new OcrCharVector(jniInterfaceJNI.OcrCharVariants_get(this.swigCPtr, this), false);
    }

    public OcrQuadrangle getQuadrangle() {
        return new OcrQuadrangle(jniInterfaceJNI.OcrCharVariants_getQuadrangle(this.swigCPtr, this), false);
    }

    public int getHighlightingMask() {
        return jniInterfaceJNI.OcrCharVariants_getHighlightingMask(this.swigCPtr, this);
    }
}
