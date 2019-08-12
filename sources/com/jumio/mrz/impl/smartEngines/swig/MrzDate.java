package com.jumio.mrz.impl.smartEngines.swig;

public class MrzDate {

    /* renamed from: a */
    private long f3206a;
    protected boolean swigCMemOwn;

    protected MrzDate(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3206a = j;
    }

    protected static long getCPtr(MrzDate mrzDate) {
        if (mrzDate == null) {
            return 0;
        }
        return mrzDate.f3206a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3206a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzDate(this.f3206a);
            }
            this.f3206a = 0;
        }
    }

    public MrzDate(int i, int i2, int i3) {
        this(mrzjniInterfaceJNI.new_MrzDate__SWIG_0(i, i2, i3), true);
    }

    public MrzDate(int i, int i2) {
        this(mrzjniInterfaceJNI.new_MrzDate__SWIG_1(i, i2), true);
    }

    public MrzDate(int i) {
        this(mrzjniInterfaceJNI.new_MrzDate__SWIG_2(i), true);
    }

    public MrzDate() {
        this(mrzjniInterfaceJNI.new_MrzDate__SWIG_3(), true);
    }

    public int getDay() {
        return mrzjniInterfaceJNI.MrzDate_getDay(this.f3206a, this);
    }

    public int getMonth() {
        return mrzjniInterfaceJNI.MrzDate_getMonth(this.f3206a, this);
    }

    public int getYear() {
        return mrzjniInterfaceJNI.MrzDate_getYear(this.f3206a, this);
    }

    public void setDay(int i) {
        mrzjniInterfaceJNI.MrzDate_setDay(this.f3206a, this, i);
    }

    public void setMonth(int i) {
        mrzjniInterfaceJNI.MrzDate_setMonth(this.f3206a, this, i);
    }

    public void setYear(int i) {
        mrzjniInterfaceJNI.MrzDate_setYear(this.f3206a, this, i);
    }

    public boolean isDayPresent() {
        return mrzjniInterfaceJNI.MrzDate_isDayPresent(this.f3206a, this);
    }

    public boolean isMonthPresent() {
        return mrzjniInterfaceJNI.MrzDate_isMonthPresent(this.f3206a, this);
    }

    public boolean isYearPresent() {
        return mrzjniInterfaceJNI.MrzDate_isYearPresent(this.f3206a, this);
    }

    public MrzDate(MrzDate mrzDate) {
        this(mrzjniInterfaceJNI.new_MrzDate__SWIG_4(getCPtr(mrzDate), mrzDate), true);
    }
}
