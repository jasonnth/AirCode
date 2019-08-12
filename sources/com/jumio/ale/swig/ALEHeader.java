package com.jumio.ale.swig;

public class ALEHeader {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ALEHeader(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ALEHeader obj) {
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
                aleEngineJNI.delete_ALEHeader(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public ALEHeader() {
        this(aleEngineJNI.new_ALEHeader(), true);
    }

    public void add(String key, String value) {
        aleEngineJNI.ALEHeader_add__SWIG_0(this.swigCPtr, this, key, value);
    }

    public void clear() {
        aleEngineJNI.ALEHeader_clear(this.swigCPtr, this);
    }
}
