package com.jumio.ocr.impl.smartEngines.swig;

public class OcrEngine extends OcrEngineBase {
    private long swigCPtr;

    protected OcrEngine(long cPtr, boolean cMemoryOwn) {
        super(jniInterfaceJNI.OcrEngine_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrEngine obj) {
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
                jniInterfaceJNI.delete_OcrEngine(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    /* access modifiers changed from: protected */
    public void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        jniInterfaceJNI.OcrEngine_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        jniInterfaceJNI.OcrEngine_change_ownership(this, this.swigCPtr, true);
    }

    public OcrEngine(OcrEngineInternalSettings internalSettings) {
        this(jniInterfaceJNI.new_OcrEngine(OcrEngineInternalSettings.getCPtr(internalSettings), internalSettings), true);
        jniInterfaceJNI.OcrEngine_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    public OcrEngineSession createSession(ResultAcceptorInterface acceptor) {
        long cPtr = jniInterfaceJNI.OcrEngine_createSession__SWIG_0(this.swigCPtr, this, ResultAcceptorInterface.getCPtr(acceptor), acceptor);
        if (cPtr == 0) {
            return null;
        }
        return new OcrEngineSession(cPtr, true);
    }

    public OcrEngineSession createSession() {
        long cPtr = jniInterfaceJNI.OcrEngine_createSession__SWIG_1(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new OcrEngineSession(cPtr, true);
    }
}
