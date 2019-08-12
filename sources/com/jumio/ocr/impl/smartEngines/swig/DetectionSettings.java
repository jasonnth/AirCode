package com.jumio.ocr.impl.smartEngines.swig;

public class DetectionSettings {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected DetectionSettings(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(DetectionSettings obj) {
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
                jniInterfaceJNI.delete_DetectionSettings(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public DetectionSettings() {
        this(jniInterfaceJNI.new_DetectionSettings(), true);
    }

    public double getRoiLeftMargin() {
        return jniInterfaceJNI.DetectionSettings_getRoiLeftMargin(this.swigCPtr, this);
    }

    public double getRoiTopMargin() {
        return jniInterfaceJNI.DetectionSettings_getRoiTopMargin(this.swigCPtr, this);
    }

    public double getRoiRightMargin() {
        return jniInterfaceJNI.DetectionSettings_getRoiRightMargin(this.swigCPtr, this);
    }

    public double getRoiBottomMargin() {
        return jniInterfaceJNI.DetectionSettings_getRoiBottomMargin(this.swigCPtr, this);
    }

    public double getRoiVerticalDeviation() {
        return jniInterfaceJNI.DetectionSettings_getRoiVerticalDeviation(this.swigCPtr, this);
    }

    public double getRoiHorizontalDeviation() {
        return jniInterfaceJNI.DetectionSettings_getRoiHorizontalDeviation(this.swigCPtr, this);
    }

    public void setRoiLeftMargin(double value) {
        jniInterfaceJNI.DetectionSettings_setRoiLeftMargin(this.swigCPtr, this, value);
    }

    public void setRoiTopMargin(double value) {
        jniInterfaceJNI.DetectionSettings_setRoiTopMargin(this.swigCPtr, this, value);
    }

    public void setRoiRightMargin(double value) {
        jniInterfaceJNI.DetectionSettings_setRoiRightMargin(this.swigCPtr, this, value);
    }

    public void setRoiBottomMargin(double value) {
        jniInterfaceJNI.DetectionSettings_setRoiBottomMargin(this.swigCPtr, this, value);
    }

    public void setRoiVerticalDeviation(double value) {
        jniInterfaceJNI.DetectionSettings_setRoiVerticalDeviation(this.swigCPtr, this, value);
    }

    public void setRoiHorizontalDeviation(double value) {
        jniInterfaceJNI.DetectionSettings_setRoiHorizontalDeviation(this.swigCPtr, this, value);
    }
}
