package com.jumio.ocr.impl.smartEngines.swig;

public class OcrEngineSession extends OcrEngineBaseSession {
    private long swigCPtr;

    protected OcrEngineSession(long cPtr, boolean cMemoryOwn) {
        super(jniInterfaceJNI.OcrEngineSession_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrEngineSession obj) {
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
                jniInterfaceJNI.delete_OcrEngineSession(this.swigCPtr);
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
        jniInterfaceJNI.OcrEngineSession_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        jniInterfaceJNI.OcrEngineSession_change_ownership(this, this.swigCPtr, true);
    }

    public OcrResult processImageFile(String a_filename, OcrEngineSettings settings) throws Exception {
        long cPtr = jniInterfaceJNI.OcrEngineSession_processImageFile__SWIG_0(this.swigCPtr, this, a_filename, OcrEngineSettings.getCPtr(settings), settings);
        if (cPtr == 0) {
            return null;
        }
        return new OcrResult(cPtr, true);
    }

    public OcrResult processImageFile(String a_filename) throws Exception {
        long cPtr = jniInterfaceJNI.OcrEngineSession_processImageFile__SWIG_1(this.swigCPtr, this, a_filename);
        if (cPtr == 0) {
            return null;
        }
        return new OcrResult(cPtr, true);
    }

    public OcrResult processImageData(byte[] a_data, OcrEngineSettings settings) throws Exception {
        long cPtr = jniInterfaceJNI.OcrEngineSession_processImageData__SWIG_0(this.swigCPtr, this, a_data, OcrEngineSettings.getCPtr(settings), settings);
        if (cPtr == 0) {
            return null;
        }
        return new OcrResult(cPtr, true);
    }

    public OcrResult processImageData(byte[] a_data) throws Exception {
        long cPtr = jniInterfaceJNI.OcrEngineSession_processImageData__SWIG_1(this.swigCPtr, this, a_data);
        if (cPtr == 0) {
            return null;
        }
        return new OcrResult(cPtr, true);
    }
}
