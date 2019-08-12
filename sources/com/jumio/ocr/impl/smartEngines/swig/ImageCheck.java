package com.jumio.ocr.impl.smartEngines.swig;

public class ImageCheck {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ImageCheck(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ImageCheck obj) {
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
                jniInterfaceJNI.delete_ImageCheck(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public static boolean isRefocusNeeded(byte[] a_data, int focusThreshold, int width, int height, int stride, int channels) throws Exception {
        return jniInterfaceJNI.ImageCheck_isRefocusNeeded(a_data, focusThreshold, width, height, stride, channels);
    }

    public static int computeFocusConfidence(byte[] a_data, int width, int height, int stride, int channels) throws Exception {
        return jniInterfaceJNI.ImageCheck_computeFocusConfidence(a_data, width, height, stride, channels);
    }

    public static boolean isFlashNeeded(byte[] a_data, int intensityThreshold, int width, int height, int stride, int channels) throws Exception {
        return jniInterfaceJNI.ImageCheck_isFlashNeeded(a_data, intensityThreshold, width, height, stride, channels);
    }

    public static float computeFlashConfidence(byte[] a_data, int width, int height, int stride, int channels) throws Exception {
        return jniInterfaceJNI.ImageCheck_computeFlashConfidence(a_data, width, height, stride, channels);
    }

    public ImageCheck() {
        this(jniInterfaceJNI.new_ImageCheck(), true);
    }
}
