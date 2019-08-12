package com.jumio.mrz.impl.smartEngines.swig;

public class MrzRect {

    /* renamed from: a */
    private long f3224a;
    protected boolean swigCMemOwn;

    protected MrzRect(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3224a = j;
    }

    protected static long getCPtr(MrzRect mrzRect) {
        if (mrzRect == null) {
            return 0;
        }
        return mrzRect.f3224a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3224a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzRect(this.f3224a);
            }
            this.f3224a = 0;
        }
    }

    public MrzRect(int i, int i2, int i3, int i4) {
        this(mrzjniInterfaceJNI.new_MrzRect__SWIG_0(i, i2, i3, i4), true);
    }

    public MrzRect(int i, int i2, int i3) {
        this(mrzjniInterfaceJNI.new_MrzRect__SWIG_1(i, i2, i3), true);
    }

    public MrzRect(int i, int i2) {
        this(mrzjniInterfaceJNI.new_MrzRect__SWIG_2(i, i2), true);
    }

    public MrzRect(int i) {
        this(mrzjniInterfaceJNI.new_MrzRect__SWIG_3(i), true);
    }

    public MrzRect() {
        this(mrzjniInterfaceJNI.new_MrzRect__SWIG_4(), true);
    }

    public int getX() {
        return mrzjniInterfaceJNI.MrzRect_getX(this.f3224a, this);
    }

    public int getY() {
        return mrzjniInterfaceJNI.MrzRect_getY(this.f3224a, this);
    }

    public int getWidth() {
        return mrzjniInterfaceJNI.MrzRect_getWidth(this.f3224a, this);
    }

    public int getHeight() {
        return mrzjniInterfaceJNI.MrzRect_getHeight(this.f3224a, this);
    }

    public void setX(int i) {
        mrzjniInterfaceJNI.MrzRect_setX(this.f3224a, this, i);
    }

    public void setY(int i) {
        mrzjniInterfaceJNI.MrzRect_setY(this.f3224a, this, i);
    }

    public void setWidth(int i) {
        mrzjniInterfaceJNI.MrzRect_setWidth(this.f3224a, this, i);
    }

    public void setHeight(int i) {
        mrzjniInterfaceJNI.MrzRect_setHeight(this.f3224a, this, i);
    }
}
