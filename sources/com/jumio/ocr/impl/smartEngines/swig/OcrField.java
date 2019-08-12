package com.jumio.ocr.impl.smartEngines.swig;

public class OcrField {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrField(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrField obj) {
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
                jniInterfaceJNI.delete_OcrField(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrField(OcrCharStringVector variants, boolean toAccept) {
        this(jniInterfaceJNI.new_OcrField__SWIG_0(OcrCharStringVector.getCPtr(variants), variants, toAccept), true);
    }

    public OcrField(OcrField other) {
        this(jniInterfaceJNI.new_OcrField__SWIG_1(getCPtr(other), other), true);
    }

    public OcrCharStringVector getOcrCharVariants() {
        return new OcrCharStringVector(jniInterfaceJNI.OcrField_getOcrCharVariants(this.swigCPtr, this), false);
    }

    public boolean getToAcceptFlag() {
        return jniInterfaceJNI.OcrField_getToAcceptFlag(this.swigCPtr, this);
    }

    public OcrConfidence getConfidence() {
        return new OcrConfidence(jniInterfaceJNI.OcrField_getConfidence(this.swigCPtr, this), true);
    }

    public String getAsString() {
        return jniInterfaceJNI.OcrField_getAsString(this.swigCPtr, this);
    }

    public long getLength() {
        return jniInterfaceJNI.OcrField_getLength(this.swigCPtr, this);
    }
}
