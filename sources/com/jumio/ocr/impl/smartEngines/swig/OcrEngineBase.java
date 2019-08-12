package com.jumio.ocr.impl.smartEngines.swig;

public class OcrEngineBase {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrEngineBase(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrEngineBase obj) {
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
                jniInterfaceJNI.delete_OcrEngineBase(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        jniInterfaceJNI.OcrEngineBase_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        jniInterfaceJNI.OcrEngineBase_change_ownership(this, this.swigCPtr, true);
    }

    public OcrEngineBase(OcrEngineInternalSettings internalSettings) {
        this(jniInterfaceJNI.new_OcrEngineBase(OcrEngineInternalSettings.getCPtr(internalSettings), internalSettings), true);
        jniInterfaceJNI.OcrEngineBase_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    public OcrEngineBaseSession createSession(ResultAcceptorInterface acceptor) {
        long cPtr = jniInterfaceJNI.OcrEngineBase_createSession__SWIG_0(this.swigCPtr, this, ResultAcceptorInterface.getCPtr(acceptor), acceptor);
        if (cPtr == 0) {
            return null;
        }
        return new OcrEngineBaseSession(cPtr, true);
    }

    public OcrEngineBaseSession createSession() {
        long cPtr = jniInterfaceJNI.OcrEngineBase_createSession__SWIG_1(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new OcrEngineBaseSession(cPtr, true);
    }

    public static OcrTimingRecordVector getTimingStats() {
        return new OcrTimingRecordVector(jniInterfaceJNI.OcrEngineBase_getTimingStats(), true);
    }

    public static int getBuildNumber() {
        return jniInterfaceJNI.OcrEngineBase_getBuildNumber();
    }
}
