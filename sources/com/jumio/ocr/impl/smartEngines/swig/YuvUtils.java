package com.jumio.ocr.impl.smartEngines.swig;

public class YuvUtils {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected YuvUtils(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(YuvUtils obj) {
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
                jniInterfaceJNI.delete_YuvUtils(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public static int yuv2rgb(byte[] a_data, byte[] out, int width, int height) {
        return jniInterfaceJNI.YuvUtils_yuv2rgb(a_data, out, width, height);
    }

    public static int yuvCutRotateScale2rgb(byte[] a_data, int yuv_width, int yuv_height, int hole_left, int hole_top, int hole_width, int hole_height, byte[] out, int rgb_width, int rgb_height, int num_rotations) {
        return jniInterfaceJNI.YuvUtils_yuvCutRotateScale2rgb(a_data, yuv_width, yuv_height, hole_left, hole_top, hole_width, hole_height, out, rgb_width, rgb_height, num_rotations);
    }

    public YuvUtils() {
        this(jniInterfaceJNI.new_YuvUtils(), true);
    }
}
