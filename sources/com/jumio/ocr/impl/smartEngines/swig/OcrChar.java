package com.jumio.ocr.impl.smartEngines.swig;

public class OcrChar {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrChar(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrChar obj) {
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
                jniInterfaceJNI.delete_OcrChar(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OcrChar() {
        this(jniInterfaceJNI.new_OcrChar__SWIG_0(), true);
    }

    public OcrChar(char a_character, double a_probability) {
        this(jniInterfaceJNI.new_OcrChar__SWIG_1(a_character, a_probability), true);
    }

    public char getCharacter() {
        return jniInterfaceJNI.OcrChar_getCharacter(this.swigCPtr, this);
    }

    public double getProbability() {
        return jniInterfaceJNI.OcrChar_getProbability(this.swigCPtr, this);
    }
}
