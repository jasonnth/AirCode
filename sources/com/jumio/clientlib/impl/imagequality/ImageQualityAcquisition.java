package com.jumio.clientlib.impl.imagequality;

public class ImageQualityAcquisition {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ImageQualityAcquisition(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ImageQualityAcquisition obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                throw new UnsupportedOperationException("C++ destructor does not have public access");
            }
            this.swigCPtr = 0;
        }
    }

    public static float Evaluate(byte[] data, long stride, int width, int height, PixelFormatType format) {
        return jniImageQualityAcquisitionJNI.ImageQualityAcquisition_Evaluate(data, stride, width, height, format.swigValue());
    }

    public static float decodeAndEvaluate(byte[] data) throws Exception {
        return jniImageQualityAcquisitionJNI.ImageQualityAcquisition_decodeAndEvaluate(data);
    }
}
