package com.jumio.ocr.impl.smartEngines.swig;

public class OcrEngineBaseSession {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OcrEngineBaseSession(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(OcrEngineBaseSession obj) {
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
                jniInterfaceJNI.delete_OcrEngineBaseSession(this.swigCPtr);
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
        jniInterfaceJNI.OcrEngineBaseSession_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        jniInterfaceJNI.OcrEngineBaseSession_change_ownership(this, this.swigCPtr, true);
    }

    public OcrResult processUncompressedImageData(byte[] a_data, int width, int height, int stride, OcrEngineSettings settings) throws Exception {
        long cPtr = jniInterfaceJNI.OcrEngineBaseSession_processUncompressedImageData__SWIG_0(this.swigCPtr, this, a_data, width, height, stride, OcrEngineSettings.getCPtr(settings), settings);
        if (cPtr == 0) {
            return null;
        }
        return new OcrResult(cPtr, true);
    }

    public OcrResult processUncompressedImageData(byte[] a_data, int width, int height, int stride) throws Exception {
        long cPtr = jniInterfaceJNI.OcrEngineBaseSession_processUncompressedImageData__SWIG_1(this.swigCPtr, this, a_data, width, height, stride);
        if (cPtr == 0) {
            return null;
        }
        return new OcrResult(cPtr, true);
    }

    public OcrResult processUncompressedYUVImageData(byte[] a_data, int width, int height, int stride, OcrEngineSettings settings) throws Exception {
        long cPtr = jniInterfaceJNI.OcrEngineBaseSession_processUncompressedYUVImageData__SWIG_0(this.swigCPtr, this, a_data, width, height, stride, OcrEngineSettings.getCPtr(settings), settings);
        if (cPtr == 0) {
            return null;
        }
        return new OcrResult(cPtr, true);
    }

    public OcrResult processUncompressedYUVImageData(byte[] a_data, int width, int height, int stride) throws Exception {
        long cPtr = jniInterfaceJNI.OcrEngineBaseSession_processUncompressedYUVImageData__SWIG_1(this.swigCPtr, this, a_data, width, height, stride);
        if (cPtr == 0) {
            return null;
        }
        return new OcrResult(cPtr, true);
    }

    public boolean isRefocusRequired(byte[] a_data, int focusThreshold, int width, int height, int stride, int channels) {
        return jniInterfaceJNI.OcrEngineBaseSession_isRefocusRequired(this.swigCPtr, this, a_data, focusThreshold, width, height, stride, channels);
    }

    public int computeFocusConfidence(byte[] a_data, int width, int height, int stride, int channels) {
        return jniInterfaceJNI.OcrEngineBaseSession_computeFocusConfidence(this.swigCPtr, this, a_data, width, height, stride, channels);
    }

    public float computeFlashConfidence(byte[] a_data, int width, int height, int stride, int channels) {
        return jniInterfaceJNI.OcrEngineBaseSession_computeFlashConfidence(this.swigCPtr, this, a_data, width, height, stride, channels);
    }

    public boolean isFlashRequired(byte[] a_data, int intensityThreshold, int width, int height, int stride, int channels) {
        return jniInterfaceJNI.OcrEngineBaseSession_isFlashRequired(this.swigCPtr, this, a_data, intensityThreshold, width, height, stride, channels);
    }

    public void resetSession() {
        jniInterfaceJNI.OcrEngineBaseSession_resetSession(this.swigCPtr, this);
    }
}
