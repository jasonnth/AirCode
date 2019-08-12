package com.jumio.ocr.impl.smartEngines.swig;

public class OcrQuadrangle {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrQuadrangle(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrQuadrangle obj) {
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
                jniInterfaceJNI.delete_OcrQuadrangle(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrQuadrangle() {
        this(jniInterfaceJNI.new_OcrQuadrangle__SWIG_0(), true);
    }

    public OcrQuadrangle(OcrPoint topLeft, OcrPoint topRight, OcrPoint bottomRight, OcrPoint bottomLeft) {
        this(jniInterfaceJNI.new_OcrQuadrangle__SWIG_1(OcrPoint.getCPtr(topLeft), topLeft, OcrPoint.getCPtr(topRight), topRight, OcrPoint.getCPtr(bottomRight), bottomRight, OcrPoint.getCPtr(bottomLeft), bottomLeft), true);
    }

    public OcrPoint getTopLeft() {
        return new OcrPoint(jniInterfaceJNI.OcrQuadrangle_getTopLeft(this.swigCPtr, this), false);
    }

    public OcrPoint getTopRight() {
        return new OcrPoint(jniInterfaceJNI.OcrQuadrangle_getTopRight(this.swigCPtr, this), false);
    }

    public OcrPoint getBottomRight() {
        return new OcrPoint(jniInterfaceJNI.OcrQuadrangle_getBottomRight(this.swigCPtr, this), false);
    }

    public OcrPoint getBottomLeft() {
        return new OcrPoint(jniInterfaceJNI.OcrQuadrangle_getBottomLeft(this.swigCPtr, this), false);
    }
}
