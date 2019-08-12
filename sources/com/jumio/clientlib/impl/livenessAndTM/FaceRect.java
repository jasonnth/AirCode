package com.jumio.clientlib.impl.livenessAndTM;

public class FaceRect {

    /* renamed from: a */
    private long f3163a;
    protected boolean swigCMemOwn;

    protected FaceRect(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3163a = j;
    }

    protected static long getCPtr(FaceRect faceRect) {
        if (faceRect == null) {
            return 0;
        }
        return faceRect.f3163a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3163a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniLivenessAndTMJNI.delete_FaceRect(this.f3163a);
            }
            this.f3163a = 0;
        }
    }

    public FaceRect(int i, int i2, int i3, int i4) {
        this(jniLivenessAndTMJNI.new_FaceRect__SWIG_0(i, i2, i3, i4), true);
    }

    public FaceRect(int i, int i2, int i3) {
        this(jniLivenessAndTMJNI.new_FaceRect__SWIG_1(i, i2, i3), true);
    }

    public FaceRect(int i, int i2) {
        this(jniLivenessAndTMJNI.new_FaceRect__SWIG_2(i, i2), true);
    }

    public FaceRect(int i) {
        this(jniLivenessAndTMJNI.new_FaceRect__SWIG_3(i), true);
    }

    public FaceRect() {
        this(jniLivenessAndTMJNI.new_FaceRect__SWIG_4(), true);
    }

    public int getX() {
        return jniLivenessAndTMJNI.FaceRect_getX(this.f3163a, this);
    }

    public int getY() {
        return jniLivenessAndTMJNI.FaceRect_getY(this.f3163a, this);
    }

    public int getWidth() {
        return jniLivenessAndTMJNI.FaceRect_getWidth(this.f3163a, this);
    }

    public int getHeight() {
        return jniLivenessAndTMJNI.FaceRect_getHeight(this.f3163a, this);
    }

    public void setX(int i) {
        jniLivenessAndTMJNI.FaceRect_setX(this.f3163a, this, i);
    }

    public void setY(int i) {
        jniLivenessAndTMJNI.FaceRect_setY(this.f3163a, this, i);
    }

    public void setWidth(int i) {
        jniLivenessAndTMJNI.FaceRect_setWidth(this.f3163a, this, i);
    }

    public void setHeight(int i) {
        jniLivenessAndTMJNI.FaceRect_setHeight(this.f3163a, this, i);
    }
}
